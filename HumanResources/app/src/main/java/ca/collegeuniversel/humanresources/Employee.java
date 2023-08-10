package ca.collegeuniversel.humanresources;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1;
    @NonNull private static final BigDecimal WORK_HOURS_PER_YEAR = new BigDecimal(2000);

    @NonNull private final String id;
    @NonNull private final String firstName;
    @NonNull private final String lastName;
    @NonNull private final String email;
    @NonNull private final LocalDate dateOfBirth;
    @NonNull private final String jobTitle;
    @NonNull private final BigDecimal compensation;
    @NonNull private final CompensationUnits compensationUnits;
    @NonNull private final EmployeeType type;
    private final boolean manager;

    public Employee(String id, String firstName, String lastName, String email, LocalDate dateOfBirth,
                    String jobTitle, BigDecimal compensation, CompensationUnits compensationUnits,
                    EmployeeType type, boolean manager) {
        this.id = Objects.requireNonNull(id);
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.email = Objects.requireNonNull(email);
        this.dateOfBirth = Objects.requireNonNull(dateOfBirth);
        this.jobTitle = Objects.requireNonNull(jobTitle);
        this.compensation = Objects.requireNonNull(compensation).setScale(2, RoundingMode.HALF_EVEN);
        this.compensationUnits = Objects.requireNonNull(compensationUnits);
        this.type = Objects.requireNonNull(type);
        this.manager = manager;
    }

    @NonNull
    @Override
    public String toString() {
        return getFullName();
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    @NonNull
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @NonNull
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @NonNull
    public String getJobTitle() {
        return jobTitle;
    }

    @NonNull
    public BigDecimal getCompensation() {
        return compensation;
    }

    @NonNull
    public CompensationUnits getCompensationUnits() {
        return compensationUnits;
    }

    @NonNull
    public BigDecimal getCompensationPerYear() {
        if (compensationUnits == CompensationUnits.Hourly) {
            return compensation.multiply(WORK_HOURS_PER_YEAR).setScale(2, RoundingMode.HALF_EVEN);
        }

        return compensation;
    }

    @NonNull
    public BigDecimal getCompensationPerHour() {
        if (compensationUnits == CompensationUnits.Yearly) {
            return compensation.divide(WORK_HOURS_PER_YEAR, 2, RoundingMode.HALF_EVEN);
        }

        return compensation;
    }

    @NonNull
    public EmployeeType getType() {
        return type;
    }

    public boolean isManager() {
        return manager;
    }
}
