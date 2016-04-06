/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package yolo.sjwek.kwetter.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Bart
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Kweet.getKweetsByUser", query = "SELECT k FROM Kweet k WHERE k.owner = :user")
})
public class Kweet implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String location;
    private String content;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date postDate;
    @ManyToOne
    private User owner;
    
    public Kweet() {
        
    }
    
    public Kweet(String content, String location, User owner) {
        this.content = content;
        this.location = location;
        this.owner = owner;
        this.postDate = new Date();
    }

    public long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return postDate;
    }

    public User getOwner() {
        return owner;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Kweet other = (Kweet) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
