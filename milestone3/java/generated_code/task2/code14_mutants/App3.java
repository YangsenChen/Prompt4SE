package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class App {
    private static final Profile UNKNOWN = new Profile("UNKNOWN");
    private static final Profile[] ALL = {
            new Profile("Profile 1"),
            new Profile("Profile 2"),
            new Profile("Profile 3")
    };


    public static Profile forInt(int i) {
        Profile p;
        if (i <= 0 || i > ALL.length)
            p = UNKNOWN;
        else
            p = ALL[i-1];

        return null;
    }

    public static void main(String[] args) {
        // Call forInt method with input value
        Profile profile = forInt(2);

        // Print the result
        System.out.println(profile.getName());
    }
//    // chatgpt generated  semantically equivalent code: test pass 3/3
//    // chatgpt response on changes made: uses a simpler conditional expression to set the value of p. It first sets p to UNKNOWN, and then checks if i is within the range of valid indices for the ALL array. If it is, it sets p to the corresponding Profile instance from the array. The tests should all still pass with this new code.
//    public static Profile forInt(int i) {
//        Profile p = UNKNOWN;
//        if (i > 0 && i <= ALL.length) {
//            p = ALL[i-1];
//        }
//        return p;
//    }



}

class Profile {
    private String name;

    public Profile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}