public static Collection<? extends PersistenceUnitInfo> parse(URL persistenceXml) {

    InputStream is = null;
    try {
      // Buffer the InputStream so we can mark it, though we'll be in
      // trouble if we have to read more than 8192 characters before finding
      // the schema!
      is = new BufferedInputStream(persistenceXml.openStream());

      JPAVersion jpaVersion = getSchemaVersion(is);
      Schema schema = getSchema(jpaVersion);

      if (schema == null) {
        throw new PersistenceException("Schema is unknown");
      }

      // Get back to the beginning of the stream
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