import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Main {
    private static final boolean splitCamelCase = true;
    private static final boolean changeCase = true;
    private static final boolean uppercase = true;
    private static final char separatorChar = '_';

    public static void main(String[] args) {
        String propertyName = "myPropertyName";
        String columnName = convertPropertyNameToColumnName(propertyName);
        System.out.println("Column name: " + columnName);
    }

    public static String convertPropertyNameToColumnName(final String propertyName) {
        StringBuilder tableName = new StringBuilder(propertyName.length() * 2);

        if (splitCamelCase) {
            String convertedTableName = Format.fromCamelCase(propertyName, separatorChar);
            tableName.append(convertedTableName);
        } else {
            tableName.append(propertyName);
        }

        if (!changeCase) {
            return tableName.toString();
        }

        return uppercase ?
                toUppercase(tableName).toString() :
                toLowercase(tableName).toString();
    }

    private static StringBuilder toUppercase(StringBuilder sb) {
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
        }
        return sb;
    }

    private static StringBuilder toLowercase(StringBuilder sb) {
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, Character.toLowerCase(sb.charAt(i)));
        }
        return sb;
    }

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

    @Test
    public void testConvertPropertyNameToColumnNameToLowercase() throws Exception {
        String propertyName = "myPropertyName";
        String expectedColumnName = "my_property_name";

        String columnName = convertPropertyNameToColumnName(propertyName);
        assertEquals(expectedColumnName, columnName);
    }
}