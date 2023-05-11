private Map<String, Object> readProperties(CommandLine commandLine) throws CliConfigurationException {
    Map<String, Object> result = new HashMap<>();

    if (!commandLine.hasOption("p") && !commandLine.hasOption("e")) {
        throw new CliConfigurationException("Either a property file (-p) or environment variables (-e) must be provided.");
    }

    if (commandLine.hasOption("p")) {
        String propertyFilePath = commandLine.getOptionValue("p");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(propertyFilePath);
            Properties properties = new Properties();
            properties.load(fileInputStream);
            for (String name : properties.stringPropertyNames()) {
                result.put(name, properties.getProperty(name));
            }
        } catch (IOException e) {
            throw new CliConfigurationException("Failed to read contents of the property file.", e);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    if (commandLine.hasOption("e")) {
        String[] envVars = commandLine.getOptionValues("e");
        for (String envVar : envVars) {
            String value = System.getenv(envVar);
            if (value != null) {
                result.put(envVar, value);
            } else {
                System.out.println(String.format("Environment variable %s is not set.", envVar));
            }
        }
    }

    return result;
}