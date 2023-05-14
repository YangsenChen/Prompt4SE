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

    if(isInitSuccessful) {
      // Read the headers (DM_RHDA)
      readHeaders();

      if (headers == null) {
        logError("Couldn't read headers");
        isInitSuccessful = false;
      }
    }
    
    if(isInitSuccessful) {
      // Read the parts (DM_RPRT)
      readParts();

      if (parts == null) {
        logError("Couldn't read parts");
        isInitSuccessful = false;
      }
    }

    if(isInitSuccessful) {
      // Read the file header info (DM_RFIL)
      readFileHeaderInfo();

      if (fileHeaderInfo == null) {
        logError("Couldn't read file header info");
        isInitSuccessful = false;
      }
    }

    return isInitSuccessful;
}