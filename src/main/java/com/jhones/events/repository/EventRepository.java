package com.jhones.events.repository;

import com.jhones.events.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findById(long id);

    //Custom query
    @Query(value = "select * from event s where s.name like %:keyword%", nativeQuery = true)
    List<Event> findByKeyword(@Param("keyword") String keyword);
}

