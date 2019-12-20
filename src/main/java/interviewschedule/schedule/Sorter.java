package interviewschedule.schedule;

import java.util.Comparator;
import java.util.List;

public class Sorter <T extends Comparable<T>> implements Comparator<List<T>> {
    @Override
    public int compare(List<T> o1, List<T> o2) {
        String one = (String) o1.get(0);
        String two = (String) o2.get(0);
        return one.compareTo(two);
    }
}