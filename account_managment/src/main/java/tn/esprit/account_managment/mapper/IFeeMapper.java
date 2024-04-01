package tn.esprit.account_managment.mapper;
import tn.esprit.account_managment.dto.FeeDto;
import tn.esprit.account_managment.model.Fee;
import java.util.List;

public interface IFeeMapper
{
    public FeeDto toDto(Fee fee);
    public Fee toEntity(FeeDto feeDto);
    public List<FeeDto> toDtoList(List<Fee> fees);
    public List<Fee> toEntityList(List<FeeDto> feeDtos);
}
