public final void setErrorConverter(Converter<Map<String, String>, OAuth2Error> errorConverter) throws Exception {
    if (errorConverter == null) {
        throw new Exception("errorConverter cannot be null");
    }
    this.errorConverter = errorConverter;
}