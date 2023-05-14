package org.example;


import org.junit.Test;

import static org.example.App.convertPropertyNameToColumnName;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void testConvertPropertyNameToColumnNameBasic() throws Exception {
        String propertyName = "myPropertyName";
        String expectedColumnName = "MY_PROPERTY_NAME";
        String columnName = convertPropertyNameToColumnName(propertyName);
        assertEquals(expectedColumnName, columnName);
    }

    @Test
    public void testConvertPropertyNameToColumnNameNoSplit() throws Exception {
        String propertyName = "mypropertyname";
        String expectedColumnName = "MYPROPERTYNAME";
        String columnName = convertPropertyNameToColumnName(propertyName);
        assertEquals(expectedColumnName, columnName);
    }

//    @Test
//    public void testConvertPropertyNameToColumnNameToLowercase() throws Exception {
//        String propertyName = "myPropertyName";
//        String expectedColumnName = "my_property_name";
//
//        String columnName = convertPropertyNameToColumnName(propertyName);
//        assertEquals(expectedColumnName, columnName);
//    }
}
