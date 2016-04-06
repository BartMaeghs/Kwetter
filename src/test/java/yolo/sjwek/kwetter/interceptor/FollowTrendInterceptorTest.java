/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.interceptor;

import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import yolo.sjwek.kwetter.dao.DatabaseCleaner;
import yolo.sjwek.kwetter.dao.KwetterDAOImpl;
import yolo.sjwek.kwetter.model.Kweet;
import yolo.sjwek.kwetter.model.User;

/**
 *
 * @author Bart
 */
public class FollowTrendInterceptorTest {
    
    public FollowTrendInterceptorTest() {
        dao = new KwetterDAOImpl();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterDBTest");
        em = emf.createEntityManager();
        dao.setEm(em);
        dbClean = new DatabaseCleaner(em);
    }
    
    private KwetterDAOImpl dao;
    private EntityManager em;
    private DatabaseCleaner dbClean;

    @Before
    public void setUp() {
        dbClean.clean();
        em.getTransaction().begin();
    }
    
    @After
    public void tearDown() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    @Ignore
    @Test
    public void testVetCoolToHardDik() throws Exception {
        User user = new User("Test", "Test", "test", "test", "test");
        dao.createUser(user);
        String content = "Dit is een vet coole kweet!";
        Kweet kweet = new Kweet(content, "hier", user);
        dao.createKweet(kweet);
        assertEquals("Dit is een dik harde kweet!", kweet.getContent());
    }
    
}
