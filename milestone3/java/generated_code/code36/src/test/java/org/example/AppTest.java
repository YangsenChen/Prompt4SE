package org.example;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.example.App.removeDups;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest
{
//    @Test
//    public void shouldRemoveDuplicates() {
//        // create a sample list of properties with duplicates
//        List<Property> originalList = new ArrayList<>();
//        originalList.add(new Property("address", "123 Main St."));
//        originalList.add(new Property("address", "123 Main St."));
//        originalList.add(new Property("nickname", "Jane"));
//        originalList.add(new Property("city", "New York"));
//        originalList.add(new Property("nickname", "John"));
//        originalList.add(new Property("city", "Los Angeles"));
//        originalList.add(new Property("state", "NY"));
//        originalList.add(new Property("state", "NY"));
//        originalList.add(new Property("nickname", "Jane"));
//
//        // call the removeDups method on the sample list
//        List<Property> uniqueList = removeDups(originalList);
//
//        // assert that the resulting list has no duplicates
//        assertEquals(5, uniqueList.size());
//        assertEquals("123 Main St.", uniqueList.get(0).getValue());
//        assertEquals("Jane", uniqueList.get(1).getValue());
//        assertEquals("New York", uniqueList.get(2).getValue());
//        assertEquals("John", uniqueList.get(3).getValue());
//        assertEquals("NY", uniqueList.get(4).getValue());
//    }

    @Test
    public void shouldHandleEmptyList() {
        List<Property> emptyList = new ArrayList<>();

        // call the removeDups method on the empty list
        List<Property> uniqueList = removeDups(emptyList);

        // assert that the resulting list is also empty
        assertEquals(0, uniqueList.size());
    }

    @Test
    public void shouldHandleSingleElementList() {
        List<Property> singleList = new ArrayList<>();
        singleList.add(new Property("name", "John"));

        // call the removeDups method on a list with a single element
        List<Property> uniqueList = removeDups(singleList);

        // assert that the resulting list has only one element
        assertEquals(1, uniqueList.size());
        assertEquals("John", uniqueList.get(0).getValue());
    }
}
