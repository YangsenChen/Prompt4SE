public void logout(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		this.csrfTokenRepository.saveToken(null, request, response);
	}