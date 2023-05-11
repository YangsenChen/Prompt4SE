public void externalize(DataOutputStream sink) throws IOException {
    // Externalize arrayVar
    arrayVar.externalize(sink);

    // Create a list to store the elements of mapVars
    List<ClientIO> elements = new ArrayList<>();

    // Traverse the elements of mapVars and append them to the list
    for (Enumeration e = mapVars.elements(); e.hasMoreElements();) {
        ClientIO bt = (ClientIO) e.nextElement();
        elements.add(bt);
    }

    // Externalize the elements of mapVars in reverse order
    for (int i = elements.size() - 1; i >= 0; i--) {
        ClientIO bt = elements.get(i);
        bt.externalize(sink);
    }
}