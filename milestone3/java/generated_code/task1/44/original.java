public User createUser(User userParam)
    {
        if(userParam != null && this.serviceTicket != null)
        {
            userParam.setServiceTicket(this.serviceTicket);
        }

        return new User(this.putJson(
                userParam, WS.Path.User.Version1.userCreate()));
    }