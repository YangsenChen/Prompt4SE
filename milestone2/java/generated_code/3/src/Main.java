public class Main {
    public static void main(String[] args) {
        // Create an Application object
        Application myApp = new Application();

        // Call the deprecated init() method to initialize the analytics module
        init(myApp, "MyApp", "myApiKey", Analytics.DeviceEvent.STARTUP);

        // Test that the analytics module is working
        Analytics.logEvent("UserLoggedIn");
    }

    @Test
    public void testInit() {
        // Create an Application object
        Application myApp = new Application();

        // Call the deprecated init() method to initialize the analytics module
        init(myApp, "MyApp", "myApiKey", Analytics.DeviceEvent.STARTUP);

        // Test that the analytics module is initialized correctly
        assertTrue(Analytics.isInitialized());
    }

    @Test
    public void testLogEvent() {
        // Create an Application object
        Application myApp = new Application();

        // Call the deprecated init() method to initialize the analytics module
        init(myApp, "MyApp", "myApiKey", Analytics.DeviceEvent.STARTUP);

        // Log an event and test that it is recorded correctly
        Analytics.logEvent("UserLoggedIn");
        assertTrue(Analytics.isEventRecorded("UserLoggedIn"));
    }

    @Test
    public void testInitTwice() {
        // Create an Application object
        Application myApp = new Application();

        // Call the deprecated init() method twice
        init(myApp, "MyApp", "myApiKey", Analytics.DeviceEvent.STARTUP);
        init(myApp, "MyApp", "myApiKey", Analytics.DeviceEvent.DEMOGRAPHIC);

        // Test that the analytics module is initialized with the last context
        assertTrue(Arrays.equals(Analytics.getContexts(), new Analytics.DeviceEvent[] {Analytics.DeviceEvent.DEMOGRAPHIC}));
    }
}