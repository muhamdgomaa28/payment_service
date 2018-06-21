package com.project.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.Transaction.Status;
import com.braintreegateway.TransactionRequest;

//import com.project.domain.service.CheckoutService;

import com.project.dao.CustomerDao;
import com.project.dao.PaymentDao;
import com.project.dao.TtransactionsDao;
import com.project.dao.productdao;
import com.project.model.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.services.*;

//new update
@RestController
@Api(value="checkout controller",description = "show  all braintree transactions ")

public class CheckoutController {

    @Autowired
  private  CheckoutService checkoutService;
    @Autowired
    private TtransactionsDao TtransactionsDao ;


    @Autowired
    private CustomerDao CustomerDao ;
    @Autowired
    private PaymentDao paymDao ;

    @Autowired
    private productdao productDao ;
    @Autowired
    private paymentservice d ;

    private Status[] TRANSACTION_SUCCESS_STATUSES = new Status[] {
            Transaction.Status.AUTHORIZED,
            Transaction.Status.AUTHORIZING,
            Transaction.Status.SETTLED,
            Transaction.Status.SETTLEMENT_CONFIRMED,
            Transaction.Status.SETTLEMENT_PENDING,
            Transaction.Status.SETTLING,
            Transaction.Status.SUBMITTED_FOR_SETTLEMENT
    };
    //////////////////////////////////////// new update///////////////
// for authorization and return with checout form

