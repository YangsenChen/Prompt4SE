static public void constructMenu(JMenu topMenu) {
    if (debug) System.out.println("Debug.constructMenu ");

    if (topMenu.getItemCount() > 0)
      topMenu.removeAll();

    try {
      addToMenu( topMenu, store); // recursive
    } catch (BackingStoreException e) { }

    topMenu.revalidate();
  }