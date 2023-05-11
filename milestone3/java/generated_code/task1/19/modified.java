static private void showFormatInfo(JFormattedTextField tf) {
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
        }

        if (formatObj instanceof DecimalFormat) {
            DecimalFormat decimalFormat = (DecimalFormat) formatObj;

            String pattern = decimalFormat.toPattern();
            System.out.println("Pattern  = " + pattern);
        }
    }
}