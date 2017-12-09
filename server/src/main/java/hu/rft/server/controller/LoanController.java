package hu.rft.server.controller;

import hu.rft.server.entity.ActiveLoan;
import hu.rft.server.service.LoanService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("loan")
public class LoanController {
    
    @Autowired
    private LoanService ls;
    
    @GetMapping("getall")
    public ResponseEntity<List<ActiveLoan>> getAllActiveLoans() {

        List<ActiveLoan> loans = ls.getAll();

        return new ResponseEntity<>(loans, HttpStatus.OK);
    }
    
    @PostMapping("save")
    public ResponseEntity<String> saveLoan(@RequestBody ActiveLoan loan) {
        
        String result = ls.save(loan);
        
        if(result.isEmpty()) {
            
            return new ResponseEntity<>(HttpStatus.OK);
            
        } else {
            
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }
    }
    
    @GetMapping("getfor/{userid}")
    public ResponseEntity<List<ActiveLoan>> getLoansForUser(@PathVariable int userid) {
        
        List<ActiveLoan> result = ls.getLoansForUser(userid);
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PutMapping("close")
    public ResponseEntity<String> closeLoan(@RequestBody ActiveLoan loan) {
        
        boolean success = ls.returned(loan);
        
        if(success) {
            
            return new ResponseEntity<>("A kölcsönzés lezárult!", HttpStatus.OK);
        
        } else {
            
            return new ResponseEntity<>("Hiba a lezárás során!", HttpStatus.CONFLICT);
        }
    }
}
