/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import yolo.sjwek.kwetter.model.Hashtag;

/**
 *
 * @author Bart
 */
@Stateless
public class HashtagDAOImpl implements IHashtagDAO {
    
    @PersistenceContext(unitName = "KwetterDB")
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }
    @Override
    public void createHashtag(Hashtag tag) {
        em.persist(tag);
    }

    @Override
    public void updateHashtag(Hashtag tag) {
        em.merge(tag);
    }

    @Override
    public boolean hashtagExists(String tag) {
        return em.find(Hashtag.class, tag) != null;
    }

    @Override
    public Hashtag getHashtagByTag(String tag) {
        return em.find(Hashtag.class, tag);
    }

    @Override
    public List<Hashtag> getHashtags() {
        return em.createNamedQuery("Hashtag.findAll").getResultList();    }
    
}
