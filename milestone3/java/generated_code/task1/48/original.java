static public Scope stringToScope(String scopeStr) {
    if(Scope.SYSTEM.toString().equalsIgnoreCase(scopeStr))
      return Scope.SYSTEM;
     if(Scope.CONTEXT.toString().equalsIgnoreCase(scopeStr))
      return Scope.CONTEXT;

    return Scope.LOCAL;
  }