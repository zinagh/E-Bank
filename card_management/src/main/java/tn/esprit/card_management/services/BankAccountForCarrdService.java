package tn.esprit.card_management.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.BankAccountForCarddto;
import tn.esprit.card_management.mapper.IBankAccountForCardmapper;
import tn.esprit.card_management.model.BankAccountForCard;
import tn.esprit.card_management.repository.BankAccountForCardRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountForCarrdService {

    private final IBankAccountForCardmapper iBankAccountForCardmapper;
    private final BankAccountForCardRepository bankAccountForCardRepository;


    public void addBankAccountForCard(BankAccountForCarddto bankAccountForCarddto) {
        BankAccountForCard bankAccountForCard = iBankAccountForCardmapper.fromdtoToentity(bankAccountForCarddto);
        bankAccountForCardRepository.save(bankAccountForCard);
    }

    public List<BankAccountForCarddto> retrieveall() {
        List<BankAccountForCard> bankAccountForCards = bankAccountForCardRepository.findAll();
        List<BankAccountForCarddto> bankAccountForCarddtos = iBankAccountForCardmapper.fromListentityTodtos(bankAccountForCards);
        return bankAccountForCarddtos;
    }



}
