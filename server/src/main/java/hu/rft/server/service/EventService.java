package hu.rft.server.service;

import hu.rft.server.dao.EventDAO;
import hu.rft.server.entity.ActiveEvent;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    
    @Autowired
    private EventDAO ed;
    
    public List<ActiveEvent> getAll() {
        
        return ed.getAll();
    }
    
    public String save(ActiveEvent ae) {
        
        return ed.save(ae);
    }
    
    public List<ActiveEvent> getByOrganizer(int id) {
        
        return ed.getByOrganizer(id);
    }
    
    public List<ActiveEvent> getByAttendee(int id) {
        
        return ed.getByAttendee(id);
    }
    
    public boolean cancelEvent(int id) {
        
        return ed.cancelEvent(id);
    }
    
    public boolean signUp(int eventID, int userID) {
        
        return ed.signUp(eventID, userID);
    }
    
    public boolean pullBack(int eventID, int userID) {
        
        return ed.pullBack(eventID, userID);
    }
}
