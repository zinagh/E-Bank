 package tn.esprit.card_management.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.Reclamationdto;
import tn.esprit.card_management.mapper.IReclamationmapper;

import tn.esprit.card_management.model.Reclamation;
import tn.esprit.card_management.repository.ReclamationRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReclamationService implements IReclamationService {
    private final IReclamationmapper iReclamationmapper;
    private final ReclamationRepository reclamationRepository;

    @Override
    public List<Reclamationdto> retrieveAllReclamations() {
        List<Reclamation> Reclamations = reclamationRepository.findAll();
        List<Reclamationdto> Reclamationdtos = iReclamationmapper.fromListentityTodtos(Reclamations);
        return Reclamationdtos;
    }

    @Override
    public Reclamation addReclamation(Reclamationdto Reclamationdto) {
        Reclamation reclamation = iReclamationmapper.dtoToEntity(Reclamationdto);
        LocalDateTime currentTime = LocalDateTime.now();
        reclamation.setDate(currentTime);
        reclamationRepository.save(reclamation);
        return reclamation;
    }

    @Override
    public void removeReclamation(Long Id) {

        reclamationRepository.deleteById(Id);    }

    @Override
    public Reclamationdto retrieveReclamation(Long Id) {
        Reclamation Reclamation = reclamationRepository.findById(Id).get();
        return iReclamationmapper.entityToDto(Reclamation);
    }

    @Override
    public Reclamation modifyReclamation(Reclamationdto Reclamationdto) {
        Reclamation Reclamation = iReclamationmapper.dtoToEntity(Reclamationdto);
        reclamationRepository.save(Reclamation);
        return Reclamation;
    }
}
