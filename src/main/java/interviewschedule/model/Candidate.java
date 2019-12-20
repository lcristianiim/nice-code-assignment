package interviewschedule.model;

import java.text.MessageFormat;

public class Candidate implements HasShortDescription {

    private String firstName;
    private String lastName;
    private String role;

    public Candidate(String firstName, String lastName, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    @Override
    public String getShortDescription() {
        return MessageFormat.format("{0}, {1} - {2}", firstName, lastName, role);
    }

    @Override
    public String toString() {
        return firstName + lastName + role;
    }
}
