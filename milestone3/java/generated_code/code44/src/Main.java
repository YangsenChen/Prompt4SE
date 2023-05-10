public class Main {
    private String serviceTicket = "abc123";

    public static void main(String[] args) {
        // example usage
        User user = new User("John Doe", "johndoe@example.com");
        Main main = new Main();
        User createdUser = main.createUser(user);
        System.out.println("New user created with ID " + createdUser.getId());
    }

    public User createUser(User userParam) {
        if (userParam != null && this.serviceTicket != null) {
            userParam.setServiceTicket(this.serviceTicket);
        }
        return new User(this.putJson(userParam, WS.Path.User.Version1.userCreate()));
    }

    private String putJson(User userParam, String url) {
        // HTTP PUT implementation goes here
        return "{\"id\": 123}";
    }

    @Test
    public void testCreateUserWithServiceTicket() {
        // given
        User user = new User("John Doe", "johndoe@example.com");
        Main main = new Main();
        user.setServiceTicket("ticket123");

        // when
        User createdUser = main.createUser(user);

        // then
        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertEquals(createdUser.getName(), user.getName());
        assertEquals(createdUser.getEmail(), user.getEmail());
        assertEquals(createdUser.getServiceTicket(), user.getServiceTicket());
    }

    @Test
    public void testCreateUserWithoutServiceTicket() {
        // given
        User user = new User("John Doe", "johndoe@example.com");
        Main main = new Main();

        // when
        User createdUser = main.createUser(user);

        // then
        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertEquals(createdUser.getName(), user.getName());
        assertEquals(createdUser.getEmail(), user.getEmail());
        assertNull(createdUser.getServiceTicket());
    }

    @Test
    public void testCreateUserWithNullParameter() {
        // given
        Main main = new Main();

        // when
        User createdUser = main.createUser(null);

        // then
        assertNull(createdUser);
    }
}

class User {
    private String id;
    private String name;
    private String email;
    private String serviceTicket;

    User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServiceTicket() {
        return this.serviceTicket;
    }

    public void setServiceTicket(String serviceTicket) {
        this.serviceTicket = serviceTicket;
    }
}

class WS {
    static class Path {
        static class User {
            static class Version1 {
                static String userCreate() {
                    return "https://example.com/api/v1/user/create";
                }
            }
        }
    }
}