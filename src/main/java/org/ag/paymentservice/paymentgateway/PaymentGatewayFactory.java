package org.ag.paymentservice.paymentgateway;

import org.ag.paymentservice.paymentgateway.razorpay.RazorPayAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayFactory {

    @Value("${payment.adapter.gateway}")
    private String paymentGateway;

    private final RazorPayAdapter razorPayAdapter;

    public PaymentGatewayFactory(RazorPayAdapter razorPayAdapter) {
        this.razorPayAdapter = razorPayAdapter;
    }

    public PaymentGatewayAdapter getPaymentGatewayAdapter() {
        if(paymentGateway.equals("RAZORPAY")){
            return razorPayAdapter;
        }

        return null;

    }
}
