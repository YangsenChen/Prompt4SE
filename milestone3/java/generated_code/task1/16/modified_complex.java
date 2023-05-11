public <A extends Annotation> A getAnnotationAnywhere(Class<A> annotationType) {
    A annotation = getAnnotation(annotationType);
    if (annotation != null) {
        return annotation;
    } else {
        // Look for annotation in all superclasses and interfaces
        List<Class<?>> classes = getAllSuperclassesAndInterfaces();
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(annotationType)) {
                return clazz.getAnnotation(annotationType);
            }
        }
        return null;
    }
}

private List<Class<?>> getAllSuperclassesAndInterfaces() {
    List<Class<?>> classes = new ArrayList<>();
    Class<?> currentClass = (Class<?>) GenericTypeReflector.erase(type);
    while (currentClass != null && !currentClass.equals(Object.class)) {
        classes.add(currentClass);
        Class<?>[] interfaces = currentClass.getInterfaces();
        for (Class<?> interfaze : interfaces) {
            if (!classes.contains(interfaze)) {
                classes.add(interfaze);
            }
        }
        currentClass = currentClass.getSuperclass();
    }
    return classes;
}