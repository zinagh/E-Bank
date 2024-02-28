package tn.esprit.card_management.services;

import tn.esprit.card_management.dto.BankAccountForCarddto;
import tn.esprit.card_management.model.BankAccountForCard;

import java.util.List;

public interface IBankAccountForCardService {
     BankAccountForCard addBankAccountForCard(BankAccountForCarddto bankAccountForCarddto);

     List<BankAccountForCarddto> retrieveAllBankAccountForCards();

     BankAccountForCard modifyBankAccountForCard(BankAccountForCarddto bankAccountForCarddto);

     BankAccountForCarddto retrieveBankAccount(String reference);
     void removeBankAccount(String reference );
}