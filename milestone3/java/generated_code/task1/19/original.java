static private void showFormatInfo( JFormattedTextField tf) {
      JFormattedTextField.AbstractFormatter ff = tf.getFormatter();
      System.out.println("AbstractFormatter  " +  ff.getClass().getName());
      if (ff instanceof NumberFormatter) {
        NumberFormatter nf = (NumberFormatter) ff;
        Format f = nf.getFormat();
        System.out.println(" Format  = " + f.getClass().getName());
        if (f instanceof NumberFormat) {
          NumberFormat nfat = (NumberFormat) f;
          System.out.println(" getMinimumIntegerDigits=" +
                             nfat.getMinimumIntegerDigits());
          System.out.println(" getMaximumIntegerDigits=" +
                             nfat.getMaximumIntegerDigits());
          System.out.println(" getMinimumFractionDigits=" +
                             nfat.getMinimumFractionDigits());
          System.out.println(" getMaximumFractionDigits=" +
                             nfat.getMaximumFractionDigits());
        }
        if (f instanceof DecimalFormat) {
          DecimalFormat df = (DecimalFormat) f;
          System.out.println(" Pattern  = " + df.toPattern());
        }
      }
    }