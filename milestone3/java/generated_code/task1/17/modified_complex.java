public boolean updateLastIndexed(TableMetadata metadata, long lastIndexed) {
    
    // Control flow: check if database is reachable
    if (!isDatabaseReachable()){
        return false; // If database is not reachable, return false
    }
    
    boolean isUpdated = false; // Initialize a boolean variable to false
    
    try {
        int geoPackageId = metadata.getGeoPackageId(); // Data flow: get geoPackage ID from metadata object
        String tableName = metadata.getTableName(); // Data flow: get table name from metadata object
        
        Connection dbConnection = connectToDB(); // Control flow: connect to database
        PreparedStatement preparedStatement = dbConnection.prepareStatement("UPDATE TableMetadata SET lastIndexed=? WHERE geoPackageId=? AND tableName=?");
        
        preparedStatement.setLong(1, lastIndexed); // Data flow: set lastIndexed value in preparedStatement
        preparedStatement.setInt(2, geoPackageId); // Data flow: set geoPackage ID in preparedStatement
        preparedStatement.setString(3, tableName); // Data flow: set table name in preparedStatement
        
        int rowsAffected = preparedStatement.executeUpdate(); // Control flow: execute update statement and get number of rows affected
        
        if (rowsAffected > 0) {
            metadata.setLastIndexed(lastIndexed); // Data flow: set the lastIndexed value in metadata object
            isUpdated = true; // Control flow: update boolean variable to indicate success
        }

        closeDBConnection(dbConnection); // Control flow: close database connection
        
    } catch (SQLException e) {
        // Handle exception
    }
    
    return isUpdated; // Control flow: return the value of boolean variable 'isUpdated'
}

private boolean isDatabaseReachable(){
    // Implementation of method to check if database is reachable
    return true; // Return boolean value of true if database is reachable, false otherwise
}

private Connection connectToDB(){
    // Implementation of method to connect to database
    return null; // Return database connection object
}

private void closeDBConnection(Connection dbConnection){
    // Implementation of method to close database connection
}