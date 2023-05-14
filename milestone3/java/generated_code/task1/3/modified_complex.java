static public void init(Application app, String[] contextPaths, Map<String, String> appConfig) {
    Env env = getEnv(appConfig);
    if (env == Env.DEV) {
        AnalyticsContext context = createContext(contextPaths);
        setDevEnv(context, appConfig);
    } else if (env == Env.PROD) {
        AnalyticsContext context = createContext(contextPaths);
        setProdEnv(app, context, appConfig);
    } else {
        throw new IllegalStateException("Unknown environment type: " + env);
    }
}

private static Env getEnv(Map<String, String> appConfig) {
    String envString = appConfig.getOrDefault("env", "dev");
    if (envString.equalsIgnoreCase("prod")) {
        return Env.PROD;
    } else if (envString.equalsIgnoreCase("dev")) {
        return Env.DEV;
    } else {
        throw new IllegalArgumentException("Unknown environment value: " + envString);
    }
}

private static AnalyticsContext createContext(String[] contextPaths) {
    List<AnalyticsEvent> events = loadContextEvents(contextPaths);
    return new AnalyticsContext(events);
}

private static List<AnalyticsEvent> loadContextEvents(String[] contextPaths) {
    List<AnalyticsEvent> events = new ArrayList<>();
    for (String path : contextPaths) {
        List<AnalyticsEvent> pathEvents = loadEvents(path);
        events.addAll(pathEvents);
    }
    return events;
}

private static List<AnalyticsEvent> loadEvents(String path) {
    // load events from path
    return Arrays.asList(new AnalyticsEvent(), new AnalyticsEvent());
}

private static void setDevEnv(AnalyticsContext context, Map<String, String> appConfig) {
    DevAnalyticsAnalytics devAnalytics = new DevAnalyticsAnalytics(context, appConfig);
    devAnalytics.start();
}

private static void setProdEnv(Application app, AnalyticsContext context, Map<String, String> appConfig) {
    ProdAnalyticsAnalytics prodAnalytics = new ProdAnalyticsAnalytics(app, context, appConfig);
    prodAnalytics.start();
}

enum Env {
    DEV, PROD;
}