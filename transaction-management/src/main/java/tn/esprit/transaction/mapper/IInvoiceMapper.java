package tn.esprit.transaction.mapper;
import tn.esprit.transaction.dto.InvoiceDto;
import tn.esprit.transaction.model.Invoice;
import java.util.List;

public interface IInvoiceMapper {
   InvoiceDto fromentityTodto(Invoice invoice);
    Invoice fromDtoToentity(InvoiceDto invoiceDto);

    List<InvoiceDto> fromentityListTodtoList(List<Invoice> invoices);
    List<Invoice> fromDtoListToentityList(List<InvoiceDto> invoiceDtos);
}
