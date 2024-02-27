package tn.esprit.card_management.mapper;

import tn.esprit.card_management.dto.BankAccountForCarddto;
import tn.esprit.card_management.model.BankAccountForCard;

public class BankAccountForCardmapper {
    public BankAccountForCarddto BankAccountForCardToBankAccountForCarddto(BankAccountForCard bankaccountforcard) {
        BankAccountForCarddto bankaccountforcarddto = new BankAccountForCarddto();
        BankAccountForCarddto.setReference(bankaccountforcard.getReference());
        BankAccountForCarddto.setTitulaire(bankaccountforcard.getTitulaire());
        BankAccountForCarddto.setActivated(bankaccountforcard.getActivated());
        BankAccountForCarddto.setAccount_balance(bankaccountforcard.getAccount_balance());
        BankAccountForCarddto.setAccount_limit(bankaccountforcard.getAccount_limit());
        BankAccountForCarddto.setSendamounts(bankaccountforcard.getSendamounts());
        BankAccountForCarddto.setCards(bankaccountforcard.getCards());
        return bankaccountforcarddto;
    }

    public BankAccountForCard BankAccountForCarddtoToBankAccountForCard(BankAccountForCarddto bankAccountForCarddto) {
        BankAccountForCard bankaccountforcard = new BankAccountForCard();
        BankAccountForCarddto.setReference(bankAccountForCarddto.getReference());
        BankAccountForCarddto.setTitulaire(bankAccountForCarddto.getReference());
        BankAccountForCarddto.setActivated(bankAccountForCarddto.getActivated());
        BankAccountForCarddto.setAccount_balance(bankAccountForCarddto.getAccount_balance());
        BankAccountForCarddto.setAccount_limit(bankAccountForCarddto.getAccount_limit());
        BankAccountForCarddto.setSendamounts(bankAccountForCarddto.getSendamounts());
        BankAccountForCarddto.setCards(bankAccountForCarddto.getCards());
        return bankaccountforcard;
    }
}