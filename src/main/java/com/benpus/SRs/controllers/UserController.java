package com.benpus.SRs.controllers;

import com.benpus.SRs.models.User;
import com.benpus.SRs.models.UserType;
import com.benpus.SRs.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserRepository userRepository;
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    record NewUserRequest(String name, String surname, String email, String password, String type){}

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users =  userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable("userId") Integer ID){
        Optional<User> userQuery = userRepository.findById(ID);
        if (userQuery.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "User with specified ID was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(userQuery.get());
        }
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody NewUserRequest request) throws NoSuchAlgorithmException {
        String errorMsg = "", name, surname, email, password;

        name = request.name;
        surname = request.surname;
        email = request.email;
        password = request.password;

        errorMsg += this.validateName(name);
        errorMsg += this.validateSurname(surname);
        errorMsg += this.validateEmail(email);
        errorMsg += this.validatePW(password);

        if (!errorMsg.isEmpty()){
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", errorMsg);
            return ResponseEntity.unprocessableEntity().body(responseBody);
        }

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encodedPass = Base64.getEncoder().encodeToString(hash);

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(encodedPass);
        if (request.type.toUpperCase().equals(UserType.ADMIN.toString())) {
            user.setType(UserType.ADMIN);
        } else if (request.type.toUpperCase().equals(UserType.USER.toString())) {
            user.setType(UserType.USER);
        } else {
            user.setType(UserType.UNCONFIRMED);
        }

        user = userRepository.save(user);
        Integer id = user.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(user);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Object> editUser(@PathVariable("userId") Integer ID, @RequestBody NewUserRequest request) throws NoSuchAlgorithmException {
        Optional<User> userQuery = userRepository.findById(ID);
        if (userQuery.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "User with specified ID was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        User user = userQuery.get();

        String errorMsg = "", name, surname, email, password, type;

        name = request.name;
        surname = request.surname;
        email = request.email;
        password = request.password;
        type = request.type;

        errorMsg += this.validateNullableName(name);
        errorMsg += this.validateNullableSurname(surname);
        errorMsg += this.validateNullableEmail(email);

        if (!errorMsg.isEmpty()){
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", errorMsg);
            return ResponseEntity.unprocessableEntity().body(responseBody);
        }

        if (name != null && !name.isEmpty()) {
            user.setName(name);
        }
        if (surname != null && !surname.isEmpty()) {
            user.setSurname(surname);
        }
        if (email != null && !email.isEmpty()) {
            user.setEmail(email);
        }
        if (password != null && !password.isEmpty()) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String encodedPass = Base64.getEncoder().encodeToString(hash);
            user.setPassword(encodedPass);
        }
        if (type != null && !type.isEmpty()) {
            if (type.toUpperCase().equals(UserType.ADMIN.toString())) {
                user.setType(UserType.ADMIN);
            } else if (type.toUpperCase().equals(UserType.USER.toString())) {
                user.setType(UserType.USER);
            } else {
                user.setType(UserType.UNCONFIRMED);
            }
        }
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") Integer ID)
    {
        Optional<User> userQuery = userRepository.findById(ID);
        if (userQuery.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "User with specified ID was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(ID);
        return ResponseEntity.noContent().build();
    }

    private String validateNullableEmail(String email) {
        if (email == null || email.isEmpty() || Pattern.matches("^[-ĄČĘĖĮŠŲŪŽąčęėįšųūž\\w.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)){
            return "";
        } else {
            return  "Provided email is invalid; ";
        }
    }

    private String validateNullableSurname(String surname) {
        if (surname == null || surname.isEmpty() || Pattern.matches("^[a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ-]+$", surname)){
            return "";
        } else {
            return  "Provided surname is invalid; ";
        }
    }

    private String validateNullableName(String name) {
        if (name == null || name.isEmpty() || Pattern.matches("^[a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ ]+$", name)){
            return "";
        } else {
            return  "Provided name is invalid; ";
        }
    }

    private String validatePW(String password) {
        if (password == null || password.isEmpty()) {
            return "User password is required\n";
        }
        return "";
    }

    private String validateName(String name) {
        if (name == null || name.isEmpty()) {
            return  "User name is required; ";
        } else if (!Pattern.matches("^[a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ ]+$", name)){
            return  "Provided name is invalid; ";
        }
        return "";
    }

    private String validateSurname(String surname) {
        if (surname == null || surname.isEmpty()) {
            return  "User surname is required; ";
        } else if (!Pattern.matches("^[a-zA-ZąčęėįšųūžĄČĘĖĮŠŲŪŽ-]+$", surname)){
            return  "Provided surname is invalid; ";
        }
        return "";
    }

    private String validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return  "User email is required; ";
        } else if (!Pattern.matches("^[-ĄČĘĖĮŠŲŪŽąčęėįšųūž\\w.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)){
            return  "Provided email is invalid; ";
        }
        return "";
    }
}
