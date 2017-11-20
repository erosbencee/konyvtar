/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.rft.rest;

import hu.rft.entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author Dani
 */
@Stateless
public class UserFacade {

    @PersistenceContext(unitName = "konyvtar_serverPU")
    private EntityManager em;
  
    public List<User> findAll() {
        return em.createNativeQuery("SELECT * FROM KVT_USER").getResultList();
    }
  
    public List<User> findByName(String name) {
        return em.createNativeQuery("SELECT * FROM KVT_USER WHERE UPPER(LOGIN_NAME) LIKE ?1", hu.rft.entity.User.class).
                         setParameter(1, "%" + name.toUpperCase() + "%").getResultList();
    }
    
    public void createUser(User user) {
        
        try {
            
            UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
            
            transaction.begin();
            em.persist(user);
            transaction.commit();
        
        } catch(Exception ex) {
            
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void updateUser(User user) {
        
        try {
            
            UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
            
            transaction.begin();
            em.merge(em.find(User.class, user.getUserId()));
            transaction.commit();
        
        } catch(Exception ex) {
            
            System.out.println(ex.getMessage());
        } 
    }
    
    public void grantAdmin(String id) {
        
         try {
            
            UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
            
            transaction.begin();
            
            em.createNativeQuery("INSERT INTO KVT_ADMINS (USER_ID, GRANTED_ON) VALUES (?1, SYSDATE())")
                .setParameter(1, id)
                .executeUpdate();
            
            transaction.commit();
        
        } catch(Exception ex) {
            
            System.out.println(ex.getMessage());
        }
    }
    
    public void revokeAdmin(String id) {
        
         try {
            
            UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
            
            transaction.begin();
            
            em.createNativeQuery("DELETE FROM KVT_ADMINS WHERE USER_ID = ?1")
                .setParameter(1, id)
                .executeUpdate();
            
            transaction.commit();
        
        } catch(Exception ex) {
            
            System.out.println(ex.getMessage());
        }
    }
}
