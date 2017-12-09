package hu.rft.model;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class RestClient {
    
    String baseURL = "http://localhost:8080/";
    
    public RestClient() {
        
    }
    
    public void registerUser(User user) {
          
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "user/save";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(user, headers);
        
        ResponseEntity<String> respEntity = rt.postForEntity(url, reqEntity, String.class);
        
        if(respEntity.getStatusCode() == HttpStatus.CONFLICT)
            throw new RuntimeException(respEntity.getBody());
    }
    
    public User updateUser(User user) {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "user/update";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(user, headers);
        
        ResponseEntity<User> respEntity = rt.exchange(url, HttpMethod.PUT, reqEntity, User.class);
        
        return respEntity.getBody();
    }
    
    public void deleteUser(User user) {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "user/delete";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(user, headers);
        
        ResponseEntity<String> respEntity = rt.exchange(url, HttpMethod.PUT, reqEntity, String.class);
        
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
        
        String result = respEntity.getBody();
        
        if(result.equals("true")) {
            
            return true;
            
        } else {
            
            return false;
        }
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
    
    public List<Book> findBooks(Book needed) {
        
        if(needed.getTitle().isEmpty() && needed.getAuthor().isEmpty() && needed.getPublisher().isEmpty() && needed.getGenre().isEmpty()) {
            
            return getAllBooks();
        }
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "book/search";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(needed, headers);
        
        ResponseEntity<Book[]> respEntity = rt.exchange(url, HttpMethod.PUT, reqEntity, Book[].class);
        
        Book[] books = respEntity.getBody();
        
        return Arrays.asList(books);
        
    }
    
    public Book findBookByISBN(String isbn) {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "book/findbyisbn/{isbn}";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(headers);
        
        ResponseEntity<Book> respEntity = rt.exchange(url, HttpMethod.GET, reqEntity, Book.class, isbn);
        
        return respEntity.getBody();
    }
    
    public User findUserByID(int id) {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "user/findbyid/{id}";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(headers);
        
        ResponseEntity<User> respEntity = rt.exchange(url, HttpMethod.GET, reqEntity, User.class, id);
        
        return respEntity.getBody();
    }
    
    
    
    public void startLoan(ActiveLoan loan) {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        String url = baseURL + "loan/save";
        
        HttpEntity<ActiveLoan> reqEntity = new HttpEntity<>(loan, headers);
        
        ResponseEntity<String> respEntity = rt.postForEntity(url, reqEntity, String.class);
        
        if(respEntity.getStatusCode() == HttpStatus.CONFLICT)
            throw new RuntimeException();
    }
    
    public List<ActiveLoan> getLoansForUser(int userid) {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "loan/getfor/{userid}";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(headers);
        
        ResponseEntity<ActiveLoan[]> respEntity = rt.exchange(url, HttpMethod.GET, reqEntity, ActiveLoan[].class, userid);
        
        ActiveLoan[] loans = respEntity.getBody();
        
        return Arrays.asList(loans);
    }
    
    public void newEvent(ActiveEvent event) {
          
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "event/save";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(event, headers);
        
        ResponseEntity<String> respEntity = rt.postForEntity(url, reqEntity, String.class);
        
        if(respEntity.getStatusCode() == HttpStatus.CONFLICT)
            throw new RuntimeException(respEntity.getBody());
    }
    
    public List<ActiveEvent> getAllEvents() {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "event/getall";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(headers);
        
        ResponseEntity<ActiveEvent[]> respEntity = rt.exchange(url, HttpMethod.GET, reqEntity, ActiveEvent[].class);
        
        ActiveEvent[] events = respEntity.getBody();
        
        return Arrays.asList(events);
    }
    
    public List<ActiveEvent> getEventsByOrganizer(int id) {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "event/get/organizer/{id}";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(headers);
        
        ResponseEntity<ActiveEvent[]> respEntity = rt.exchange(url, HttpMethod.GET, reqEntity, ActiveEvent[].class, id);
        
        ActiveEvent[] events = respEntity.getBody();
        
        return Arrays.asList(events);
    }
    
    public List<ActiveEvent> getEventsByAttendee(int id) {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "event/get/attendee/{id}";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(headers);
        
        ResponseEntity<ActiveEvent[]> respEntity = rt.exchange(url, HttpMethod.GET, reqEntity, ActiveEvent[].class, id);
        
        ActiveEvent[] events = respEntity.getBody();
        
        return Arrays.asList(events);
    }
    
    public String signUpToEvent(int eventid, int userid) {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "event/singup/{eventid}/{userid}";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(headers);
        
        ResponseEntity<String> respEntity = rt.exchange(url, HttpMethod.GET, reqEntity, String.class, eventid, userid);
        
        if(respEntity.getStatusCode() == HttpStatus.CONFLICT)
            throw new RuntimeException(respEntity.getBody());
        
        return respEntity.getBody();
    }
    
    public String pullBackFromEvent(int eventid, int userid) {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "event/pullback/{eventid}/{userid}";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(headers);
        
        ResponseEntity<String> respEntity = rt.exchange(url, HttpMethod.GET, reqEntity, String.class, eventid, userid);
        
        return respEntity.getBody();
    }
    
    public String cancelEvent(int id) {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "event/cancel/{id}";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(headers);
        
        ResponseEntity<String> respEntity = rt.exchange(url, HttpMethod.GET, reqEntity, String.class, id);
        
        if(respEntity.getStatusCode() == HttpStatus.CONFLICT)
            throw new RuntimeException(respEntity.getBody());
        
        return respEntity.getBody();
    }
    
    public String closeEvent(ActiveEvent event) {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "event/close";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(event, headers);
        
        ResponseEntity<String> respEntity = rt.exchange(url, HttpMethod.PUT, reqEntity, String.class);
        
        if(respEntity.getStatusCode() == HttpStatus.CONFLICT)
            throw new RuntimeException(respEntity.getBody());
        
        return respEntity.getBody();
    }
    
    public String closeBorrow(ActiveLoan loan) {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "loan/close";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(loan, headers);
        
        ResponseEntity<String> respEntity = rt.exchange(url, HttpMethod.PUT, reqEntity, String.class);
        
        if(respEntity.getStatusCode() == HttpStatus.CONFLICT)
            throw new RuntimeException(respEntity.getBody());
        
        return respEntity.getBody();
    }
    
    public List<ActiveLoan> getAllBorrows() {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "loan/getall";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(headers);
        
        ResponseEntity<ActiveLoan[]> respEntity = rt.exchange(url, HttpMethod.GET, reqEntity, ActiveLoan[].class);
        
        ActiveLoan[] loans = respEntity.getBody();
        
        return Arrays.asList(loans);
    }
    
    public List<User> getAllUsers() {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "user/getall";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(headers);
        
        ResponseEntity<User[]> respEntity = rt.exchange(url, HttpMethod.GET, reqEntity, User[].class);
        
        User[] users = respEntity.getBody();
        
        return Arrays.asList(users);
    }
    
    public void newBook(Book book) {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        RestTemplate rt = new RestTemplate();
        
        rt.setErrorHandler(new MyResponseErrorHandler());
        
        String url = baseURL + "book/save";
        
        HttpEntity<?> reqEntity = new HttpEntity<>(book, headers);
        
        ResponseEntity<String> respEntity = rt.postForEntity(url, reqEntity, String.class);
        
        if(respEntity.getStatusCode() == HttpStatus.CONFLICT)
            throw new RuntimeException(respEntity.getBody());
    }
    
}
