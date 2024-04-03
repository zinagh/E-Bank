package tn.esprit.transaction.model;

import lombok.Data;

@Data
public class SmsSendRequest {
    private String destinationSmsNumber;
    private String smsMessage;



}
