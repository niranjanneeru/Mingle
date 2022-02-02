package com.txtkm.txtkm.utility;

import com.txtkm.txtkm.database.Profile;

import java.util.regex.Pattern;

public class Utility {

    public static Profile profile;

    private static String regexPattern = "^(.+)@(\\S+)$";

    protected static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public static boolean emailPatternMatches(String emailAddress) {
        return patternMatches(emailAddress, regexPattern);
    }


}
