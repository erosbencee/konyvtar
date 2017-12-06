package hu.rft.server.service;

import hu.rft.server.dao.LoanDAO;
import hu.rft.server.entity.ActiveLoan;
import hu.rft.server.entity.Book;
import hu.rft.server.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
    
    @Autowired
    private LoanDAO ld;
    
    public List<ActiveLoan> getAll() {
        
        return ld.getAll();
    }
    
    public String save(ActiveLoan loan) {
        
        return ld.save(loan);
    }
    
    public List<ActiveLoan> getLoansForUser(int id) {
        
        return ld.getLoansForUser(id);
    }
}
