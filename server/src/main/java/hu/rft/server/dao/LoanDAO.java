package hu.rft.server.dao;

import hu.rft.server.entity.Book;
import hu.rft.server.entity.ActiveLoan;
import hu.rft.server.entity.LoanHistory;
import java.math.BigInteger;
import java.time.LocalDate;
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
            
            Book b = (Book) em.createNativeQuery("SELECT * FROM KVT_BOOKS WHERE ISBN_ID = ?1", Book.class)
                              .setParameter(1, loan.getBookIsbn())
                              .getSingleResult();
            
            em.createNativeQuery("UPDATE KVT_BOOKS SET ONHAND_QTY = ?1 WHERE ISBN_ID = ?2")
              .setParameter(1, b.getOnhandQty() - loan.getQuantity())
              .setParameter(2, b.getIsbnId())
              .executeUpdate();
            
        } catch (Exception e) {
            
            result = e.getMessage();
        }
        
        return result;
    }
    
    public boolean returned(ActiveLoan loan) {
        
        int res = em.createNativeQuery("DELETE FROM KVT_ACTIVE_LOANS WHERE LOAN_ID = ?1")
                    .setParameter(1, loan.getLoanId())
                    .executeUpdate();
        
        LoanHistory hist = new LoanHistory();
        
        hist.setLoanId(loan.getLoanId());
        hist.setBookIsbn(loan.getBookIsbn());
        hist.setQuantity(loan.getQuantity());
        hist.setLoanerId(loan.getLoanerId());
        hist.setStartDate(loan.getStartDate());
        hist.setReturnDeadline(loan.getReturnDeadline());
        hist.setReturnedOn(LocalDate.now());
        
        if(loan.getReturnDeadline().isBefore(LocalDate.now())) {
            
            hist.setReturnedLate("Y");
            
        } else {
            
            hist.setReturnedLate("N");
        }
        
        em.persist(hist);
        
        em.createNativeQuery("UPDATE KVT_BOOKS SET ONHAND_QTY = ONHAND_QTY + ?1 WHERE ISBN_ID = ?2")
          .setParameter(1, loan.getQuantity())
          .setParameter(2, loan.getBookIsbn())
          .executeUpdate();
        
        return res > 0;
    }
    
    public List<ActiveLoan> getLoansForUser(int id) {
        
        return em.createNativeQuery("SELECT * FROM KVT_ACTIVE_LOANS WHERE LOANER_ID = ?1", ActiveLoan.class).setParameter(1, id).getResultList();
    }
}
