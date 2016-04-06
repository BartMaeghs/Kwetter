/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.service;

import yolo.sjwek.kwetter.service.KwetterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import yolo.sjwek.kwetter.dao.IHashtagDAO;
import yolo.sjwek.kwetter.dao.IKwetterDAO;
import yolo.sjwek.kwetter.model.Kweet;
import yolo.sjwek.kwetter.model.User;

/**
 *
 * @author Bart
 */
@RunWith(MockitoJUnitRunner.class)
public class KwetterServiceTest {
    
    private KwetterService service;
    @Mock
    private IKwetterDAO kwetterDAO;
    @Mock
    private IHashtagDAO hashtagDAO;
    
    public KwetterServiceTest() {
    }
    
    @Before
    public void setUp() {
        service = new KwetterService();
        service.setKwetterDAO(kwetterDAO);
        service.setHashtagDAO(hashtagDAO);
    }

    @Test
    public void testRegisterUser() throws Exception {
        User user = new User("MrTest", "123", "Hierzo", "www.mockito.org", "Le bio.");
        service.registerUser(user);
        Mockito.verify(kwetterDAO, Mockito.times(1)).createUser(user);
    }

    @Test
    public void testEditUser() throws Exception {
        User user = new User("MrTest", "123", "Hierzo", "www.mockito.org", "Le bio.");
        service.registerUser(user);
        user.setPassword("1234");
        service.editUser(user);
        Mockito.verify(kwetterDAO, Mockito.times(1)).updateUser(user);
    }

    @Test
    public void testFindUserByName() throws Exception {
        service.findUserByName("DoesNotExist");
        Mockito.verify(kwetterDAO, Mockito.times(1)).findUserByName("DoesNotExist");
    }

    @Test
    public void testFollowUser() throws Exception {
        User user1 = new User("MrTest", "123", "Hierzo", "www.mockito.org", "Le bio.");
        service.registerUser(user1);
        User user2 = new User("MrTesto", "123", "Hierzo", "www.mockito.org", "Le bio.");
        service.registerUser(user2);
        service.followUser(user1, user2);
        Mockito.verify(kwetterDAO, Mockito.times(1)).updateUser(user1);
    }

    @Test
    public void testUnfollowUser() throws Exception {
        User user1 = new User("MrTest", "123", "Hierzo", "www.mockito.org", "Le bio.");
        service.registerUser(user1);
        User user2 = new User("MrTesto", "123", "Hierzo", "www.mockito.org", "Le bio.");
        service.registerUser(user2);
        service.unfollowUser(user1, user2);
        Mockito.verify(kwetterDAO, Mockito.times(1)).updateUser(user1);
    }

    @Test
    public void testGetFollowers() throws Exception {
        User user1 = new User("MrTest", "123", "Hierzo", "www.mockito.org", "Le bio.");
        service.registerUser(user1);
        User user2 = new User("MrTesto", "123", "Hierzo", "www.mockito.org", "Le bio.");
        service.registerUser(user2);
        service.followUser(user1, user2);
        service.getFollowers(user2);
        Mockito.verify(kwetterDAO, Mockito.times(1)).getFollowers(user2);
    }

    @Test
    public void testCreateKweet() throws Exception {
        User user1 = new User("MrTest", "123", "Hierzo", "www.mockito.org", "Le bio.");
        service.registerUser(user1);
        Kweet kweet = new Kweet("test", "#test", user1);
        service.createKweet(kweet);
        Mockito.verify(kwetterDAO, Mockito.times(1)).createKweet(kweet);
    }
    
}
