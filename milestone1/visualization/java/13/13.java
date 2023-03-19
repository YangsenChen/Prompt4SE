private static boolean checkOffset(String value, int offset, char expected) {
    return (offset < value.length()) && (value.charAt(offset) == expected);
}