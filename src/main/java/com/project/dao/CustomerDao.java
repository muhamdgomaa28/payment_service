package com.project.dao;

import com.project.model.Customers;
import com.project.model.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by muhamd gomaa on 1/22/2018.
 */
public interface  CustomerDao extends CrudRepository<Customers, Integer> {

    Customers findByid(int id);




}
