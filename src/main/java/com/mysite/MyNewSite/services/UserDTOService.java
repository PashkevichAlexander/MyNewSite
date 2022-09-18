package com.mysite.MyNewSite.services;

import com.mysite.MyNewSite.DTOConverters.UserDTOConverter;
import com.mysite.MyNewSite.dto.UserDTO;
import com.mysite.MyNewSite.entity.User;
import com.mysite.MyNewSite.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDTOService { //DAO data access object. returns userDTO
    private final UserRepository userRepo;

    private final UserDTOConverter converter;
    private final RoleService roleService;


    public UserDTOService(UserRepository userRepo, UserDTOConverter converter, RoleService roleService) {
        this.userRepo = userRepo;
        this.converter = converter;
        this.roleService = roleService;
    }

    public List<UserDTO> getAll() {
        List<UserDTO> users = new ArrayList<>();
        for (com.mysite.MyNewSite.entity.User u : userRepo.findAll()) {
            users.add(converter.fromUserToDTO(u));
        }
        return users;
    }

    public UserDTO getById(final int id) {
        Optional<User> user = userRepo.findById(id);
        return user.map(converter::fromUserToDTO).orElse(null);
    }

    public Set<UserDTO> convert(Set<User> users) {
        if (users == null) return null;
        Set<UserDTO> userDTOS = new HashSet<>();
        for (User user : users) {
            userDTOS.add(converter.fromUserToDTO(user));
        }
        return userDTOS;
    }

    public UserDTO getByLogin(final String login) {
        User user = userRepo.findByUsername(login);
        if (user != null) {
            return converter.fromUserToDTO(user);
        }
        return null;
    }

    public boolean notExistById(final int id) {
        return userRepo.existsUserById(id);
    }

    public boolean existByUsername(final String login) {
        return userRepo.existsUserByUsername(login);
    }

    public void deleteById(final int id) {
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (roleService.isAdmin(user)) return;
            //   orderRepo.deleteAllByUser(user);
            user.setRoles(null);
            userRepo.save(user);
            userRepo.deleteById(id);
        }
    }

    public UserDTO create() {
        return new UserDTO();
    }

    public void edit(UserDTO userDTO) {
        if (userDTO == null) return;
        if (userRepo.existsUserById(userDTO.getId())) {
            userRepo.save(converter.fromDTOToUser(userDTO));
        }
    }

    public Set<UserDTO> find(String find) {
        if (find == null) return null;
        Set<User> users = new HashSet<>();
        users.addAll(userRepo.findUsersByUsernameContainsIgnoreCase(find));
        return convert(users);
    }

    public void add(UserDTO userDTO) {
        if (userDTO == null) return;
        if (!userRepo.existsUserById(userDTO.getId())) {
            userRepo.save(converter.fromDTOToUser(userDTO));
        }
    }
}