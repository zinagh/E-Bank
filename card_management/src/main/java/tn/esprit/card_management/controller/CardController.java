package tn.esprit.card_management.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.model.Card;
import tn.esprit.card_management.services.ICardService;

import javax.net.ssl.SSLEngineResult;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/card")
public class CardController {
    ICardService iCardService;
    @GetMapping("retrieve-all-card")
    public List<Carddto> getCards(){
        List<Carddto> listCards = iCardService.retrieveAllCards();
        return listCards ;
    }
    @PostMapping("/add-card")
    public Card addCard(@RequestBody Carddto c) {
        Card card = iCardService.addCard(c);
        return card;
    }
    @DeleteMapping("/remove-card/{numero-card}")
    public void removeCard(@PathVariable("numero-card") String numcard) {
        iCardService.removeCard(numcard);
    }
    @GetMapping("/retrieve-card/{numcard}")
    public ResponseEntity<Carddto> getCard(@PathVariable("numcard") String numerocard) {
        try {
            Carddto carddto = iCardService.retrieveCard(numerocard);
            if (carddto != null) {
                return new ResponseEntity<>(carddto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


@PutMapping("/modify-card")
    public Card updateCard(@RequestBody Carddto c){
        Card card = iCardService.modifyCard(c);
        return card;
}

}
