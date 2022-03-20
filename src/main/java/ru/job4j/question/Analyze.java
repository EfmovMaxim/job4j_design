package ru.job4j.question;
import java.util.*;

public class Analyze {
    public static Info diff(Set<User> previous, Set<User> current) {

        Set<User> added = new HashSet<>(current);
        added.removeAll(previous);

        Set<User> removed = new HashSet<>(previous);
        removed.removeAll(current);

        Set<User> changedCurr = new TreeSet<>(current);
        changedCurr.retainAll(previous);

        Set<User> changedPrev = new TreeSet<>(previous);
        changedPrev.retainAll(changedCurr);

        Iterator<User> iteratorCurr = changedCurr.iterator();
        Iterator<User> iteratorPrev = changedPrev.iterator();

        int changed = 0;
        User currUser;
        User prevUser;

        while (iteratorCurr.hasNext() && iteratorPrev.hasNext()) {

            currUser = iteratorCurr.next();
            prevUser = iteratorPrev.next();

            if (currUser.equals(prevUser)) {
                if (!currUser.getName().equals(prevUser.getName())) {
                    changed++;
                }
            } else {
                System.out.println("error");
                break;
            }

        }

        return new Info(added.size(), changed, removed.size());
    }
}