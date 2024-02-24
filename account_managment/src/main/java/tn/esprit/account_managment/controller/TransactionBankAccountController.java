package tn.esprit.account_managment.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.account_managment.dto.BankAccountDto;
import tn.esprit.account_managment.dto.TransactionBankAccountDto;
import tn.esprit.account_managment.dto.UserAsStudentonBADto;
import tn.esprit.account_managment.service.ITransactionBankAccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionBankAccountController
{
    private final ITransactionBankAccountService iTransactionBankAccountService;
    @PostMapping("/addtransactionbankaccount")
    public void addtransactionbankaccount(@RequestBody TransactionBankAccountDto transactionBankAccountDto,
                                          @RequestParam String destinationId,
                                          @RequestParam String sourceId) {
        iTransactionBankAccountService.addTransactionBankAccount(transactionBankAccountDto, destinationId, sourceId);
    }

    @GetMapping("/gettransactionbankaccountby/{transactionBankAccountId}")
    public TransactionBankAccountDto gettransactionbankaccountby(@PathVariable String transactionBankAccountId) {
        return iTransactionBankAccountService.retrieveTransactionBankAccount(transactionBankAccountId);

    }

    @GetMapping("/getalltransactionsbankaccount")
    public List<TransactionBankAccountDto> getalltransactionsbankaccount() {
        return iTransactionBankAccountService.retrieveAllTransactionBankAccounts();
    }

    @PutMapping("/modifytransactionbankaccount")
    public void modifytransactionbankaccount(@RequestBody TransactionBankAccountDto transactionBankAccountDto) {
        iTransactionBankAccountService.modifyTransactionBankAccount(transactionBankAccountDto);
    }
    @DeleteMapping("/deletetransactionbankaccount/{transactionBankAccountId}")
    public void deletetransactionbankaccount(@PathVariable String transactionBankAccountId) {
        iTransactionBankAccountService.removeTransactionBankAccount(transactionBankAccountId);

    }
}
