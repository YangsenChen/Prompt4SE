protected boolean init(boolean fullCheck) throws IOException {
    boolean isInitSuccessful = true;
    
    // Data Flow
    dmLabel = new DMLabel();
    keys = null;
    headers = null;
    parts = null;
    fileHeaderInfo = null;
    
    // Control flow
    if (rf == null) {
        throw new IOException("file has not been set");
    }
    
    try {
        // Read the label (DM_LABEL)
        boolean labelOk = dmLabel.init();
        
        if (!labelOk) {
            logError("not a GEMPAK file");
            return false;
        }
        
        // Read the keys  (DM_RKEY)
        readKeys();
        
        if (keys == null) {
            logError("Couldn't read keys");
            isInitSuccessful = false;
        }
        
        // Read the headers (DM_RHDA)
        if(isInitSuccessful) {
            readHeaders();
            
            if (headers == null) {
                logError("Couldn't read headers");
                isInitSuccessful = false;
            }
        }
        
        // Read the parts (DM_RPRT)
        if(isInitSuccessful) {
            readParts();
            
            if (parts == null) {
                logError("Couldn't read parts");
                isInitSuccessful = false;
            }
        }
        
        // Read the file header info (DM_RFIL)
        if(isInitSuccessful) {
            readFileHeaderInfo();
            
            if (fileHeaderInfo == null) {
                logError("Couldn't read file header info");
                isInitSuccessful = false;
            }
        }
        
        // Extra checks (not part of original code)
        if(fullCheck && isInitSuccessful) {
            if(!checkValidity()) {
                logError("Invalid file");
                isInitSuccessful = false;
            }
            
            if(!checkPermissions()) {
                logError("Insufficient permissions to read file");
                isInitSuccessful = false;
            }
        }
    } catch(IOException e) {
        logError("Error reading file: " + e.getMessage());
        isInitSuccessful = false;
    }
    
    return isInitSuccessful;
}