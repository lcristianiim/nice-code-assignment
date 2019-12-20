package interviewschedule.schedule;

import interviewschedule.data.FileBasedDataRepo;
import interviewschedule.model.CandidateBuilder;
import interviewschedule.model.HasShortDescription;
import interviewschedule.model.InterviewerBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleImplTest {
    private Schedule schedule;

    @BeforeEach
    private void getSchedule(){
        schedule = new ScheduleImpl(new FileBasedDataRepo());
    }

    @Test
    void getCandidates() throws IOException {
        Set<String> shortDescriptionSet = getShortDescriptionSet(schedule.getCandidates());
        Set<String> expectedShortDescriptionSet = createSet(
                "Kravitz, Keira - Developer",
                "Klink, Kina - Quality Assurance",
                "Lesher, Liz - Business Analyst"
        );

        assertEquals(expectedShortDescriptionSet, shortDescriptionSet);
    }

    @Test
    void getInterviewers() throws IOException {
        Set<String> shortDescriptionSet = getShortDescriptionSet(schedule.getInterviewers());
        Set<String> expectedShortDescriptionSet = createSet(
                "Altamirano, Alleen - A-team",
                "Amell, Aide - A-team",
                "Austell, Anita - A-team",
                "Behan, Birgit - B-team",
                "Brosnan, Billi - B-team",
                "Cripe, Chauncey - C-team",
                "Hurston, Herbert - H-team",
                "Imhoff, Irvin - I-team",
                "Ito, Inger - I-team"
                );

        assertEquals(expectedShortDescriptionSet, shortDescriptionSet);
    }

    @Test
    void getCandidatesByInterviewer() throws IOException {
        Set<String> shortDescriptionSet = getShortDescriptionSet(schedule.getCandidatesByInterviewer(new InterviewerBuilder().build("Alleen", "Altamirano", "A-team")));
        Set<String> expectedShortDescriptionSet = createSet(
                "Kravitz, Keira - Developer",
                "Lesher, Liz - Business Analyst"
        );

        assertEquals(expectedShortDescriptionSet, shortDescriptionSet);
    }

    @Test
    void getCompletedInterviewTimeSpentOn() throws IOException {
        Duration interviewTimeSpentBy = schedule.getCompletedInterviewTimeSpentOn(new CandidateBuilder().build("Kina", "Klink", "Quality Assurance"));
        Duration expectedTimeSpent = Duration.ofHours(3).plusMinutes(30);

        assertEquals(expectedTimeSpent, interviewTimeSpentBy);
    }

    @Test
    void getScheduleForAllInterviewsInThePast() throws IOException {
        // expected data valid for date 26/10/2019
        String[][] expectedScheduleData = {
                {"Interviewer last name", "Interviewer first name", "Interviewer team", "Candidate last name", "Candidate first name", "Candidate role", "Interview type", "Interview time ago"},
                {"Altamirano", "Alleen", "A-team", "Kravitz", "Keira", "Developer", "Onsite", "1 year ago"},
                {"Altamirano", "Alleen", "A-team", "Lesher", "Liz", "Business Analyst", "Telefonic", "1 day ago"},
                {"Amell", "Aide", "A-team", "Kravitz", "Keira", "Developer", "Telefonic", "2 years ago"},
                {"Austell", "Anita", "A-team", "Kravitz", "Keira", "Developer", "HR", "5 months ago"},
                {"Behan", "Birgit", "B-team", "Klink", "Kina", "Quality Assurance", "Telefonic", "3 weeks ago"},
                {"Brosnan", "Billi", "B-team", "Klink", "Kina", "Quality Assurance", "Telefonic", "1 month ago"},
                {"Cripe", "Chauncey", "C-team", "Klink", "Kina", "Quality Assurance", "Onsite", "1 week ago"},
                {"Hurston", "Herbert", "H-team", "Klink", "Kina", "Quality Assurance", "HR", "4 days ago"},

        };

        List<List<String>> interviewScheduleInThePast = schedule.getScheduleForAllInterviewsInThePast(LocalDate.of(2019,10,26));
        List<List<String>> expectedSchedule = getArraysAsLists(expectedScheduleData);

        assertEquals(expectedSchedule, interviewScheduleInThePast);
    }

    private <T extends HasShortDescription> Set<String> getShortDescriptionSet(Set<T> hasShortDescriptionSet){
        return hasShortDescriptionSet.stream().map(HasShortDescription::getShortDescription).collect(Collectors.toSet());
    }
    private <T> Set<T> createSet(T ... args){
        return new HashSet<T>(Arrays.asList(args));
    }
    private List<List<String>> getArraysAsLists(String[][] scheduleData){
        return Arrays.stream(scheduleData)
                .map(Arrays::asList)
                .collect(Collectors.toList());
    }
}