import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Main {

    @Retention(RetentionPolicy.RUNTIME)
    @interface MyAnnotation {
        String value();
    }

    static class MyClass {
        @MyAnnotation("exampleAnnotationValue")
        public void annotatedMethod() {}

        public void nonAnnotatedMethod() {}
    }

    public <A extends Annotation> A getAnnotationAnywhere(Class<A> annotationType) {
        A anno = getAnnotation(annotationType);
        if (anno == null) {
            Class<?> clazz = (Class<?>) GenericTypeReflector.erase(type);
            return clazz.getAnnotation(annotationType);
        } else {
            return anno;
        }
    }

    @Test
    public void testGetAnnotationAnywhereWithAnnotationPresent() {
        MyClass obj = new MyClass();
        MyAnnotation annotation = obj.getAnnotationAnywhere(MyAnnotation.class);
        assertNotNull(annotation);
        assertEquals("exampleAnnotationValue", annotation.value());
    }

    @Test
    public void testGetAnnotationAnywhereWithAnnotationNotPresent() {
        MyClass obj = new MyClass();
        Test annotation = obj.getAnnotationAnywhere(Test.class);
        assertNull(annotation);
    }

    @Test
    public void testGetAnnotationAnywhereWithNonAnnotatedClass() {
        String stringObject = "testString";
        Deprecated annotation = getAnnotationAnywhere(Deprecated.class);
        assertNull(annotation);
    }
}