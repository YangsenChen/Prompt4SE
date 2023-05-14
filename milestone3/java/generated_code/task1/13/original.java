@Override
	public void write(HttpServletResponse httpResponse, Object value) throws IOException {
		((StreamHandler<?>) value).invokeHandler(httpResponse.getOutputStream());
	}