/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.batch;

import java.util.Date;

/**
 *
 * @author Bart
 */
public class ImportedKweet {
    
    private String screenName;
    private String content;
    private String location;
    private Date time;

    public ImportedKweet(String screenname, String content, String location, Date time) {
        this.screenName = screenname;
        this.content = content;
        this.location = location;
        this.time = time;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getContent() {
        return content;
    }

    public String getLocation() {
        return location;
    }

    public Date getTime() {
        return time;
    }
    
    
    
}
