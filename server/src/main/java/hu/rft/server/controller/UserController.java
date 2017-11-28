package hu.rft.server.controller;

import java.util.List;

import hu.rft.server.entity.User;
import hu.rft.server.service.UserService;
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
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<User>> saveUser(@RequestBody User user) {
        
        us.save(user);
        
        List<User> users = us.getAll();
        
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
