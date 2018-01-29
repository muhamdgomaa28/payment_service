//package com.project.controller;
//
///**
// * Created by muhamd gomaa on 12/8/2017.
// */
//
//        import com.braintreegateway.Transaction;
//
////        import io.davis.application.model.CreditCart;
////        import io.davis.application.model.action.AddCreditCartAction;
////        import io.davis.domain.service.PaymentService;
////        import io.swagger.annotations.ApiOperation;
//
//        import com.project.services.paymentservice;
//        import org.slf4j.Logger;
//        import org.slf4j.LoggerFactory;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.web.bind.annotation.PostMapping;
//        import org.springframework.web.bind.annotation.RequestParam;
//        import org.springframework.web.bind.annotation.RestController;
//
//        import java.math.BigDecimal;
//
///**
// * Created by Davis on 16/12/27.
// */
//@RestController
//public class PaymentController {
//    /**
//     * log.
//     */
//    private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);
//
//    @Autowired
//    private paymentservice paymentService;
//
//
//    //@ApiOperation("checkout")
//    @PostMapping("/payment")
//    public Transaction checkout(@RequestParam String amount, @RequestParam
//            String token) {
//        LOG.debug("enter checkout, amount is : {}, token is : {}", amount, token);
//        BigDecimal decimalAmount = null;
//        try {
//            decimalAmount = new BigDecimal(amount);
//        } catch (NumberFormatException e) {
//            LOG.debug("some thing wrong");
//        }
//        Transaction result = paymentService.checkout(decimalAmount, token);
//        LOG.debug("end checkout, result is : {}", result);
//        return result;
//    }
//}
