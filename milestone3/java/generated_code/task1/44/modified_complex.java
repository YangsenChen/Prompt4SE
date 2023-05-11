public User createUser(User userParam) throws IOException {
    User user = null;

    try {
        // Validate user parameters
        if(userParam == null || this.serviceTicket == null || userParam.getName() == null
            || userParam.getAge() == 0 || userParam.getEmail() == null) {
            // Throw an exception if any of the required user parameters is null or empty
            throw new IllegalArgumentException("Invalid user parameters");
        } else {
            // Set the service ticket of the user parameter
            userParam.setServiceTicket(this.serviceTicket);

            // Create a new user object
            user = new User(this.putJson(userParam, WS.Path.User.Version1.userCreate()));

            // Send a welcome email to the user
            sendWelcomeEmail(user);
        }
    } catch(IOException e) {
        // Catch and log any IO exception and throw a custom exception
        logError(e);
        throw new IOException("Error creating user", e);
    } catch(Exception e) {
        // Catch and log any other exception and throw a custom exception
        logError(e);
        throw new Exception("Error creating user", e);
    }

    // Return the user object
    return user;
}

private void sendWelcomeEmail(User user) {
    // Send a welcome email to the user
    // ...
}

private void logError(Exception e) {
    // Log the error message
    // ...
}