public String getDecimalSeparatorByDefaultLocale() {
    Locale defaultLocale = getDefaultLocale();
    DecimalFormat nf = createDecimalFormatWithLocale(defaultLocale);
    char decimalSeparator = getDecimalSeparatorFromFormat(nf);
    return String.valueOf(decimalSeparator);
}

private Locale getDefaultLocale() {
    // Some code to retrieve the default locale
}

private DecimalFormat createDecimalFormatWithLocale(Locale locale) {
    return (DecimalFormat) DecimalFormat.getInstance(locale);
}

private char getDecimalSeparatorFromFormat(DecimalFormat nf) {
    DecimalFormatSymbols symbols = nf.getDecimalFormatSymbols();
    return symbols.getDecimalSeparator();
}