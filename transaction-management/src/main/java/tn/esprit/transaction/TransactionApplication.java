package tn.esprit.transaction;

import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Month;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "tn.esprit.transaction.repository")
public class TransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionApplication.class, args);
	}
	//@Autowired
	//private EmailService emailService ;
	//@EventListener(ApplicationReadyEvent.class)
	//public void SendMail(){
	//	emailService.sendEmail( "khiari.linda@esprit.tn", "transaction" , "transaction_avec_succes" );

	//}


	}





