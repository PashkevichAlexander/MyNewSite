package com.mysite.MyNewSite.DTOConverters;

import com.mysite.MyNewSite.dto.UserDTO;
import com.mysite.MyNewSite.entity.User;
import com.mysite.MyNewSite.services.RoleService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDTOConverter {
    private final RoleService roleService;
    private final BCryptPasswordEncoder encoder;

    public UserDTOConverter(RoleService roleService, BCryptPasswordEncoder encoder) {
        this.roleService = roleService;
        this.encoder = encoder;
    }

    public UserDTO fromUserToDTO(User user) {
        if (user == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setRoleEditor(roleService.isEditor(user));
        return dto;
    }

    public User fromDTOToUser(UserDTO userDTO) {
        if (userDTO == null) return null;
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastname());
        if (userDTO.getPassword().length() < 35) userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        user.setPassword(userDTO.getPassword());
        if (userDTO.isRoleEditor()) {
            user.setRoles(roleService.set(false, true, false));
        } else {
            user.setRoles(roleService.set(false, false, true));
        }
        return user;
    }
}

