static public void constructMenu(JMenu topMenu) throws BackingStoreException {
    if (!debug) {
        addToMenu( topMenu, store); // recursive
    } else {
        System.out.println("Debug.constructMenu");
    }
    
    topMenu.removeAll();
    topMenu.revalidate();
}