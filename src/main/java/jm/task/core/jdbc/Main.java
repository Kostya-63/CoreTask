package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        User Vasya = new User("Вася", "Пупкин", (byte) 32);
        User Kolya = new User("Колян", "Валуев", (byte) 46);
        User Obama = new User("Барак", "Обама", (byte) 59);
        User Nikita = new User("Никита", "ДжЫгурда", (byte) 59);

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.createUsersTable();
        userService.saveUser(Vasya.getName(), Vasya.getLastName(), Vasya.getAge());
        userService.saveUser(Kolya.getName(), Kolya.getLastName(), Kolya.getAge());
        userService.saveUser(Obama.getName(), Obama.getLastName(), Obama.getAge());
        userService.saveUser(Nikita.getName(), Nikita.getLastName(), Nikita.getAge());
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
