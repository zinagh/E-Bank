package com.mfbank.account_managment.service;
import com.mfbank.account_managment.mapper.IFeeMapper;
import com.mfbank.account_managment.repository.FeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mfbank.account_managment.dto.FeeDto;
import com.mfbank.account_managment.model.Fee;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeeServiceImpl implements IFeeService
{
    @Autowired
    private final FeeRepository feeRepository;
    @Autowired
    private final IFeeMapper iFeeMapper;

    @Override
    public List<FeeDto> retrieveAllFees() {
        List<Fee> fees = feeRepository.findAll();
        List<FeeDto> feeDtos = iFeeMapper.toDtoList(fees);
        return feeDtos;
    }


    @Override
    public FeeDto retrieveFee(Long feeId) {
        Fee fee =  feeRepository.findById(feeId).get();
        FeeDto feedto = iFeeMapper.toDto(fee);
        return feedto;
    }

    @Override
    public void addFee(FeeDto feeDto) {
        if(feeDto.getFeeType().toString().equals("TRANSACTION_FEE_currentAccount")){
            feeDto.setAmountPercent(300);
        } else if (feeDto.getFeeType().toString().equals("TRANSACTION_FEE_savingsAccount")) {
            feeDto.setAmountPercent(150);

        } else if (feeDto.getFeeType().toString().equals("CURRENCY_CONVERSION_FEE")) {
            feeDto.setAmountPercent(500);

        }
        Fee fee = iFeeMapper.toEntity(feeDto);
        feeRepository.save(fee);
    }
    @Override
    public void removeFee(Long feeId) {
        feeRepository.deleteById(feeId);
    }
    @Override
    public void modifyFee(FeeDto feeDto) {
        Fee fee = iFeeMapper.toEntity(feeDto);
        feeRepository.save(fee);
    }
}
