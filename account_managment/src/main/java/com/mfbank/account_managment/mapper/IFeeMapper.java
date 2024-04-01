package com.mfbank.account_managment.mapper;
import com.mfbank.account_managment.dto.FeeDto;
import com.mfbank.account_managment.model.Fee;
import java.util.List;

public interface IFeeMapper
{
    public FeeDto toDto(Fee fee);
    public Fee toEntity(FeeDto feeDto);
    public List<FeeDto> toDtoList(List<Fee> fees);
    public List<Fee> toEntityList(List<FeeDto> feeDtos);
}
