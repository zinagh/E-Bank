package tn.esprit.transaction.service;
import tn.esprit.transaction.model.Invoice;

public interface IInvoiceService {
    public Invoice record(Invoice invoice);

    public Invoice markAsPaid(Invoice invoice) ;

    public void downloadPdf(String reference);
}
