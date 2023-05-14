// Define an enum for the available scopes
enum Scope {
    LOCAL, SYSTEM, CONTEXT
}

// A class that handles scope operations
class ScopeManager {
    // A hash map containing the available scopes
    private final Map<String, Scope> scopeMap = new HashMap<>();

    // Constructor that initializes the scope map
    public ScopeManager() {
        scopeMap.put("System", Scope.SYSTEM);
        scopeMap.put("Context", Scope.CONTEXT);
        scopeMap.put("Local", Scope.LOCAL);
    }

    // A method that returns the scope based on the scopeStr parameter
    public Scope stringToScope(String scopeStr) throws IllegalArgumentException {
        // Check if the input string is valid
        if (!scopeMap.containsKey(scopeStr)) {
            throw new IllegalArgumentException("Invalid scope string");
        }

        // Return the scope corresponding to the input string
        return scopeMap.get(scopeStr);
    }
}