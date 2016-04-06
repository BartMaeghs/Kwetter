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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Bart
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Hashtag.findAll", query = "SELECT h FROM Hashtag h")
})
public class Hashtag implements Serializable, Comparable<Object> {
    @Id
    private String hashtag;
    @OneToMany
    private List<Kweet> kweets = new ArrayList<>();
    
    public Hashtag() {}

    public Hashtag(String hashtag) {
        this.hashtag = hashtag.toLowerCase();
    }
         
    public String getHashTag() {
        return hashtag;
    }

    public void addKweet(Kweet kweet) {
        this.kweets.add(kweet);
    }
    
    public List<Kweet> getKweets() {
        return kweets;
    }
    
    public int size() {
        return kweets.size();
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.hashtag);
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
        final Hashtag other = (Hashtag) obj;
        if (!Objects.equals(this.hashtag, other.hashtag)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object o) {
        Hashtag other = (Hashtag) o;
        return this.size() - other.size();
    }
    
}
