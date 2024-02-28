package tn.esprit.card_management.mapper;

import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.BankAccountForCarddto;
import tn.esprit.card_management.model.BankAccountForCard;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountForCardmapper implements IBankAccountForCardmapper {

    @Override
    public BankAccountForCarddto fromentityTodto(BankAccountForCard bankaccountforcard) {
        BankAccountForCarddto bankaccountforcarddto = new BankAccountForCarddto();
        bankaccountforcarddto.setReference(bankaccountforcard.getReference());
        bankaccountforcarddto.setTitulaire(bankaccountforcard.getTitulaire());
        bankaccountforcarddto.setActivated(bankaccountforcard.isActivated());
        bankaccountforcarddto.setAccount_balance(bankaccountforcard.getAccount_balance());
        bankaccountforcarddto.setAccount_limit(bankaccountforcard.getAccount_limit());
        bankaccountforcarddto.setSendamounts(bankaccountforcard.getSendamounts());
        return bankaccountforcarddto;
    }

    @Override
    public BankAccountForCard fromdtoToentity(BankAccountForCarddto bankAccountForCarddto) {
        BankAccountForCard bankaccountforcard = new BankAccountForCard();
        bankAccountForCarddto.setReference(bankAccountForCarddto.getReference());
        bankAccountForCarddto.setTitulaire(bankAccountForCarddto.getReference());
        bankAccountForCarddto.setActivated(bankAccountForCarddto.isActivated());
        bankAccountForCarddto.setAccount_balance(bankAccountForCarddto.getAccount_balance());
        bankAccountForCarddto.setAccount_limit(bankAccountForCarddto.getAccount_limit());
        bankAccountForCarddto.setSendamounts(bankAccountForCarddto.getSendamounts());
        return bankaccountforcard;
    }

    @Override
    public List<BankAccountForCarddto>fromListentityTodtos (List<BankAccountForCard> bankAccountForCards) {
        return bankAccountForCards.stream()
                .map(this::fromentityTodto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BankAccountForCard>fromListdtosToentities(List<BankAccountForCarddto> bankAccountForCarddtos){
            return bankAccountForCarddtos.stream()
                    .map(this::fromdtoToentity)
                    .collect(Collectors.toList());
    }


}