    @RequestMapping(value = "/api/braintree/getclienttoken", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ClientToken get_client_token() {
        // String clientToken = BraintreeGateway.clientToken().generate();
      String clientToken = checkoutService.getClientToken();

        ClientToken c = new ClientToken(clientToken);

        return c;
    }





    // create purchase
    @RequestMapping(value = "/api/braintree/createpurchase", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity postForm(@RequestBody Nonce nonce,Payment payment){
       BigDecimal decimalAmount = null;
        try {
            decimalAmount = new BigDecimal(nonce.getChargeAmount());
        } catch (NumberFormatException e) {

            System.out.println("errorr");

        }
        TransactionRequest request = new TransactionRequest()
                .amount(decimalAmount)
                .paymentMethodNonce(nonce.getNonce())
                .options()
                .submitForSettlement(true)
                .done();

        Result<Transaction> result = checkoutService.checkout(decimalAmount, nonce.getNonce());
        Transaction transaction = result.getTarget();
        System.out.println(transaction.getId());
        //save payment
        payment.setTransactionid(transaction.getId());
        payment.setAllowed(1);
        payment.setPaymentType(transaction.getCreditCard().getCardType());
        TtransactionsDao.save(payment);

        return new ResponseEntity(transaction, HttpStatus.OK);
    }
    // get cart objects to specific customer
    @RequestMapping(value="/getcarts",method=RequestMethod.GET)
    public List<Cart> getcarts(Principal p){
 return d.getallcarts(p);
    }

    // get one cart
    @RequestMapping(value="/checkout/{id}",method=RequestMethod.GET)
    public Cart getcart(Principal p,@PathVariable int id)
    {
        return d.getcart(p,id);
    }

    @RequestMapping(value="/cart/{transactionid}",method=RequestMethod.POST)
    public void post(@RequestBody Cart c,@PathVariable String transactionid,Orders order,OrderDetail orderdetail)
    {
//create order
        Product pro=productDao.findByid(c.getId().getProductid());

        Customers cc=CustomerDao.findByid(c.getId().getCustomersid());
        Payment p=paymDao.findByTransactionid(transactionid);
        order.setPayment(p);
        order.setQuantity(1);
        order.setPaid(0);
        order.setOrderDate(new Date());
        order.setPaymentDate(new Date());
        order.setSalesTax(1.0);
        order.setDeletedorder(0);
        order.setCustomers(cc);

        TtransactionsDao.save(order);

        OrderDetailId oreder=new OrderDetailId();
        oreder.setOrdersid(order.getId());
        oreder.setProductid(c.getId().getProductid());
        orderdetail.setId(oreder);
        orderdetail.setOrderNumber(order.getId());
        orderdetail.setDiscount(pro.getDiscount());
        orderdetail.setPrice(pro.getPrice());
        orderdetail.setTotal(pro.getPrice()-pro.getDiscount());
        orderdetail.setBillDate(new Date());
        orderdetail.setQuantity(c.getQuantity());
        //Product pro=productDao.findByid(c.getId().getProductid());
        orderdetail.setProduct(pro);
        orderdetail.setOrders(order);
        order.getOrderDetails().add(orderdetail);
//
        TtransactionsDao.save(orderdetail);

    }

    @RequestMapping(value="/carts/{transactionid}",method=RequestMethod.POST)
    public void postorders(@RequestBody Cart[] c,@PathVariable String transactionid,Orders order,OrderDetail orderdetail)
    {
        Customers cc=CustomerDao.findByid(c[0].getId().getCustomersid());
        Payment p=paymDao.findByTransactionid(transactionid);
        order.setPayment(p);
        order.setQuantity(c.length);
        order.setPaid(0);
        order.setOrderDate(new Date());
        order.setPaymentDate(new Date());
        order.setSalesTax(.10);
        order.setDeletedorder(0);
        order.setCustomers(cc);

        TtransactionsDao.save(order);
        OrderDetailId oreder=new OrderDetailId();
        for(int i =0;i<c.length;i++){
            Product pro=productDao.findByid(c[i].getId().getProductid());
            oreder.setOrdersid(order.getId());
            oreder.setProductid(c[i].getId().getProductid());
            orderdetail.setId(oreder);
            orderdetail.setQuantity(c[i].getQuantity());
        orderdetail.setPrice(pro.getPrice());
        orderdetail.setTotal(c[i].getQuantity()*(pro.getPrice()-pro.getDiscount()));
        orderdetail.setBillDate(new Date());

        orderdetail.setProduct(pro);
        orderdetail.setOrders(order);
        order.getOrderDetails().add(orderdetail);
//
        TtransactionsDao.save(orderdetail);

        }

    }


    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public Product getproduct(@PathVariable Integer id) {
        return productDao.findByid(id);
    }
    //get all products debend on busness
    @RequestMapping(value = "/products/bussness/{id}", method = RequestMethod.GET)
    public List<Product> get_busness_products(@PathVariable Integer id) {
        return productDao.findByBusinessId(id);
    }
    @RequestMapping(value = "/amounts", method = RequestMethod.POST)
    public Double get_amounts(@RequestBody Cart[] c)
    {
Double amount = 0.0;
        
        for(int i=0;i<c.length;i++){
          Product p = productDao.findByid(c[i].getId().getProductid());
        amount+=p.getPrice()-p.getDiscount();

        }
        return amount;
    }
//

















    //////////end of update//////////

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String root(Model model) {
//        return "redirect:checkouts";
//    }
//
//
//    @ApiOperation("get client token to begin checkout and redirect it")
//    @RequestMapping(value = "/checkouts", method = RequestMethod.GET)
//    public String checkout(Model model) {
//        // String clientToken = BraintreeGateway.clientToken().generate();
//        String clientToken = checkoutService.getClientToken();
//
//// for authorization and return with checout form
//     model.addAttribute("clientToken", clientToken);
//
//
//        return "new";
//    }
//    @ApiOperation("get info from checkoutform  and begin transaction here")
//    @RequestMapping(value = "/checkouts", method = RequestMethod.POST)
//    public String postForm(@RequestParam("amount") String amount, @RequestParam("payment_method_nonce") String nonce, Payment payment, Orders order, Model model, final RedirectAttributes redirectAttributes) {
//        BigDecimal decimalAmount;
//
//        int productid=1;
//        int customerid=1;
//        int quuntity=3;
//        int shiperid=1;
//
//
//        try {
//            decimalAmount = new BigDecimal(amount);
//        } catch (NumberFormatException e) {
//            redirectAttributes.addFlashAttribute("errorDetails", "Error: Amount is an invalid format.");
//            return "redirect:checkouts";
//        }
//        System.out.println("nonce is" +nonce);
//        TransactionRequest request = new TransactionRequest()
//                .amount(decimalAmount)
//                .paymentMethodNonce(nonce)
//                .options()
//                .submitForSettlement(true)
//                .done();
//        Result<Transaction> result = checkoutService.checkout(decimalAmount, nonce);
//// if success transaction
//        if (result.isSuccess()) {
//// save payment
//            Transaction transaction = result.getTarget();
//            payment.setTransaction_id(transaction.getId());
//            payment.setAllowed(1);
//            payment.setPaymentType(transaction.getCreditCard().getCardType());
//            TtransactionsDao.save(payment);
//            //save order
//            Customers c=CustomerDao.findByid(customerid);
//             order.setCustomers(c);
//            Shippers sh=shipersDao.findByid(shiperid);
//             order.setShippers(sh);
//             order.setPayment(payment);
//             order.setDeletedorder(0);
//             order.setOrderDate(new Date());
//             order.setQuantity((double) quuntity); //get fromproduct object
//             order.setSalesTax(12.5);
//             order.setPaymentDate(new Date());
//             order.setPaid(1);
//            TtransactionsDao.save(order);
//            // save order detail
//            return "redirect:checkouts/" + transaction.getId();
//        } else if (result.getTransaction() != null) {
//            Transaction transaction = result.getTransaction();
//            return "redirect:checkouts/" + transaction.getId();
//        } else {
//            String errorString = "";
//            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
//                errorString += "Error: " + error.getCode() + ": " + error.getMessage() + "\n";
//            }
//            redirectAttributes.addFlashAttribute("errorDetails", errorString);
//            return "redirect:checkouts";
//        }
//    }
//
//
////when succes payment occur
//    @RequestMapping(value = "/checkouts/{transactionId}",method = RequestMethod.GET)
//    public String getTransaction(@PathVariable String transactionId, Model model) {
//        Transaction transaction;
//        CreditCard creditCard;
//        Customer customer;
//
//        try {
//            //transaction = gateway.transaction().find(transactionId);
//            transaction = checkoutService.getTransactionById(transactionId);
//
//
//            creditCard = transaction.getCreditCard();
//            customer = transaction.getCustomer();
//        } catch (Exception e) {
//            System.out.println("Exception: " + e);
//            return "redirect:/checkouts";
//        }
//
//        model.addAttribute("isSuccess", Arrays.asList(TRANSACTION_SUCCESS_STATUSES).contains(transaction.getStatus()));
//        model.addAttribute("transaction", transaction);
//        model.addAttribute("creditCard", creditCard);
//        model.addAttribute("customer", customer);
//        return "show";
//    }


}
