package org.example;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.prefs.BackingStoreException;

public class App {

    static boolean debug = true; // Set to false to disable debugging

    public static void main(String[] args) {
        //Main method implementation
    }

    // test pass 1/3
    static public void constructMenu(JMenu topMenu) {
        if (debug) System.out.println("Debug.constructMenu ");

        if (topMenu.getItemCount() >= 0)
            topMenu.removeAll();

//        try {
//            addToMenu( topMenu, store); // recursive
//        } catch (BackingStoreException e) { }

        topMenu.revalidate();
    }

//    //    chatgpt generated  semantically equivalent code: test pass 1/3
//    //    chatgpt made the following change:
//    // This implementation first checks if the specified JMenu object has any items, and if so, removes all of its items. If any of the items are themselves JMenu objects, it also removes all of their items recursively. Finally, it revalidates the JMenu.
//    static public void constructMenu(JMenu topMenu) {
//        if (debug) System.out.println("Debug.constructMenu ");
//
//        if (topMenu.getItemCount() > 0) {
//            for (Component c : topMenu.getMenuComponents()) {
//                if (c instanceof JMenu) {
//                    ((JMenu) c).removeAll();
//                }
//            }
//            topMenu.removeAll();
//        }
//
//        topMenu.revalidate();
//    }

    // Assume that addToMenu and store variables are defined elsewhere
//    static public void addToMenu(JMenu menu, Store store) throws BackingStoreException {
//        //addToMenu implementation
//    }


}