public void externalize(DataOutputStream sink) throws IOException {
    // Create a Map to store the externalized data in key-value pairs
    Map<String, byte[]> externalizedData = new LinkedHashMap<>();

    // Externalize arrayVar and add it to the Map with a key
    byte[] arrayVarData = externalizeData(arrayVar);
    externalizedData.put("arrayVar", arrayVarData);

    // Traverse the elements of mapVars and externalize them
    for (Enumeration e = mapVars.elements(); e.hasMoreElements();) {
        ClientIO bt = (ClientIO) e.nextElement();
        String key = "mapVar_" + bt.getID();
        byte[] value = externalizeData(bt);
        externalizedData.put(key, value);
    }

    // Write the size of the Map to the stream
    int mapSize = externalizedData.size();
    sink.writeInt(mapSize);

    // Traverse the key-value pairs of the Map and write them to the stream
    for (Map.Entry<String, byte[]> entry : externalizedData.entrySet()) {
        String key = entry.getKey();
        byte[] value = entry.getValue();
        int keySize = key.length();
        int valueSize = value.length;
        sink.writeInt(keySize);
        sink.writeUTF(key);
        sink.writeInt(valueSize);
        sink.write(value);
    }
}

private byte[] externalizeData(Externalizable obj) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    obj.writeExternal(new DataOutputStream(oos));
    oos.close();
    return baos.toByteArray();
}