private static String log(String message, Object... args) {
    try {
        message = String.format(message, args);
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Server.class.getCanonicalName());
        logger.log(java.util.logging.Level.SEVERE, message);
        return message;
    } catch (Exception e) {
        java.util.logging.Logger.getLogger(Server.class.getCanonicalName()).log(java.util.logging.Level.SEVERE, "Error in logging message: " + message);
        return "";
    }
}