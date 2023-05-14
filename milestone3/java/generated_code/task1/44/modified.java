public User createUser(User userParam)
{
    User user = null;
    if(userParam == null || this.serviceTicket == null) {
        // Throw an exception if the user parameter or serviceTicket is null
        throw new IllegalArgumentException("User parameter or service ticket is null");
    } else {
        // Set the service ticket of the user parameter
        userParam.setServiceTicket(this.serviceTicket);
        // Create a new user object
        user = new User(this.putJson(userParam, WS.Path.User.Version1.userCreate()));
    }

    // Return the user object
    return user;
}