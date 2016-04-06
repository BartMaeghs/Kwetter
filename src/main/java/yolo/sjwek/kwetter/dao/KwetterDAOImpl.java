/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
//import yolo.sjwek.kwetter.interceptor.FollowTrendInterceptor;
import yolo.sjwek.kwetter.model.Hashtag;
import yolo.sjwek.kwetter.model.Kweet;
import yolo.sjwek.kwetter.model.User;

/**
 *
 * @author Bart
 */
@Stateless
public class KwetterDAOImpl implements IKwetterDAO {

    @PersistenceContext(unitName = "KwetterDB")
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void createUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public List<User> getFollowers(User user) {
        List<User> followers;
        followers = em.createNamedQuery("User.getFollowers").setParameter("uid", user.getId()).getResultList();
        return followers;
    }

    @Override
    public User findUserById(long id) {
        return (User) em.createNamedQuery("User.findById").setParameter("uid", id).getSingleResult();
    }

    @Override
    public User findUserByName(String username) {
        try {
            return (User) em.createNamedQuery("User.findByName").setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    //@Interceptors(FollowTrendInterceptor.class)
    public void createKweet(Kweet kweet) {
        em.persist(kweet);
    }

    @Override
    public List<Kweet> getKweetsByUser(User user) {
        return em.createNamedQuery("Kweet.getKweetsByUser").setParameter("user", user).getResultList();
    }

}
