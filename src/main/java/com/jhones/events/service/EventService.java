package com.jhones.events.service;

import com.jhones.events.model.Event;
import com.jhones.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService{

    @Autowired
    EventRepository eventRepository;

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findAll(){
        return eventRepository.findAll();
    }

    public Event findById(long id){
        return eventRepository.findById(id);
    }

    public List<Event> findByKeyWord(String keyword){
        return eventRepository.findByKeyword(keyword);
    }

    public void delete(Event event){
        eventRepository.delete(event);
    }
}
