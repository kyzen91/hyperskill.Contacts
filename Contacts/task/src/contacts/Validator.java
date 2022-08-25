package contacts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The validator class validates user inputs
 * User Input can be:
 * record data,
 * menu selections
 */
public class Validator {
    private final String phoneNumberFormat = // pattern for valid phone numbers
            "\\+?\\([a-zA-Z0-9]+\\)([ -][a-zA-Z0-9]{2,})*|" +
            "\\+?[a-zA-Z0-9]+[ -]\\([a-zA-Z0-9]{2,}\\)([ -][a-zA-Z0-9]{2,})*|" +
            "\\+?[a-zA-Z0-9]+([ -][a-zA-Z0-9]{2,})*";
    private final Pattern phoneNumberPattern;

    Validator() {
        this.phoneNumberPattern = Pattern.compile(phoneNumberFormat); // pattern is compiled, when object is instantiated
    }

    String isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = phoneNumberPattern.matcher(phoneNumber);
        if (matcher.matches()) {
            return phoneNumber;
        } else {
            System.out.println("Wrong number format!");
            return null;
        }
    }

    LocalDate isValidBirthDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        try {
            return LocalDate.parse(dateString, formatter);
        } catch(DateTimeParseException e) {
            System.out.println("Bad birth date!");
            return null;
        }
    }

    String isValidGender(String gender) {
        gender = gender.toLowerCase();
        if (gender.equals("m") || gender.equals("f")) {
            return gender.toUpperCase();
        } else {
            System.out.println("Bad gender!");
            return null;
        }
    }

    boolean isNaturalNumber(String str) {
        return str.matches("\\d+");
    }
}
