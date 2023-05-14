public static List<Property> removeDups(List<Property> org) {
    Set<Property> propertySet = new HashSet<>(); // to keep track of visited properties
    List<Property> result = new ArrayList<>(); // to store distinct properties
    for (Property p : org){
        if(propertySet.contains(p)){ // If the property already exists in the set
            continue;
        }else{
            propertySet.add(p); // Adding the property to the set
            result.add(p);
        }
    }
    return result;
}