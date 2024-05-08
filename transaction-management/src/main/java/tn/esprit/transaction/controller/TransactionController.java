package tn.esprit.transaction.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.transaction.EmailService;
import tn.esprit.transaction.dto.TransactionDto;
import tn.esprit.transaction.model.Transaction;
import tn.esprit.transaction.service.ITransactionService;
import tn.esprit.transaction.service.SmsService;


import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

        @Autowired
        private ITransactionService transactionService;
    @Autowired
    private EmailService emailService ;

    @Autowired
    SmsService smsService;

    @PostMapping("/create")
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDto transactionDto) {
        try {
            Transaction transaction = transactionService.addTransacation(transactionDto);
            String email = transactionService.getEmail();
            System.out.println(email);
            emailService.sendEmail(email, "transaction" , "Transaction passed sucessfuly with reference: " + transaction.getReference() + " You sent " +
                    transaction.getMontant() + " TND to the bank Account : " + transaction.getDestination());
            smsService.sendSms("+21655891733", "Invoice passed successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body("Transaction created successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create transaction");
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable String id) {
        TransactionDto transactionDto = transactionService.getByTransactionId(id);
        if (transactionDto != null) {
            return ResponseEntity.ok(transactionDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable String id) {
        try {
            transactionService.deleteTransaction(id);
            return ResponseEntity.ok("Transaction with id " + id + " has been deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting the transaction");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTransaction(@PathVariable String id, @RequestBody TransactionDto transactionDto) {
        try {
            transactionDto.setReference(id); // Assuming reference cannot be changed
            transactionService.modifyTransaction(transactionDto);
            return ResponseEntity.ok("Transaction with id " + id + " has been updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating the transaction");
        }
    }
    @GetMapping("/search")
    public ResponseEntity<List<TransactionDto>> searchTransactions(@RequestParam String keyword) {
        List<TransactionDto> transactions = transactionService.searchTransactions(keyword);
        return ResponseEntity.ok(transactions);
    }
    @GetMapping("/all")
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        List<TransactionDto> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }



    }
