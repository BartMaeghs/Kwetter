/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import yolo.sjwek.kwetter.model.User;
import yolo.sjwek.kwetter.service.KwetterService;

/**
 *
 * @author Bart
 */
@Stateless
@Path("kwetterest")
public class KwetteRest {
    
    @Inject
    private KwetterService service;
    
    @GET
    @Path("user/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserResponse getUser(@PathParam("username") String username, @Context UriInfo uriinfo) {
        User user = service.findUserByName(username);
        return new UserResponse(user.getScreenName(), user.getWeb(), false, uriinfo.getRequestUri().toString());
    }
    
    @GET
    @Path("user/followers/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserResponse> getFollow(@PathParam("username") String username, @Context UriInfo uriinfo) {
        User requestee = service.findUserByName(username);
        List<UserResponse> result = new ArrayList<>();
        for (User user : service.getFollowers(requestee)) {
            List<User> followers = service.getFollowers(user);
            boolean following = followers.contains(requestee);
            result.add(new UserResponse(user.getScreenName(), user.getWeb(), following, uriinfo.getRequestUri().toString()));
        }
        return result;
    }
    
    @GET
    @Path("user/follow/{follower}/{followee}")
    public String follow(@PathParam("follower") String followerstr, @PathParam("followee") String followeestr) {
        User follower = service.findUserByName(followerstr);
        User followee = service.findUserByName(followeestr);
        service.followUser(follower, followee);
        return "Sjwek";
    }
    
    @GET
    @Path("user/unfollow/{follower}/{followee}")
    public String unfollow(@PathParam("follower") String followerstr, @PathParam("followee") String followeestr) {
        User follower = service.findUserByName(followerstr);
        User followee = service.findUserByName(followeestr);
        service.unfollowUser(follower, followee);
        return "Sjwek";
    }
}
