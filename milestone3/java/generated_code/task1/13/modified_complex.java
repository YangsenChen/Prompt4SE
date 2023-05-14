@Override
public void write(HttpServletResponse httpResponse, Object value) throws IOException {
  if (value instanceof StreamHandler) {
    StreamHandler<?> handler = (StreamHandler<?>) value;
    if (handler.isAuthorized()) {
      OutputStream outputStream = httpResponse.getOutputStream();
      try {
        handler.prepareHandler();
        handler.invokeHandler(outputStream);
      } catch (HandlerException e) {
        throw new IOException("Error occurred while invoking the handler", e);
      } finally {
        handler.cleanupHandler();
      }
    } else {
      httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
  } else {
    throw new UnsupportedOperationException("Unexpected value type: " + value.getClass());
  }
}