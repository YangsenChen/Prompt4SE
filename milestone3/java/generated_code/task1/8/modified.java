public String getColumnName(String propertyName, boolean changeCase, boolean splitCamelCase, char separatorChar, boolean uppercase) {
    StringBuilder columnNameBuilder = new StringBuilder(propertyName.length() * 2);

    if (changeCase && uppercase) {
        propertyName = propertyName.toUpperCase();
    } else if (changeCase && !uppercase) {
        propertyName = propertyName.toLowerCase();
    }

    if (splitCamelCase) {
        String[] words = propertyName.split("(?<=[a-z])(?=[A-Z])");

        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                columnNameBuilder.append(separatorChar);
            }
            columnNameBuilder.append(words[i]);
        }
    } else {
        columnNameBuilder.append(propertyName);
    }
    
    return columnNameBuilder.toString();
}