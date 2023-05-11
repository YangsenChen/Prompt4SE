public static <T extends Member> T moreVisible(T a, T b) {
    ModifierType am = ModifierType.fromModifiers(a.getModifiers());
    ModifierType bm = ModifierType.fromModifiers(b.getModifiers());
    switch (am) {
        case PUBLIC:
            return a;
        case PROTECTED:
            if (bm == ModifierType.PRIVATE) {
                return a;
            }
            break;
        case PRIVATE:
            return b;
    }
    switch (bm) {
        case PUBLIC:
            return b;
        case PROTECTED:
            return a;
        case PRIVATE:
            return a;
    }
    return a; // same
}

enum ModifierType {
    PUBLIC,
    PROTECTED,
    PRIVATE;

    static ModifierType fromModifiers(int modifiers) {
        if (Modifier.isPublic(modifiers)) {
            return PUBLIC;
        } else if (Modifier.isProtected(modifiers)) {
            return PROTECTED;
        } else {
            return PRIVATE;
        }
    }
}