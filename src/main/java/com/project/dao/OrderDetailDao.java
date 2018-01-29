package com.project.dao;

import com.project.model.Customers;
import com.project.model.OrderDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by muhamd gomaa on 1/28/2018.
 */

public interface  OrderDetailDao extends CrudRepository<OrderDetail, Integer> {

    //List<OrderDetail> findAll();




}
