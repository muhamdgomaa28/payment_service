package com.project.dao;

import com.project.model.Cart;
import com.project.model.Customers;
import com.project.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by muhamd gomaa on 2/24/2018.
 */
public interface CartDao extends CrudRepository<Cart,Integer> {


List<Cart> findAllByCustomers(Customers c);

Cart findByProductAndCustomers(Product p,Customers c);



}
