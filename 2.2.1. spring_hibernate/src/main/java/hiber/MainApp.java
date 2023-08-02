package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
        Car car1 = new Car("Model1", 001);
        Car car2 = new Car("Model2", 010);
        Car car3 = new Car("Model3", 011);
        Car car4 = new Car("Model4", 100);

        userService.add(user1.setCar(car1).setUser(user1));
        userService.add(user2.setCar(car2).setUser(user2));
        userService.add(user3.setCar(car3).setUser(user3));
        userService.add(user4.setCar(car4).setUser(user4));

        // 1. Пользователи с машинами
        for (User user : userService.listUsers()) {
            System.out.println(user + " " + user.getCar());
            System.out.println("1. _____________________________________________");
        }

        // 2. Выбрать пользователя, владеющего машиной (по ее модели и серии)
        System.out.println(userService.getUserByCar("Model2", 010));
        System.out.println("2. _____________________________________________");

        context.close();
    }
}
