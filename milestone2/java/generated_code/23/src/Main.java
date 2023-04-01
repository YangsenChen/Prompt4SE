import org.apache.commons.cli.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("p", true, "property file path");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            Map<String, Object> propertiesMap = readProperties(cmd);
            // use propertiesMap here...
        } catch (CliConfigurationException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Object> readProperties(CommandLine commandLine) throws CliConfigurationException {
        final Properties properties = new Properties();
        InputStream propertiesStream;
        if (commandLine.hasOption("p")) {
            File propertyFile = new File(commandLine.getOptionValue("p"));
            if (!propertyFile.exists()) {
                throw new CliConfigurationException("Property file given by command line does not exist: " + propertyFile.getAbsolutePath());
            }
            try {
                propertiesStream = new FileInputStream(propertyFile);
            } catch (IOException e) {
                throw new CliConfigurationException("Cannot open property file.", e);
            }
        } else {
            propertiesStream = Main.class.getResourceAsStream("/jqassistant.properties");
        }
        Map<String, Object> result = new HashMap<>();
        if (propertiesStream != null) {
            try {
                properties.load(propertiesStream);
            } catch (IOException e) {
                throw new CliConfigurationException("Cannot load properties from file.", e);
            }
            for (String name : properties.stringPropertyNames()) {
                result.put(name, properties.getProperty(name));
            }
        }
        return result;
    }

    @Test
    void testReadPropertiesWithValidCommandLineOption() throws CliConfigurationException {
        String filePath = getClass().getClassLoader().getResource("jqassistant.properties").getFile();
        Map<String, Object> propertiesMap = readProperties(new CommandLine(new Option("p", filePath)));
        assertNotNull(propertiesMap);
        assertFalse(propertiesMap.isEmpty());
        assertEquals("value1", propertiesMap.get("property1"));
        assertEquals("value2", propertiesMap.get("property2"));
    }

    @Test
    void testReadPropertiesWithInvalidCommandLineOption() {
        String filePath = "path/to/nonexistent/file.properties";
        assertThrows(CliConfigurationException.class,
                () -> readProperties(new CommandLine(new Option("p", filePath))));
    }

    @Test
    void testReadPropertiesWithoutCommandLineOption() throws CliConfigurationException {
        Map<String, Object> propertiesMap = readProperties(new CommandLine(new Options()));
        assertNotNull(propertiesMap);
        assertFalse(propertiesMap.isEmpty());
        assertEquals("value1", propertiesMap.get("property1"));
        assertEquals("value2", propertiesMap.get("property2"));
    }
}