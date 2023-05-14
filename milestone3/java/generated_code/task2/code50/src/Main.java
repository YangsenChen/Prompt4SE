import static org.junit.Assert.assertEquals;

import javax.xml.bind.JAXBException;

import org.junit.Test;

public class Main {

    @Test
    public void canConvertDocumentToObject() throws JAXBException {
        // Arrange
        MyClass myClass = new MyClass();
        String xml = "<root><item>1</item><item>2</item><item>3</item></root>";
        myClass.setDocument(xml);

        // Act
        Immoxml immoxml = myClass.toObject();

        // Assert
        assertEquals(3, immoxml.getItems().getItem().size());
    }

    @Test
    public void canHandleEmptyDocument() throws JAXBException {
        // Arrange
        MyClass myClass = new MyClass();
        String xml = "";
        myClass.setDocument(xml);

        // Act
        Immoxml immoxml = myClass.toObject();

        // Assert
        assertEquals(null, immoxml.getItems());
    }

    @Test(expected = JAXBException.class)
    public void throwsExceptionForInvalidXml() throws JAXBException {
        // Arrange
        MyClass myClass = new MyClass();
        String xml = "<root><item>1</item><item>2</item><item>3</root>";
        myClass.setDocument(xml);

        // Act
        Immoxml immoxml = myClass.toObject();

        // Assert
        // Should throw a JAXBException because of invalid XML format
    }

    public static void main(String[] args) {
        // Test methods will be automatically executed by the testing framework
    }
}