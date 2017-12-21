package io.pivotal.management.user.controller;

import io.pivotal.management.user.model.User;
import io.pivotal.management.user.repository.UserRepository;
import io.pivotal.management.user.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Map<String,String> serviceUp() {
        Map<String,String> result = new HashMap<>();
        result.put("data","success");
        return result;
    }


    @RequestMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public User getUser(@PathVariable String id) {
        User user = new User();
        user.setId(id);
        user = repository.findById(id).get();
        return user;
    }

    @RequestMapping(value = "/user/{firstname}/{lastname}/{username}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public User createUser(@PathVariable String firstname, @PathVariable String lastname, @PathVariable String username) {
        //Map<String,String> result = new HashMap<>();
        User user = new User(firstname,lastname,username, securityService.securePassword(SecurityService.DEFAULT_PASSWORD), securityService.getSalt());
        User result = repository.save(user);
        return user;
    }

    @RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public User updateUser(Long id, User user) {
        //TODO add logic to update user
        return user;
    }

    @RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public void deleteUser(Long id) {
        //TODO add logic to delete user
    }
}
