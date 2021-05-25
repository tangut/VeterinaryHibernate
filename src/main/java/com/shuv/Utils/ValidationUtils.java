package com.shuv.Utils;

public class ValidationUtils {
    public static boolean isValidId(int id) {
        return id > 0;
    }

    public static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isValidAge(int age) {
        return age > 0;
    }

    public static boolean isValidPassword(String password) {
        return password != null && !(password.equals("")) && password.length() >= 5;
    }

    public static boolean isValidName(String name) {
        return name != null && !(name.equals(""));
    }
}
