private static volatile Map<String, WebsockifySslContext> INSTANCE_MAP = new ConcurrentHashMap<>();

public static WebsockifySslContext getInstance(String keystore, String password, String keyPassword) {
    if (keystore == null || password == null || keyPassword == null) {
        throw new IllegalArgumentException("keystore, password, and keyPassword must not be null");
    }

    WebsockifySslContext context = searchContext(keystore, password, keyPassword);
    if (context == null) {
        context = createContext(keystore, password, keyPassword);
        addContextToMap(keystore, context);
    }

    return context;
}

private static WebsockifySslContext searchContext(String keystore, String password, String keyPassword) {
    WebsockifySslContext context = INSTANCE_MAP.get(keystore);
    if (context != null && context.isMatchingPassword(password, keyPassword)) {
        return context;
    }
    return null;
}

private static WebsockifySslContext createContext(String keystore, String password, String keyPassword) {
    return new WebsockifySslContext(keystore, password, keyPassword);
}

private static void addContextToMap(String keystore, WebsockifySslContext context) {
    synchronized (INSTANCE_MAP) {
        WebsockifySslContext existingContext = INSTANCE_MAP.get(keystore);
        if (existingContext == null) {
            INSTANCE_MAP.put(keystore, context);
        } else if (existingContext.isMatchingPassword(context.getPassword(), context.getKeyPassword())) {
            throw new RuntimeException("Context for keystore already exists with matching password and key password");
        } else {
            throw new RuntimeException("Context for keystore already exists with different password or key password");
        }
    }
}