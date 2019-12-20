package interviewschedule.schedule;

import interviewschedule.model.Candidate;
import interviewschedule.model.DurationInterview;
import interviewschedule.model.Interviewer;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface Repository {
    Set<Candidate> getCandidates() throws IOException;
    Set<Interviewer> getInterviewers() throws IOException;
    Set<Candidate> getCandidatesByInterviewer(Interviewer interviewer) throws IOException;
    List<DurationInterview> getCompletedDurationsInterviewsOfCandidate(Candidate candidate) throws IOException;
    List<List<String>> getRawRows() throws IOException;
}
