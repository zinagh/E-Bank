package tn.esprit.card_management.mapper;

import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.Atmdto;
import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.model.Atm;
import tn.esprit.card_management.model.Card;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Atmmapper  implements IAtmmapper  {


    @Override
    public Atmdto fromentityTodto(Atm atm) {
        Atmdto  atmdto = new Atmdto();
        atmdto.setId(atm.getId());
        atmdto.setLocation(atm.getLocation());
        atmdto.setSomme(atm.getSomme());
        atmdto.setStatut(atm.isStatut());
        atmdto.setRecu(atm.getRecu());
        return atmdto;
    }

    @Override
    public Atm fromdtoToentity(Atmdto atmdto) {
        Atm atm = new Atm();
        atm.setId(atmdto.getId());
        atm.setLocation(atmdto.getLocation());
        atm.setSomme(atmdto.getSomme());
        atm.setStatut(atmdto.isStatut());
        atm.setRecu(atmdto.getRecu());
        return atm;
    }

    @Override
    public List<Atmdto> fromListentityTodtos(List<Atm> atms) {
        return atms.stream()
                .map(this::fromentityTodto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Atm> fromListdtosToentities(List<Atmdto> atmdtos) {
        return atmdtos.stream()
                .map(this::fromdtoToentity)
                .collect(Collectors.toList());
    }

}
