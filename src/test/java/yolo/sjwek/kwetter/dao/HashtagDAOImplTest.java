/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import yolo.sjwek.kwetter.model.Hashtag;
import yolo.sjwek.kwetter.model.Kweet;
import yolo.sjwek.kwetter.model.User;

/**
 *
 * @author Bart
 */
public class HashtagDAOImplTest {
    
    public HashtagDAOImplTest() {
        dao = new HashtagDAOImpl();
        kwetterDAO = new KwetterDAOImpl();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterDBTest");
        em = emf.createEntityManager();
        dao.setEm(em);
        kwetterDAO.setEm(em);
        dbClean = new DatabaseCleaner(em);
    }
    
    private HashtagDAOImpl dao;
    private KwetterDAOImpl kwetterDAO;
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

    @Test
    public void testCreateHashtag() {
        Hashtag tag1 = new Hashtag("yolo");
        dao.createHashtag(tag1);
        Hashtag tag2 = dao.getHashtagByTag("yolo");
        assertEquals(tag1, tag2);
    }

    @Test
    public void testUpdateHashtag() {
        Hashtag tag1 = new Hashtag("yolo");
        User user = new User("Test", "test", "test", "Test", "Test");
        kwetterDAO.createUser(user);
        Kweet kweet = new Kweet("#yolo sjwek", "hier", user);
        kwetterDAO.createKweet(kweet);
        dao.createHashtag(tag1);
        tag1.addKweet(kweet);
        dao.updateHashtag(tag1);
        assertEquals(1, tag1.getKweets().size());
        assertEquals(kweet, tag1.getKweets().get(0));
        
    }

    @Test
    public void testHashtagExists() {
        Hashtag tag1 = new Hashtag("yolo");
        dao.createHashtag(tag1);
        assertEquals(true, dao.hashtagExists("yolo"));
        assertEquals(false, dao.hashtagExists("sjwek"));
    }
    
}
