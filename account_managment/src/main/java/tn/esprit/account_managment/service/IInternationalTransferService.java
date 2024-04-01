package tn.esprit.account_managment.service;
import tn.esprit.account_managment.dto.InternationalTransferDto;
import java.util.List;

public interface IInternationalTransferService
{
    public List<InternationalTransferDto> retrieveAllInternationalTransfers();
    public InternationalTransferDto retrieveInternationalTransfer(Long internationalTransferId);
    public void addInternationalTransfer(InternationalTransferDto internationalTransferDto);
    public void removeInternationalTransfer(Long internationalTransferId);
    public void modifyInternationalTransfer(InternationalTransferDto internationalTransferDto);
    public void approveinternationaltransfer(String employeeApprovalUsername, Long id);
}
