package tn.esprit.card_management.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.Feedto;
import tn.esprit.card_management.mapper.IFeemapper;
import tn.esprit.card_management.model.Fee;
import tn.esprit.card_management.model.FeeType;
import tn.esprit.card_management.repository.FeeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor

public class FeeService implements IFeeService{
    private final IFeemapper iFeemapper;
    private final FeeRepository feeRepository;
    @Override
    public List<Feedto> retrieveAllFees() {
        List<Fee> fees = feeRepository.findAll();
        List<Feedto> feedtos = iFeemapper.fromListentityTodtos(fees);
        return feedtos;    }

    @Override
    public Fee addFee(Feedto feedto) {
        Fee fee = iFeemapper.dtoToEntity(feedto);
        if(fee.getType().equals(FeeType.FeeForCLASSIC)){
            fee.setAmount(0.1);
        }
        if(fee.getType().equals(FeeType.FeeForGOLD)){
            fee.setAmount(0.25);
        }
        if(fee.getType().equals(FeeType.FeeForPREMIUM)){
            fee.setAmount(0.35);
        }
        feeRepository.save(fee);
        return fee;
    }

    @Override
    public void removeFee(Long Id) {
        feeRepository.deleteById(Id);
    }

    @Override
    public Feedto retrieveFee(Long Id) {
        Fee fee = feeRepository.findById(Id).get();
        return iFeemapper.entityToDto(fee);
    }

    @Override
    public Fee modifyFee(Feedto feedto) {
        Fee fee = iFeemapper.dtoToEntity(feedto);
        feeRepository.save(fee);
        return fee;
    }

}
