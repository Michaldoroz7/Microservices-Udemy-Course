package com.mdoroz.rest.webservices.restfulwebservices.user;


import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Michal", LocalDate.now().minusYears(20)));
        users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(12)));
        users.add(new User(++usersCount, "Klaus", LocalDate.now().minusYears(54)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(Integer id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User saveUser(User user) {
        user.setId(++usersCount);
        users.add(user);

        return user;
    }

    public void deleteUserById(Integer id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
