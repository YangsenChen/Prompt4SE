public static Collection<? extends PersistenceUnitInfo> parse(URL persistenceXml) {
    // Declare and initialize the input stream outside the try block
    InputStream is = null;
    try {
        JPAVersion jpaVersion;
        Schema schema;
        URL persistenceXmlRoot;

        is = bufferedInputStream(persistenceXml); // create buffered input stream
        
        jpaVersion = getSchemaVersion(is); // get JPA version
        schema = getSchema(jpaVersion); // get schema
        
        if (schema == null) {
            throw new PersistenceException("Schema is unknown");
        }

        // Get root URL of persistence.xml
        persistenceXmlRoot = new URL("file://" + getPersistenceXmlRoot(persistenceXml));

        // fetch persistence units
        Collection<? extends PersistenceUnitInfo> persistenceUnitInfo = fetchPersistenceUnits(is, persistenceXmlRoot, jpaVersion);

        return persistenceUnitInfo;
    } catch (Exception e) {
        throw new PersistenceException("An error occurred while parsing persistence.xml", e);
    } finally {
        closeInputStream(is); // close input stream
    }
}

/**
 * This method returns the root path of the persistence.xml file.
 */
public static String getPersistenceXmlRoot(URL persistenceXml) {
    int endIndex = persistenceXml.getPath().length() - PERSISTENCE_XML_BASE_NAME.length();
    return persistenceXml.getFile().substring(0, endIndex);
}

/**
 * This method creates a buffered input stream.
 */
public static InputStream bufferedInputStream(URL persistenceXml) throws IOException {
    InputStream is = persistenceXml.openStream(); // open input stream
    return new BufferedInputStream(is); // buffer the input stream
}

/**
 * This method fetches persistence units.
 */
public static Collection<? extends PersistenceUnitInfo> fetchPersistenceUnits(InputStream is, URL persistenceXmlRoot, JPAVersion jpaVersion) throws Exception {
    parserFactory.setNamespaceAware(true); // set namespace aware
    
    // Parse the persistence xml input and return persistence units
    return getPersistenceUnits(is, persistenceXmlRoot, jpaVersion);
}

/**
 * This method closes the input stream.
 */
public static void closeInputStream(InputStream is) {
    if (is != null)
        try {
            is.close();
        } catch (IOException e) {
            // No logging necessary, just consume
        }
}