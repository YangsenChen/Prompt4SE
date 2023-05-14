protected String headerInfoDump(Map<String, String> headerInfo) {
  StringBuilder builder = new StringBuilder();

  headerInfo.forEach((title, value) -> {
    builder.append(title);
    builder.append(":::::");
    builder.append(value);
    builder.append(":::::\n");
  });

  return builder.toString();
}