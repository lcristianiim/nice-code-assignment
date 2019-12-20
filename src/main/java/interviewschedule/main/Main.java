package interviewschedule.main;

import interviewschedule.data.FileBasedDataRepo;
import interviewschedule.schedule.ScheduleImpl;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

public class Main {

    /**
     * Coding assignment instructions
     *
     * Setup steps:
     * - Set up the project in your preferred IDE, using Maven.
     *
     * Guidelines:
     * - Do not add any additional libraries, than are already defined in the pom.xml
     *
     * Disclaimer:
     * - All data, names and dates used in this exercise are made up. Any resemblance to real-life data is a coincidence.
     *
     * Assignment:
     * 1. Write appropriate code to make all tests in the class ScheduleImplTest pass. In order to do this you might
     *    have to create new and change existing classes.
     *
     * 2. Add a JUnit test for the functionality that generates the "3 months ago" string used in
     *    ScheduleImplTest.getScheduleForAllInterviewsInThePast. The JUnit test should provide a good coverage of the
     *    functionality.
     *
     * Have fun and good luck!
     */
    public static void main(String[] args) throws Exception {
        String path = new URI(Main.class.getResource("/schedule.tsv").getFile()).getPath();

        ScheduleImpl schedule = new ScheduleImpl(new FileBasedDataRepo());

        List<List<String>> scheduleInThePast = schedule
                .getScheduleForAllInterviewsInThePast(LocalDate.of(2019,10,26));

        System.out.println(scheduleInThePast);

    }
}
