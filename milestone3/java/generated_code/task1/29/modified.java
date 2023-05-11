@SafeVarargs
public static <K,V> ImMap<K,V> map(Map.Entry<K,V>... kvPairs) {
    ImMap<K,V> immutableMap = null;
    try {
        // Check if kvPairs is empty or null
        if (kvPairs == null) {
            throw new IllegalArgumentException("kvPairs cannot be null");
        } else if (kvPairs.length == 0) {
            throw new IllegalArgumentException("kvPairs cannot be an empty array");
        }

        List<Map.Entry<K,V>> list = new ArrayList<>(Arrays.asList(kvPairs));
        for(Map.Entry<K,V> kv : list) {
            if (kv == null) {
                throw new IllegalArgumentException("List elements of kvPairs cannot be null");
            }
        }
        immutableMap = PersistentHashMap.of(list);
    } catch(IllegalArgumentException e) {
        System.err.println(e.getMessage());
    }
    return immutableMap;
}