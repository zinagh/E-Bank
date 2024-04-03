package tn.esprit.transaction.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.transaction.dto.InvoiceDto;
import tn.esprit.transaction.mapper.IInvoiceMapper;
import tn.esprit.transaction.mapper.ITransactionMapper;
import tn.esprit.transaction.model.Invoice;
import tn.esprit.transaction.model.Transaction;
import tn.esprit.transaction.repository.InvoiceRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceService implements IInvoiceService {

    private final IInvoiceMapper iInvoiceMapper;
    private final InvoiceRepository invoiceRepository;
    private final ITransactionMapper iTransactionMapper;

    @Override
    public void addInvoice(InvoiceDto invoiceDto){
        Invoice invoice = iInvoiceMapper.fromDtoToentity(invoiceDto);
        Transaction transaction = iTransactionMapper.fromDtoToentity(invoiceDto.getTransactionDto());
        invoice.setTransaction(transaction);
        invoiceRepository.save(invoice);
    }

    @Override
    public List<InvoiceDto> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        List<InvoiceDto> invoiceDtos = iInvoiceMapper.fromentityListTodtoList(invoices);
        for (int i = 0; i < invoiceDtos.size(); i++) {
            InvoiceDto invoiceDto = invoiceDtos.get(i);
            Invoice invoice = invoices.get(i);
            invoiceDto.setTransactionDto(iTransactionMapper.fromentityTodto(invoice.getTransaction()));
        }
        return invoiceDtos;
    }


    public void deleteInvoice(String id){
        Optional<Invoice> optionnalinvoice = invoiceRepository.findById(id);
        if(optionnalinvoice.isPresent()) {
            Invoice invoice = optionnalinvoice.get();
            invoiceRepository.delete(invoice);
        }
    }

    public InvoiceDto getByInvoiceId(String id){
        Optional<Invoice> optionnalinvoice = invoiceRepository.findById(id);
        if(optionnalinvoice.isEmpty()){
            return  null;
        }
        Invoice invoice = optionnalinvoice.get();
        InvoiceDto invoiceDto = iInvoiceMapper.fromentityTodto(invoice);
        invoiceDto.setTransactionDto(iTransactionMapper.fromentityTodto(invoice.getTransaction()));
        return invoiceDto;
    }

    @Override
    public void updateInvoice(String id, InvoiceDto updatedInvoiceDto) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if (optionalInvoice.isPresent()) {
            Invoice existingInvoice = optionalInvoice.get();
            Invoice updatedInvoice = iInvoiceMapper.fromDtoToentity(updatedInvoiceDto);
            existingInvoice.setStatut(updatedInvoice.getStatut());
            existingInvoice.setTransaction(updatedInvoice.getTransaction());
            invoiceRepository.save(existingInvoice);
        }
    }
    @Override
    public BigDecimal calculateTotalAmountByMonth(Month month, int year) {
        List<Invoice> invoices = invoiceRepository.findAll();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (Invoice invoice : invoices) {
            Transaction transaction = invoice.getTransaction();
            if (transaction != null) {
                LocalDate transactionDate = transaction.getDate_heure().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (transactionDate.getMonth() == month && transactionDate.getYear() == year) {
                    totalAmount = totalAmount.add(BigDecimal.valueOf(transaction.getMontant()));
                }
            }
        }

        return totalAmount;
    }

}
