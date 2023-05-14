static public void constructMenu(JMenu topMenu, boolean debugMode) throws BackingStoreException {
    if (debugMode) {
        System.out.println("Debug.constructMenu");
    } else {
        if (topMenu == null) {
            throw new IllegalArgumentException("The topMenu parameter cannot be null.");
        }
        if (topMenu.getItemCount() > 0) {
            for (Component component : topMenu.getMenuComponents()) {
                if (component instanceof JMenu) {
                    ((JMenu)component).removeAll();
                }
            }
            topMenu.removeAll();
        }
        addToMenu(topMenu, store); // recursive
    }
    
    topMenu.revalidate();
}