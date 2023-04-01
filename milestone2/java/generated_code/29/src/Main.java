import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

public class Main {

    public static void main(String[] args) {
        runTests(); // Run the tests
    }

    // Test 1: Test with null input array
    @Test
    public static void testMapWithNullInput() {
        ImMap<Integer, String> myMap = map();
        assertTrue(myMap.isEmpty()); // The map should be empty
    }

    // Test 2: Test with non-empty input array
    @Test
    public static void testMapWithNonEmptyInput() {
        // Creating the key-value array to be passed to the map() method
        Map.Entry<Integer, String> pair1 = new SimpleEntry<>(1, "Apple");
        Map.Entry<Integer, String> pair2 = new SimpleEntry<>(2, "Banana");
        Map.Entry<Integer, String>[] kvPairs = new Map.Entry[]{pair1, pair2};

        ImMap<Integer, String> myMap = map(kvPairs);

        assertFalse(myMap.isEmpty()); // The map should not be empty
        assertTrue(myMap.containsKey(1)); // The map should contain key 1
        assertTrue(myMap.containsKey(2)); // The map should contain key 2
        assertFalse(myMap.containsKey(3)); // The map should not contain key 3
        assertEquals("Apple", myMap.get(1)); // The value for key 1 should be "Apple"
        assertEquals("Banana", myMap.get(2)); // The value for key 2 should be "Banana"
    }

    // Test 3: Test with empty input array
    @Test
    public static void testMapWithEmptyInput() {
        Map.Entry<Integer, String>[] kvPairs = new Map.Entry[0];

        ImMap<Integer, String> myMap = map(kvPairs);

        assertTrue(myMap.isEmpty()); // The map should be empty
    }

    @SafeVarargs
    public static <K,V> ImMap<K,V> map(Map.Entry<K,V>... kvPairs) {
        if ( (kvPairs == null) || (kvPairs.length < 1) ) { return PersistentHashMap.empty(); }
        return PersistentHashMap.of(Arrays.asList(kvPairs));
    }

    // Helper method to run all the test methods
    public static void runTests() {
        System.out.println("Running tests...\n");
        testMapWithNullInput();
        testMapWithNonEmptyInput();
        testMapWithEmptyInput();
        System.out.println("\nAll tests passed!");
    }
}