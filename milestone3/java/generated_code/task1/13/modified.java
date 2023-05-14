@Override
public void write(HttpServletResponse httpResponse, Object value) throws IOException {
  if (value instanceof StreamHandler) {
    StreamHandler<?> handler = (StreamHandler<?>) value;
    OutputStream outputStream = httpResponse.getOutputStream();
    handler.invokeHandler(outputStream);
  } else {
    throw new UnsupportedOperationException("Unexpected value type: " + value.getClass());
  }
}