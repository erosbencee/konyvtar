
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
}
