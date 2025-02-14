package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain;

import com.thws.eventmanager.domain.models.Payment;
import com.thws.eventmanager.infrastructure.GraphQL.Models.PaymentGQL;

public class PaymentMapperGQL extends Mapper<Payment, PaymentGQL> {
    EnumMapper enumMapper = new EnumMapper();

    public Payment toModel(PaymentGQL paymentGQL) {
        if (paymentGQL == null) return null;
        Payment payment = new Payment();
        payment.setPaymentMethodId(paymentGQL.getPaymentMethodId());
        payment.setAmount(paymentGQL.getAmount());
        payment.setStatus(enumMapper.toModel(paymentGQL.getStatus()));
        payment.setPaymentIntentId(paymentGQL.getPaymentIntentId());
        if(paymentGQL.getId()!=null) payment.setId(Long.parseLong(paymentGQL.getId()));

        return payment;
    }

    public PaymentGQL toModelGQL(Payment payment) {
        if (payment == null) return null;
        PaymentGQL paymentGQL = new PaymentGQL();
        paymentGQL.setPaymentMethodId(payment.getPaymentMethodId());
        paymentGQL.setAmount((int)payment.getAmount());
        paymentGQL.setStatus(enumMapper.toModelGQL(payment.getStatus()));
        paymentGQL.setPaymentIntentId(payment.getPaymentIntentId());
        if(payment.getId()!=-1) paymentGQL.setId(String.valueOf(payment.getId()));

        return paymentGQL;
    }
}
