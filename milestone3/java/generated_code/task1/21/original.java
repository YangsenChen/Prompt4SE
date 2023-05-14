protected Set<Principal> getMethodRolesAsPrincipals() {
        Set<Principal> methodRoles = new HashSet<Principal>();
        if (this.ejbMethodSecurityMetaData.isDenyAll())
            methodRoles.add(NobodyPrincipal.NOBODY_PRINCIPAL);
        else if (this.ejbMethodSecurityMetaData.isPermitAll())
            methodRoles.add(AnybodyPrincipal.ANYBODY_PRINCIPAL);
        else {
            for (String role : this.ejbMethodSecurityMetaData.getRolesAllowed())
                methodRoles.add(new SimplePrincipal(role));
        }
        return methodRoles;
    }

    
    protected MethodInterfaceType getMethodInterfaceType(MethodIntf viewType) {
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
        if (! WildFlySecurityManager.isChecking()) {
            final String previousID = PolicyContext.getContextID();
            PolicyContext.setContextID(contextID);
            return previousID;
        } else {
            final PrivilegedAction<String> action = new SetContextIDAction(contextID);
            return AccessController.doPrivileged(action);
        }
    }

   
    private static class SetContextIDAction implements PrivilegedAction<String> {

        private String contextID;

        SetContextIDAction(final String contextID) {
            this.contextID = contextID;
        }

        @Override
        public String run() {
            final String previousID = PolicyContext.getContextID();
            PolicyContext.setContextID(this.contextID);
            return previousID;
        }
    }
}