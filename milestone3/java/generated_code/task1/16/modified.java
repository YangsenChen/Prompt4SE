public <A extends Annotation> A getAnnotationAnywhere(Class<A> annotationType) {
		A anno = getAnnotation(annotationType); // get annotation from the current class
		if (anno != null) {
			return anno; // if annotation is present in current class then return it
		} 
		return getClassAnnotation(annotationType); //if not present then get class annotation and return it
		
	}

@SuppressWarnings("unchecked")
private <A extends Annotation> A getClassAnnotation(Class<A> annotationType) {
    Class<?> clazz = (Class<?>) GenericTypeReflector.erase(type); 
    // get the class from the type by erasing type variable information
    if (clazz.isAnnotationPresent(annotationType)) {
      //check if the annotation is present in the class
        return clazz.getAnnotation(annotationType);
      //if it is present then return the annotation
    }

    Class<?> superClass = clazz.getSuperclass(); 
    //get the super class of the class
    if (superClass != null) {
      //if super class exist
        return superClass.getDeclaredAnnotation(annotationType); 
        // get the annotation from super class and return it
    }

    return null; // else return null
}