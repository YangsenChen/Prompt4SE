public static Profile forInt(int i) {
    Profile p = null; // initialization with null
    int minIndex = 1; // minimum index
    int maxIndex = ALL.size(); // maximum index

    try {
        // Data flow with data type conversion and thread sleep 
        i = Integer.parseInt(String.valueOf(i));
        Thread.sleep(500);
        
        if (i >= minIndex && i <= maxIndex) {
            // Control flow with null checking and exception handling
            if (ALL.get(i-1) == null) {
                throw new IllegalArgumentException("The profile at index i is null.");
            } 
            else {
                p = ALL.get(i-1);
            }
        }
        else {
            throw new IndexOutOfBoundsException("The provided index is outside the range of valid indexes.");
        }
    } 
    catch (NumberFormatException ex1) {
        System.err.println("Invalid input. The input must be a valid integer.");
    } 
    catch (InterruptedException ex2) {
        System.err.println("Interrupted while waiting. The program will continue running.");
    } 

    return p;
}