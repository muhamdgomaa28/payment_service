package com.project.dao;

import com.project.model.Business;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by muhamd gomaa on 1/28/2018.
 */

public interface  BusnessDao extends CrudRepository<Business, Integer> {

    Business findByid(int id);




}
