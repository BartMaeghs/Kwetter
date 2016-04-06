/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.Assert;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import yolo.sjwek.kwetter.model.Kweet;
import yolo.sjwek.kwetter.model.User;

/**
 *
 * @author Bart
 */
public class KwetterDAOImplTest {

    public KwetterDAOImplTest() {
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

    @Test
    public void testCreateUser() throws Exception {
        Assert.assertTrue(true);
        User createdUser = new User("MrTest", "123", "Hier", "www.sjwek.com", "Is this the real life?");
        dao.createUser(createdUser);
        User foundUser = dao.findUserById(createdUser.getId());
        assertEquals(createdUser, foundUser);
    }

    @Test
    public void testCreateKweet() throws Exception {
        User user = new User("MrTest", "123", "Hier", "www.sjwek.com", "Is this the real life?");
        dao.createUser(user);
        Kweet kweet1 = new Kweet("Ik heb veel sjwek, jij ook @MrTest ?", "Hierzo", user);
        dao.createKweet(kweet1);
        Kweet kweet2 = new Kweet("Yolo trolololo", "Hierzo", user);
        dao.createKweet(kweet2);
        
        em.getTransaction().commit();
        
        int kweetCount = dao.getKweetsByUser(user).size();
        assertEquals(2, kweetCount);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User("MrTest", "123", "Hier", "www.sjwek.com", "Is this the real life?");
        dao.createUser(user);
        user.setScreenName("Extra Sjwek");
        dao.updateUser(user);
        User found = dao.findUserByName("Extra Sjwek");
        assertEquals(user, found);
    }

    @Test
    public void testGetFollowers() throws Exception {
        User followee = new User("Followee", "123", "Hier", "www.sjwek.com", "Le Bio Treant");
        dao.createUser(followee);
        User follower = new User("Follower", "123", "Hier", "www.sjwek.com", "Le Bio Treant");
        dao.createUser(follower);
        follower.addFollow(followee);
        dao.updateUser(follower);
        
        List<User> following = follower.getFollowing();
        assertEquals(1, following.size());
        assertEquals(followee, following.get(0));
        
        List<User> followers = dao.getFollowers(followee);
        assertEquals(1, followers.size());
        assertEquals(follower, followers.get(0));
    }
    
}
