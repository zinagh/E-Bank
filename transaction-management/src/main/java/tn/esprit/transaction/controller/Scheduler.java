package tn.esprit.transaction.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.transaction.dto.TransactionDto;
import tn.esprit.transaction.dto.TransactionHistoryDto;
import tn.esprit.transaction.service.ITransactionHistoryService;
import tn.esprit.transaction.service.ITransactionService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class Scheduler {
    private final ITransactionService iTransactionService;
    private final ITransactionHistoryService iTransactionHistoryService;

     @Scheduled(cron = "0 0 0 1 * *")
   // @Scheduled(cron = "0 * * * * *") //each minute
    public void executeMonthlyTask() {
        List<TransactionHistoryDto> transactionHistoryDtos = iTransactionHistoryService.getAllTransactionhistories();
        for (TransactionHistoryDto transactionHistoryDto: transactionHistoryDtos) {
            Date transactionDate = transactionHistoryDto.getDate();
            Date currentDate = new Date();
            long differenceInMillis = currentDate.getTime() - transactionDate.getTime();
            long thirtyDaysInMillis = 30L * 24 * 60 * 60 * 1000;
            if (differenceInMillis >= thirtyDaysInMillis) {
                iTransactionHistoryService.deleteTransactionHistory(transactionHistoryDto.getReference());
            }
        }
    }


    @Scheduled(cron = "0 0 0 1 1 *")
    // @Scheduled(cron = "0 * * * * *") //each minute
    public void executeYearlyTask() {
        List<TransactionDto> transactionDtoList = iTransactionService.getAllTransactions();
        for (TransactionDto transactionDto: transactionDtoList) {
            Date transactionDate = transactionDto.getDate_heure();
            Date currentDate = new Date();

            long differenceInMillis = currentDate.getTime() - transactionDate.getTime();
            long oneYearDaysInMillis = calculateOneYearDaysInMillis();
            if (differenceInMillis >= oneYearDaysInMillis) {
                iTransactionService.deleteTransaction(transactionDto.getReference());
            }
        }
        System.out.println("Yearly task executed!");
    }

    public static long calculateOneYearDaysInMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        Date oneYearAgo = calendar.getTime();
        Date currentDate = new Date();
        long differenceInMillis = currentDate.getTime() - oneYearAgo.getTime();
        return differenceInMillis;
    }




}
