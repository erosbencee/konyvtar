package hu.rft.server.controller;

import hu.rft.server.entity.ActiveEvent;
import hu.rft.server.service.EventService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("event")
public class EventController {
    
    @Autowired
    private EventService es;
    
    @GetMapping("getall")
    public ResponseEntity<List<ActiveEvent>> getAllActiveEvents() {

        List<ActiveEvent> events = es.getAll();

        return new ResponseEntity<>(events, HttpStatus.OK);
    }
    
    @PostMapping("save")
    public ResponseEntity<String> saveEvent(@RequestBody ActiveEvent loan) {
        
        String result = es.save(loan);
        
        if(result.isEmpty()) {
            
            return new ResponseEntity<>(HttpStatus.OK);
            
        } else {
            
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }
    }
    
    @GetMapping("get/organizer/{id}")
    public ResponseEntity<List<ActiveEvent>> getEventsByOrganizer(@PathVariable int id) {
        
        List<ActiveEvent> events = es.getByOrganizer(id);

        return new ResponseEntity<>(events, HttpStatus.OK);
    }
    
    @GetMapping("get/attendee/{id}")
    public ResponseEntity<List<ActiveEvent>> getEventsByAttendee(@PathVariable int id) {
        
        List<ActiveEvent> events = es.getByAttendee(id);

        return new ResponseEntity<>(events, HttpStatus.OK);
    }
    
    @GetMapping("cancel/{id}")
    public ResponseEntity<String> cancelEvent(@PathVariable int id) {
        
        boolean success = es.cancelEvent(id);
        
        if(success) {
            
            return new ResponseEntity<>("Az esemény törlésre került!", HttpStatus.OK);
        
        } else {
            
            return new ResponseEntity<>("Az esemény törlése sikertelen volt! Biztosan létezik?", HttpStatus.CONFLICT);
        }
    }
    
    @GetMapping("singup/{eventid}/{userid}")
    public ResponseEntity<String> signUp(@PathVariable int eventid, @PathVariable int userid) {
        
        boolean success = es.signUp(eventid, userid);
        
        if(success) {
            
            return new ResponseEntity<>("Sikeres jelentkezés!", HttpStatus.OK);
        
        } else {
            
            return new ResponseEntity<>("A jelentkezés sikertelen volt!", HttpStatus.CONFLICT);
        }
    }
    
    @GetMapping("pullback/{eventid}/{userid}")
    public ResponseEntity<String> pullBack(@PathVariable int eventid, @PathVariable int userid) {
        
        boolean success = es.pullBack(eventid, userid);
        
        if(success) {
            
            return new ResponseEntity<>("Sikeresen lejelentkeztél!", HttpStatus.OK);
        
        } else {
            
            return new ResponseEntity<>("Hiba a lejelentkezés során!", HttpStatus.CONFLICT);
        }
    }
    
    @PutMapping("close")
    public ResponseEntity<String> closeEvent(@RequestBody ActiveEvent event) {
        
        boolean success = es.closeEvent(event);
        
        if(success) {
            
            return new ResponseEntity<>("A rendezvény lezárult!", HttpStatus.OK);
        
        } else {
            
            return new ResponseEntity<>("Hiba a lezárás során!", HttpStatus.CONFLICT);
        }
    }
}
