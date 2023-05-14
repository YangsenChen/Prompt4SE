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