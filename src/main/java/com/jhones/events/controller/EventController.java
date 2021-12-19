package com.jhones.events.controller;

import com.jhones.events.model.Event;
import com.jhones.events.repository.EventRepository;
import com.jhones.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/createEvent", method = RequestMethod.GET)
    public String createEvent(){
        return "events/createEventForm";
    }

    @RequestMapping(value = "/createEvent", method = RequestMethod.POST)
    public String createEvent(Event event){
        eventService.save(event);
        return("redirect:/events");
    }

    @RequestMapping(value = "/events")
    public ModelAndView listEvents(){
        ModelAndView modelAndView = new ModelAndView("events/listOfEvents");
        Iterable<Event> events = eventService.findAll();
        modelAndView.addObject("events",events);
        return modelAndView;
    }

    @RequestMapping(path = {"/search"})
    public ModelAndView listEventSearched(Event event, String keyword){
        ModelAndView modelAndView = new ModelAndView("events/listOfEvents");
        if(keyword!=null){
            Iterable<Event> events = eventService.findByKeyWord(keyword);
            modelAndView.addObject("events",events);
        }else{
            Iterable<Event> events = eventService.findAll();
            modelAndView.addObject("events",events);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/updateEvent", method = RequestMethod.GET)
    public ModelAndView updateEventList(long id){
        ModelAndView modelAndView = new ModelAndView("events/updateEventForm");
        Event event = eventService.findById(id);
        modelAndView.addObject("events",event);
        return modelAndView;
    }

    @RequestMapping(value = "/updateEvent", method = RequestMethod.POST)
    public String updateEvent(Event event){
        eventService.save(event);
        return "redirect:/events";
    }

    @RequestMapping(value = "/deleteEvent")
    public String deleteEvent(long id){
        Event event = eventRepository.findById(id);
        eventService.delete(event);
        return "redirect:/events";
    }
}
