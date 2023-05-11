package org.example;


import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest
{
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

}
