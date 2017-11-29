
package hu.rft.server.dao;

import hu.rft.server.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class UserDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<User> getAll() {
        
        return em.createNativeQuery("SELECT * FROM KVT_USER", User.class).getResultList();
    }
    
    public String save(User user) {
        
        String result = "";
        
        try {
            
            em.persist(em);
            
        } catch (Exception e) {
            
            result = e.getMessage();
        }
        
        return result;
    }
    
    public User findByID(int id) {
        
        return em.find(User.class, id);
    }
    
    public boolean isAdmin(int id) {
        
        int occurence = em.createNativeQuery("SELECT COUNT(*) FROM KVT_ADMINS WHERE USER_ID = ?1")
                          .setParameter(1, id)
                          .getFirstResult();
        
        return occurence > 0;
    }
    
    public boolean grantAdmin(int id) {
        
        User user = em.find(User.class, id);
        
        if(user == null)
            return false;
        
        int rows = em.createNativeQuery("INSERT INTO KVT_ADMINS (USER_ID, LOGIN_NAME, GRANTED_ON) VALUES (?1, ?2, ?3)")
                     .setParameter(1, id)
                     .setParameter(2, user.getLoginName())
                     .setParameter(3, LocalDateTime.now())
                     .executeUpdate();
          
        return rows > 0;        
    }
    
    public boolean revokeAdmin(int id) {
        
        int rows = em.createNativeQuery("DELETE FROM KVT_ADMINS WHERE USER_ID = ?1")
                     .setParameter(1, id)
                     .executeUpdate();
        
        return rows > 0;
    }
    
    public User login(String userName, String password) {
        
        User user;
        
        try {
            
            user = (User) em.createNativeQuery("SELECT * FROM KVT_USER "
                                                  + "WHERE LOGIN_NAME = ?1 "
                                                  + "AND PASSWORD = ?2", User.class)
                             .setParameter(1, userName)
                             .setParameter(2, password)
                             .getSingleResult();
            
            em.createNativeQuery("UPDATE KVT_USER SET LAST_LOGIN = CURRENT_TIMESTAMP() WHERE USER_ID = ?1")
              .setParameter(1, user.getUserId())
              .executeUpdate();
            
            em.refresh(user);
            
        } catch(NoResultException nre) {
            
            return null;
        }
        
        return user;
    }
}
