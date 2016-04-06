/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.dao;

import java.util.List;
import yolo.sjwek.kwetter.model.Hashtag;

/**
 *
 * @author Bart
 */
public interface IHashtagDAO {
    public void createHashtag(Hashtag tag);
    
    public void updateHashtag(Hashtag tag);
    
    public boolean hashtagExists(String tag);
    
    public Hashtag getHashtagByTag(String tag);
    
    public List<Hashtag> getHashtags();
}
