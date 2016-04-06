/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.jsfcontroller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import yolo.sjwek.kwetter.model.User;
import yolo.sjwek.kwetter.service.KwetterService;

/**
 *
 * @author Bart
 */
@ManagedBean
@RequestScoped
public class UserBean implements Serializable {
    @Inject
    private KwetterService service;
    
    @ManagedProperty(value = "#{param.user}")
    private String username;
    private User user;
    private List<User> followers;
    private int mode;
    
    public void setMode(int i) {
        mode = i;
    }
    public UserBean() {}

    public String getUsername() {
        if (username == null || username.equals("")) {
            username = "Sjwekie";
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        if (user == null) {
            user = service.findUserByName(getUsername());
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getFollowers() {
        if (followers == null) {
            followers = service.getFollowers(getUser());
        }
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
    
    public List<User> getFollowingers() {
        if (mode == 0) {
            return getUser().getFollowing();
        } else {
            return getFollowers();
        }
    }
    
    
}
