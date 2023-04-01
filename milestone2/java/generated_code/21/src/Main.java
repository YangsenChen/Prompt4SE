import org.junit.Assert;
import org.junit.Test;
import java.security.Principal;
import java.security.Policy;
import java.security.PolicyContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Set;
import java.util.HashSet;

public class Main {

    @Test
    public void testGetMethodRolesAsPrincipals() {
        // Arrange
        EjbMethodSecurityMetaData metadata = new EjbMethodSecurityMetaData();
        metadata.setRolesAllowed("admin", "user");

        // Act
        Set<Principal> principals = getMethodRolesAsPrincipals(metadata);

        // Assert
        Assert.assertEquals(principals.size(), 2);
        for (Principal principal : principals) {
            Assert.assertTrue(principal instanceof SimplePrincipal);
            Assert.assertTrue(principal.getName().equals("admin") || principal.getName().equals("user"));
        }
    }

    @Test
    public void testGetMethodInterfaceType() {
        // Arrange
        MethodIntf intf = MethodIntf.LOCAL;

        // Act
        MethodInterfaceType intfType = getMethodInterfaceType(intf);

        // Assert
        Assert.assertEquals(intfType, MethodInterfaceType.Local);
    }

    @Test
    public void testSetContextID() {
        // Arrange
        String contextID = "testContextID";
        String expectedPreviousID = "previousContextID";
        PolicyContext.setContextID(expectedPreviousID);

        // Act
        String previousID = setContextID(contextID);

        // Assert
        Assert.assertEquals(previousID, expectedPreviousID);
        Assert.assertEquals(PolicyContext.getContextID(), contextID);
    }

    protected Set<Principal> getMethodRolesAsPrincipals(EjbMethodSecurityMetaData metadata) {
        Policy.setPolicy(new SecurityPolicy());
        Set<Principal> methodRoles = new HashSet<Principal>();
        if (metadata.isDenyAll())
            methodRoles.add(NobodyPrincipal.NOBODY_PRINCIPAL);
        else if (metadata.isPermitAll())
            methodRoles.add(AnybodyPrincipal.ANYBODY_PRINCIPAL);
        else {
            for (String role : metadata.getRolesAllowed())
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

    private static class SecurityPolicy extends Policy {
        @Override
        public boolean implies(ProtectionDomain domain, Permission permission) {
            // Permission check logic goes here
            return true;
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