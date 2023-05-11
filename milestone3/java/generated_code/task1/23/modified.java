private Map<String, Object> readProperties(CommandLine commandLine) throws CliConfigurationException {
    Map<String, Object> result = new HashMap<>();
    Properties properties = new Properties();
    InputStream propertiesStream = null;
    
    if (commandLine.hasOption("p")) { // If command line parameter '-p' is present
        String propertyFilePath = commandLine.getOptionValue("p");
        try {
            propertiesStream = new FileInputStream(propertyFilePath);
        } catch (FileNotFoundException e) {
            throw new CliConfigurationException("Cannot open property file.", e);
        }
    } else { // If command line parameter '-p' is not present
        propertiesStream = Main.class.getResourceAsStream("/jqassistant.properties");
    }
    
    if (propertiesStream != null) { // If propertiesStream is not null
        try {
            properties.load(propertiesStream); // Load properties from propertiesStream
            for (String name : properties.stringPropertyNames()) {
                result.put(name, properties.getProperty(name)); // Put the properties in result map
            }
        } catch (IOException e) {
            throw new CliConfigurationException("Cannot load properties from file.", e);
        } finally {
            try {
                propertiesStream.close(); // Close the propertiesStream
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return result;
}