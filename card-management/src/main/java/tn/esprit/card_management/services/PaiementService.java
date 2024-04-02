package tn.esprit.card_management.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaiementService implements IPaiementService{
    @Value("${stripe.secretKey}")
    private String stripeSecretKey;
    public PaymentIntent createPaymentIntent(Double amount) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        PaymentIntentCreateParams createParams = PaymentIntentCreateParams.builder()
                .setCurrency("usd")
                .setAmount((long) (amount * 100)) // Convert amount to cents
                .build();

        return PaymentIntent.create(createParams);
    }
}
