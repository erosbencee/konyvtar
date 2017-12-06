
package hu.rft.server.dao;

import hu.rft.server.entity.Book;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class BookDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Book> getAll() {
        
        return em.createNativeQuery("SELECT * FROM KVT_BOOKS", Book.class).getResultList();
    }
    
    public String save(Book book) {
        
        String result = "";
        
        try {
            
            em.persist(book);
            
        } catch (Exception e) {
            
            result = e.getMessage();
        }
        
        return result;
    }
    
    public Book findByISBN(int isbn) {
        
        return em.find(Book.class, isbn);
    }
    
    public boolean deleteBook(int isbn) {
        
        int rows = em.createNativeQuery("DELETE FROM KVT_BOOKS WHERE ISBN_ID = ?1")
                     .setParameter(1, isbn)
                     .executeUpdate();
        
        return rows > 0;
    }
    
    public boolean updateBook(Book book) {
        
        Book updated = em.merge(book);
        
        return book.equals(updated);
    }
    
    public List<Book> findBooks(String title, String author, String publisher, String genre) {
        
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Publisher: " + publisher);
        System.out.println("Genre: " + genre);
        
        List<Book> result = em.createNativeQuery("SELECT * FROM KVT_BOOKS "
                                                + "WHERE UPPER(TITLE) LIKE UPPER(?1) "
                                                + "AND UPPER(AUTHOR) LIKE UPPER(?2) "
                                                + "AND UPPER(PUBLISHER) LIKE UPPER(?3) "
                                                + "AND UPPER(GENRE) LIKE UPPER(?4)", Book.class)
                              .setParameter(1, "%" + title + "%")
                              .setParameter(2, "%" + author + "%")
                              .setParameter(3, "%" + publisher + "%")
                              .setParameter(4, "%" + genre + "%")
                              .getResultList();
        
        return result;
    }
}
