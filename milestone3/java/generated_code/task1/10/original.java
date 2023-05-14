public static void bootstrap(final String dsn, Optional<String> tags, boolean cleanRootLogger) {
    bootstrap(dsn, tags, Optional.empty(), Optional.empty(), cleanRootLogger);
  }