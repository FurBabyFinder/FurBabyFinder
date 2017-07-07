package com.capstone.repositories;

import com.capstone.models.Filter;
import com.capstone.models.Pet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by melodytempleton on 7/2/17.
 */
public interface FilterRepository extends CrudRepository<Filter, Long> {

    public List<Filter> findAllByFilterName(String name);

    @Query("SELECT id FROM Filter WHERE filter_name = :passFilter")
    public Long findFilterIDByFilterName(@Param("passFilter") String passFilter);

    public List<Filter> findAllByOrderByIdAsc ();


    public Filter findByFilterName(String name);

    public List<Filter> findAllByPetCollection(Collection<Pet> petCollection);



}
