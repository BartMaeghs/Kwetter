/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.dao;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import yolo.sjwek.kwetter.model.Hashtag;
import yolo.sjwek.kwetter.model.Kweet;
import yolo.sjwek.kwetter.model.User;

/**
 *
 * @author Bart
 */
public class DatabaseCleaner {
    private static final Class<?>[] ENTITY_TYPES = {
        Hashtag.class,
        Kweet.class,
        User.class
    };
    
    private final EntityManager em;

    public DatabaseCleaner(EntityManager entityManager) {
        em = entityManager;
    }

    public void clean() {
        em.getTransaction().begin();

        for (Class<?> entityType : ENTITY_TYPES) {
            deleteEntities(entityType);
        }
        em.getTransaction().commit();
    }

    private void deleteEntities(Class<?> entityType) {
        em.createQuery("delete from " + getEntityName(entityType)).executeUpdate();
    }

    protected String getEntityName(Class<?> clazz) {
        EntityType et = em.getMetamodel().entity(clazz);
        return et.getName();
    }
}
