package tn.esprit.card_management.services;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface IPaiementService {
    public PaymentIntent createPaymentIntent(Double amount
    ) throws StripeException;
}
