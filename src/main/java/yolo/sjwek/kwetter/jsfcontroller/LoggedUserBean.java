/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.jsfcontroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import yolo.sjwek.kwetter.model.Hashtag;
import yolo.sjwek.kwetter.model.Kweet;
import yolo.sjwek.kwetter.model.User;
import yolo.sjwek.kwetter.service.KwetterService;

/**
 *
 * @author Bart
 */
@ManagedBean
@RequestScoped
public class LoggedUserBean {
    @Inject
    private KwetterService service;
    
    @ManagedProperty(value = "#{param.user}")
    private String username;
    private User user;
    
    private String kweetContent;

    public String getKweetContent() {
        return kweetContent;
    }

    public void setKweetContent(String kweetContent) {
        this.kweetContent = kweetContent;
    }

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
    
    public List<Kweet> getTimelineKweets() {
        List<Kweet> kweets = new ArrayList<>();
        for (User user : getUser().getFollowing()) {
            kweets.addAll(service.getKweets(user));
        }
        kweets.addAll(service.getKweets(user));
        return kweets;
    }
    
    public void submitKweet() {
        Kweet kweet = new Kweet(kweetContent, "Fontys", getUser());
        service.createKweet(kweet);
    }
    
    public List<Hashtag> getHashtags() {
        List<Hashtag> tags = service.getHashtags();
        Collections.sort(tags);
        return tags;
    }
}
