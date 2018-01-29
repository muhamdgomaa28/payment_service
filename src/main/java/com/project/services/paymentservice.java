package com.project.services;

import com.project.dao.*;
import com.project.model.OrderDetail;
import com.project.model.Orders;
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
import java.util.Date;
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
    private productdao proDao;
    @Autowired
    private CustomerDao cusDao;

    @Autowired
    private shipersDao shiperdao;
    @Autowired
    private TtransactionsDao transdao;

    @Autowired
    private OrderDetailDao OrderDetailDao;
    @Autowired
    private OrderDao OrderDao;

    // delete cart
    public void deletecart(int c_id, int p_id) {
        transdao.delete_cart(c_id, p_id);
    }

    // delete cart to specific order
    public void deletecart_bus(int b_id) {
        transdao.delete_cart_bus(b_id);
    }

    // insert order
    public void insert_order_service(Orders order) {
     order.setOrderDate(new Date());

        transdao.insert_order(order);
    }

    // delete order
    public void delete_order( int id){
        OrderDao.deleteById(id);


    }

/// save the complete order when buy success in (order detail table)


    public void save_orderDetail(OrderDetail orderDetail) {

     transdao.insert_order(orderDetail);
    }



    // get all current orders
    public List<OrderDetail> all_orders(){
    return (List<OrderDetail>) OrderDetailDao.findAll();
    }



    public Transaction checkout(BigDecimal decimalAmount, String token) {

        TransactionRequest request = TransactionRequestMapper.of(decimalAmount, token);
        Result<Transaction> result = gateway.transaction().sale(request);


        return result.getTarget();
    }
}
