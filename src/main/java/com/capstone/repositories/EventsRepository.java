package com.capstone.repositories;

import com.capstone.models.Event;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by frenchfryes on 7/9/17.
 */
public interface EventsRepository extends CrudRepository<Event, Long>{
    Event findById(Long id);
}
