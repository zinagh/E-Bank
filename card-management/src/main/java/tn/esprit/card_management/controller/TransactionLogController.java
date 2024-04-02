package tn.esprit.card_management.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.card_management.dto.TransactionLogdto;
import tn.esprit.card_management.model.TransactionLog;
import tn.esprit.card_management.services.ITransactionLogService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/transactions")
public class TransactionLogController {
    ITransactionLogService iTransactionLogService;
    @GetMapping("retrieve-all-TransactionLog")
    public List<TransactionLogdto> getTransactionLogs(){
        List<TransactionLogdto> listTransactionLogs = iTransactionLogService.retrieveAllTransactionLogs();
        return listTransactionLogs ;
    }
    @PostMapping("/add-TransactionLog")
    public TransactionLog addTransactionLog(@RequestBody TransactionLogdto a) {
        TransactionLog transactionLog = iTransactionLogService.addTransactionLog(a);
        return transactionLog;
    }
    @DeleteMapping("/remove-TransactionLog/{id}")
    public void removeTransactionLog(@PathVariable("id") Long Id) {
        iTransactionLogService.removeTransactionLog(Id);
    }
    @GetMapping("/retrieve-TransactionLog/{id}")
    public ResponseEntity<TransactionLogdto> getTransactionLog(@PathVariable("id") Long Id) {
        try {
            TransactionLogdto transactionLogdto = iTransactionLogService.retrieveTransactionLog(Id);
            if (transactionLogdto != null) {
                return new ResponseEntity<>(transactionLogdto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/modify-TransactionLog")
    public TransactionLog updateTransactionLog(@RequestBody TransactionLogdto a){
        TransactionLog transactionLog = iTransactionLogService.modifyTransactionLog(a);
        return transactionLog;
    }

}
