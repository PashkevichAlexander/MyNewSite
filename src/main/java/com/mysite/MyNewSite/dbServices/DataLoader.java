package com.mysite.MyNewSite.dbServices;

import com.mysite.MyNewSite.entity.User;
import com.mysite.MyNewSite.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        userRepository.save(new User("user1","$2a$10$2Xohn4ngjeN6m3AD0ujzxuBnOz7xYCwI1MkhAm/h9B/J/rwiTcN..","bob","tester"));
    }
}
