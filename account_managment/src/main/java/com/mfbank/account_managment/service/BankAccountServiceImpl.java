package com.mfbank.account_managment.service;
import com.mfbank.account_managment.dtouser.Userdto;
import com.mfbank.account_managment.repository.BankAccountRepository;
import com.mfbank.account_managment.repository.FeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import com.mfbank.account_managment.dto.BankAccountDto;
import com.mfbank.account_managment.mapper.IBankAccountMapper;
import com.mfbank.account_managment.model.BankAccount;
import com.mfbank.account_managment.model.Fee;
import com.mfbank.account_managment.model.FeeType;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements  IBankAccountService{
    private final BankAccountRepository bankAccountRepository;
    private final FeeRepository feeRepository;
    private final IBankAccountMapper iBankAccountMapper;
    private final WebClient.Builder webClient;
    private SecurityContextHolder securityContextHolder;

    @Value("${principle-attribute}")
    private String principleAttribut;

    @Override
    public List<BankAccountDto> retrieveAllBankAccounts() {
        List<BankAccount> bankaccounts = bankAccountRepository.findAll();
        List<BankAccountDto> bankAccountDtos = iBankAccountMapper.toDtoList(bankaccounts);
        for(BankAccountDto bankAccountDto :  bankAccountDtos){
            Userdto userdto = webClient.build()
                    .get()
                    .uri("http://user-management/user/retrieve-user/" + bankAccountDto.getTitulaire())
                    .retrieve()
                    .bodyToMono(Userdto.class)
                    .block(Duration.ofSeconds(5));
            bankAccountDto.setUserdto(userdto);
        }
         return bankAccountDtos;
    }


    @Override
    public BankAccountDto retrieveBankAccount(String bankAccountId) {
        BankAccount bankAccount =  bankAccountRepository.findById(bankAccountId).get();
        BankAccountDto bankAccountdto = iBankAccountMapper.toDto(bankAccount);
        Userdto userdto = webClient.build()
                        .get()
                        .uri("http://user-management/user/retrieve-user/" + bankAccountdto.getTitulaire())
                        .retrieve()
                        .bodyToMono(Userdto.class)
                        .block(Duration.ofSeconds(5));
        bankAccountdto.setUserdto(userdto);

        return bankAccountdto;

    }

    @Override
    public BankAccountDto retrieveBankAccountByTitulaire(String bankAccountTitulaire) {
        BankAccount bankAccount =  bankAccountRepository.findByTitulaire(bankAccountTitulaire).get();
        BankAccountDto bankAccountdto = iBankAccountMapper.toDto(bankAccount);
        return bankAccountdto;

    }

      @Override
    public void addBankAccount(BankAccountDto bankAccountDto) {
          BankAccount bankAccount = iBankAccountMapper.toEntity(bankAccountDto);
          bankAccount.setTitulaire(getUsername());
          String typeBankAccount = bankAccountDto.getType().toString();
           Fee fee = getFeeFromBankAccount(typeBankAccount);
           if(fee != null) {
               bankAccount.setDefaultFees(fee);
           }
           Date currentDate = new Date();
           bankAccount.setCreation_date(currentDate);
           bankAccount.setActivated(false);
           bankAccount.setNegativeSoldeAllowed(true);
           bankAccount.setNegativeSoldeDepassement(false);
           bankAccount.setNegativeSoldeAmount(200.0f);

           bankAccountRepository.save(bankAccount);


    }


    public Fee getFeeFromBankAccount(String typeBankAccount) {
        if(typeBankAccount.equals("currentAccount")){
            List<Fee> fees = feeRepository.findByFeeType(FeeType.TRANSACTION_FEE_currentAccount);
            if(!fees.isEmpty()){
                Fee fee = fees.get(0);
                return fee;
            }
        } else if (typeBankAccount.equals("savingsAccount")) {
            List<Fee> fees = feeRepository.findByFeeType(FeeType.TRANSACTION_FEE_savingsAccount);
            if(!fees.isEmpty()){
                Fee fee = fees.get(0);
                return fee;
            }
        }
        return null;
    }

    @Override
    public void removeBankAccount(String bankAccountId) {
        bankAccountRepository.deleteById(bankAccountId);
    }
    @Override
    public void modifyBankAccount(BankAccountDto bankAccountDto) {
        BankAccount bankAccount = iBankAccountMapper.toEntity(bankAccountDto);
         bankAccountRepository.save(bankAccount);
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
