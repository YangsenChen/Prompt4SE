@SafeVarargs
    public static <K,V> ImMap<K,V> map(Map.Entry<K,V>... kvPairs) {
        if ( (kvPairs == null) || (kvPairs.length < 1) ) { return PersistentHashMap.empty(); }
        return PersistentHashMap.of(Arrays.asList(kvPairs));
    }