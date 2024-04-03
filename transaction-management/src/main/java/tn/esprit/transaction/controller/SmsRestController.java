package tn.esprit.transaction.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.transaction.model.SmsSendRequest;
import tn.esprit.transaction.service.SmsService;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class SmsRestController {
    @Autowired
    SmsService smsService;

@PostMapping("/processSms")
    public String processSms(@RequestBody SmsSendRequest sendRequest){
        log.info("processSms Started sendRequest: "+ sendRequest.toString() );
        return smsService.sendSms("+21655891733", sendRequest.getSmsMessage());


}
}
