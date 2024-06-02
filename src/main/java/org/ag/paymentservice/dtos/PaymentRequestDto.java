package org.ag.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {

    private String orderId;
    private String currency;
    private String contact;
    private String email;
    private String phoneNumber;
    private Long amount;
}
