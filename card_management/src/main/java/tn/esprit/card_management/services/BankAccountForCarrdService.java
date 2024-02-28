package tn.esprit.card_management.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.BankAccountForCarddto;
import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.mapper.IBankAccountForCardmapper;
import tn.esprit.card_management.model.BankAccountForCard;
import tn.esprit.card_management.model.Card;
import tn.esprit.card_management.repository.BankAccountForCardRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountForCarrdService implements IBankAccountForCardService{

    private final IBankAccountForCardmapper iBankAccountForCardmapper;
    private final BankAccountForCardRepository bankAccountForCardRepository;

@Override
    public BankAccountForCard addBankAccountForCard(BankAccountForCarddto bankAccountForCarddto) {
        BankAccountForCard bankAccountForCard = iBankAccountForCardmapper.fromdtoToentity(bankAccountForCarddto);
        bankAccountForCardRepository.save(bankAccountForCard);
        return bankAccountForCard;
    }
@Override
    public List<BankAccountForCarddto> retrieveAllBankAccountForCards() {
        List<BankAccountForCard> bankAccountForCards = bankAccountForCardRepository.findAll();
        List<BankAccountForCarddto> bankAccountForCarddtos = iBankAccountForCardmapper.fromListentityTodtos(bankAccountForCards);
        return bankAccountForCarddtos;
    }
 @Override
 public BankAccountForCard modifyBankAccountForCard(BankAccountForCarddto bankAccountForCarddto){
     BankAccountForCard bankAccountForCard = iBankAccountForCardmapper.fromdtoToentity(bankAccountForCarddto);
     bankAccountForCardRepository.save(bankAccountForCard);
     return bankAccountForCard;

 }
    @Override
    public BankAccountForCarddto retrieveBankAccount(String reference) {
        BankAccountForCard bankAccountForCard = bankAccountForCardRepository.findById(reference).get();
        return iBankAccountForCardmapper.fromentityTodto(bankAccountForCard);
    }
    @Override
    public void removeBankAccount(String reference ){

        bankAccountForCardRepository.deleteById(reference);
    }


}
