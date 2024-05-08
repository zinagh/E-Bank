package com.mfbank.service;
import com.mfbank.configuration.KeycloakSecurity;
import com.mfbank.dto.Userdto;
import com.mfbank.mapper.Imapper;
import com.mfbank.mapper.Usermapper;
import com.mfbank.model.User;
import com.mfbank.otherDtos.*;
import com.mfbank.repository.UserRepository;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImp  implements IUserService{
    private final WebClient.Builder webClient;
    @Value("${principle-attribute}")
    private String principleAttribut;
    private SecurityContextHolder securityContextHolder;
    @Autowired
    Imapper imapper;
    @Autowired
    UserRepository userRepository;

    @Autowired
    Usermapper usermapper;
    @Autowired
    KeycloakSecurity keycloakSecurity;
    @Value("${realm}")
    private String realm;
    @Override
    public List<Userdto> retrieveAllUsers() {
     List<User>  users =userRepository.findAll();
     List<Userdto> userdtos = usermapper.usersTouserdtos(users);
     return userdtos;
    }
    @Override
    public Userdto retrieveUser(String userName) {
        User user=  userRepository.findById(userName).get();
        Userdto userdto = usermapper.userTouserdto(user);
        return userdto;
    }
    @Override
    public void addUser(Userdto userdto ) {
        UserRepresentation userRepresentation = imapper.mapuserRep(userdto);
        System.out.println(userRepresentation);
        Keycloak keycloak =keycloakSecurity.getKeycloakInstance();
        Response response = keycloak.realm(realm).users().create(userRepresentation);
        if(response.getStatus() == 201) {
            String userId = keycloak.realm(realm).users()
                    .search(userRepresentation
                    .getUsername()).get(0).getId();
            imapper.assignerole(userdto.getRole().toString() ,userId);
            User user= usermapper.userdtoTouser(userdto) ;
            userRepository.save(user);
        }
    }
    @Override
    public void removeUser(String userName) {
        Keycloak keycloak = keycloakSecurity.getKeycloakInstance();
        List<UserRepresentation> userRepresentations = keycloak.realm(realm).users().search(userName);
        String id = userRepresentations.get(0).getId();
        keycloak.realm(realm).users().delete(id);
        userRepository.deleteById(userName);
        BankAccountDto bankAccountDto = webClient.build()
                .get()
                .uri("http://account-management/account/getbankaccountbyTitulaire/" + userName)
                .retrieve()
                .bodyToMono(BankAccountDto.class)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    System.err.println("Failed to retrieve bank account info: " + ex.getMessage());
                    return Mono.empty();
                })
                .blockOptional(Duration.ofSeconds(5))
                .orElse(null);
        if(bankAccountDto != null) {
            webClient.build()
                    .delete()
                    .uri("http://account-management/account/deletebankaccountby/" + bankAccountDto.getAccountNumber())
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block(Duration.ofSeconds(5));
        }

    }
    @Override
    public User modifyUser(Userdto userdto) {
        Keycloak keycloak = keycloakSecurity.getKeycloakInstance();
        List<UserRepresentation> userRepresentations = keycloak
                .realm(realm).users()
                .search(userdto.getUserName());

        if (!userRepresentations.isEmpty()) {
            String id = userRepresentations.get(0).getId();
            UserRepresentation userToUpdate = imapper.mapuserRepToUpdate(userdto);
            keycloak.realm(realm).users().get(id).update(userToUpdate);
            User user = usermapper.userdtoTouser(userdto);
            userRepository.save(user);
            return user;
        } else {
            // Handle the case where no user with the given username is found
            throw new NoSuchElementException("No user found with the given username");
        }
    }
    @Override
    public String updatepassword(String username ,String newpass ,String verifpass){
        if (newpass.equals(verifpass)) {
            Keycloak keycloak = keycloakSecurity.getKeycloakInstance();
            List<UserRepresentation> userRepresentations = keycloak.realm(realm).users().search(username);
            UserRepresentation userRepresentation = userRepresentations.get(0);
            List<CredentialRepresentation> creds = new ArrayList<>();
            CredentialRepresentation cred = new CredentialRepresentation();
            cred.setTemporary(false);
            cred.setValue(newpass);
            creds.add(cred);
            userRepresentation.setCredentials(creds);
            userRepresentation.setRequiredActions(Collections.emptyList());
            keycloak.realm(realm).users().get(userRepresentation.getId()).update(userRepresentation);
return "password is updated";
        } return "comparaison failed";
    }
    @Override
    public  Double getAdditionalDebtForecast(RepaymentPlanDto plan) {
        Double projectedTotalDebt = plan.getStartingBalance() - plan.getRepaymentOfCcapital();
        return (projectedTotalDebt - plan.getStartingBalance()) / plan.getStartingBalance();
    }
    @Override
    public  Integer getDaysToPayoffByPrincipal(RepaymentPlanDto plan) {
        Double remainingBalance = plan.getStartingBalance();
        int days = 0;
        LocalDate currentDate = LocalDate.now();

        int daysInCurrentMonth = currentDate.lengthOfMonth() - currentDate.getDayOfMonth() + 1;

        while (remainingBalance >= 0) {
            remainingBalance -= plan.getRepaymentOfCcapital();

            int daysInMonth = daysInCurrentMonth;
            if (daysInCurrentMonth == 0) {
                Period periodToNextMonth = Period.between(currentDate, currentDate.withDayOfMonth(1).plusMonths(1));
                daysInMonth = periodToNextMonth.getDays();
            }

            days += daysInMonth;
            currentDate = currentDate.plusDays(daysInMonth);
            daysInCurrentMonth = 0;
        }

        return days;
    }
    @Override
    public  Double getAverageDailyInterestAccrual(CreditDto credit) {

        if (credit == null) {
            throw new IllegalArgumentException("CreditDto cannot be null");
        }


        RepaymentPlanDto repaymentPlan = credit.getRepaymentPlan();
        if (repaymentPlan == null) {
            throw new IllegalStateException("CreditDto must have a RepaymentPlanDto");
        }

        Double dailyInterestRate = credit.getCreditRate() / 365.0;


        Double startingBalance = repaymentPlan.getStartingBalance();
        return dailyInterestRate * startingBalance;
    }

    @Override
    public Double getProjectedOutstandingBalance(RepaymentPlanDto plan, int periods) {

            Double remainingBalance = plan.getStartingBalance();


            for (int i = 0; i < periods; i++) {
                System.out.println("Iteration " + i + ":");
                System.out.println("  - Interest: " + plan.getInterest());
                System.out.println("  - Repayment: " + plan.getRepaymentOfCcapital());
                remainingBalance += plan.getInterest();
                remainingBalance -= plan.getRepaymentOfCcapital();
                System.out.println("  - Remaining Balance: " + remainingBalance);
            }

            return remainingBalance;
        }
    @Override
    public  Double getCoverageRatioByPlannedRepayments(RepaymentPlanDto plan) {
        Double totalRepayment = plan.getStartingBalanceimbursementAount() ;
        Double totalInterest = plan.getInterest() ;
        return totalRepayment / totalInterest ;
    }






    @Override
    public Double getAccountActivityRatio(BankAccountDto account) {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Get the date one month before
        calendar.add(Calendar.MONTH, -1);
        Date oneMonthBeforeDate = calendar.getTime();

        List<InternationalTransferDto> transfers = account.getInternationalTransfers();
        int transferCount = 0;
        for (InternationalTransferDto transfer : transfers) {
            if (transfer.getDate().equals(currentDate) || transfer.getDate().equals(oneMonthBeforeDate) ||
                    (transfer.getDate().compareTo(oneMonthBeforeDate) > 0 && transfer.getDate().compareTo(currentDate) <= 0)) {
                transferCount++;
                System.out.println("Transfer date within period: " + transfer.getDate()); // Add print statement
            }
        }

        // Calculate days in the period (ensure non-negative value)
        double days = Math.max((currentDate.getTime() - oneMonthBeforeDate.getTime()) / (1000.0 * 60 * 60 * 24), 1.0);

        return (double) transferCount / days;
    }


    @Override
    public  Map<Double,List<Double>>  getFeeIncomePerAccount(String username) {
        List<Double> monthlyTranfersFees = new ArrayList<>();
        double totalAmountofAllMonths = 0;
        for (int monthF = 1; monthF <= 12; monthF++) {
            List<InternationalTransferDto> internationalTransferDtoList = webClient.build()
                    .get().uri("http://account-management/account/findInternationalTransferByUsernameAndDate?username=" + username + "&monthF=" + monthF)
                    .retrieve()
                    .bodyToFlux(InternationalTransferDto.class)
                    .collectList()
                    .block(Duration.ofSeconds(5));
            int totalTransfers = internationalTransferDtoList.size();
            System.out.println("Month is : " + monthF + "Size is :" + totalTransfers);
            if (totalTransfers == 0) {
               monthlyTranfersFees.add(0.0);
            } else {
                double totalAmountOneMonth = 0;
                for (InternationalTransferDto inter : internationalTransferDtoList){
                    double feeDtoAmount = inter.getInternationnalFees().getAmountPercent() * inter.getAmount();
                    totalAmountOneMonth = totalAmountOneMonth + feeDtoAmount;
                }
                totalAmountofAllMonths = totalAmountofAllMonths + totalAmountOneMonth;
                monthlyTranfersFees.add(totalAmountOneMonth);
            }

        }
        List<Double> feePercentage = new ArrayList<>();
        for (Double db :monthlyTranfersFees ){
            double monthfees = (db * 100) / totalAmountofAllMonths;
            feePercentage.add(monthfees);
        }
        Map<Double, List<Double>> maptoReturn = new HashMap<>();
        maptoReturn.put(totalAmountofAllMonths, feePercentage);
        return  maptoReturn;
    }

    @Override
    public  Double getAccountUtilizationRatio() {
        BankAccountDto account = webClient.build()
                .get()
                .uri("http://account-management/account/getbankaccountbyTitulaire/" + getUsername())
                .retrieve()
                .bodyToMono(BankAccountDto.class)
                .block(Duration.ofSeconds(5));

        Double result =0.0;
        if (!account.getNegativeSoldeAllowed()) {
            return 0.0;
        }

        Double availableBalance = account.getAccount_balance() - account.getNegativeSoldeAmount();
        result = Math.round(account.getAccount_balance() / availableBalance * 100.0) / 100.0;
        return (result * 100);
    }
    public List<Double> getMonthlyOutgoingTransfers(String username) {
        List<Double> monthlyTranfers = new ArrayList<>();

        for (int monthF = 1; monthF <= 12; monthF++) {
            List<InternationalTransferDto> internationalTransferDtoList = webClient.build()
                    .get().uri("http://account-management/account/findInternationalTransferByUsernameAndDate?username=" + username + "&monthF=" + monthF)
                    .retrieve()
                    .bodyToFlux(InternationalTransferDto.class)
                    .collectList()
                    .block(Duration.ofSeconds(5));
            int totalTransfers = internationalTransferDtoList.size();
            System.out.println("Month is : " + monthF + "Size is :" + totalTransfers);
            if (totalTransfers == 0) {
                monthlyTranfers.add(0.0);
            } else {
                int outgoingTransfers = 0;
                for (InternationalTransferDto transfer : internationalTransferDtoList) {
                    if (transfer.isSendOrReceive() == false) {
                        outgoingTransfers++;
                    }
                }

                System.out.print("outgoingTransfers " + outgoingTransfers);
                System.out.print("totalTransfers " + totalTransfers);

                Double result = Math.round((double)  outgoingTransfers / totalTransfers * 100.0) / 100.0;
                System.out.print("result " + result);
                monthlyTranfers.add(result * 100);
            }
        }

        return monthlyTranfers;

    }


    public String getUsername() {
        Authentication authentication = securityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication instanceof JwtAuthenticationToken) {
            Jwt jwt = ((JwtAuthenticationToken) authentication).getToken();
            return (String) jwt.getClaim(principleAttribut);
        }
        throw new IllegalStateException("Could not retrieve token from SecurityContext");
    }

}
