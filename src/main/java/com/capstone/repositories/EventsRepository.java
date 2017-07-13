package com.capstone.repositories;

import com.capstone.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by frenchfryes on 7/9/17.
 */
@Repository
public interface EventsRepository extends CrudRepository<Event, Long>{
     void deleteEvent(long id);

}


