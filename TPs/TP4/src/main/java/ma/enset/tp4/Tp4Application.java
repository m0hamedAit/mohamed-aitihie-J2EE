package ma.enset.tp4;

import ma.enset.tp4.entities.Role;
import ma.enset.tp4.entities.User;
import ma.enset.tp4.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class Tp4Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp4Application.class, args);
    }

    @Bean  // chaque methode avec l'annotation @Bean, s'execute au demarrage de l'app
    CommandLineRunner start(UserService userService){
        return args->{
            User u1 = new User();
            u1.setUsername("user1");
            u1.setPassword("userrrr");
            userService.addUser(u1);

            User u2 = new User();
            u2.setUsername("admin");
            u2.setPassword("admin");
            userService.addUser(u2);

            Stream.of("STUDENT","ADMIN","USER").forEach(r->{
                Role role = new Role();
                role.setRoleName(r);
                userService.addRole(role);
            });

            userService.addRoleToUser("user1", "STUDENT");
            userService.addRoleToUser("user1", "USER");
            userService.addRoleToUser("admin", "ADMIN");
            userService.addRoleToUser("admin", "USER");

            try{
                User user = userService.authenticate("user1","userrrr");
                System.out.println(user.getUsername());
                System.out.println("Roles : ");
                user.getRoles().forEach(r->{
                    System.out.println("\t"+r.getRoleName());
                });
            }catch(Exception exception){
                exception.printStackTrace();
            }

        };
    }
}
