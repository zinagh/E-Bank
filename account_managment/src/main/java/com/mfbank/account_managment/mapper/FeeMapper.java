package com.mfbank.account_managment.mapper;
import com.mfbank.account_managment.model.Fee;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mfbank.account_managment.dto.FeeDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeeMapper implements IFeeMapper
{
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FeeDto toDto(Fee fee) {
        return modelMapper.map(fee, FeeDto.class);
    }
    @Override
    public Fee toEntity(FeeDto feeDto) {
        return modelMapper.map(feeDto, Fee.class);
    }
   @Override
    public List<FeeDto> toDtoList(List<Fee> fees) {
        return fees.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<Fee> toEntityList(List<FeeDto> feeDtos) {
        return feeDtos.stream()
                .map(this::toEntity) // Reuse the existing toEntity method for each element
                .collect(Collectors.toList());
    }


}
