package hu.rft.model;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


public class RestClient {
    
    String baseURL = "http://localhost:8080/";
    
    public RestClient() {
        
    }
    
    public void registerUser(User user) {
          
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        String url = baseURL + "user/save";
        
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
    
    public User loginUser(String userName, String password) {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "user/login/{username}/{password}";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(headers);
        
        ResponseEntity<User> respEntity = rt.exchange(url, HttpMethod.GET, reqEntity, User.class, userName, password);
        
        System.out.println(respEntity.getBody() == null);
        
        if(respEntity.getStatusCode() != HttpStatus.OK) {
            
            throw new RuntimeException("A megadott felhasználónév-jelszó páros helytelen!");
            
        } else {
            
            return respEntity.getBody();
        }
    }
    
    public boolean isAdmin(int id) {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "user/isadmin/{id}";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(headers);
        
        ResponseEntity<String> respEntity = rt.exchange(url, HttpMethod.GET, reqEntity, String.class, id);
        
        return respEntity.getStatusCodeValue() == HttpStatus.OK.value();
    }
    
    public List<Book> getAllBooks() {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "book/getall";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(headers);
        
        ResponseEntity<Book[]> respEntity = rt.exchange(url, HttpMethod.GET, reqEntity, Book[].class);
        
        Book[] books = respEntity.getBody();
        
        return Arrays.asList(books);
        
    }
    
    public List<Book> findBooks(String title, String author, String publisher, String genre) {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "book/search/{title}/{author}/{publisher}/{genre}";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(headers);
        
        ResponseEntity<Book[]> respEntity = rt.exchange(url, HttpMethod.GET, reqEntity, Book[].class, title, author, publisher, genre);
        
        Book[] books = respEntity.getBody();
        
        return Arrays.asList(books);
        
    }
    
    
}
