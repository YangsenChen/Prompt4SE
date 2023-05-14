public String convertPropertyNameToColumnName(final String propertyName, final boolean splitCamelCase, final boolean prefixUnderscore) {
    String[] words;

    if (splitCamelCase) {
        words = propertyName.split("(?<=[a-z0-9])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z0-9])");
    } else {
        words = new String[]{propertyName};
    }

    StringBuilder columnName = new StringBuilder();

    if (prefixUnderscore) {
        columnName.append("_");
    }

    for (int i = 0; i < words.length; i++) {
        String word = words[i];

        if (i > 0) {
            columnName.append("_");
        }

        if (word.length() == 1) {
            columnName.append(word.toLowerCase());
        } else if (word.matches("[A-Z]+")) {
            columnName.append(word.toLowerCase());
        } else if (word.matches("[a-z]+")) {
            columnName.append(word);
        } else if (word.matches("[A-Z][a-z]+")) {
            String firstLetter = word.substring(0, 1);
            String restOfWord = word.substring(1);

            columnName.append(firstLetter.toLowerCase());
            columnName.append(restOfWord);
        } else {
            columnName.append(word);
        }
    }
    
    return columnName.toString();
}