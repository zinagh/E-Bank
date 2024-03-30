package tn.esprit.card_management.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.card_management.dto.Atmdto;
import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.mapper.IAtmmapper;
import tn.esprit.card_management.model.Atm;
import tn.esprit.card_management.model.Card;
import tn.esprit.card_management.services.IAtmService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/atm")
public class AtmCotroller {
    IAtmService iAtmService;
    @GetMapping("retrieve-all-atm")
    public List<Atmdto> getAtms(){
        List<Atmdto> listAtms = iAtmService.retrieveAllAtms();
        return listAtms ;
    }
    @PostMapping("/add-atm")
    public Atm addAtm(@RequestBody Atmdto a) {
        Atm atm = iAtmService.addAtm(a);
        return atm;
    }
    @DeleteMapping("/remove-atm/{id}")
    public void removeAtm(@PathVariable("id") Long Id) {
        iAtmService.removeAtm(Id);
    }
    @GetMapping("/retrieve-atm/{id}")
    public ResponseEntity<Atmdto> getAtm(@PathVariable("id") Long Id) {
        try {
            Atmdto atmdto = iAtmService.retrieveAtm(Id);
            if (atmdto != null) {
                return new ResponseEntity<>(atmdto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/modify-atm")
    public Atm updateAtm(@RequestBody Atmdto a){
        Atm atm = iAtmService.modifyAtm(a);
        return atm;
    }

}
