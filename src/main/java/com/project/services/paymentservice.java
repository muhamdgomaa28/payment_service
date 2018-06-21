package com.project.services;

import com.project.dao.*;
import com.project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by muhamd gomaa on 12/7/2017.
 */

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Davis on 16/12/27.
 */
@Service
public class paymentservice {
    /**
     * log.
     */
    private static final Logger LOG = LoggerFactory.getLogger(paymentservice.class);

    @Autowired
    private BraintreeGateway gateway;

    @Autowired
    private PaymentDao pd;
    @Autowired
    private CartDao cartdao;
    @Autowired
    private CustomerDao customerdao;

    @Autowired
    private productdao proDao;
    @Autowired
    private CustomerDao cusDao;

//    @Autowired
//    private shipersDao shiperdao;
    @Autowired
    private TtransactionsDao transdao;

    @Autowired
    private OrderDetailDao OrderDetailDao;
    @Autowired
    private OrderDao OrderDao;
//////////////new update////////

    public List<Cart> getallcarts(Principal p){
        //Change username
        Customers c =customerdao.findByName("mohamed");
        List<Cart> carts = (List<Cart>) cartdao.findAllByCustomers(c);
        return  carts;

    }
    public Cart getcart(Principal p,int id){
                   //  princepal = p.getname >> (mohamed)for test;
        Customers c =customerdao.findByName("mohamed");
        Product pro=proDao.findByid(id);
        Cart cart=cartdao.findByProductAndCustomers(pro,c);
        System.out.println(pro.getProductName());
        System.out.println(c.getName());

        return  cart;

    }





    ////////// end of update////////////
    // delete cart
  //  public void deletecart(int c_id, int p_id) {
    //    transdao.delete_cart(c_id, p_id);
    //}

    // delete cart to specific order
//    public void deletecart_bus(int b_id) {
//        transdao.delete_cart_bus(b_id);
//    }

    // insert order
//    public void insert_order_service(Orders order) {
//     order.setOrderDate(new Date());
//
//        transdao.save(order);
//    }

    // delete order
//    public void delete_order( int id){
//        OrderDao.deleteById(id);
//
//
//    }

/// save the complete order when buy success in (order detail table)


//    public void save_orderDetail(OrderDetail orderDetail) {
//
//     transdao.save(orderDetail);
//    }



    // get all current orders
//    public List<OrderDetail> all_orders(){
//    return (List<OrderDetail>) OrderDetailDao.findAll();
//    }
//
//

    public Transaction checkout(BigDecimal decimalAmount, String token) {

        TransactionRequest request = TransactionRequestMapper.of(decimalAmount, token);
        Result<Transaction> result = gateway.transaction().sale(request);


        return result.getTarget();
    }
}
