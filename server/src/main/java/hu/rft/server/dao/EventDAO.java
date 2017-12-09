package hu.rft.server.dao;

import hu.rft.server.entity.ActiveEvent;
import hu.rft.server.entity.EventHistory;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class EventDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<ActiveEvent> getAll() {
        
        return em.createNativeQuery("SELECT * FROM KVT_ACTIVE_EVENTS", ActiveEvent.class).getResultList();
    }
    
    public String save(ActiveEvent ae) {
        
        String result = "";
        
        try {
            
            em.persist(ae);
            
        } catch (Exception e) {
            
            result = e.getMessage();
        }
        
        return result;
    }
    
    public List<ActiveEvent> getByOrganizer(int id) {
        
        return em.createNativeQuery("SELECT * FROM KVT_ACTIVE_EVENTS WHERE ORGANIZER_ID = ?1", ActiveEvent.class).setParameter(1, id).getResultList();
    }
    
    public List<ActiveEvent> getByAttendee(int id) {
        
        return em.createNativeQuery("SELECT * FROM KVT_ACTIVE_EVENTS "
                                  + "WHERE EVENT_ID IN (SELECT EVENT_ID FROM KVT_EVENT_ATTENDEES "
                                                     + "WHERE USER_ID = ?1)", ActiveEvent.class)
                 .setParameter(1, id)
                 .getResultList();
    }
    
    public boolean cancelEvent(int id) {
        
        int del = em.createNativeQuery("DELETE FROM KVT_ACTIVE_EVENTS WHERE EVENT_ID = ?1").setParameter(1, id).executeUpdate();
        
        if(del > 0) {
            
            //email
            
            return true;
        }
        
        return false;
    }
    
    public boolean closeEvent(ActiveEvent event) {
        
        int res = em.createNativeQuery("DELETE FROM KVT_ACTIVE_EVENTS WHERE EVENT_ID = ?1")
                    .setParameter(1, event.getEventId())
                    .executeUpdate();
        
        EventHistory hist = new EventHistory();
        
        hist.setEventId(event.getEventId());
        hist.setEventName(event.getEventName());
        hist.setOrganizerId(event.getOrganizerId());
        hist.setEventLocation(event.getEventLocation());
        hist.setEventBegan(event.getEventBegins());
        hist.setEventEnded(LocalDateTime.now());
        hist.setExpectedPpl(event.getExpectedPpl());
        hist.setAttendedPpl(event.getExpectedPpl());
        
        em.persist(hist);
        
        return res > 0;
    }
    
    public boolean signUp(int eventID, int userID) {
        
        int res = em.createNativeQuery("INSERT INTO KVT_EVENT_ATTENDEES (EVENT_ID, USER_ID, SIGNUP_DATE) VALUES (?1, ?2, SYSDATE())")
                    .setParameter(1, eventID)
                    .setParameter(2, userID)
                    .executeUpdate();
        
        res += em.createNativeQuery("UPDATE KVT_ACTIVE_EVENTS SET EXPECTED_PPL = EXPECTED_PPL + 1 WHERE EVENT_ID = ?1")
                 .setParameter(1, eventID)
                 .executeUpdate();
        
        return res > 0;
    }
    
    public boolean pullBack(int eventID, int userID) {
        
        int res = em.createNativeQuery("DELETE FROM KVT_EVENT_ATTENDEES WHERE EVENT_ID = ?1 AND USER_ID = ?2")
                    .setParameter(1, eventID)
                    .setParameter(2, userID)
                    .executeUpdate();
        
        res += em.createNativeQuery("UPDATE KVT_ACTIVE_EVENTS SET EXPECTED_PPL = EXPECTED_PPL - 1 WHERE EVENT_ID = ?1")
                 .setParameter(1, eventID)
                 .executeUpdate();
        
        return res > 0;
    }
}
