
package hu.rft.server.service;

import hu.rft.server.dao.UserDAO;
import hu.rft.server.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserDAO ud;
    
    public List<User> getAll() {
        
        return ud.getAll();
    }
    
    public String save(User user) {
        
        return ud.save(user);
    }
    
    public User findByID(int id) {
        
        return ud.findByID(id);
    }
    
    public boolean isAdmin(int id) {
        
        return ud.isAdmin(id);
    }
    
    public boolean grantAdmin(int id) {
        
        return ud.grantAdmin(id);
    }
    
    public boolean revokeAdmin(int id) {
        
        return ud.revokeAdmin(id);
    }
    
    public User login(String userName, String password) {
        
        return ud.login(userName, password);
    }
    
    public User update(User user) {
        
        return ud.update(user);
    }
    
    public void delete(User user) {
        
        ud.delete(user);
    }
}
