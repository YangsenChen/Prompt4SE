static private void showFormatInfo(JFormattedTextField tf) throws Exception {
    if (tf == null) {
        throw new IllegalArgumentException("JFormattedTextField cannot be null");
    }

    Object formatterObj = tf.getFormatter();
    if (formatterObj == null) {
        throw new IllegalArgumentException("Formatter cannot be null");
    }

    String formatterName = formatterObj.getClass().getName();
    System.out.println("AbstractFormatter " + formatterName);

    if (formatterObj instanceof NumberFormatter) {
        NumberFormatter formatter = (NumberFormatter) formatterObj;

        Object formatObj = formatter.getFormat();
        if (formatObj == null) {
            throw new IllegalArgumentException("Format cannot be null");
        }

        String formatName = formatObj.getClass().getName();
        System.out.println("Format  = " + formatName);

        if (formatObj instanceof NumberFormat) {
            NumberFormat numberFormat = (NumberFormat) formatObj;

            int minIntegerDigits = numberFormat.getMinimumIntegerDigits();
            int maxIntegerDigits = numberFormat.getMaximumIntegerDigits();
            int minFractionDigits = numberFormat.getMinimumFractionDigits();
            int maxFractionDigits = numberFormat.getMaximumFractionDigits();

            System.out.println("getMinimumIntegerDigits=" + minIntegerDigits);
            System.out.println("getMaximumIntegerDigits=" + maxIntegerDigits);
            System.out.println("getMinimumFractionDigits=" + minFractionDigits);
            System.out.println("getMaximumFractionDigits=" + maxFractionDigits);

            double value = 1234.5678;
            String formattedValue = numberFormat.format(value);
            System.out.println("Formatted Value = " + formattedValue);
        }

        if (formatObj instanceof DecimalFormat) {
            DecimalFormat decimalFormat = (DecimalFormat) formatObj;

            String pattern = decimalFormat.toPattern();
            System.out.println("Pattern  = " + pattern);

            int maxDigitsBeforeDecimal = decimalFormat.getMaximumIntegerDigits();
            int minDigitsBeforeDecimal = decimalFormat.getMinimumIntegerDigits();
            int maxDigitsAfterDecimal = decimalFormat.getMaximumFractionDigits();
            int minDigitsAfterDecimal = decimalFormat.getMinimumFractionDigits();

            System.out.println("Max Digits Before Decimal = " + maxDigitsBeforeDecimal);
            System.out.println("Min Digits Before Decimal = " + minDigitsBeforeDecimal);
            System.out.println("Max Digits After Decimal = " + maxDigitsAfterDecimal);
            System.out.println("Min Digits After Decimal = " + minDigitsAfterDecimal);
        }
    }

    if (formatterObj instanceof MaskFormatter) {
        MaskFormatter formatter = (MaskFormatter) formatterObj;

        String mask = formatter.getMask();
        System.out.println("Mask = " + mask);

        char placeholder = formatter.getPlaceholder();
        System.out.println("Placeholder = " + placeholder);
    }

    // Change the format to a date format and display some information about it
    DateFormat dateFormat = DateFormat.getDateInstance();
    formatterObj = new DateFormatter(dateFormat);
    tf.setFormatterFactory(new DefaultFormatterFactory(formatterObj));

    formatterObj = tf.getFormatter();
    if (formatterObj instanceof DateFormatter) {
        DateFormatter formatter = (DateFormatter) formatterObj;

        Date value = new Date();
        String formattedValue = formatter.valueToString(value);
        System.out.println("Formatted Value = " + formattedValue);

        DateFormat df = formatter.getFormat();
        System.out.println("Date Format = " + df.getClass().getName());
    }
}