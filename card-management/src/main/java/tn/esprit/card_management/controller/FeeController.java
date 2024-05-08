package tn.esprit.card_management.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.card_management.dto.Feedto;
import tn.esprit.card_management.model.Fee;
import tn.esprit.card_management.model.FeeType;
import tn.esprit.card_management.services.IFeeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/fee")
public class FeeController {
    IFeeService iFeeService;
    @GetMapping("retrieve-all-Fee")
    public List<Feedto> getFees(){
        List<Feedto> listFees = iFeeService.retrieveAllFees();
        return listFees ;
    }
    @PostMapping("/add-Fee")
    public Fee addFee(@RequestBody Feedto a) {
        Fee fee = iFeeService.addFee(a);
        return fee;
    }
    @DeleteMapping("/remove-Fee/{id}")
    public void removeFee(@PathVariable("id") Long Id) {
        iFeeService.removeFee(Id);
    }
    @GetMapping("/retrieve-Fee/{id}")
    public ResponseEntity<Feedto> getFee(@PathVariable("id") Long Id) {
        try {
            Feedto feedto = iFeeService.retrieveFee(Id);
            if (feedto != null) {
                return new ResponseEntity<>(feedto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/modify-Fee")
    public Fee updateFee(@RequestBody Feedto a){
        Fee fee = iFeeService.modifyFee(a);
        return fee;
    }

}
