/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Bart
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :uid"),
    @NamedQuery(name = "User.findByName", query = "SELECT u from User u where u.screenName = :username"),
    @NamedQuery(name = "User.getFollowers", query = "SELECT DISTINCT u FROM User u JOIN u.following f WHERE f.id = :uid"),
})
@Table(name = "KwetterUser")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String screenName;
    private String password;
    private String location;
    private String web;
    private String bio;
    @OneToMany(mappedBy = "owner")
    private List<Kweet> kweets;
    @ManyToMany
    private List<User> following;
    
    @OneToMany
    private List<Kweet> mentions;

    public User() {
    }

    public User(String screenName, String password, String location, String web, String bio) {
        this.screenName = screenName;
        this.password = password;
        this.location = location;
        this.web = web;
        this.bio = bio;
        this.kweets = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getBio() {
        return bio;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void addFollow(User follow) {
        this.following.add(follow);
    }
    
    public void removeFollow(User follow) {
        this.following.remove(follow);
    }
    
    public List<Kweet> getMentions() {
        return mentions;
    }
    
    public void addMention(Kweet kweet) {
        this.mentions.add(kweet);
    }
    
    public void addKweet(Kweet kweet) {
        this.kweets.add(kweet);
    }

    public List<Kweet> getKweets() {
        return kweets;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return true;
    }
    
    
}
