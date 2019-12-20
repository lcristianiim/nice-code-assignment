package interviewschedule.model;

public class CandidateBuilder {
    public Candidate build(String firstName, String lastName, String role) {
        return new Candidate(firstName, lastName, role);
    }
}
