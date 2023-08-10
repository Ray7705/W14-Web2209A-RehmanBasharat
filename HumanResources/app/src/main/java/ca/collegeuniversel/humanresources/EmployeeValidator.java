package ca.collegeuniversel.humanresources;

import android.content.res.Resources;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class EmployeeValidator {
    private final Resources resources;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
    private final String dateOfBirth;
    private final String jobTitle;
    private final String compensation;
    private final CompensationUnits compensationUnits;
    private final EmployeeType type;
    private final boolean manager;

    public EmployeeValidator(Resources resources, String firstName, String lastName, String password,
                             String email, String dateOfBirth, String jobTitle,
                             String compensation, CompensationUnits compensationUnits,
                             EmployeeType type, boolean manager) {
        this.resources = Objects.requireNonNull(resources);
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.jobTitle = jobTitle;
        this.compensation = compensation;
        this.compensationUnits = compensationUnits;
        this.type = type;
        this.manager = manager;
    }

    public String validateFirstName() {
        if (firstName.isEmpty()) {
            return resources.getString(R.string.enter_first_name);
        }

        return null;
    }

    public String validateLastName() {
        if (lastName.isEmpty()) {
            return resources.getString(R.string.enter_last_name);
        }

        return null;
    }

    public String validatePassword() {
        if (password.isEmpty()) {
            return resources.getString(R.string.enter_password);
        }

        if (password.length() < 8) {
            return resources.getString(R.string.password_must_have_at_least_8_characters);
        }

        return null;
    }

    public String validateEmail() {
        if (email.isEmpty()) {
            return resources.getString(R.string.enter_email);
        }

        if (!email.contains("@")) {
            return resources.getString(R.string.enter_valid_email);
        }

        return null;
    }

    public String validateDateOfBirth() {
        if (this.dateOfBirth.isEmpty()) {
            return resources.getString(R.string.enter_date_of_birth);
        }

        LocalDate dateOfBirth = tryParseLocalDate(this.dateOfBirth);
        if (dateOfBirth == null || dateOfBirth.getYear() < 1900 || dateOfBirth.isAfter(LocalDate.now())) {
            return resources.getString(R.string.enter_valid_date_of_birth);
        }

        return null;
    }

    public String validateJobTitle() {
        if (jobTitle.isEmpty()) {
            return resources.getString(R.string.enter_job_title);
        }

        return null;
    }

    public String validateCompensation() {
        if (this.compensation.isEmpty()) {
            return resources.getString(R.string.enter_compensation);
        }

        BigDecimal compensation = tryParseBigDecimal(this.compensation);
        if (compensation == null) {
            return resources.getString(R.string.enter_valid_compensation);
        }

        if (compensation.compareTo(BigDecimal.ZERO) <= 0) {
            return resources.getString(R.string.enter_compensation_greater_than_0);
        }

        if (compensationUnits == null) {
            return resources.getString(R.string.select_compensation_units);
        }

        return null;
    }

    public String validateType() {
        if (type == null) {
            return resources.getString(R.string.select_type);
        }

        if (type == EmployeeType.Contractor && compensationUnits != CompensationUnits.Hourly) {
            return resources.getString(R.string.contractors_require_hourly_compensation);
        }

        if (type == EmployeeType.Contractor && manager) {
            return resources.getString(R.string.contractors_cannot_be_hired_as_managers);
        }

        if (type == EmployeeType.Intern && manager) {
            return resources.getString(R.string.interns_cannot_be_hired_as_managers);
        }

        return null;
    }

    private static LocalDate tryParseLocalDate(String text) {
        try {
            return LocalDate.parse(text);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static BigDecimal tryParseBigDecimal(String text) {
        try {
            return new BigDecimal(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
