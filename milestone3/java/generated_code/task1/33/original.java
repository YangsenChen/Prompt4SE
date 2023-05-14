protected boolean init(boolean fullCheck) throws IOException {
    if (rf == null) {
      throw new IOException("file has not been set");
    }

    dmLabel = new DMLabel();
    boolean labelOk = dmLabel.init();

    if (!labelOk) {
      logError("not a GEMPAK file");
      return false;
    }

    // Read the keys  (DM_RKEY)
    readKeys();
    if (keys == null) {
      logError("Couldn't read keys");
      return false;
    }

    // Read the headers (DM_RHDA)
    readHeaders();
    if (headers == null) {
      logError("Couldn't read headers");
      return false;
    }

    // Read the parts (DM_RPRT)
    readParts();
    if (parts == null) {
      logError("Couldn't read parts");
      return false;
    }

    // Read the file header info (DM_RFIL)
    readFileHeaderInfo();
    if (fileHeaderInfo == null) {
      logError("Couldn't read file header info");
      return false;
    }

    return true;
  }