package com.txtkm.txtkm.exceptions;

public class UserNotFoundException extends Exception {
    @Override
    public String toString() {
        return "User Not Found";
    }
}
