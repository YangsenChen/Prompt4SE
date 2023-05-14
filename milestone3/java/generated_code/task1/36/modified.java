public static List<Property> removeDups(List<Property> org) {
    HashMap<Property,Boolean> propertyMap = new HashMap<>(); // to keep track of visited properties
    List<Property> result = new ArrayList<Property>(); // to store distinct properties
    for (Property p : org){
        if (propertyMap.get(p) != null && propertyMap.get(p)){ // If the property already exists in the map
            continue;
        }
        result.add(p);
        propertyMap.put(p,true); // Adding the property to the map
    }
    return result;
}