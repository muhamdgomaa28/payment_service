package com.project.dao;

import com.project.model.OrderDetail;
import com.project.model.Orders;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by muhamd gomaa on 1/28/2018.
 */

public interface  OrderDao extends CrudRepository<Orders, Integer> {

    //List<OrderDetail> findAll();
    @Transactional
void deleteById(int id);

}
