package tn.esprit.transaction.mapper;
import org.springframework.stereotype.Service;
import tn.esprit.transaction.dto.InvoiceDto;
import tn.esprit.transaction.model.Invoice;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class InvoiceMapper implements IInvoiceMapper {
    @Override
    public InvoiceDto fromentityTodto(Invoice invoice){

        String reference;
        String statut;
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setReference(invoice.getReference());
        invoiceDto.setStatut(invoice.getStatut());
        return invoiceDto;
    }

    @Override
    public Invoice fromDtoToentity(InvoiceDto invoiceDto){
        Invoice invoice = new Invoice();
        invoice.setReference(invoiceDto.getReference());
        invoice.setStatut(invoiceDto.getStatut());
        return invoice;

    }

    @Override
    public List<InvoiceDto> fromentityListTodtoList(List<Invoice> invoices){
        return invoices.stream()
                .map(this::fromentityTodto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Invoice> fromDtoListToentityList(List<InvoiceDto> invoiceDtos){
        return invoiceDtos.stream()
                .map(this::fromDtoToentity)
                .collect(Collectors.toList());
    }


}
