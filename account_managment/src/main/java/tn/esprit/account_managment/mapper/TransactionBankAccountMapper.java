package tn.esprit.account_managment.mapper;

import org.springframework.stereotype.Service;
import tn.esprit.account_managment.dto.BankAccountDto;
import tn.esprit.account_managment.dto.TransactionBankAccountDto;
import tn.esprit.account_managment.model.TransactionBankAccount;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionBankAccountMapper implements ITransactionBankAccountMapper
{
    //transactionbankaccount-> transactionbankaccountdto

    @Override
    public TransactionBankAccountDto transactionttodto(TransactionBankAccount transactionBankAccount)
    {
        TransactionBankAccountDto transactionBankAccountDto = new TransactionBankAccountDto();
        transactionBankAccountDto.setReference(transactionBankAccount.getReference());
        //transactionBankAccountDto.setDestination(transactionBankAccount.getDestination());
        //transactionBankAccountDto.setSource(transactionBankAccount.getSource());
        transactionBankAccountDto.setMontant(transactionBankAccount.getMontant());
        transactionBankAccountDto.setDate_heure(transactionBankAccount.getDate_heure());
        transactionBankAccountDto.setType(transactionBankAccount.getType());
        transactionBankAccountDto.setDescription(transactionBankAccount.getDescription());
        transactionBankAccountDto.setValidation(transactionBankAccount.getValidation());
        transactionBankAccountDto.setCancelBysender(transactionBankAccount.getCancelBysender());
        transactionBankAccountDto.setCancelBysender(transactionBankAccount.getCancelBysender());
        return transactionBankAccountDto;
    }

    //transactionbankaccountdto -> transactionbankaccount

    @Override
    public TransactionBankAccount dtototransaction(TransactionBankAccountDto transactionBankAccountDto)
    {
        TransactionBankAccount transactionBankAccount = new TransactionBankAccount();
        transactionBankAccount.setReference(transactionBankAccountDto.getReference());
       //transactionBankAccount.setDestination(transactionBankAccountDto.getDestination()); destination;
      //transactionBankAccount.setSource(transactionBankAccountDto.getSource()); source;
        transactionBankAccount.setMontant(transactionBankAccountDto.getMontant());
        transactionBankAccount.setDate_heure(transactionBankAccountDto.getDate_heure());
        transactionBankAccount.setType(transactionBankAccountDto.getType());
        transactionBankAccount.setDescription(transactionBankAccountDto.getDescription());
        transactionBankAccount.setValidation(transactionBankAccountDto.getValidation());
        transactionBankAccount.setCancelBysender(transactionBankAccountDto.getCancelBysender());
        transactionBankAccount.setCancelByreceiver(transactionBankAccountDto.getCancelByreceiver());
        return transactionBankAccount;
    }

    @Override
    public List<TransactionBankAccountDto> transactionBankAccountsTotransactionBankAccountsDtos(List<TransactionBankAccount> transactionBankAccounts)
    {
        return transactionBankAccounts.stream()
                .map(this::transactionttodto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionBankAccount> transactionBankAccountsDtosTotransactionBankAccounts(List<TransactionBankAccountDto> transactionBankAccountDtos)
    {
        return transactionBankAccountDtos.stream()
                .map(this::dtototransaction)
                .collect(Collectors.toList());
    }

}
