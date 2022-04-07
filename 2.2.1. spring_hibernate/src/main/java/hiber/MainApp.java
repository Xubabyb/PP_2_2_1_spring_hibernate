package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
//      WARN!!!  hibernate.hbm2ddl.auto=create
        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("toyota", 562348)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("mazda", 6)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("lincoln", 568444)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("tesla", 350455)));

//      Добавить 'лень' чтобы не тянуть кары пока они не надо, хмм.. что то тут есть непонятное надо поразмыслить

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar().getModel());
            System.out.println();
        }
        System.out.println("____________________________________");
        System.out.println(userService.getUserByCar(new Car("lincoln", 568444)));
        context.close();
    }
}
