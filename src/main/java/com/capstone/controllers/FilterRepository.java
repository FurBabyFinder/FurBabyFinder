package com.capstone.controllers;

import com.capstone.models.Filter;
import com.capstone.models.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by melodytempleton on 7/2/17.
 */
public interface FilterRepository extends CrudRepository<Filter, Long> {

    public List<Pet> findAllByFilterName(String name);

}
