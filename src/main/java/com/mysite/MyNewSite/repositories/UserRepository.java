package com.mysite.MyNewSite.repositories;

import com.mysite.MyNewSite.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<com.mysite.MyNewSite.entity.User, Integer> {


    com.mysite.MyNewSite.entity.User findByUsername(String login);


    Set<com.mysite.MyNewSite.entity.User> findUsersByUsernameContainsIgnoreCase(String name);

    List<User> findAll();

    boolean existsUserByUsername(String login);

    boolean existsUserById(int id);

}
