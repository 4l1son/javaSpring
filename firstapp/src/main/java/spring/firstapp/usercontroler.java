package spring.firstapp;

import spring.firstapp.Controllers.model.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * controler
 */
@RestController
@RequestMapping("/users")

public class usercontroler {
    private List<user> users = new ArrayList<>();

    @GetMapping(value = "/")
    public user user() {
        user user = new user(null, null, null);
        {
            user.setId(1L);
            user.setName("Alisson");
            user.setUsername("4llson");

            return user;
        }
    }

    @PostMapping("/")
    public user user(@RequestBody user user) {
        users.add(user);

        return user;
    }

    @GetMapping("/list")
    public List<user> list() {
        return users;
    }
}