package com.example.expense.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessage {
    public static final String INTERNAL_ERROR = "Internal error occurred.";
    public static final String MALFORMED_JSON_REQUEST = "Input format is invalid.";
    public static final String INVALID_DATA = "Input data is invalid.";
    public static final String INFO_NOT_FOUND = "Info not found.";
    public static final String DB_ERROR = "Database error occurred.";

    public static final String USER_ALREADY_EXISTS = "User already exists.";
    public static final String ROLE_NOT_FOUND = "Role not found.";

}