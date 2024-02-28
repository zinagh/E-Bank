package tn.esprit.card_management.mapper;

import tn.esprit.card_management.dto.BankAccountForCarddto;
import tn.esprit.card_management.model.BankAccountForCard;

import java.util.List;

public interface IBankAccountForCardmapper {
    BankAccountForCarddto fromentityTodto(BankAccountForCard bankaccountforcard);
    BankAccountForCard fromdtoToentity(BankAccountForCarddto bankAccountForCarddto);
    List<BankAccountForCarddto> fromListentityTodtos (List<BankAccountForCard> bankAccountForCards);

    List<BankAccountForCard>fromListdtosToentities(List<BankAccountForCarddto> bankAccountForCarddtos);
}
