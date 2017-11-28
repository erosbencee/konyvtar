package hu.rft.model;

import java.net.URI;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class RestClient {
    
    public RestClient() {
        
    }
    
    public void registerUser(User user) {
          
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        String url = "http://localhost:8080/user";
        
        HttpEntity<User> reqEntity = new HttpEntity<>(user, headers);
        
        ResponseEntity<User[]> respEntity = rt.postForEntity(url, reqEntity, User[].class);
        
        User[] users = respEntity.getBody();
        
        for(User u : users) {
            
            System.out.println("User ID:\t" + u.getUserId());
            System.out.println("Forename:\t" + u.getForename());
            System.out.println("Surname:\t" + u.getSurname());
            System.out.println("DOB:\t" + u.getDateOfBirth().toString());
            System.out.println("Register date:\t" + u.getRegisteredOn().toString());
            System.out.println("Last login date:\t" + u.getLastLogin().toString());
        }
    }
    
    
}
