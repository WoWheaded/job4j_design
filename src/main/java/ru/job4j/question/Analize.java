package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> mapUsers = new HashMap<>();
        for (User user : current) {
            mapUsers.put(user.getId(), user.getName());
        }
        for (User previousUser : previous) {
            String name = mapUsers.get(previousUser.getId());
            if (name == null) {
                deleted++;
            } else if (!name.equals(previousUser.getName())) {
                changed++;
            }
        }
        return new Info(current.size() - previous.size() + deleted, changed, deleted);
    }

}
