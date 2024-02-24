package tn.esprit.account_managment.service;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.account_managment.dto.TransactionBankAccountDto;
import tn.esprit.account_managment.mapper.IBankAccountMapper;
import tn.esprit.account_managment.mapper.ITransactionBankAccountMapper;
import tn.esprit.account_managment.model.BankAccount;
import tn.esprit.account_managment.model.TransactionBankAccount;
import tn.esprit.account_managment.repository.BankAccountRepository;
import tn.esprit.account_managment.repository.TransactionBankAccountRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionBankAccountServiceImpl implements ITransactionBankAccountService
{
    @Autowired
    private final TransactionBankAccountRepository transactionBankAccountRepository;
    @Autowired
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    private final ITransactionBankAccountMapper iTransactionBankAccountMapper;

    @Override
    public List<TransactionBankAccountDto> retrieveAllTransactionBankAccounts() {
        List<TransactionBankAccount> transactionBankAccounts = transactionBankAccountRepository.findAll();
        List<TransactionBankAccountDto> transactionBankAccountDtos = iTransactionBankAccountMapper.transactionBankAccountsTotransactionBankAccountsDtos(transactionBankAccounts);
        return transactionBankAccountDtos;
    }

    @Override
    public TransactionBankAccountDto retrieveTransactionBankAccount(String transactionBankAccountId) {
        TransactionBankAccount transactionBankAccount = transactionBankAccountRepository.findById(transactionBankAccountId).get();
        TransactionBankAccountDto transactionBankAccountDto = iTransactionBankAccountMapper.transactionttodto(transactionBankAccount);
        return transactionBankAccountDto;
    }

    @Override
    public void addTransactionBankAccount(TransactionBankAccountDto transactionBankAccountDto,String destinationId, String sourceId) {

        Optional<BankAccount> optionaldestinationAccount = bankAccountRepository.findById(destinationId);
        Optional<BankAccount> optionalsourceAccount = bankAccountRepository.findById(sourceId);
        if(optionaldestinationAccount.isPresent() && optionalsourceAccount.isPresent()) {
            BankAccount destinationAccount = optionaldestinationAccount.get();
            BankAccount sourceAccount = optionalsourceAccount.get();
            Date transDate = new Date();
            TransactionBankAccount transactionBankAccount = iTransactionBankAccountMapper.dtototransaction(transactionBankAccountDto);
            transactionBankAccount.setValidation(false);
            transactionBankAccount.setCancelBysender(false);
            transactionBankAccount.setCancelByreceiver(false);
            transactionBankAccount.setDate_heure(transDate);
            transactionBankAccount.setDestination(destinationAccount);
            transactionBankAccount.setSource(sourceAccount);
            transactionBankAccountRepository.save(transactionBankAccount);
        }
    }

    @Override
    public void removeTransactionBankAccount(String transactionBankAccountId) {
        transactionBankAccountRepository.deleteById(transactionBankAccountId);
    }

    @Override
    public void modifyTransactionBankAccount(TransactionBankAccountDto transactionBankAccountDto) {
        TransactionBankAccount transactionBankAccount = iTransactionBankAccountMapper.dtototransaction(transactionBankAccountDto);
        transactionBankAccountRepository.save(transactionBankAccount);
    }
}
