public <A extends Annotation> A getAnnotationAnywhere(Class<A> annotationType) {
		A anno = getAnnotation(annotationType);
		if (anno == null) {
			Class<?> clazz = (Class<?>) GenericTypeReflector.erase(type);
			return clazz.getAnnotation(annotationType);
		} else {
			return anno;
		}
	}