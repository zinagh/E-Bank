package tn.esprit.card_management.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.card_management.model.Paiement;
import tn.esprit.card_management.repository.PaiementRepository;
import tn.esprit.card_management.services.IPaiementService;
import tn.esprit.card_management.services.PaiementService;

@RestController





public class PaiementController {
    @Autowired
    private PaiementRepository paiementRepository;
    @Autowired
    private IPaiementService paymentService;
    @PostMapping("/create-payment-intent")
    public String createPaymentIntent(@RequestParam(value = "amount") Double amount) {
        try {
            PaymentIntent paymentIntent = paymentService.createPaymentIntent(amount);
            Paiement paiement=new Paiement();
            paiement.setStripePaymentIntentId(paymentIntent.getId());
            paiement.setMontant(amount);
            paiementRepository.save(paiement);

            return paymentIntent.toJson();
        } catch (StripeException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
            return "Error creating PaymentIntent";
        }
    }
}