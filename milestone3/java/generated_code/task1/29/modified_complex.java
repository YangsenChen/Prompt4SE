public static <K,V> ImMap<K,V> map(List<Map.Entry<K,V>> kvPairs) throws CustomException {
    try {
        if (kvPairs == null || kvPairs.isEmpty()) {
            throw new CustomException("List of kvPairs cannot be empty or null.");
        }

        Map<K,V> tempMap = new HashMap<>();
        boolean validInput = true;

        // Validate each pair and add them to Temp Map
        for (Map.Entry<K, V> kvPair : kvPairs) {
            if (kvPair == null || kvPair.getKey() == null || kvPair.getValue() == null) {
                validInput = false;
                break;
            }
            if (tempMap.containsKey(kvPair.getKey())) {
                // Duplicate Key Found, ignore it, and log the error message
                System.err.println("Duplicate key found. Key - " + kvPair.getKey());
            } else {
                tempMap.put(kvPair.getKey(), kvPair.getValue());
            }
        }

        if (!validInput) {
            throw new CustomException("Invalid kvPairs found. Pairs cannot be null or have null keys/values.");
        }

        // Validate if Temp Map is not empty
        if (tempMap.isEmpty()) {
            throw new CustomException("Cannot create an empty Map object.");
        }

        return PersistentHashMap.of(tempMap);

    } catch (NullPointerException | IllegalArgumentException | UnsupportedOperationException e) {
        throw new CustomException(e.getMessage(), e.getCause());
    }
}