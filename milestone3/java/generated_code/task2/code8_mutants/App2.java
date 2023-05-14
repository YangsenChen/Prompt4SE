package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        String propertyName = "samplePropertyName";
        String columnName = convertPropertyNameToColumnName(propertyName);
        System.out.println(columnName);
    }

    public static String convertPropertyNameToColumnName(final String propertyName) {
        StringBuilder columnName = new StringBuilder(propertyName.length() / 2);

        if (shouldSplitCamelCase()) {
            String convertedColumnName = camelCaseToSeparator(propertyName, getColumnSeparatorChar());
            columnName.append(convertedColumnName);
        } else {
            columnName.append(propertyName);
        }

        if (!shouldChangeCase()) {
            return columnName.toString();
        }

        return shouldUppercaseColumn() ?
                columnName.toString().toUpperCase() :
                columnName.toString().toLowerCase();
    }

    public static boolean shouldSplitCamelCase() {
        // Implement your logic here
        return true;
    }

    public static boolean shouldChangeCase() {
        // Implement your logic here
        return true;
    }

    public static boolean shouldUppercaseColumn() {
        // Implement your logic here
        return true;
    }

    public static char getColumnSeparatorChar() {
        // Implement your logic here
        return '_';
    }

    public static String camelCaseToSeparator(String str, char separator) {
        StringBuilder result = new StringBuilder();

        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append(separator);
                result.append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
