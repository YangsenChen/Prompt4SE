package org.example;

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class App {

    public HashMap<String, String> headerInfo;

    public App() {
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

    // chatgpt generated  semantically equivalent code: test pass 3/3
    // chatgpt made the following change: I changed the loop used to iterate over the headerInfo map. Instead of looping over the key set and then looking up the value for each key separately, I'm using an enhanced for loop over the entrySet of the map. This allows me to get both the key and value in a single step, making the code more efficient.
    protected String headerInfoDump1() {
        StringBuilder retVal = new StringBuilder();
        for (Map.Entry<String, String> entry : this.headerInfo.entrySet()) {
            String curHeaderTitle = entry.getKey();
            String curHeaderValue = entry.getValue();
            retVal.append(curHeaderTitle);
            retVal.append(":::::");
            retVal.append(curHeaderValue);
            retVal.append(":::::\n");
        }
        return retVal.toString();
    }

    // chatgpt generated semantically equivalent code: test pass 3/3
// chatgpt made the following change: I used the map's forEach method to iterate over the entries. This method takes a lambda function that performs operations on both the key and value.
    protected String headerInfoDump2() {
        StringBuilder retVal = new StringBuilder();
        this.headerInfo.forEach((curHeaderTitle, curHeaderValue) -> {
            retVal.append(curHeaderTitle);
            retVal.append(":::::");
            retVal.append(curHeaderValue);
            retVal.append(":::::\n");
        });
        return retVal.toString();
    }

    public static void main(String[] args) {
        App app = new App();
        System.out.println("Output of headerInfoDump: \n" + app.headerInfoDump());
        System.out.println("Output of headerInfoDump1: \n" + app.headerInfoDump1());
    }
}