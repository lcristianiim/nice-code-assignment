package interviewschedule.model;

import java.text.MessageFormat;

public class Interviewer implements HasShortDescription {
    private String firstName;
    private String lastName;
    private String team;

    public Interviewer(String firstName, String lastName, String team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
    }

    @Override
    public String getShortDescription() {
        return MessageFormat.format("{0}, {1} - {2}", firstName, lastName, team);
    }

    @Override
    public String toString() {
        return firstName + lastName + team;
    }
}
