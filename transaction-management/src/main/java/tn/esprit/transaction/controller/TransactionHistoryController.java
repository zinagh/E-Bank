package tn.esprit.transaction.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.transaction.dto.TransactionHistoryDto;
import tn.esprit.transaction.service.ITransactionHistoryService;
import java.util.List;


@RestController
@RequestMapping("/transactionhistories")
public class TransactionHistoryController {


        @Autowired
        private ITransactionHistoryService transactionHistoryService;

        @GetMapping("/all")
        public ResponseEntity<List<TransactionHistoryDto>> getAllTransactionHistories() {
            List<TransactionHistoryDto> transactionHistories = transactionHistoryService.getAllTransactionhistories();
            return ResponseEntity.ok(transactionHistories);
        }

        @PostMapping("/create")
        public ResponseEntity<String> createTransactionHistory(@RequestBody TransactionHistoryDto transactionHistoryDto) {
            try {
                transactionHistoryService.addTransacationHistory(transactionHistoryDto);
                return ResponseEntity.ok("Transaction history created successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating the transaction history");
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<TransactionHistoryDto> getTransactionHistoryById(@PathVariable String id) {
            TransactionHistoryDto transactionHistoryDto = transactionHistoryService.getByTransactionHistoryId(id);
            if (transactionHistoryDto != null) {
                return ResponseEntity.ok(transactionHistoryDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteTransactionHistory(@PathVariable String id) {
            try {
                transactionHistoryService.deleteTransactionHistory(id);
                return ResponseEntity.ok("Transaction history with id " + id + " has been deleted");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting the transaction history");
            }
        }

        @PutMapping("/update")
        public ResponseEntity<String> updateTransactionHistory(@RequestBody TransactionHistoryDto transactionHistoryDto) {
            try {
                transactionHistoryService.modifyTransactionHistory(transactionHistoryDto);
                return ResponseEntity.ok("Transaction history updated successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating the transaction history");
            }
        }
    }


