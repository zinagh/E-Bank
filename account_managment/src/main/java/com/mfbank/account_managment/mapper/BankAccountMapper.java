package com.mfbank.account_managment.mapper;
import com.mfbank.account_managment.model.BankAccount;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mfbank.account_managment.dto.BankAccountDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountMapper implements IBankAccountMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BankAccountDto toDto(BankAccount bankAccount) {
        return modelMapper.map(bankAccount, BankAccountDto.class);
    }
    @Override
    public BankAccount toEntity(BankAccountDto bankAccountDto) {
        return modelMapper.map(bankAccountDto, BankAccount.class);
    }
    @Override
    public List<BankAccountDto> toDtoList(List<BankAccount> bankAccounts) {
        return bankAccounts.stream()
                .map(this::toDto) // Reuse the existing toDto method for each element
                .collect(Collectors.toList());
    }
    @Override
    public List<BankAccount> toEntityList(List<BankAccountDto> bankAccountDtos) {
        return bankAccountDtos.stream()
                .map(this::toEntity) // Reuse the existing toEntity method for each element
                .collect(Collectors.toList());
    }




}
