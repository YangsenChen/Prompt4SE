package org.example;

import junit.framework.TestCase;
import org.example.App;

import javax.persistence.PersistenceException;
import javax.persistence.spi.PersistenceUnitInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Collection;


public class AppTest extends TestCase {

    private File createSamplePersistenceXml() throws IOException {
        String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<persistence xmlns=\"http://java.sun.com/xml/ns/persistence\" version=\"2.1\">\n"
                + "    <persistence-unit name=\"sample-unit\" transaction-type=\"RESOURCE_LOCAL\">\n"
                + "        <provider>org.hibernate.ejb.HibernatePersistence</provider>\n"
                + "    </persistence-unit>\n"
                + "</persistence>";

        File tempFile = File.createTempFile("persistence", ".xml");
        try (OutputStream os = new FileOutputStream(tempFile)) {
            os.write(content.getBytes());
            os.flush();
        }

        return tempFile;
    }

//    public void testParseValidPersistenceXml() throws IOException {
//        File sampleXml = createSamplePersistenceXml();
//        URL sampleXmlUrl = sampleXml.toURI().toURL();
//        Collection<? extends PersistenceUnitInfo> units = App.parse(sampleXmlUrl);
//
//        assertNotNull(units);
//        assertEquals(1, units.size());
//
//        PersistenceUnitInfo unit = units.iterator().next();
//        assertEquals("sample-unit", unit.getPersistenceUnitName());
//    }

    public void testParseInvalidPersistenceXml() {
        String invalidUrl = "file:/invalid/path/to/persistence.xml";
        try {
            App.parse(new URL(invalidUrl));
            fail("Expected PersistenceException");
        } catch (PersistenceException e) {
            // Expected exception
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

//    public void testParseEmptyPersistenceXml() throws IOException {
//        String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
//                + "<persistence xmlns=\"http://java.sun.com/xml/ns/persistence\" version=\"2.1\">\n"
//                + "</persistence>";
//
//        File tempFile = File.createTempFile("emptyPersistence", ".xml");
//        try (OutputStream os = new FileOutputStream(tempFile)) {
//            os.write(content.getBytes());
//            os.flush();
//        }
//
//        URL emptyXmlUrl = tempFile.toURI().toURL();
//        Collection<? extends PersistenceUnitInfo> units = App.parse(emptyXmlUrl);
//
//        assertNotNull(units);
//        assertTrue(units.isEmpty());
//    }
}
