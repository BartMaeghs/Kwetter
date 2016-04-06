/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package yolo.sjwek.kwetter.dao;

import java.util.List;
import yolo.sjwek.kwetter.model.Kweet;
import yolo.sjwek.kwetter.model.User;

/**
 *
 * @author Bart
 */
public interface IKwetterDAO {
    
    public void createUser(User user);
    
    public void updateUser(User user);
    
    public List<User> getFollowers(User user);
    
    public User findUserById(long id);

    public User findUserByName(String username);
    
    //maybe split here?
    public void createKweet(Kweet kweet);
    
    public List<Kweet> getKweetsByUser(User user);
}
