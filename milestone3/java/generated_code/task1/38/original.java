private static String log(String message, Object... args) {
		message = String.format(message, args);
		java.util.logging.Logger.getLogger(Server.class.getCanonicalName()).log(java.util.logging.Level.SEVERE, message);
		return message;
	}