package com.mfbank.account_managment.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.mfbank.account_managment.dto.BankAccountDto;
import com.mfbank.account_managment.dto.FeeDto;
import com.mfbank.account_managment.dto.InternationalTransferDto;
import com.mfbank.account_managment.service.IBankAccountService;
import com.mfbank.account_managment.service.IFeeService;
import com.mfbank.account_managment.service.IInternationalTransferService;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class bankAccountController
{
    private final IBankAccountService iBankAccountService;
    private final IFeeService iFeeService;
    private final IInternationalTransferService iInternationalTransferService;


    @PostMapping("/addbankaccount")
    public void addbankaccount(@RequestBody BankAccountDto bankAccountDto) {
        iBankAccountService.addBankAccount(bankAccountDto);
    }
    @PostMapping("/addfee")
    public void addFee(@RequestBody FeeDto feeDto) {
        iFeeService.addFee(feeDto);
    }

    @PostMapping("/addinternationaltransfer")
    public void addInternationalTransfer(@RequestBody InternationalTransferDto internationalTransferDto) {
        iInternationalTransferService.addInternationalTransfer(internationalTransferDto);
    }

    @GetMapping("/getbankaccountby/{bankAccountId}")
    public BankAccountDto getbankaccountby(@PathVariable String bankAccountId) {
        return iBankAccountService.retrieveBankAccount(bankAccountId);
    }

    @GetMapping("/getbankaccountbyTitulaire/{bankAccountTitulaire}")
    public BankAccountDto getbankaccountbyTitulaire(@PathVariable String bankAccountTitulaire) {
        return iBankAccountService.retrieveBankAccountByTitulaire(bankAccountTitulaire);
    }

    @GetMapping("/getfeeby/{feeId}")
    public FeeDto retrieveFee(@PathVariable Long feeId) {
        return iFeeService.retrieveFee(feeId);

    }

    @GetMapping("/getinternationaltransferby/{internationalTransferId}")
    public InternationalTransferDto retrieveInternationalTransfer(@PathVariable Long internationalTransferId) {
        return iInternationalTransferService.retrieveInternationalTransfer(internationalTransferId);

    }
    @GetMapping("/getallbankaccounts")
    public List<BankAccountDto> getallbankaccounts() {
        return iBankAccountService.retrieveAllBankAccounts();
    }

    @GetMapping("/getallfees")
    public List<FeeDto> retrieveAllFees() {
        return iFeeService.retrieveAllFees();
    }

    @GetMapping("/getallinternationaltransfers")
    public List<InternationalTransferDto> retrieveAllInternationalTransfers() {
        return iInternationalTransferService.retrieveAllInternationalTransfers();
    }


    @PutMapping("/modifybankaccount")
    public void modifybankaccount(@RequestBody BankAccountDto bankAccountDto) {
        iBankAccountService.modifyBankAccount(bankAccountDto);
    }

    @PutMapping("/modifyfee")
    public void modifyFee(@RequestBody FeeDto feeDto) {
        iFeeService.modifyFee(feeDto);
    }

    @PutMapping("/modifyinternationaltransfer")
    public void modifyInternationalTransfer(@RequestBody InternationalTransferDto internationalTransferDto) {
        iInternationalTransferService.modifyInternationalTransfer(internationalTransferDto);
    }
    @PutMapping("/modify/{employeeApprovalUsername}/{id}")
    public void approveinternationaltransfer(@PathVariable String employeeApprovalUsername, Long id) {
        iInternationalTransferService.approveinternationaltransfer(employeeApprovalUsername , id);
    }
    @DeleteMapping("/deletebankaccountby/{bankAccountId}")
    public void deletebankaccountby(@PathVariable String bankAccountId) {
        iBankAccountService.removeBankAccount(bankAccountId);

    }

    @DeleteMapping("/deletebfeeby/{feeId}")
    public void removeFee(@PathVariable Long feeId) {
        iFeeService.removeFee(feeId);

    }

    @DeleteMapping("/deleteinternationaltransferby/{internationalTransferId}")
    public void removeInternationalTransfer(@PathVariable Long internationalTransferId) {
        iInternationalTransferService.removeInternationalTransfer(internationalTransferId);

    }

}