package tn.esprit.account_managment.service;
import tn.esprit.account_managment.dto.FeeDto;
import java.util.List;

public interface IFeeService
{
    public List<FeeDto> retrieveAllFees();
    public FeeDto retrieveFee(Long feeId);
    public void addFee(FeeDto feeDto);
    public void removeFee(Long feeId);
    public void modifyFee(FeeDto feeDto);
}
