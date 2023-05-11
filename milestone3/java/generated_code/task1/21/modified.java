protected Set<Principal> getMethodRolesAsPrincipals(EjbMethodSecurityMetaData ejbMethodSecurityMetaData) {
    Set<Principal> methodRoles = new HashSet<Principal>();

    // Data Flow: Get EJB method security metadata
    boolean isDenyAll = ejbMethodSecurityMetaData.isDenyAll();
    boolean isPermitAll = ejbMethodSecurityMetaData.isPermitAll();
    List<String> rolesAllowed = ejbMethodSecurityMetaData.getRolesAllowed();

    // Control Flow: Determine principal based on security metadata
    if (isDenyAll) {
        methodRoles.add(NobodyPrincipal.NOBODY_PRINCIPAL);
    } else if (isPermitAll) {
        methodRoles.add(AnybodyPrincipal.ANYBODY_PRINCIPAL);
    } else {
        for (String role : rolesAllowed) {
            methodRoles.add(new SimplePrincipal(role));
        }
    }

    // Data Flow: Return Set of principals
    return methodRoles;
}

protected MethodInterfaceType getMethodInterfaceType(MethodIntf viewType) {

    // Control Flow: Determine MethodInterfaceType based on viewType
    switch (viewType) {
        case HOME:
            return MethodInterfaceType.Home;
        case LOCAL_HOME:
            return MethodInterfaceType.LocalHome;
        case SERVICE_ENDPOINT:
            return MethodInterfaceType.ServiceEndpoint;
        case LOCAL:
            return MethodInterfaceType.Local;
        case REMOTE:
            return MethodInterfaceType.Remote;
        case TIMER:
            return MethodInterfaceType.Timer;
        case MESSAGE_ENDPOINT:
            return MethodInterfaceType.MessageEndpoint;
        default:
            return null;
    }

}

protected String setContextID(final String contextID) {

    // Data Flow: Store previous context ID for return
    String previousID;

    // Control Flow: Set context ID based on WildFlySecurityManager
    if (!WildFlySecurityManager.isChecking()) {
        previousID = PolicyContext.getContextID();
        PolicyContext.setContextID(contextID);
    } else {
        final PrivilegedAction<String> action = new SetContextIDAction(contextID);
        previousID = AccessController.doPrivileged(action);
    }

    // Data Flow: Return previous context ID
    return previousID;
}

private static class SetContextIDAction implements PrivilegedAction<String> {

    // Data Flow: Store context ID for setting
    private String contextID;

    SetContextIDAction(final String contextID) {
        this.contextID = contextID;
    }

    @Override
    public String run() {
        // Data Flow: Store previous context ID for return
        final String previousID = PolicyContext.getContextID();
        PolicyContext.setContextID(this.contextID);

        // Data Flow: Return previous context ID
        return previousID;
    }
}