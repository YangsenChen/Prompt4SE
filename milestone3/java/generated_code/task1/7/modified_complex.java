public static Map<String, List<String>> parseHeader(String headerString){
    Map<String, List<String>> headerInfo = new HashMap<>();

    String[] lines= headerString.split("\n");

    for (String line : lines) {
        String[] parts = line.split(":");

        if (parts.length >= 2) {
            String key = parts[0].trim();
            String[] values = parts[1].split(",");
            List<String> valueList = Arrays.asList(values);
            headerInfo.put(key, valueList);
        }
    }

    return headerInfo;
}

protected String headerInfoDump(Map<String, List<String>> headerInfo) {
  StringBuilder builder = new StringBuilder();

  headerInfo.forEach((title, values) -> {
    builder.append(title);
    builder.append(":::::");
    builder.append(String.join(",", values));
    builder.append(":::::\n");
  });

  return builder.toString();
}