package tn.esprit.transaction.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.transaction.dto.InvoiceDto;
import tn.esprit.transaction.model.SmsSendRequest;
import tn.esprit.transaction.service.IInvoiceService;
import tn.esprit.transaction.service.SmsService;
import java.math.BigDecimal;
import java.time.Month;
import java.util.List;


@RestController
@RequestMapping("/invoices")
@Slf4j
public class InvoiceController {
        @Autowired
        private IInvoiceService invoiceService;
    @Autowired
    SmsService smsService;

        @GetMapping("/all")
        public ResponseEntity<List<InvoiceDto>> getAllInvoices() {
            List<InvoiceDto> invoices = invoiceService.getAllInvoices();
            return ResponseEntity.ok(invoices);
        }

        @PostMapping("/create")

        public ResponseEntity<String> createInvoice(@RequestBody InvoiceDto invoiceDto) {
            try {
                invoiceService.addInvoice(invoiceDto);
                return ResponseEntity.ok("Invoice created successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating the invoice");
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<InvoiceDto> getInvoiceById(@PathVariable String id) {
            InvoiceDto invoiceDto = invoiceService.getByInvoiceId(id);
            if (invoiceDto != null) {
                return ResponseEntity.ok(invoiceDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteInvoice(@PathVariable String id) {
            try {
                invoiceService.deleteInvoice(id);
                return ResponseEntity.ok("Invoice with id " + id + " has been deleted");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting the invoice");
            }
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<String> updateInvoice(@PathVariable String id, @RequestBody InvoiceDto updatedInvoiceDto) {
            try {
                invoiceService.updateInvoice(id, updatedInvoiceDto);
                return ResponseEntity.ok("Invoice with id " + id + " has been updated");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating the invoice");
            }
        }
    @GetMapping("/total-amount-by-month")
    public ResponseEntity<BigDecimal> getTotalAmountByMonth(
            @RequestParam int year,
            @RequestParam Month month) {
        BigDecimal totalAmount = invoiceService.calculateTotalAmountByMonth(month, year);
        return ResponseEntity.ok(totalAmount);
    }

}


