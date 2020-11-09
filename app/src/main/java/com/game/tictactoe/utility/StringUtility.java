package com.game.tictactoe.utility;

public class StringUtility {
    //It will return the cell position(row & column) after converting in string like
    public static String stringFromNumbers(int... numbers) {
        StringBuilder sNumbers = new StringBuilder();
        for (int number : numbers)
            sNumbers.append(number);
        return sNumbers.toString();
    }
    //  returns true if value is null,undefined or empty
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.length() == 0;
    }
}
