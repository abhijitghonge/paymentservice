package org.ag.paymentservice.services;


import org.ag.paymentservice.paymentgateway.PaymentGatewayFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentGatewayFactory paymentGatewayFactory;

    public PaymentService(PaymentGatewayFactory paymentGatewayFactory) {
        this.paymentGatewayFactory = paymentGatewayFactory;
    }

    public String initiatePayment(String orderId, String currency, String contact, String email, String phoneNumber, Long amount){
        return paymentGatewayFactory
                .getPaymentGatewayAdapter()
                .generatePaymentLink(orderId, currency, contact, email, phoneNumber, amount);
    }
}
