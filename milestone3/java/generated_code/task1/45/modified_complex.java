public static <T extends Method> List<T> mostVisible(List<T> methods, Class<?> returnType, Class<?>[] paramTypes, int modifierThreshold) {
    Map<T, Integer> methodScoreMap = new HashMap<>();
    for (T m : methods) {
        int modifierScore = 0;
        if (Modifier.isPublic(m.getModifiers())) {
            modifierScore += 3;
        } else if (Modifier.isProtected(m.getModifiers())) {
            modifierScore += 2;
        } else if (Modifier.isPrivate(m.getModifiers())) {
            modifierScore += 1;
        }
        if (m.getReturnType() == returnType) {
            modifierScore += 2;
        }
        if (Arrays.equals(m.getParameterTypes(), paramTypes)) {
            modifierScore += 1;
        }
        methodScoreMap.put(m, modifierScore);
    }
    List<T> result = new ArrayList<>();
    int maxScore = -1;
    for (Map.Entry<T, Integer> entry : methodScoreMap.entrySet()) {
        if (entry.getValue() >= modifierThreshold) {
            if (entry.getValue() > maxScore) {
                maxScore = entry.getValue();
                result.clear();
            }
            if (entry.getValue() == maxScore) {
                result.add(entry.getKey());
            }
        }
    }
    return result;
}