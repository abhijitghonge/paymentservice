package org.ag.paymentservice.controllers;

import org.ag.paymentservice.dtos.PaymentRequestDto;
import org.ag.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public String initiatePayment(@RequestBody PaymentRequestDto paymentRequestDto) {

        return paymentService.initiatePayment(paymentRequestDto.getOrderId(),
                paymentRequestDto.getCurrency(),
                paymentRequestDto.getContact(),
                paymentRequestDto.getEmail(),
                paymentRequestDto.getPhoneNumber(),
                paymentRequestDto.getAmount());
    }


}
