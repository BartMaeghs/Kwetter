/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yolo.sjwek.kwetter.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bart
 */
public class TagParserTest {

    public TagParserTest() {
    }

    @Before
    public void setUp() {
    }
    
    private String toParse = "@yolo #swag @420swagit #stringlife de rest van deze string bevat geen tags of @wel";
    
    @Test
    public void testFindAtTags() {
        char indentifier = '@';
        List<String> expected = new ArrayList<>(
                Arrays.asList("yolo", "420swagit", "wel")
        );
        List<String> actual = TagParser.findTags(indentifier, toParse);
        assertTrue(Arrays.equals(expected.toArray(), actual.toArray()));
    }

    @Test
    public void testFindHashTags() {
        char indentifier = '#';
        List<String> expected = new ArrayList<>(
                Arrays.asList("swag", "stringlife")
        );
        List<String> actual = TagParser.findTags(indentifier, toParse);
        assertTrue(Arrays.equals(expected.toArray(), actual.toArray()));
    }

}
