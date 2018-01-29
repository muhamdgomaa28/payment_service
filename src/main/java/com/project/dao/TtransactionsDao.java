package com.project.dao;

import com.project.model.Business;
import com.project.model.Customers;
import com.project.model.Orders;
import com.project.model.Product;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by muhamd gomaa on 1/25/2018.
 */

@Transactional
@Component
public class TtransactionsDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private productdao proDao;
    @Autowired
    private CustomerDao cusDao;
    @Autowired
    private BusnessDao busdao;

    @Autowired
    private PaymentDao PaymentDao;

    public void delete_cart(int cid, int pid) {
        Product pr = proDao.findByid(pid);
        Customers c = cusDao.findByid(cid);
        PaymentDao.deleteAllByCustomersAndProduct(c, pr);


        ////hql ////
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        Query q=session.createQuery("delete from Cart c where c.product= :pro and c.customers= :cus");
//        q.setEntity("pro",pr);
//        q.setEntity("cus",c);
//        System.out.println(q.executeUpdate());
//        transaction.commit();
//        session.flush();

    }

    ///////// delete cart belong to  speciffic bussness //////////
    public void delete_cart_bus(int b_id) {

        Business b = busdao.findByid(b_id);
        PaymentDao.deleteAllByProductBusiness(b);
    }

// save object
    public void insert_order(Object order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
       session.saveOrUpdate(order);
        transaction.commit();
        session.flush();


    }




}
