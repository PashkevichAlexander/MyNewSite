package com.mysite.MyNewSite.dbServices;

import com.mysite.MyNewSite.entity.User;
import com.mysite.MyNewSite.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final BCryptPasswordEncoder encoder;
    private UserRepository userRepository;
    private String adminPassword1 = "123456789";
    private String adminPassword2 = "987654321";
    private String userPassword1 = "123456789";
    private String userPassword2 = "987654321";

    @Autowired
    public DataLoader(BCryptPasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        userRepository.save(new User("Admin1","$2a$10$2Xohn4ngjeN6m3AD0ujzxuBnOz7xYCwI1MkhAm/h9B/J/rwiTcN..","Admin1bob","Admin1tester"));
        userRepository.save(new User("Admin2","$2a$10$2Xohn4ngjeN6m3AD0ujzxuBnOz7xYCwI1MkhAm/h9B/J/rwiTcN..","Admin1bob","Admin2tester"));
    }
}
