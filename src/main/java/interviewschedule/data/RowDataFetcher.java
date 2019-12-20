package interviewschedule.data;

public class RowDataFetcher {

    public static String candidateLastName(String[] row) {
        return row[4];
    }

    public static String candidateFirstName(String[] row) {
        return row[3];
    }

    public static String candidateRole(String[] row) {
        return row[5];
    }

    public static String interviewerLastName(String[] row) {
        return row[1];
    }

    public static String interviewerFirstName(String[] row) {
        return row[0];
    }

    public static String interviewerTeam(String[] row) {
        return row[2];
    }

    public static String interviewStartTime(String[] row) {
        return row[8];
    }

    public static String interviewEndTime(String[] row) {
        return row[9];
    }

    public static String interviewState(String[] row) {
        return row[10];
    }

    public static String interviewDate(String[] row) {
        return row[7];
    }

    public static String interviewType(String[] row) {
        return row[6];
    }
}