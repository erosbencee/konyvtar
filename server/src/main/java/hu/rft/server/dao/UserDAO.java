
package hu.rft.server.dao;

import hu.rft.server.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
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
            
            em.persist(user);
            
        } catch (Exception e) {
            
            result = e.getMessage();
        }
        
        return result;
    }
}
