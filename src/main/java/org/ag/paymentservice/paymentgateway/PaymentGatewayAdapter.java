package org.ag.paymentservice.paymentgateway;

public interface PaymentGatewayAdapter {

    String generatePaymentLink(String orderId, String currency, String contact, String email, String phoneNumber, Long amount);

}
