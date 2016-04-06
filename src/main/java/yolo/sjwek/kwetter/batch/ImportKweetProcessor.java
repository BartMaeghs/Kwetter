/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.batch;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.chunk.ItemProcessor;

/**
 *
 * @author Bart
 */
public class ImportKweetProcessor implements ItemProcessor {

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    @Override
    public ImportedKweet processItem(Object t) {
        System.out.println("processItem: " + t);
        String line = (String) t;
        
        if (line.contains("screenName") == false) {
            return new ImportedKweet(null, null, null, null);
        }
        if (line.substring(0, 1).equals(",")) {
            line = line.substring(1);
        }

        JsonParser parser = new JsonParser();
        JsonObject jobject = (JsonObject) parser.parse(line);
        String screenname = jobject.get("screenName").getAsString();
        String content = jobject.get("tweet").getAsString();
        String lcation = jobject.get("postedFrom").getAsString();
        Date time;
        try {
            time = format.parse(jobject.get("postDate").getAsString());
        } catch (ParseException ex) {
            Logger.getLogger(ImportKweetProcessor.class.getName()).log(Level.SEVERE, null, ex);
            time = new Date();
        }
        
        ImportedKweet imported = new ImportedKweet(screenname, content, lcation, time);
        return imported;
    }

}
