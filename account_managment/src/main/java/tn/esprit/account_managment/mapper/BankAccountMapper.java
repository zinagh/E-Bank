package tn.esprit.account_managment.mapper;

import org.springframework.stereotype.Service;
import tn.esprit.account_managment.dto.BankAccountDto;
import tn.esprit.account_managment.dto.TypeBankAccount;
import tn.esprit.account_managment.model.BankAccount;
import tn.esprit.account_managment.model.UserAsStudentonBA;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountMapper implements IBankAccountMapper {
    //BankAccount -> BankAccountDto
    @Override
    public BankAccountDto bankAccountTobankAccountDto(BankAccount bankAccount){
        BankAccountDto bankAccountDto = new BankAccountDto();
        bankAccountDto.setTitulaire(bankAccount.getTitulaire());
        bankAccountDto.setAccount_balance(bankAccount.getAccount_balance());
        bankAccountDto.setAccount_type(bankAccount.getAccount_type());
        bankAccountDto.setCreation_date(bankAccount.getCreation_date());
        bankAccountDto.setActivated(bankAccount.getActivated());
        bankAccountDto.setDeactivation_date(bankAccount.getDeactivation_date());
        bankAccountDto.setAccount_limit(bankAccount.getAccount_limit());
        bankAccountDto.setAmount_fund(bankAccount.getAmount_fund());
        bankAccountDto.setCode(bankAccount.getCode());
        bankAccountDto.setType(bankAccount.getType());
        bankAccountDto.setPrime_rates(bankAccount.getPrime_rates());
        bankAccountDto.setNegativeSoldeAllowed(bankAccount.getNegativeSoldeAllowed());
        bankAccountDto.setNegativeSoldeAmount(bankAccount.getNegativeSoldeAmount());
        bankAccountDto.setNegativeSoldeDepassement(bankAccount.getNegativeSoldeDepassement());
        bankAccountDto.setNegativeSoldeDepassementDay(bankAccount.getNegativeSoldeDepassementDay());
        return bankAccountDto;
    }


    //BankAccountDto -> BankAccount
    @Override
     public BankAccount bankAccountDtoTobankAccount(BankAccountDto bankAccountDto){
        BankAccount bankAccount= new  BankAccount();
        bankAccount.setTitulaire(bankAccountDto.getTitulaire());
        bankAccount.setAccount_balance(bankAccountDto.getAccount_balance());
        bankAccount.setAccount_type(bankAccountDto.getAccount_type());
        bankAccount.setCreation_date(bankAccountDto.getCreation_date());
        bankAccount.setActivated(bankAccountDto.getActivated());
        bankAccount.setDeactivation_date(bankAccountDto.getDeactivation_date());
        bankAccount.setAccount_limit(bankAccountDto.getAccount_limit());
        bankAccount.setAmount_fund(bankAccountDto.getAmount_fund());
        bankAccount.setCode(bankAccountDto.getCode());
        bankAccount.setType(bankAccountDto.getType());
        bankAccount.setPrime_rates(bankAccountDto.getPrime_rates());
        bankAccount.setNegativeSoldeAllowed(bankAccountDto.getNegativeSoldeAllowed());
        bankAccount.setNegativeSoldeAmount(bankAccountDto.getNegativeSoldeAmount());
        bankAccount.setNegativeSoldeDepassement(bankAccountDto.getNegativeSoldeDepassement());
        bankAccount.setNegativeSoldeDepassementDay(bankAccountDto.getNegativeSoldeDepassementDay());
        return bankAccount;
    }
   @Override
    public List<BankAccountDto> bankAccountsTobankAccountDtos(List<BankAccount> bankAccounts){
        return bankAccounts.stream()
                .map(this::bankAccountTobankAccountDto)
                .collect(Collectors.toList());

    }
   @Override
   public List<BankAccount> bankAccountsDtosTobankAccounts(List<BankAccountDto> bankAccountDtos){
        return  bankAccountDtos.stream()
                .map(this::bankAccountDtoTobankAccount)
                .collect(Collectors.toList());
   }

}
