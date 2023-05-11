public String getDecimalSeparatorByDefaultLocale() {
		final DecimalFormat nf = (DecimalFormat) DecimalFormat.getInstance(getDefaultLocale());
		return "" + nf.getDecimalFormatSymbols().getDecimalSeparator();
	}