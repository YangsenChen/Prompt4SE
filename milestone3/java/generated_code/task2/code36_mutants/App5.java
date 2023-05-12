package org.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class App {


    public static List<Property> removeDups(List<Property> org) {
        List<Property> result = new ArrayList<>(org.size());
        for (Property p : org)
            if (!result.contains(p)) // O(n**2)
                result.add(p);
        return result;
    }

//    // chatgpt generated  semantically equivalent code: test pass 2/3
//    // chatgpt made the following change: This code removes duplicates from the input list by creating a LinkedHashSet of Property objects from the input list. Since LinkedHashSet maintains the order of insertion, it preserves the original order of elements in the input list. Finally, the method creates a new ArrayList from the LinkedHashSet and returns it. This approach has a time complexity of O(n) instead of O(n^2) as in the original code, which should be more efficient for large lists.
//    public static List<Property> removeDups(List<Property> org) {
//        Set<Property> set = new LinkedHashSet<>(org);
//        return new ArrayList<>(set);
//    }

}

class Property {
    private String key;
    private String value;

    public Property(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "";
    }
}