Copy code
public void logout(HttpServletRequest request, HttpServletResponse response,
                   Authentication authentication, CsrfTokenRepository csrfTokenRepository) {
    // Check if the user is authenticated
    if (authentication != null && authentication.isAuthenticated()) {
        // Save a null token to the repository
        csrfTokenRepository.saveToken(null, request, response);
    }
}