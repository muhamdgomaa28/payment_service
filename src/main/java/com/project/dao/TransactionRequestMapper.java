package com.project.dao;

import com.braintreegateway.TransactionRequest;

import java.math.BigDecimal;

/**
 * Created by Davis on 17/2/3.
 */
public final class TransactionRequestMapper {
  public static TransactionRequest of(BigDecimal decimalAmount, String token) {
    return new TransactionRequest()
        .amount(decimalAmount)
        .paymentMethodToken(token)
        .options()
        .submitForSettlement(true)
        .done();
  }
}
