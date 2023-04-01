import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;

import javax.persistence.PersistenceException;
import javax.persistence.spi.PersistenceUnitInfo;

import org.junit.jupiter.api.Test;

public class Main {

    @Test
    public void testParseWithValidUrl() {
        URL persistenceXmlUrl = Main.class.getResource("/persistence.xml");
        Collection<? extends PersistenceUnitInfo> persistenceUnits = parse(persistenceXmlUrl);

        assertEquals(2, persistenceUnits.size());
    }

    @Test
    public void testParseWithInvalidUrl() {
        URL persistenceXmlUrl = Main.class.getResource("/invalid.xml");
        Collection<? extends PersistenceUnitInfo> persistenceUnits = parse(persistenceXmlUrl);

        assertEquals(0, persistenceUnits.size());
    }

    @Test
    public void testParseWithUnknownSchema() {
        URL persistenceXmlUrl = Main.class.getResource("/persistence-unknown-schema.xml");
        try {
            Collection<? extends PersistenceUnitInfo> persistenceUnits = parse(persistenceXmlUrl);

            // Will not reach here since an exception would be thrown
            assertEquals(0, persistenceUnits.size());
        } catch (PersistenceException e) {
            assertEquals("Schema is unknown", e.getMessage());
        }
    }

    public static Collection<? extends PersistenceUnitInfo> parse(URL persistenceXml) {
        InputStream is = null;
        try {
            is = new BufferedInputStream(persistenceXml.openStream());

            JPAVersion jpaVersion = getSchemaVersion(is);
            Schema schema = getSchema(jpaVersion);

            if (schema == null) {
                throw new PersistenceException("Schema is unknown");
            }

            is = new BufferedInputStream(persistenceXml.openStream());

            parserFactory.setNamespaceAware(true);

            int endIndex = persistenceXml.getPath().length() - PERSISTENCE_XML_BASE_NAME.length();
            URL persistenceXmlRoot = new URL("file://" + persistenceXml.getFile().substring(0, endIndex));

            return getPersistenceUnits(is, persistenceXmlRoot, jpaVersion);
        } catch (Exception e) {
            throw new PersistenceException("Something goes wrong while parsing persistence.xml", e);
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    // No logging necessary, just consume
                }
        }
    }
}