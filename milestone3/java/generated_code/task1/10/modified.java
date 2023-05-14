public static void bootstrap(final String dsn, boolean cleanRootLogger, Optional<String> tags) {
    if(cleanRootLogger) {
        // Logic to clean root logger
    }
    
    // Data flow: dsn is used as input to bootstrap method
    // Control flow: tags parameter is conditionally passed, based on whether it has a value
    if(tags.isPresent()) {
        bootstrap(dsn, tags.get());
    } else {
        bootstrap(dsn);
    }
}

private static void bootstrap(String dsn, String tags) {
    // Logic to bootstrap with tags parameter
}

private static void bootstrap(String dsn) {
    // Logic to bootstrap without tags parameter
}