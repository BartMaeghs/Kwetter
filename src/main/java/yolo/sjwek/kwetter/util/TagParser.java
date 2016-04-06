/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Bart
 */
public class TagParser {
    private static final String pattern = "(\\w+|\\W+)";
    
    public static List<String> findTags(char indentifier, String toSearch) {
        Pattern tagPattern  = Pattern.compile(indentifier + pattern);
        Matcher matcher = tagPattern.matcher(toSearch);
        List<String> results = new ArrayList<>();
        while (matcher.find()) {
            results.add(matcher.group(1));
        }
        return results;
    }
    
}
