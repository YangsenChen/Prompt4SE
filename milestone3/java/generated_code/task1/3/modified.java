@Deprecated
static public void init(Application app, String applicationName, String clientApiKey, boolean isProd, boolean isDev, AnalyticContext... contexts) {
      if (isDev) {
          setDevEnv(clientApiKey, contexts);
      } else if (isProd) {
          setProdEnv(applicationName, clientApiKey, contexts);
      } else {
          throw new IllegalArgumentException("Please provide either 'isDev' or 'isProd'");
      }
}

private static void setDevEnv(String clientApiKey, AnalyticContext... contexts) {
    // set up the dev environment
    // ...
}

private static void setProdEnv(String applicationName, String clientApiKey, AnalyticContext... contexts) {
    // set up the prod environment
    // ...
}