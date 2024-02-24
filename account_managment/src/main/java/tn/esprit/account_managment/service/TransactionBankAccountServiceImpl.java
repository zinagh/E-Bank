package tn.esprit.account_managment.service;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.account_managment.dto.TransactionBankAccountDto;
import tn.esprit.account_managment.mapper.IBankAccountMapper;
import tn.esprit.account_managment.mapper.ITransactionBankAccountMapper;
import tn.esprit.account_managment.model.TransactionBankAccount;
import tn.esprit.account_managment.repository.TransactionBankAccountRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor


public class TransactionBankAccountServiceImpl implements ITransactionBankAccountService
{
    TransactionBankAccountRepository transactionBankAccountRepository;
    ITransactionBankAccountMapper iTransactionBankAccountMapper;

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
    public TransactionBankAccount addTransactionBankAccount(TransactionBankAccount t) {
        return transactionBankAccountRepository.save(t);
    }

    @Override
    public void removeTransactionBankAccount(String transactionBankAccountId) {
        transactionBankAccountRepository.deleteById(transactionBankAccountId);
    }

    @Override
    public TransactionBankAccount modifyTransactionBankAccount(TransactionBankAccount transactionBankAccount) {
        return transactionBankAccountRepository.save(transactionBankAccount);
    }
}
