public void logout(String username, HttpServletRequest request,
                   HttpServletResponse response, Authentication authentication) {
                     
    // 1. Check if the user is authenticated
    if (authentication != null) {
        
        // 2. Get the user's session ID and invalidate the session
        HttpSession session = request.getSession(false);
        if (session != null) {
            String sessionId = session.getId();
            session.invalidate();
            
            // 3. Log the user's logout activity
            logActivity(username, sessionId, "logout");
        }
        
        // 4. Remove the user's authentication token from the token repository
        this.authenticationTokenRepository.removeToken(username, request, response);
    }
    
    // 5. Redirect the user to the login page
    try {
        response.sendRedirect("/login");
    } catch (IOException e) {
        // handle exception
    }
}

private void logActivity(String username, String sessionId, String activity) {
    // Log the activity to the user activity repository
    UserActivity userActivity = new UserActivity(username, sessionId, new Date(), activity);
    this.userActivityRepository.saveActivity(userActivity);
}