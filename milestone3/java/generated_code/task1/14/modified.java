public static Profile forInt(int i) {
    Profile p;
    int MIN = 1; // minimum index
    int MAX = ALL.length; // maximum index

    // Control flow with data validation
    if (i < MIN || i > MAX) {
        p = UNKNOWN;
    } 
    else {
        p = ALL[i-1];
    }
    return p;
}