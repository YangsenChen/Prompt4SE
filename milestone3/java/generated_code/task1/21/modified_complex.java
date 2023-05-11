protected Set<Principal> getMethodRolesAsPrincipals(EjbMethodSecurityMetaData ejbMethodSecurityMetaData) {
    Set<Principal> methodRoles = new HashSet<>();

    // Data Flow: Get EJB method security metadata
    boolean isDenyAll = ejbMethodSecurityMetaData.isDenyAll();
    boolean isPermitAll = ejbMethodSecurityMetaData.isPermitAll();
    List<String> rolesAllowed = ejbMethodSecurityMetaData.getRolesAllowed();

    // Control Flow: Determine principal based on security metadata
    if (isDenyAll) {
        // Data Flow: Add NobodyPrincipal
        methodRoles.add(NobodyPrincipal.NOBODY_PRINCIPAL);
    } else if (isPermitAll) {
        // Data Flow: Add AnybodyPrincipal
        methodRoles.add(AnybodyPrincipal.ANYBODY_PRINCIPAL);
    } else {
        // Control Flow: Check if CallerPrincipal is present
        if (ejbMethodSecurityMetaData.isCallerPrincipal()) {
            // Data Flow: Add CallerPrincipal
            methodRoles.add(new SimplePrincipal("CallerPrincipal"));
        }
        // Control Flow: Check if UserRoles are present
        if (!ejbMethodSecurityMetaData.getUserRoles().isEmpty()) {
            // Data Flow: Add UserRoles
            for (String role : ejbMethodSecurityMetaData.getUserRoles()) {
                methodRoles.add(new SimplePrincipal(role));
            }
        }
        // Control Flow: Check if GroupRoles are present
        if (!ejbMethodSecurityMetaData.getGroupRoles().isEmpty()) {
            // Data Flow: Add GroupRoles
            for (String role : ejbMethodSecurityMetaData.getGroupRoles()) {
                methodRoles.add(new GroupPrincipal(role));
            }
        }
        // Control Flow: Check if AdditionalRoles are present
        if (!ejbMethodSecurityMetaData.getAdditionalRoles().isEmpty()) {
            // Data Flow: Add AdditionalRoles
            for (String role : ejbMethodSecurityMetaData.getAdditionalRoles()) {
                methodRoles.add(new AdditionalPrincipal(role));
            }
        }
    }

    // Data Flow: Return Set of principals
    return methodRoles;
}

protected MethodInterfaceType getMethodInterfaceType(MethodIntf viewType) {

    // Control Flow: Determine MethodInterfaceType based on viewType
    switch (viewType) {
        case HOME:
        case LOCAL_HOME:
            // Data Flow: Return MethodInterfaceType.Home or MethodInterfaceType.LocalHome
            return viewType.equals(MethodIntf.HOME) ? MethodInterfaceType.Home : MethodInterfaceType.LocalHome;
        case SERVICE_ENDPOINT:
            // Data Flow: Return MethodInterfaceType.ServiceEndpoint
            return MethodInterfaceType.ServiceEndpoint;
        case LOCAL:
            // Data Flow: Return MethodInterfaceType.Local
            return MethodInterfaceType.Local;
        case REMOTE:
            // Data Flow: Return MethodInterfaceType.Remote
            return MethodInterfaceType.Remote;
        case TIMER:
            // Data Flow: Return MethodInterfaceType.Timer
            return MethodInterfaceType.Timer;
        case MESSAGE_ENDPOINT:
            // Data Flow: Return MethodInterfaceType.MessageEndpoint
            return MethodInterfaceType.MessageEndpoint;
        default:
            // Data Flow: Return null
            return null;
    }

}

protected String setContextID(final String contextID) {

    // Data Flow: Store previous context ID for return
    String previousID;

    // Control Flow: Set context ID based on WildFlySecurityManager
    if (!WildFlySecurityManager.isChecking()) {
        // Data Flow: Store previous context ID
        previousID = PolicyContext.getContextID();
        // Data Flow: Set context ID
        PolicyContext.setContextID(contextID);
    } else {
        final PrivilegedAction<String> action = new SetContextIDAction(contextID);
        // Data Flow: Run SetContextIDAction
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
        // Data Flow: Store previous context ID
        final String previousID = PolicyContext.getContextID();
        // Data Flow: Set context ID
        PolicyContext.setContextID(this.contextID);
        // Data Flow: Return previous context ID
        return previousID;
    }
}