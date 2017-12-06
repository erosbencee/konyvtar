package hu.rft.server.dao;

import hu.rft.server.entity.Book;
import hu.rft.server.entity.ActiveLoan;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class LoanDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<ActiveLoan> getAll() {
        
        return em.createNativeQuery("SELECT * FROM KVT_ACTIVE_LOANS", ActiveLoan.class).getResultList();
    }
    
    public String save(ActiveLoan loan) {
        
        String result = "";
        
        try {
            
            em.persist(loan);
            
        } catch (Exception e) {
            
            result = e.getMessage();
        }
        
        return result;
    }
    
    public List<ActiveLoan> getLoansForUser(int id) {
        
        return em.createNativeQuery("SELECT * FROM KVT_ACTIVE_LOANS WHERE LOANER_ID = ?1", ActiveLoan.class).setParameter(1, id).getResultList();
    }
}
