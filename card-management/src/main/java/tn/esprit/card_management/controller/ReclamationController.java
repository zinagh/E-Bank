package tn.esprit.card_management.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.card_management.dto.Reclamationdto;
import tn.esprit.card_management.model.Reclamation;
import tn.esprit.card_management.services.IReclamationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Reclamation")
public class ReclamationController {
    IReclamationService iReclamationService;

    @GetMapping("retrieve-all-Reclamation")
    public List<Reclamationdto> getReclamations() {
        List<Reclamationdto> listReclamations = iReclamationService.retrieveAllReclamations();
        return listReclamations;
    }

    @PostMapping("/add-Reclamation")
    public Reclamation addReclamation(@RequestBody Reclamationdto n) {
        Reclamation Reclamation = iReclamationService.addReclamation(n);
        return Reclamation;
    }

    @DeleteMapping("/remove-Reclamation/{id}")
    public void removenotif(@PathVariable("id") Long id) {
        iReclamationService.removeReclamation(id);

    }
    @GetMapping("/retrieve-atm/{id}")
    public ResponseEntity<Reclamationdto> getNotif(@PathVariable("id") Long id) {
        try {
            Reclamationdto Reclamationdto = iReclamationService.retrieveReclamation(id);
            if (Reclamationdto != null) {
                return new ResponseEntity<>(Reclamationdto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/modify-Reclamation")
    public Reclamation updateNotif(@RequestBody Reclamationdto n){
        Reclamation Reclamation = iReclamationService.modifyReclamation(n);
        return Reclamation;
    }
}
