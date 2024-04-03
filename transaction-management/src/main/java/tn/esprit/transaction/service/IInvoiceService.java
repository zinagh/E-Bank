package tn.esprit.transaction.service;
import tn.esprit.transaction.dto.InvoiceDto;


import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

public interface IInvoiceService {
    void addInvoice(InvoiceDto invoiceDto);
    List<InvoiceDto> getAllInvoices();
    InvoiceDto getByInvoiceId(String id);
    void deleteInvoice(String id);
     void updateInvoice(String id, InvoiceDto updatedInvoiceDto);
    BigDecimal calculateTotalAmountByMonth(Month month, int year);
}
