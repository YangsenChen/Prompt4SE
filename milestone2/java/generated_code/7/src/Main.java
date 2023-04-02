import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Main {

    private HashMap<String, String> headerInfo;

    public Main() {
        // Initialize headerInfo hashmap
        this.headerInfo = new HashMap<>();
        this.headerInfo.put("Header1", "Value1");
        this.headerInfo.put("Header2", "Value2");
        this.headerInfo.put("Header3", "Value3");
    }

    // Wrapper for headerInfoDump() function
    protected String headerInfoDump() {
        StringBuilder retVal = new StringBuilder();
        for (String curHeaderTitle : this.headerInfo.keySet()) {
            String curHeaderValue = this.headerInfo.get(curHeaderTitle);
            retVal.append(curHeaderTitle);
            retVal.append(":::::");
            retVal.append(curHeaderValue);
            retVal.append(":::::\n");
        }

        return (retVal.toString());
    }

//    // chatgpt generated  semantically equivalent code: test pass 3/3
//    // chatgpt made the following change: I changed the loop used to iterate over the headerInfo map. Instead of looping over the key set and then looking up the value for each key separately, I'm using an enhanced for loop over the entrySet of the map. This allows me to get both the key and value in a single step, making the code more efficient.
//    protected String headerInfoDump() {
//        StringBuilder retVal = new StringBuilder();
//        for (Map.Entry<String, String> entry : this.headerInfo.entrySet()) {
//            String curHeaderTitle = entry.getKey();
//            String curHeaderValue = entry.getValue();
//            retVal.append(curHeaderTitle);
//            retVal.append(":::::");
//            retVal.append(curHeaderValue);
//            retVal.append(":::::\n");
//        }
//        return retVal.toString();
//    }


    // Unit Test 1 - Test headerInfoDump() function output
    @Test
    public void testHeaderInfoDump() {
        String expectedOutput = "Header1:::::Value1:::::\nHeader2:::::Value2:::::\nHeader3:::::Value3:::::\n";
        assertEquals(expectedOutput, this.headerInfoDump());
    }

    // Unit Test 2 - Test headerInfoDump() function with empty hashmap
    @Test
    public void testHeaderInfoDumpWithEmptyHashMap() {
        this.headerInfo = new HashMap<>();
        String expectedOutput = "";
        assertEquals(expectedOutput, this.headerInfoDump());
    }

    // Unit Test 3 - Test headerInfoDump() function with new header added
    @Test
    public void testHeaderInfoDumpWithNewHeaderAdded() {
        this.headerInfo.put("Header4", "Value4");
        String expectedOutput = "Header1:::::Value1:::::\nHeader2:::::Value2:::::\nHeader3:::::Value3:::::\nHeader4:::::Value4:::::\n";
        assertEquals(expectedOutput, this.headerInfoDump());
    }
}