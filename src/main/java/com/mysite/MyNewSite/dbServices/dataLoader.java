package com.mysite.MyNewSite.dbServices;

import com.mysite.MyNewSite.entity.User;
import com.mysite.MyNewSite.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class dataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public dataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        userRepository.save(new User());
    }
}
