/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.rest;

/**
 *
 * @author Bart
 */
public class UserResponse {
    private String name;
    private String website;
    private Boolean following;
    private String url;

    public UserResponse() {
    }

    public UserResponse(String name, String website, boolean following, String url) {
        this.name = name;
        this.website = website;
        this.following = following;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }
    
    
}
