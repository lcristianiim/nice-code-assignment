package interviewschedule.model;

public class InterviewerBuilder {
    public Interviewer build(String firstName, String lastName, String team) {
        return new Interviewer(firstName, lastName, team);
    }
}
