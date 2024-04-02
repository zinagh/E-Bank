package com.mfbank.service;
import com.mfbank.configuration.KeycloakSecurity;
import com.mfbank.dto.Userdto;
import com.mfbank.mapper.Imapper;
import com.mfbank.mapper.Usermapper;
import com.mfbank.model.User;
import com.mfbank.otherDtos.BankAccountDto;
import com.mfbank.otherDtos.CreditDto;
import com.mfbank.otherDtos.InternationalTransferDto;
import com.mfbank.otherDtos.RepaymentPlanDto;
import com.mfbank.repository.UserRepository;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImp  implements IUserService{
    private final WebClient.Builder webClient;

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
    public List<Userdto> retrieveAllUsers() {
     List<User>  users =userRepository.findAll();
     List<Userdto> userdtos = usermapper.usersTouserdtos(users);
     return userdtos;
    }
    public Userdto retrieveUser(String userName) {
        User user=  userRepository.findById(userName).get();
        Userdto userdto = usermapper.userTouserdto(user);
        return userdto;
    }
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
                .block(Duration.ofSeconds(5));
        webClient.build()
                .delete()
                .uri("http://account-management/account/deletebankaccountby/" + bankAccountDto.getAccountNumber())
                .retrieve()
                .bodyToMono(Void.class)
                .block(Duration.ofSeconds(5));

    }
    public User modifyUser(Userdto userdto) {
        Keycloak keycloak=keycloakSecurity.getKeycloakInstance();
        List<UserRepresentation>  userRepresentations=keycloak
                .realm(realm).users()
                .search(userdto.getUserName());
        String id=userRepresentations.get(0).getId();
        UserRepresentation userToUpdate= imapper.mapuserRepToUpdate(userdto);
        keycloak.realm(realm).users().get(id).update(userToUpdate);
        User user = usermapper.userdtoTouser(userdto);
        userRepository.save(user);
        return user;
    }
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
    public  Double getAdditionalDebtForecast(RepaymentPlanDto plan) {
        Double projectedTotalDebt = plan.getStartingBalance() - plan.getRepaymentOfCcapital();
        return (projectedTotalDebt - plan.getStartingBalance()) / plan.getStartingBalance();
    }
    public  Integer getDaysToPayoffByPrincipal(RepaymentPlanDto plan) {
        Double remainingBalance = plan.getStartingBalance();
        int days = 0;
        LocalDate currentDate = LocalDate.now(); // Get current date

        // Calculate remaining days in the current month (if starting date isn't the 1st)
        int daysInCurrentMonth = currentDate.lengthOfMonth() - currentDate.getDayOfMonth() + 1;

        while (remainingBalance >= 0) {
            remainingBalance -= plan.getRepaymentOfCcapital();

            // Use daysInCurrentMonth for the first iteration
            int daysInMonth = daysInCurrentMonth;
            if (daysInCurrentMonth == 0) { // After first iteration, use periodToNextMonth
                Period periodToNextMonth = Period.between(currentDate, currentDate.withDayOfMonth(1).plusMonths(1));
                daysInMonth = periodToNextMonth.getDays();
            }

            days += daysInMonth;
            currentDate = currentDate.plusDays(daysInMonth);
            daysInCurrentMonth = 0; // Reset for subsequent iterations
        }

        return days;
    }

    public  Double getAverageDailyInterestAccrual(CreditDto credit) {
        // Handle null CreditDto gracefully (optional)
        if (credit == null) {
            throw new IllegalArgumentException("CreditDto cannot be null");
        }

        // Ensure RepaymentPlanDto exists (optional)
        RepaymentPlanDto repaymentPlan = credit.getRepaymentPlan();
        if (repaymentPlan == null) {
            throw new IllegalStateException("CreditDto must have a RepaymentPlanDto");
        }

        // Calculate daily interest rate (assuming 365 days, adjust if needed)
        Double dailyInterestRate = credit.getCreditRate() / 365.0; // Use BigDecimal for higher precision (consider)

        // Calculate and return average daily interest accrual
        Double startingBalance = repaymentPlan.getStartingBalance();
        return dailyInterestRate * startingBalance;
    }


    public Double getProjectedOutstandingBalance(RepaymentPlanDto plan, int periods) {

            Double remainingBalance = plan.getStartingBalance();

            // Apply interest first in each period, then subtract repayment
            for (int i = 0; i < periods; i++) {
                System.out.println("Iteration " + i + ":"); // Added for debugging
                System.out.println("  - Interest: " + plan.getInterest()); // Added for debugging
                System.out.println("  - Repayment: " + plan.getRepaymentOfCcapital()); // Added for debugging
                remainingBalance += plan.getInterest(); // Apply interest first
                remainingBalance -= plan.getRepaymentOfCcapital();
                System.out.println("  - Remaining Balance: " + remainingBalance); // Added for debugging
            }

            return remainingBalance;
        }

    public  Double getCoverageRatioByPlannedRepayments(RepaymentPlanDto plan) {
        Double totalRepayment = plan.getStartingBalanceimbursementAount() ;
        Double totalInterest = plan.getInterest() ;
        return totalRepayment / totalInterest ;
    }

    public  Double getAccountActivityRatio(BankAccountDto account, Date startDate, Date endDate) {
        List<InternationalTransferDto> transfers = account.getInternationalTransfers();
        int transferCount = 0;
        for (InternationalTransferDto transfer : transfers) {
            if (transfer.getDate().equals(startDate) || transfer.getDate().equals(endDate) ||
                    transfer.getDate().compareTo(startDate) > 0 && transfer.getDate().compareTo(endDate) < 0) {
                transferCount++;
            System.out.println("Transfer date within period: " + transfer.getDate()); // Add print statement
            }
        }


        // Calculate days in the period (ensure non-negative value)
        double days = Math.max((endDate.getTime() - startDate.getTime()) / (1000.0 * 60 * 60 * 24), 1.0);

        return (double) transferCount / days;
    }



    public  Double getFeeIncomePerAccount(BankAccountDto account) {
        Double totalFees = 0.0;
        if (account.getDefaultFees() != null) {
            totalFees += account.getDefaultFees().getAmountPercent(); // Assuming amountPercent represents income
        }
        for (InternationalTransferDto transfer : account.getInternationalTransfers()) {
            if (transfer.getInternationnalFees() != null) {
                totalFees += transfer.getInternationnalFees().getAmountPercent(); // Assuming amountPercent represents income
            }
        }
        return totalFees;
    }


    public  Double getAccountUtilizationRatio(BankAccountDto account) {
        if (!account.getNegativeSoldeAllowed()) {
            return 0.0; // No negative balance allowed, utilization is 0
        }

        Double availableBalance = account.getAccount_balance() + account.getNegativeSoldeAmount();
        return account.getAccount_balance() / availableBalance;
    }
    public  Double getPercentageOutgoingTransfers(BankAccountDto account) {
        int totalTransfers = account.getInternationalTransfers().size();
        int outgoingTransfers = 0;

        for (InternationalTransferDto transfer : account.getInternationalTransfers()) {
            if (!transfer.isSendOrReceive()) { // Assuming sendOrReceive = false indicates outgoing
                outgoingTransfers++;
            }
        }

        // Handle potential division by zero
        if (totalTransfers == 0) {
            return 0.0; // Or another appropriate value for no transfers
        }

        return (double) outgoingTransfers / totalTransfers * 100;
    }
    public  Double getAverageInternationalTransferFee(List<InternationalTransferDto> transfers) {
        Double totalFees = 0.0;
        for (InternationalTransferDto transfer : transfers) {
            totalFees += transfer.getFees();
        }

        // Handle potential division by zero
        if (transfers.isEmpty()) {
            return 0.0; // Or another appropriate value for no transfers
        }

        return totalFees / transfers.size();
    }



}
