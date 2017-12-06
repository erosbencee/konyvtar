package hu.rft.server.controller;

import java.util.List;

import hu.rft.server.entity.User;
import hu.rft.server.service.UserService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService us;

    @GetMapping("getall")
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = us.getAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    @GetMapping("findbyid/{id}")
    public ResponseEntity<User> findUserByID(@PathVariable int id) {
        
        User result = us.findByID(id);
        
        if(result != null) {
            
            return new ResponseEntity<>(result, HttpStatus.OK);
            
        } else {
            
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
    
    @PostMapping("save")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        
        String result = us.save(user);
        
        if(result.isEmpty()) {
            
            return new ResponseEntity<>(HttpStatus.OK);
            
        } else {
            
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }
       
    }
    
    @GetMapping("login/{username}/{password}")
    public ResponseEntity<User> loginUser(@PathVariable("username") String userName, @PathVariable("password") String password) {
        
        User user = us.login(userName, password);
        
        if(user != null) {
            
            return new ResponseEntity<>(user, HttpStatus.OK);
            
        } else {
            
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
    
    @GetMapping("isadmin/{id}")
    public ResponseEntity<String> isAdminUser(@PathVariable int id) {
        
        if(us.isAdmin(id)) {
            
            return new ResponseEntity<>("true", HttpStatus.OK);
            
        } else {
            
            return new ResponseEntity<>("false", HttpStatus.CONFLICT);
        }
    }
    
    @PutMapping("grantadmin/{id}")
    public ResponseEntity<String> grantAdminRights(@PathVariable int id) {
        
        if(us.grantAdmin(id)) {
            
            return new ResponseEntity<>(HttpStatus.OK);
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    
    @PutMapping("revokeadmin/{id}")
    public ResponseEntity<String> revokeAdminRights(@PathVariable int id) {
        
        if(us.revokeAdmin(id)) {
            
            return new ResponseEntity<>(HttpStatus.OK);
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
