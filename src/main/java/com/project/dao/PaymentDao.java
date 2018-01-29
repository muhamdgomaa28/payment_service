package com.project.dao;

import com.project.model.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by muhamd gomaa on 12/8/2017.
 */
public interface  PaymentDao extends CrudRepository<Cart, Integer> {

    @Transactional
    void deleteByproduct(Product pro);

    @Transactional
    void deleteByCustomers(Customers c);
   // List<Brands> findAll();

void deleteAllByCustomersAndProduct(Customers c,Product p);

    void deleteAllByProductBusiness(Business B);






//deleteallbyprduct

}
