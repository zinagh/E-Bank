package tn.esprit.transaction.service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tn.esprit.transaction.TransactionSpecifications;
import tn.esprit.transaction.dto.InvoiceDto;
import tn.esprit.transaction.dto.TransactionDto;
import tn.esprit.transaction.dto.TransactionHistoryDto;
import tn.esprit.transaction.dtoBank.BankAccountDto;
import tn.esprit.transaction.dtouser.Userdto;
import tn.esprit.transaction.mapper.ITransactionHistoryMapper;
import tn.esprit.transaction.mapper.ITransactionMapper;
import tn.esprit.transaction.model.Invoice;
import tn.esprit.transaction.model.Transaction;
import tn.esprit.transaction.model.TransactionHistory;
import tn.esprit.transaction.repository.InvoiceRepository;
import tn.esprit.transaction.repository.TransactionReposiroty;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService implements  ITransactionService{

    private final ITransactionMapper iTransactionMapper;
    private final ITransactionHistoryMapper iTransactionHistoryMapper;
    private final ITransactionHistoryService iTransactionHistoryService;
    private final IInvoiceService invoiceService;
    private final TransactionReposiroty transactionReposiroty;
    private final InvoiceRepository invoiceRepository;

    private final WebClient.Builder webClient;
    @Value("${principle-attribute}")
    private String principleAttribut;
    private SecurityContextHolder securityContextHolder;

    @Override
    public Transaction addTransacation(TransactionDto transactionDto){
        Date curretDate = new Date();
        Transaction transaction = iTransactionMapper.fromDtoToentity(transactionDto);
        transaction.setCancelByreceiver(false);
        transaction.setCancelBysender(false);
        transaction.setCancelBysender(false);
        transaction.setValidation(true);
        transaction.setDate_heure(curretDate);

        BankAccountDto bankAccountDtoSender = webClient.build()
                .get()
                .uri("http://account-management/account/getbankaccountbyTitulaire/" + getUsername())
                .retrieve()
                .bodyToMono(BankAccountDto.class)
                .block(Duration.ofSeconds(5));
        transaction.setSource(bankAccountDtoSender.getAccountNumber());
        System.out.println("transaction: " + transaction);
        if(transaction.getMontant() > bankAccountDtoSender.getAccount_balance()){
            return null;
        }
        Transaction transactionsaved = transactionReposiroty.save(transaction);
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setStatut("Payed");
        invoiceDto.setTransactionDto(iTransactionMapper.fromentityTodto(transactionsaved));
        System.out.println("invoiceDto: " + invoiceDto);

        invoiceService.addInvoice(invoiceDto);


        BankAccountDto bankAccountDtoReceiver = webClient.build()
                .get()
                .uri("http://account-management/account/getbankaccountby/" + transactionDto.getDestination())
                .retrieve()
                .bodyToMono(BankAccountDto.class)
                .block(Duration.ofSeconds(5));



        bankAccountDtoSender.setAccount_balance(bankAccountDtoSender.getAccount_balance() - transaction.getMontant());
        System.out.println("bankAccountDtoSender.setAccount_balance(): " + bankAccountDtoSender.getAccount_balance());

        webClient.build()
                .put()
                .uri("http://account-management/account/modifybankaccount" )
                .bodyValue(bankAccountDtoSender)
                .retrieve()
                .bodyToMono(void.class)
                .block(Duration.ofSeconds(5));



        bankAccountDtoReceiver.setAccount_balance(bankAccountDtoReceiver.getAccount_balance() + transaction.getMontant());
        System.out.println("bankAccountDtoReceiver.getAccount_balance(): " + bankAccountDtoReceiver.getAccount_balance());

        webClient.build()
                .put()
                .uri("http://account-management/account/modifybankaccount")
                .bodyValue(bankAccountDtoReceiver)
                .retrieve()
                .bodyToMono(void.class)
                .block(Duration.ofSeconds(5));

        return  transaction;
    }
    @Override
    public List<TransactionDto> getAllTransactions(){
        List<Transaction> transactions = transactionReposiroty.findAll();
        List<TransactionDto> transactionDtos = iTransactionMapper.fromentityListTodtoList(transactions);
        for (TransactionDto transactionDto: transactionDtos) {
            BankAccountDto bankAccountDto = webClient.build()
                    .get()
                    .uri("http://account-management/account/getbankaccountby/" + transactionDto.getSource())
                    .retrieve()
                    .bodyToMono(BankAccountDto.class)
                    .block(Duration.ofSeconds(5));
            BankAccountDto bankAccountForReceiverDto = webClient.build()
                    .get()
                    .uri("http://account-management/account/getbankaccountby/" + transactionDto.getDestination())
                    .retrieve()
                    .bodyToMono(BankAccountDto.class)
                    .block(Duration.ofSeconds(5));
            transactionDto.setBankAccountDto(bankAccountDto);
            transactionDto.setBankAccountForRecieverDto(bankAccountForReceiverDto);

        }
        return transactionDtos;
    }


    public TransactionDto getByTransactionId(String id){
        Optional<Transaction> optionnaltransaction = transactionReposiroty.findById(id);
        if(optionnaltransaction.isEmpty()){
            return  null;
        }
        Transaction transaction = optionnaltransaction.get();
        TransactionDto transactionDto = iTransactionMapper.fromentityTodto(transaction);
        BankAccountDto bankAccountDto = webClient.build()
                .get()
                .uri("http://account-management/account/getbankaccountby/" + transactionDto.getSource())
                .retrieve()
                .bodyToMono(BankAccountDto.class)
                .block(Duration.ofSeconds(5));
        BankAccountDto bankAccountDtoReceiver = webClient.build()
                .get()
                .uri("http://account-management/account/getbankaccountby/" + transactionDto.getDestination())
                .retrieve()
                .bodyToMono(BankAccountDto.class)
                .block(Duration.ofSeconds(5));
        System.out.print("bankAccountDtoReceiver : "+ bankAccountDtoReceiver);
        System.out.print("transactionDto.getDestination() : "+ transactionDto.getDestination());

        transactionDto.setBankAccountDto(bankAccountDto);
        transactionDto.setBankAccountForRecieverDto(bankAccountDtoReceiver);
        return transactionDto;
    }

    public void deleteTransaction(String id){
        Optional<Transaction> optionnaltransaction = transactionReposiroty.findById(id);
        if(optionnaltransaction.isPresent()) {
            Transaction transaction = optionnaltransaction.get();
            Optional<Invoice> optionalInvoice =  invoiceRepository.findByTransaction(transaction);
            if(optionalInvoice.isPresent()){
                Invoice invoice = optionalInvoice.get();
                invoiceRepository.delete(invoice);
            }

            TransactionDto transactionDto = iTransactionMapper.fromentityTodto(transaction);
            TransactionHistoryDto transactionHistoryDto = iTransactionHistoryMapper.fromTransactionDtoentityToTransactionHistoryDto(transactionDto);
            iTransactionHistoryService.addTransacationHistory(transactionHistoryDto);
            transactionReposiroty.delete(transaction);
        }
    }
    @Override
    public void modifyTransaction(TransactionDto transactionDto) {
        Transaction transaction = iTransactionMapper.fromDtoToentity(transactionDto);
        transactionReposiroty.save(transaction);
    }
    public List<TransactionDto> searchTransactions(String keyword) {
        List<Transaction> transactions = transactionReposiroty.findAll(TransactionSpecifications.keywordSearch(keyword));
        List<TransactionDto> transactionList = iTransactionMapper.fromentityListTodtoList(transactions);
        for (TransactionDto transactionDto: transactionList) {
            BankAccountDto bankAccountDto = webClient.build()
                    .get()
                    .uri("http://account-management/account/getbankaccountbyTitulaire/" + transactionDto.getSource())
                    .retrieve()
                    .bodyToMono(BankAccountDto.class)
                    .block(Duration.ofSeconds(5));
            BankAccountDto bankAccountDtoReceiver = webClient.build()
                    .get()
                    .uri("http://account-management/account/getbankaccountbyTitulaire/" + transactionDto.getSource())
                    .retrieve()
                    .bodyToMono(BankAccountDto.class)
                    .block(Duration.ofSeconds(5));
            transactionDto.setBankAccountDto(bankAccountDto);
            transactionDto.setBankAccountForRecieverDto(bankAccountDtoReceiver);

        }
        return transactionList;
    }


    public String getUsername() {
        Authentication authentication = securityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication instanceof JwtAuthenticationToken) {
            Jwt jwt = ((JwtAuthenticationToken) authentication).getToken();
            return (String) jwt.getClaim(principleAttribut);
        }
        throw new IllegalStateException("Could not retrieve token from SecurityContext");
    }

    public String getEmail() {
        Userdto userdto = webClient.build()
                .get()
                .uri("http://user-management/user/retrieve-user/" + getUsername())
                .retrieve()
                .bodyToMono(Userdto.class)
                .block(Duration.ofSeconds(5));
        return userdto.getEmail();
    }


}
