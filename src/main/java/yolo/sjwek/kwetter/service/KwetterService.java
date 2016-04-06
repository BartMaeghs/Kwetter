/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import yolo.sjwek.kwetter.dao.IHashtagDAO;
import yolo.sjwek.kwetter.dao.IKwetterDAO;
import yolo.sjwek.kwetter.model.Hashtag;
import yolo.sjwek.kwetter.model.Kweet;
import yolo.sjwek.kwetter.model.User;
import yolo.sjwek.kwetter.util.TagParser;

/**
 *
 * @author Bart
 */
@Stateless
public class KwetterService {

    @Inject
    private IKwetterDAO kwetterDAO;
    @Inject
    private IHashtagDAO hashtagDAO;
    
    /**
     * Used for mockito testing only!
     * @param dao dao to set
     */
    public void setKwetterDAO(IKwetterDAO dao) {
        kwetterDAO = dao;
    }
    
    /**
     * Used for mockito testing only!
     * @param dao dao to set
     */
    public void setHashtagDAO(IHashtagDAO dao) {
        hashtagDAO = dao;
    }

    /**
     * Registers a new user in the kwetter application.
     * @param user User to be registered
     */
    public void registerUser(User user) {
        kwetterDAO.createUser(user);
    }

    /**
     * Updates user information in the kwetter application.
     * @param user USer to be updated
     */
    public void editUser(User user) {
        kwetterDAO.updateUser(user);
    }

    /**
     * Searches the kwetter application for a user with the given username.
     * @param username Name to find
     * @return User if found, otherwise null
     */
    public User findUserByName(String username) {
        return kwetterDAO.findUserByName(username);
    }

    /**
     * Makes one user follow another use in the kwetter application.
     * @param follower The user that will follow
     * @param followee The user that will be followed
     */
    public void followUser(User follower, User followee) {
        follower.addFollow(followee);
        kwetterDAO.updateUser(follower);
    }

    /**
     * Stops the following of one user by another user.
     * @param follower User that will stop following
     * @param followee User that will stop being followed
     */
    public void unfollowUser(User follower, User followee) {
        follower.removeFollow(followee);
        kwetterDAO.updateUser(follower);
    }

    /**
     * Gets the followers of a user.
     * @param user User whose followers to get
     * @return List containing the followers of the user
     */
    public List<User> getFollowers(User user) {
        return kwetterDAO.getFollowers(user);
    }

    /**
     * Creates a new kweet.
     * @param kweet Kweet to create
     */
    public void createKweet(Kweet kweet) {
        kwetterDAO.createKweet(kweet);
        addMentions(kweet);
        addHashtags(kweet);
    }
    
    public List<Kweet> getKweets(User user) {
        return kwetterDAO.getKweetsByUser(user);
    }
    
    public List<Hashtag> getHashtags() {
        return hashtagDAO.getHashtags();
    }

    private void addMentions(Kweet kweet) {
        List<String> mentions = TagParser.findTags('@', kweet.getContent());
        for (String mention : mentions) {
            User user = kwetterDAO.findUserByName(mention);
            if (user != null) {
                user.addMention(kweet);
                kwetterDAO.updateUser(user);
            }
        }
    }
    
    private void addHashtags(Kweet kweet) {
        List<String> tags = TagParser.findTags('#', kweet.getContent());
        for (String tag : tags) {
            tag = tag.toLowerCase();
            Hashtag hashtag;
            if (hashtagDAO.hashtagExists(tag)) {
                hashtag = hashtagDAO.getHashtagByTag(tag);
            } else {
                hashtag = new Hashtag(tag);
                hashtagDAO.createHashtag(hashtag);
            }
            hashtag.addKweet(kweet);
            hashtagDAO.updateHashtag(hashtag);
        }
    }

}
