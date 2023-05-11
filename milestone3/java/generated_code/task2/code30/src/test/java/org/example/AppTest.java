package org.example;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;

import static org.example.App.constructMenu;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
//    @org.junit.Test
//    public void testMenuCreation() {
//        // Create a new JMenuBar
//        JMenuBar menuBar = new JMenuBar();
//
//        // Create a new JMenu and add it to the menuBar
//        JMenu topMenu = new JMenu("Main");
//        menuBar.add(topMenu);
//
//        // Call the provided constructMenu method to add items to the topMenu
//        constructMenu(topMenu);
//
//        // Check that topMenu has been populated with items
//        Assert.assertTrue(topMenu.getItemCount() > 0);
//
//        // Check that topMenu is a child of menuBar
//        Assert.assertEquals(menuBar, topMenu.getParent());
//
//        // Check that topMenu has the correct name
//        Assert.assertEquals("Main", topMenu.getText());
//    }

    @org.junit.Test
    public void testMenuRemoval() {
        // Create a new JMenuBar
        JMenuBar menuBar = new JMenuBar();

        // Create a new JMenu and add it to the menuBar
        JMenu topMenu = new JMenu("Main");
        menuBar.add(topMenu);

        // Add some items to topMenu
        JMenuItem item1 = new JMenuItem("Item 1");
        topMenu.add(item1);
        JMenuItem item2 = new JMenuItem("Item 2");
        topMenu.add(item2);

        // Call the provided constructMenu method to remove items from topMenu
        constructMenu(topMenu);

        // Check that topMenu has no items
        Assert.assertEquals(0, topMenu.getItemCount());
    }

//    @Test
//    public void testMenuReordering() {
//        // Create a new JMenuBar
//        JMenuBar menuBar = new JMenuBar();
//
//        // Create two new JMenus and add them to the menuBar
//        JMenu firstMenu = new JMenu("First");
//        menuBar.add(firstMenu);
//        JMenu secondMenu = new JMenu("Second");
//        menuBar.add(secondMenu);
//
//        // Call the provided constructMenu method to reorder the menus
//        constructMenu(secondMenu);
//
//        // Check that secondMenu is now the first child of menuBar
//        Assert.assertEquals(secondMenu, menuBar.getMenu(0));
//    }
}
