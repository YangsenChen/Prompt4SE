package org.example;

public class OAuth2Error {

    private String error;
    private String errorDescription;
    private String errorUri;

    public OAuth2Error(String error, String errorDescription, String errorUri) {
        this.error = error;
        this.errorDescription = errorDescription;
        this.errorUri = errorUri;
    }

    public String getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public String getErrorUri() {
        return errorUri;
    }

    @Override
    public String toString() {
        return "OAuth2Error{" +
                "error='" + error + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                ", errorUri='" + errorUri + '\'' +
                '}';
    }
}

