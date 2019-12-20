package interviewschedule.schedule;

import interviewschedule.model.Candidate;
import interviewschedule.model.DurationInterview;
import interviewschedule.model.Interviewer;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class ScheduleImpl implements Schedule{
    private Repository repo;

    public ScheduleImpl(Repository repo) {
        this.repo = repo;
    }

    @Override
    public Set<Candidate> getCandidates() throws IOException {
        return repo.getCandidates();
    }

    @Override
    public Set<Interviewer> getInterviewers() throws IOException {
        return repo.getInterviewers();
    }

    @Override
    public Set<Candidate> getCandidatesByInterviewer(Interviewer interviewer) throws IOException {
        return repo.getCandidatesByInterviewer(interviewer);
    }

    @Override
    public Duration getCompletedInterviewTimeSpentOn(Candidate candidate) throws IOException {

        return sumOfDurations(repo.getCompletedDurationsInterviewsOfCandidate(candidate));
    }

    @Override
    public List<List<String>> getScheduleForAllInterviewsInThePast(LocalDate date) throws IOException {
        InterviewScheduleService service = new InterviewScheduleService(repo.getRawRows());
        return service.getInterviewsBeforeDate(date);
    }
    private Duration sumOfDurations(List<DurationInterview> durationsOfCandidate) {
        final Duration[] result = {Duration.ofHours(0)};
        durationsOfCandidate.forEach(d -> result[0] = result[0].plus(d.getDuration()));
        return result[0];
    }


}