package tn.esprit.account_managment.mapper;

import tn.esprit.account_managment.dto.BankAccountDto;
import tn.esprit.account_managment.model.BankAccount;

import java.util.List;

public interface IBankAccountMapper {
    BankAccountDto bankAccountTobankAccountDto(BankAccount bankAccount);
    BankAccount bankAccountDtoTobankAccount(BankAccountDto bankAccount);

    List<BankAccountDto> bankAccountsTobankAccountDtos(List<BankAccount> bankAccounts);

    List<BankAccount> bankAccountsDtosTobankAccounts(List<BankAccountDto> bankAccountDtos);

}
