public static void bootstrap(final String dsn, Optional<String> tags, boolean cleanRootLogger, int numWorkers) {
    if(cleanRootLogger) {
        // Logic to clean root logger
    }
    
    // Data flow: dsn is used as input to bootstrap method, the output is used as input to config method
    String config = getConfig(dsn);
    
    // Control flow: tags parameter is conditionally passed, based on whether it has a value
    if(tags.isPresent()) {
        // Data flow: tags parameter is used as input to config method
        config = getConfigWithTags(config, tags.get());
    }
    
    // Data flow: config and numWorkers are used as inputs to configure method
    configure(config, numWorkers);
}

private static String getConfig(String dsn) {
    // Logic to get config based on dsn
    return "<config>";
}

private static String getConfigWithTags(String config, String tags) {
    // Logic to append tags to config
    return config + " " + tags;
}

private static void configure(String config, int numWorkers) {
    // Logic to configure system with given config and numWorkers
}