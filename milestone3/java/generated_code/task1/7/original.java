protected String headerInfoDump()
  {
    StringBuilder retVal = new StringBuilder( );
    for ( String curHeaderTitle : this.headerInfo.keySet() ) {
      String curHeaderValue = this.headerInfo.get( curHeaderTitle );
      retVal.append( curHeaderTitle );
      retVal.append( ":::::" );
      retVal.append( curHeaderValue );
      retVal.append( ":::::\n" );
    }

    return( retVal.toString() );
  }