private static void padInt(StringBuilder buffer, int value, int length) {
    String strValue = Integer.toString(value);
    for (int i = length - strValue.length(); i > 0; i--) {
        buffer.append('0');
    }
    buffer.append(strValue);
}
