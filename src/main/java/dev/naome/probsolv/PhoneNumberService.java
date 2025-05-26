package dev.naome.probsolv;

public class PhoneNumberService {
    private final String number;

    PhoneNumberService(String numberString) {
        // Check for letters first
        if (numberString.matches(".*[a-zA-Z].*")) {
            throw new IllegalArgumentException("letters not permitted");
        }

        // Check for invalid punctuation
        if (numberString.matches(".*[@:!].*")) {
            throw new IllegalArgumentException("punctuations not permitted");
        }

        // Remove all non-digit characters
        String digits = numberString.replaceAll("[^0-9]", "");

        // Validate length
        if (digits.length() < 10) {
            throw new IllegalArgumentException("must not be fewer than 10 digits");
        }
        if (digits.length() > 11) {
            throw new IllegalArgumentException("must not be greater than 11 digits");
        }

        // Handle 11-digit numbers
        if (digits.length() == 11) {
            if (digits.charAt(0) != '1') {
                throw new IllegalArgumentException("11 digits must start with 1");
            }
            digits = digits.substring(1);
        }

        // Validate area code
        if (digits.charAt(0) == '0') {
            throw new IllegalArgumentException("area code cannot start with zero");
        }
        if (digits.charAt(0) == '1') {
            throw new IllegalArgumentException("area code cannot start with one");
        }

        // Validate exchange code
        if (digits.charAt(3) == '0') {
            throw new IllegalArgumentException("exchange code cannot start with zero");
        }
        if (digits.charAt(3) == '1') {
            throw new IllegalArgumentException("exchange code cannot start with one");
        }

        this.number = digits;
    }

    String getNumber() {
        return number;
    }
}
