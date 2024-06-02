package org.ag.paymentservice.paymentgateway.razorpay;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import org.ag.paymentservice.paymentgateway.PaymentGatewayAdapter;
import org.json.JSONObject;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.stereotype.Component;

import java.time.*;

@Component
public class RazorPayAdapter implements PaymentGatewayAdapter {


    private RazorpayClient razorpay;

    public RazorPayAdapter(RazorpayClient razorpay) {
        this.razorpay = razorpay;
    }

    private long tomorrow(){
        // Get tomorrow's date
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        // Convert to UTC time zone (adjust if needed)
        ZonedDateTime tomorrowMidnight = tomorrow.atStartOfDay().atZone(ZoneId.systemDefault());

        // Get epoch time in milliseconds
        return tomorrowMidnight.toInstant().toEpochMilli();
    }

    @Override
    public String generatePaymentLink(String orderId, String currency, String contact, String email, String phoneNumber, Long amount) {

        try{
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount",amount);
            paymentLinkRequest.put("currency",currency);
            paymentLinkRequest.put("accept_partial",false);
            paymentLinkRequest.put("expire_by",tomorrow());
            paymentLinkRequest.put("reference_id",orderId);
            paymentLinkRequest.put("description","Payment for order #"+orderId);
            JSONObject customer = new JSONObject();
            customer.put("name",phoneNumber);
            customer.put("contact",contact);
            customer.put("email",email);
            paymentLinkRequest.put("customer",customer);
            JSONObject notify = new JSONObject();
            notify.put("sms",true);
            notify.put("email",true);
            paymentLinkRequest.put("notify",notify);
            paymentLinkRequest.put("reminder_enable",true);
           /* JSONObject notes = new JSONObject();
            notes.put("policy_name","Jeevan Bima");
            paymentLinkRequest.put("notes",notes);*/
            paymentLinkRequest.put("callback_url","https://example-callback-url.com/");
            paymentLinkRequest.put("callback_method","get");

            PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);

            return payment.get("short_url").toString();

        }catch (RazorpayException re){
            throw new RuntimeException(re);
        }

    }
}
