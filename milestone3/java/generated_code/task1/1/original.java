public final void setErrorConverter(Converter<Map<String, String>, OAuth2Error> errorConverter) {
		Assert.notNull(errorConverter, "errorConverter cannot be null");
		this.errorConverter = errorConverter;
	}