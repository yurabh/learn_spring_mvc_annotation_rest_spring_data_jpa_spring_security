package com;

import com.configuration.ConfigApp;
import com.domain.Role;
import com.domain.User;
import com.repository.UserRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigApp.class);

        UserRepository repository = context.getBean(UserRepository.class);

        Role simpleUser = new Role("ROLE_USER");
        Role simpleUser1 = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");

        User john = new User("John", "123456", true);

        User jack = new User("Jack", "123", true);

        Set<Role> rolesForJohn = new HashSet<>(Arrays.asList(simpleUser));
        Set<Role> rolesForJack = new HashSet<>(Arrays.asList(simpleUser, admin));

        john.setRoles(rolesForJohn);
        jack.setRoles(rolesForJack);

        repository.saveAll(Arrays.asList(jack, john));
//        repository.save(john);
    }
}
