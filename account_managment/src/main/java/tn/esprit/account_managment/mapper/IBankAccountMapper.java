package tn.esprit.account_managment.mapper;
import tn.esprit.account_managment.dto.BankAccountDto;
import tn.esprit.account_managment.model.BankAccount;
import java.util.List;

public interface IBankAccountMapper
{
    public BankAccountDto toDto(BankAccount bankAccount);
    public BankAccount toEntity(BankAccountDto bankAccountDto);
    public List<BankAccountDto> toDtoList(List<BankAccount> bankAccounts);
    public List<BankAccount> toEntityList(List<BankAccountDto> bankAccountDtos);
}
