package tn.esprit.account_managment.mapper;
import tn.esprit.account_managment.dto.InternationalTransferDto;
import tn.esprit.account_managment.model.InternationalTransfer;
import java.util.List;

public interface IInternationalTransferMapper
{
    public InternationalTransferDto toDto(InternationalTransfer internationalTransfer);
    public InternationalTransfer toEntity(InternationalTransferDto internationalTransferDto);
    public List<InternationalTransferDto> toDtoList(List<InternationalTransfer> internationalTransfers);
    public List<InternationalTransfer> toEntityList(List<InternationalTransferDto> internationalTransferDtos);
}
