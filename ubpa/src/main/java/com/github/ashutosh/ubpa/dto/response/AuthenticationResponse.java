package com.github.ashutosh.ubpa.dto.response;

public class AuthenticationResponse {

    private String message;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
