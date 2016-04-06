/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.batch;

import java.io.Serializable;
import java.util.List;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.batch.api.chunk.ItemWriter;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import yolo.sjwek.kwetter.dao.HashtagDAOImpl;
import yolo.sjwek.kwetter.dao.IKwetterDAO;
import yolo.sjwek.kwetter.dao.KwetterDAOImpl;
import yolo.sjwek.kwetter.model.Kweet;
import yolo.sjwek.kwetter.model.User;
import yolo.sjwek.kwetter.service.KwetterService;

/**
 *
 * @author Bart
 */
@Named
public class ImportKweetWriter extends AbstractItemWriter {

    @Inject
    KwetterService service;
    //EntityManager em;

    public ImportKweetWriter() {
    }

    @Override
    public void writeItems(List list) {
        //setService();
        //em.getTransaction().begin();
        System.out.println("writeItems: " + list);
        for (Object object : list) {
            ImportedKweet imported = (ImportedKweet) object;
            if (imported.getScreenName() == null) {
                continue;
            }

            User user = getUser(imported);
            Kweet kweet = new Kweet(imported.getContent(), imported.getLocation(), user);
            service.createKweet(kweet);

            System.out.println("Succesfully imported a kweet!");
        }
        //em.flush();
        //em.getTransaction().commit();
    }

    private User getUser(ImportedKweet imported) {
        User user = service.findUserByName(imported.getScreenName());
        if (user == null) {
            user = new User(imported.getScreenName(), "admin", "", "", "");
            service.registerUser(user);
        }
        return user;
    }
    
    /**
    private void setService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterDBBatch");
        em = emf.createEntityManager();
        KwetterDAOImpl kwetterDAO = new KwetterDAOImpl();
        kwetterDAO.setEm(em);
        HashtagDAOImpl hastagDAO = new HashtagDAOImpl();
        hastagDAO.setEm(em);
        service = new KwetterService();
        service.setHashtagDAO(hastagDAO);
        service.setKwetterDAO(kwetterDAO);
    }
    */

}
