private static String log(String message, Object... args) throws Exception {
    if (message == null) {
        throw new Exception("Message is null");
    }

    try {
        StringBuilder logMessageBuilder = new StringBuilder();
        logMessageBuilder.append(LocalDateTime.now().toString());
        logMessageBuilder.append(" - ");
        logMessageBuilder.append(String.format(message, args));
        String logMessage = logMessageBuilder.toString();

        writeToFile(logMessage);

        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Server.class.getCanonicalName());
        logger.log(java.util.logging.Level.INFO, logMessage);
        return logMessage;
    } catch (Exception e) {
        java.util.logging.Logger.getLogger(Server.class.getCanonicalName()).log(java.util.logging.Level.SEVERE, "Error in logging message: " + message);
        return "";
    }
}

private static void writeToFile(String logMessage) throws IOException {
    String fileName = "logs.txt";
    File file = new File(fileName);

    if (!file.exists()) {
        boolean fileCreated = file.createNewFile();
        if (!fileCreated) {
            throw new IOException("Could not create log file");
        }
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
        writer.write(logMessage);
        writer.newLine();
    }
}