package tn.esprit.card_management.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.card_management.dto.BankAccountForCarddto;
import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.model.BankAccountForCard;
import tn.esprit.card_management.model.Card;
import tn.esprit.card_management.services.IBankAccountForCardService;
import tn.esprit.card_management.services.ICardService;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/bankaccount")

public class BankAccountForCardController {
    IBankAccountForCardService iBankAccountForCardService;
    @GetMapping("retrieve-all-bankaccounts")
    public List<BankAccountForCarddto> getBankAccounts(){
        List<BankAccountForCarddto> listBankAccounts = iBankAccountForCardService.retrieveAllBankAccountForCards();
        return listBankAccounts ;
    }
    @PostMapping("/add-bankaccount")
    public BankAccountForCard addBankAccount(@RequestBody BankAccountForCarddto b) {
        BankAccountForCard bankAccountForCard = iBankAccountForCardService.addBankAccountForCard(b);
        return bankAccountForCard;
    }
    @DeleteMapping("/remove-bankaccount/{reference-bankaccount}")
    public void removeBankAccount(@PathVariable("reference-bankaccount") String reference) {
        iBankAccountForCardService.removeBankAccount(reference);
    }
    @GetMapping("/retrieve-bankaccount/{reference-bankaccount}")
    public ResponseEntity<BankAccountForCarddto> getBankAccount(@PathVariable("reference-bankaccount") String ref) {
        try {
            BankAccountForCarddto bankAccountForCard = iBankAccountForCardService.retrieveBankAccount(ref);
            if (bankAccountForCard != null) {
                return new ResponseEntity<>(bankAccountForCard, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/modify-bankaccount")
    public BankAccountForCard updateBankAcccount(@RequestBody BankAccountForCarddto b){
        BankAccountForCard bankAccountForCard = iBankAccountForCardService.modifyBankAccountForCard(b);
        return bankAccountForCard;
    }
}
