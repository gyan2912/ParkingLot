package org.parking.lot.abhigyan.validator;

public class IntegerValidator {
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
