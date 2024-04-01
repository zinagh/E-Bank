package tn.esprit.account_managment.mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import tn.esprit.account_managment.dto.InternationalTransferDto;
import tn.esprit.account_managment.model.InternationalTransfer;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InternationalTransferMapper implements IInternationalTransferMapper
{

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public InternationalTransferDto toDto(InternationalTransfer internationalTransfer) {
        return modelMapper.map(internationalTransfer, InternationalTransferDto.class);
    }
    @Override
    public InternationalTransfer toEntity(InternationalTransferDto internationalTransferDto) {
        return modelMapper.map(internationalTransferDto, InternationalTransfer.class);
    }
    @Override
    public List<InternationalTransferDto> toDtoList(List<InternationalTransfer> internationalTransfers) {
        return internationalTransfers.stream()
                .map(this::toDto) // Reuse the existing toDto method for each element
                .collect(Collectors.toList());
    }
    @Override
    public List<InternationalTransfer> toEntityList(List<InternationalTransferDto> internationalTransferDtos) {
        return internationalTransferDtos.stream()
                .map(this::toEntity) // Reuse the existing toEntity method for each element
                .collect(Collectors.toList());
    }
}
