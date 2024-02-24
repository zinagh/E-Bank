package tn.esprit.account_managment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.account_managment.dto.BankAccountDto;
import tn.esprit.account_managment.dto.UserAsStudentonBADto;
import tn.esprit.account_managment.service.IBankAccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class bankAccountController
{
    private final IBankAccountService iBankAccountService;

    @PostMapping("/addbankaccount")
    public void addbankaccount(@RequestBody BankAccountDto bankAccountDto) {
        iBankAccountService.addBankAccount(bankAccountDto);
    }

    @GetMapping("/getbankaccountby/{bankAccountId}")
    public BankAccountDto getbankaccountby(@PathVariable String bankAccountId) {
        return iBankAccountService.retrieveBankAccount(bankAccountId);

    }

    @GetMapping("/getallbankaccounts")
    public List<BankAccountDto> getallbankaccounts() {
        return iBankAccountService.retrieveAllBankAccounts();
    }
    @PutMapping("/modifybankaccount")
    public void modifybankaccount(@RequestBody BankAccountDto bankAccountDto) {
        iBankAccountService.modifyBankAccount(bankAccountDto);
    }

    @DeleteMapping("/deletebankaccountby/{bankAccountId}")
    public void deletebankaccountby(@PathVariable String bankAccountId) {
        iBankAccountService.removeBankAccount(bankAccountId);

    }

}
