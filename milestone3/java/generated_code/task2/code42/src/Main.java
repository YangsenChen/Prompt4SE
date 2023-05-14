import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Main {
    // Existing code...

    @Test
    void testValidCatalog() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<XMLCatalog>\n" +
                "  <Base HRef=\"file:///home/user/documents/\" />\n" +
                "  <Delegate PublicId=\"-//OASIS//DTD DocBook V4.1//EN\" HRef=\"docbook.dtd\" />\n" +
                "  <Extend HRef=\"catalog.xml\" />\n" +
                "  <Map PublicId=\"-//W3C//DTD XHTML 1.1//EN\" HRef=\"xhtml11.dtd\" />\n" +
                "  <Remap SystemId=\"http://www.company.com/myschema.xsd\" HRef=\"myschema.xsd\" />\n" +
                "</XMLCatalog>";

        Catalog catalog = parseCatalog(xml);
        Vector<CatalogEntry> entries = catalog.getEntries();

        Assertions.assertEquals(5, entries.size());

        CatalogEntry baseEntry = entries.get(0);
        Assertions.assertEquals(1, baseEntry.getEntryType());
        Assertions.assertEquals("file:///home/user/documents/", baseEntry.getEntryArgs().get(0));

        CatalogEntry delegateEntry = entries.get(1);
        Assertions.assertEquals(2, delegateEntry.getEntryType());
        Assertions.assertEquals("-//OASIS//DTD DocBook V4.1//EN", delegateEntry.getEntryArgs().get(0));
        Assertions.assertEquals("docbook.dtd", delegateEntry.getEntryArgs().get(1));

        CatalogEntry extendEntry = entries.get(2);
        Assertions.assertEquals(3, extendEntry.getEntryType());
        Assertions.assertEquals("catalog.xml", extendEntry.getEntryArgs().get(0));

        CatalogEntry mapEntry = entries.get(3);
        Assertions.assertEquals(4, mapEntry.getEntryType());
        Assertions.assertEquals("-//W3C//DTD XHTML 1.1//EN", mapEntry.getEntryArgs().get(0));
        Assertions.assertEquals("xhtml11.dtd", mapEntry.getEntryArgs().get(1));

        CatalogEntry remapEntry = entries.get(4);
        Assertions.assertEquals(5, remapEntry.getEntryType());
        Assertions.assertEquals("http://www.company.com/myschema.xsd", remapEntry.getEntryArgs().get(0));
        Assertions.assertEquals("myschema.xsd", remapEntry.getEntryArgs().get(1));
    }

    @Test
    void testInvalidEntryType() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<XMLCatalog>\n" +
                "  <Invalid Entry=\"test\" />\n" +
                "</XMLCatalog>";

        Catalog catalog = parseCatalog(xml);
        Vector<CatalogEntry> entries = catalog.getEntries();

        Assertions.assertEquals(0, entries.size());
        Assertions.assertTrue(catalog.getCatalogManager().debug.message.contains("Invalid catalog entry type"));
    }

    @Test
    void testInvalidEntry() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<XMLCatalog>\n" +
                "  <Map />\n" +
                "</XMLCatalog>";

        Catalog catalog = parseCatalog(xml);
        Vector<CatalogEntry> entries = catalog.get