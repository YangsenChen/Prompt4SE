import org.junit.jupiter.api.Test;

import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {
    @Test
    public void testFieldVisibility() {
        Field field = null;
        try {
            field = ExampleClass.class.getDeclaredField("privateField");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        assertEquals("private", Modifier.toString(field.getModifiers()));
    }

    @Test
    public void testConstructorVisibility() {
        Constructor[] constructors = ExampleClass.class.getDeclaredConstructors();
        Constructor constructor = constructors[0];

        assertEquals("public", Modifier.toString(constructor.getModifiers()));
    }

    @Test
    public void testMethodVisibility() {
        Method[] methods = ExampleClass.class.getDeclaredMethods();
        Method method = methods[0];

        assertEquals("public", Modifier.toString(method.getModifiers()));
    }

    public static void main(String[] args) {
        // Example usage
        Class<ExampleClass> clazz = ExampleClass.class;
        Field field = null;
        try {
            field = clazz.getDeclaredField("privateField");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        Constructor[] constructors = clazz.getDeclaredConstructors();
        Method[] methods = clazz.getDeclaredMethods();
        Constructor constructor = constructors[0];
        Method method = methods[0];

        System.out.println("Field visibility: " + Modifier.toString(field.getModifiers()));
        System.out.println("Constructor visibility: " + Modifier.toString(constructor.getModifiers()));
        System.out.println("Method visibility: " + Modifier.toString(method.getModifiers()));

        Member moreVisibleMember = moreVisible(field, method);
        if (moreVisibleMember instanceof Field) {
            System.out.println("More visible member: " + Modifier.toString(moreVisibleMember.getModifiers())
                    + " " + ((Field) moreVisibleMember).getType().getName() + " " + moreVisibleMember.getName());
        } else if (moreVisibleMember instanceof Method) {
            System.out.println("More visible member: " + Modifier.toString(moreVisibleMember.getModifiers())
                    + " " + ((Method) moreVisibleMember).getReturnType().getName() + " " + moreVisibleMember.getName());
        }
    }

    public static <T extends Member> T moreVisible(T a, T b) {
        int am = a.getModifiers();
        int bm = b.getModifiers();
        if (isPublic(am))
            return a;
        if (isPublic(bm))
            return b;
        if (isProtected(am))
            return a;
        if (isProtected(bm))
            return b;
        if (isPrivate(bm))
            return a;
        if (isPrivate(am))
            return b;
        return a; // same
    }

    private static boolean isPublic(int modifiers) {
        return Modifier.isPublic(modifiers);
    }

    private static boolean isProtected(int modifiers) {
        return Modifier.isProtected(modifiers);
    }

    private static boolean isPrivate(int modifiers) {
        return Modifier.isPrivate(modifiers);
    }
}

class ExampleClass {
    private String privateField;

    public ExampleClass() {}

    public ExampleClass(String privateField) {
        this.privateField = privateField;
    }

    public String getPrivateField() {
        return privateField;
    }

    public void setPrivateField(String privateField) {
        this.privateField = privateField;
    }

    public void publicMethod() {}

    private void privateMethod() {}

    protected void protectedMethod() {}
}