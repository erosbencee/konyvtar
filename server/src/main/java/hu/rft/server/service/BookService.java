
package hu.rft.server.service;

import hu.rft.server.dao.BookDAO;
import hu.rft.server.entity.Book;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    
    @Autowired
    private BookDAO bd;
    
    public List<Book> getAll() {
        
        return bd.getAll();
    }
    
    public String save(Book book) {
        
        return bd.save(book);
    }
    
    public Book findByISBN(String isbn) {
        
        return bd.findByISBN(isbn);
    }
    
    public boolean deleteBook(String isbn) {
        
        return bd.deleteBook(isbn);
    }
    
    public boolean updateBook(Book book) {
        
        return bd.updateBook(book);
    }
    
    public List<Book> findBooks(String title, String author, String publisher, String genre) {
        
        return bd.findBooks(title, author, publisher, genre);
    }
}
