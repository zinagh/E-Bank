package com.mfbank.account_managment.service;
import com.mfbank.account_managment.dto.InternationalTransferDto;
import com.mfbank.account_managment.model.BankAccount;

import java.util.List;

public interface IInternationalTransferService
{
    List<InternationalTransferDto> retrieveAllInternationalTransfers();
    InternationalTransferDto retrieveInternationalTransfer(Long internationalTransferId);
    void addInternationalTransfer(InternationalTransferDto internationalTransferDto);
    void removeInternationalTransfer(Long internationalTransferId);
    void modifyInternationalTransfer(InternationalTransferDto internationalTransferDto);
    void approveinternationaltransfer(String employeeApprovalUsername, Long id);
    List<Float> getstatisticsForChart (String bankAccountId, int month);
    List<InternationalTransferDto> retrieveAllInternationalTransfersByTitulaireAccount(String username);
}
