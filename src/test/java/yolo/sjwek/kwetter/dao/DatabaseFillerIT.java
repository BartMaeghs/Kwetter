/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;
import yolo.sjwek.kwetter.model.Kweet;
import yolo.sjwek.kwetter.model.User;
import yolo.sjwek.kwetter.model.UserGroup;
import yolo.sjwek.kwetter.service.KwetterService;

/**
 *
 * @author Bart
 */
public class DatabaseFillerIT {
    
    private HashtagDAOImpl dao;
    private KwetterDAOImpl kwetterDAO;
    private EntityManager em;
    private DatabaseCleaner dbClean;
    private KwetterService service;

    public DatabaseFillerIT() {
        dao = new HashtagDAOImpl();
        kwetterDAO = new KwetterDAOImpl();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterDBTest");
        em = emf.createEntityManager();
        dao.setEm(em);
        kwetterDAO.setEm(em);
        dbClean = new DatabaseCleaner(em);
        service = new KwetterService();
        service.setHashtagDAO(dao);
        service.setKwetterDAO(kwetterDAO);
    }
    
    @Test
    public void fillDatabase() {
        dbClean.clean();
        em.getTransaction().begin();
        User sjwekie = new User("Sjwekie", "123", "Fontys", "www.sjwek.nl", "Ik heb veel sjwek!");
        service.registerUser(sjwekie);
        User testo = new User("Testo", "123", "Testistan", "www.test.com", "Ik ben een unit test :D");
        service.registerUser(testo);
        User stalko = new User("Stalko", "123", "Stalkeria", "www.stalking.com", "Ik stalk Sjwekie lolz");
        service.registerUser(stalko);
        
        em.getTransaction().commit();
        em.getTransaction().begin();
        
        service.followUser(testo, sjwekie);
        service.followUser(stalko, sjwekie);
        service.followUser(sjwekie, testo);
        
        Kweet kweet = new Kweet("Yo kwetteraars ik ben Sjwekie en ik heb veel #SJWEK !", "Fontus", sjwekie);
        service.createKweet(kweet);
        kweet = new Kweet("Ook heb ik veel #Yolo !", "Fontus", sjwekie);
        service.createKweet(kweet);
        kweet = new Kweet("Yo @Sjwekie ik volg je nu!", "Fontus", testo);
        service.createKweet(kweet);
        kweet = new Kweet("@Testo volgt mij! Coole shit yo!", "Fontus", sjwekie);
        service.createKweet(kweet);
        
        
        
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        service.followUser(stalko, testo);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        User admin = new User("admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", "Administan", "www.admin.com", "Ik ben kwetman, super snel kwetteren!");
        UserGroup group = new UserGroup("admin");
        em.persist(group);
        admin.addGroup(group);
        em.persist(admin);
        em.getTransaction().commit();
    }
    
    
}
