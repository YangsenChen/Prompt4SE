static public Scope stringToScope(String scopeStr) {
    Scope result = Scope.LOCAL;
    switch(scopeStr.toUpperCase()) {
      case "SYSTEM":
        result = Scope.SYSTEM;
        break;
      case "Context":
        result = Scope.CONTEXT;
        break;
    }
    return result;
}