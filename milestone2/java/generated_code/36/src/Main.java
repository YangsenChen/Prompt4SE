import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class Main {
    @Test
    public void shouldRemoveDuplicates() {
        // create a sample list of properties with duplicates
        List<Property> originalList = new ArrayList<>();
        originalList.add(new Property("address", "123 Main St."));
        originalList.add(new Property("address", "123 Main St."));
        originalList.add(new Property("nickname", "Jane"));
        originalList.add(new Property("city", "New York"));
        originalList.add(new Property("nickname", "John"));
        originalList.add(new Property("city", "Los Angeles"));
        originalList.add(new Property("state", "NY"));
        originalList.add(new Property("state", "NY"));
        originalList.add(new Property("nickname", "Jane"));

        // call the removeDups method on the sample list
        List<Property> uniqueList = removeDups(originalList);

        // assert that the resulting list has no duplicates
        assertEquals(5, uniqueList.size());
        assertEquals("123 Main St.", uniqueList.get(0).getValue());
        assertEquals("Jane", uniqueList.get(1).getValue());
        assertEquals("New York", uniqueList.get(2).getValue());
        assertEquals("John", uniqueList.get(3).getValue());
        assertEquals("NY", uniqueList.get(4).getValue());
    }

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
        return "Property{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}