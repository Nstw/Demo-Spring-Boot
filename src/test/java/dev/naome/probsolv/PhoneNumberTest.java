package dev.naome.probsolv;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

public class PhoneNumberTest {

    @Test
    public void cleansTheNumber() {
        String expectedNumber = "2234567890";
        String actualNumber = new PhoneNumberService("(223) 456-7890").getNumber();

        assertThat(actualNumber).isEqualTo(expectedNumber);
    }

    @Test
    public void cleansNumbersWithDots() {
        String expectedNumber = "2234567890";
        String actualNumber = new PhoneNumberService("223.456.7890").getNumber();

        assertThat(actualNumber).isEqualTo(expectedNumber);
    }

    @Test
    public void cleansNumbersWithMultipleSpaces() {
        String expectedNumber = "2234567890";
        String actualNumber = new PhoneNumberService("223 456   7890   ").getNumber();

        assertThat(actualNumber).isEqualTo(expectedNumber);
    }

    @Test
    public void invalidWhen9Digits() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new PhoneNumberService("123456789"))
                .withMessage("must not be fewer than 10 digits");
    }

    @Test
    public void invalidWhen11DigitsDoesNotStartWith1() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new PhoneNumberService("22234567890"))
                .withMessage("11 digits must start with 1");
    }

    @Test
    public void validWhen11DigitsAndStartingWith1() {
        String expectedNumber = "2234567890";
        String actualNumber = new PhoneNumberService("12234567890").getNumber();

        assertThat(actualNumber).isEqualTo(expectedNumber);
    }

    @Test
    public void validWhen11DigitsAndStartingWith1EvenWithPunctuation() {
        String expectedNumber = "2234567890";
        String actualNumber = new PhoneNumberService("+1 (223) 456-7890").getNumber();

        assertThat(actualNumber).isEqualTo(expectedNumber);
    }

    @Test
    public void invalidWhenMoreThan11Digits() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new PhoneNumberService("321234567890"))
                .withMessage("must not be greater than 11 digits");
    }

    @Test
    public void invalidWithLetters() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new PhoneNumberService("523-abc-7890"))
                .withMessage("letters not permitted");
    }

    @Test
    public void invalidWithPunctuations() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new PhoneNumberService("523-@:!-7890"))
                .withMessage("punctuations not permitted");
    }

    @Test
    public void invalidIfAreaCodeStartsWith0() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new PhoneNumberService("(023) 456-7890"))
                .withMessage("area code cannot start with zero");
    }

    @Test
    public void invalidIfAreaCodeStartsWith1() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new PhoneNumberService("(123) 456-7890"))
                .withMessage("area code cannot start with one");
    }

    @Test
    public void invalidIfExchangeCodeStartsWith0() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new PhoneNumberService("(223) 056-7890"))
                .withMessage("exchange code cannot start with zero");
    }

    @Test
    public void invalidIfExchangeCodeStartsWith1() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new PhoneNumberService("(223) 156-7890"))
                .withMessage("exchange code cannot start with one");
    }

    @Test
    public void invalidIfAreaCodeStartsWith0OnValid11DigitNumber() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new PhoneNumberService("1 (023) 456-7890"))
                .withMessage("area code cannot start with zero");
    }

    @Test
    public void invalidIfAreaCodeStartsWith1OnValid11DigitNumber() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new PhoneNumberService("1 (123) 456-7890"))
                .withMessage("area code cannot start with one");
    }

    @Test
    public void invalidIfExchangeCodeStartsWith0OnValid11DigitNumber() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new PhoneNumberService("1 (223) 056-7890"))
                .withMessage("exchange code cannot start with zero");
    }

    @Test
    public void invalidIfExchangeCodeStartsWith1OnValid11DigitNumber() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new PhoneNumberService("1 (223) 156-7890"))
                .withMessage("exchange code cannot start with one");
    }
}
