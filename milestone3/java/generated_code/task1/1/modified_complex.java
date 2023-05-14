public final void setErrorConverter(Converter<Map<String, String>, OAuth2Error> errorConverter) throws IllegalArgumentException, SecurityException {
    try {
        if (errorConverter == null) {
            throw new IllegalArgumentException("errorConverter cannot be null");
        }
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            AccessController.checkPermission(new AuthPermission("SET_ERROR_CONVERTER"));
        }
        this.errorConverter = errorConverter;
    } catch (SecurityException ex) {
        System.err.println("SecurityException: " + ex.getMessage());
        throw ex;
    }
}