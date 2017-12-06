package hu.rft.server.controller;

import hu.rft.server.entity.Book;
import hu.rft.server.service.BookService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bs;

    @GetMapping("getall")
    public ResponseEntity<List<Book>> getAllBooks() {

        List<Book> books = bs.getAll();

        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    @GetMapping("findbyisbn/{isbn}")
    public ResponseEntity<Book> findBookByISBN(@PathVariable int isbn) {
        
        Book result = bs.findByISBN(isbn);
        
        if(result != null) {
            
            return new ResponseEntity<>(result, HttpStatus.OK);
            
        } else {
            
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
    
    @PostMapping("save")
    public ResponseEntity<String> saveBook(@RequestBody Book book) {
        
        String result = bs.save(book);
        
        if(result.isEmpty()) {
            
            return new ResponseEntity<>(HttpStatus.OK);
            
        } else {
            
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }
       
    }

    @PutMapping("delete/{isbn}")
    public ResponseEntity<String> deleteBook(@PathVariable int isbn) {
        
        if(bs.deleteBook(isbn)) {
            
            return new ResponseEntity<>(HttpStatus.OK);
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    
    @PutMapping("update")
    public ResponseEntity<String> updateBook(@RequestBody Book book) {
        
        if(bs.updateBook(book)) {
            
            return new ResponseEntity<>(HttpStatus.OK);
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.CONFLICT); 
        }
    }
    
    @PutMapping("search")
    public ResponseEntity<List<Book>> findBooks(@RequestBody Book needed) { 
        
        String title = "";
        String author = "";
        String publisher = "";
        String genre = "";
        
        if(needed.getTitle() != null)
            title = needed.getTitle();
        
        if(needed.getAuthor() != null)
            author = needed.getAuthor();
        
        if(needed.getPublisher() != null)
            publisher = needed.getPublisher();
        
        if(needed.getGenre() != null)
            genre = needed.getGenre();
        
        List<Book> result = bs.findBooks(title, author, publisher, genre);
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
