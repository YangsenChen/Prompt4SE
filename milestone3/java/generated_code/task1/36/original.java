public static List<Property> removeDups(List<Property> org) {
    List<Property> result = new ArrayList<>(org.size());
    for (Property p : org)
      if (!result.contains(p))  // O(n**2)
        result.add(p);
    return result;
  }