package tn.esprit.card_management.mapper;

import tn.esprit.card_management.dto.Atmdto;
import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.model.Atm;
import tn.esprit.card_management.model.Card;

import java.util.List;

public interface IAtmmapper {
    Atmdto fromentityTodto(Atm atm);
    Atm fromdtoToentity(Atmdto atmdto);
    List<Atmdto> fromListentityTodtos (List<Atm> atms);

    List<Atm>fromListdtosToentities(List<Atmdto> atmdtos);
}
