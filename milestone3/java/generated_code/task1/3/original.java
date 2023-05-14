@Deprecated
    static public void init(Application app, String applicationName, String clientApiKey, Analytics.DeviceEvent... contexts) {
        init(app, applicationName, clientApiKey, false, false, contexts);
    }