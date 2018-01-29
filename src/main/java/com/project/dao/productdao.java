package com.project.dao;

import com.project.model.Business;
import com.project.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by muhamd gomaa on 12/8/2017.
 */
    public interface  productdao extends CrudRepository<Product, Integer> {



    Product findByid(int id);

   // List<Product> findProductsByBusiness(Business bus);
void  deleteAllByBusiness(Business bus);

}
