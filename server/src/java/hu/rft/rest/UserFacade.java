/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.rft.rest;

import hu.rft.entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dani
 */
@Stateless
public class UserFacade {

    @PersistenceContext(unitName = "konyvtar_serverPU")
    private EntityManager em;
  
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u").getResultList();
    }
  
    public List<User> findByName(String name) {
        return em.createNativeQuery("SELECT * FROM KVT_USER WHERE UPPER(LOGIN_NAME) LIKE ?1", hu.rft.entity.User.class).
                         setParameter(1, "%" + name.toUpperCase() + "%").getResultList();
    }
}
