public String formatDoubleWithLocale(double number, Locale locale) throws IllegalArgumentException {
    if (number < 0) {
        throw new IllegalArgumentException("Number must be positive");
    }

    DecimalFormat nf = createDecimalFormatWithLocale(locale);
    String formattedNumber = formatNumberWithDecimalFormat(number, nf);

    return formattedNumber;
}

private DecimalFormat createDecimalFormatWithLocale(Locale locale) {
    DecimalFormat nf = (DecimalFormat) DecimalFormat.getInstance(locale);
    nf.setGroupingUsed(true);
    return nf;
}

private String formatNumberWithDecimalFormat(double number, DecimalFormat nf) {
    nf.applyPattern("#,##0.00");
    return nf.format(number);
}