package org.example;

import java.lang.reflect.*;

/**
 * Hello world!
 *
 */
public class App 
{
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

        // System.out.println("Field visibility: " + Modifier.toString(field.getModifiers()));
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

//    // chatgpt generated  semantically equivalent code: test pass 3/3
//
//    // chatgpt made the following change: This implementation first determines the visibility level of a and b by calling the getVisibility method. The visibility levels are then compared to determine which member is more visible. If a is more visible than b, it is returned. If b is more visible than a, it is returned. If they have the same visibility level, a is returned (which is the same as the original implementation).
//    public static <T extends Member> T moreVisible(T a, T b) {
//        int am = a.getModifiers();
//        int bm = b.getModifiers();
//
//        // Determine the visibility level of a and b
//        int av = getVisibility(am);
//        int bv = getVisibility(bm);
//
//        // Compare the visibility levels
//        if (av > bv) {
//            return a;
//        } else if (bv > av) {
//            return b;
//        } else {
//            return a; // same
//        }
//    }
//
//    private static int getVisibility(int modifiers) {
//        if (Modifier.isPublic(modifiers)) {
//            return 2;
//        } else if (Modifier.isProtected(modifiers)) {
//            return 1;
//        } else if (Modifier.isPrivate(modifiers)) {
//            return 0;
//        } else {
//            return -1;
//        }
//    }



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
