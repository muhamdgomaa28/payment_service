package com.project.services;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by Davis on 16/12/27.
 */
@Service
public class CheckoutService {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(CheckoutService.class);

  @Autowired
  private BraintreeGateway gateway;

  public String getClientToken() {
    String clientToken = gateway.clientToken().generate();
    return clientToken;
  }

  public Result<Transaction> checkout(BigDecimal decimalAmount, String nonce) {
    TransactionRequest request = new TransactionRequest()
        .amount(decimalAmount)
        .paymentMethodNonce(nonce)
        .options()
        .submitForSettlement(true)
        .done();
    Result<Transaction> result = gateway.transaction().sale(request);
    return result;
  }

  public Transaction getTransactionById(String transactionId) {
    return gateway.transaction().find(transactionId);
  }
}
