package com.mysite.MyNewSite.dto;


import java.util.HashSet;
import java.util.Set;

public class UserDTO {
    private int id;
    private String username;
    private String password;

    private boolean enabled = true;
    private boolean isEditor = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isRoleEditor() {
        return isEditor;
    }

    public void setRoleEditor(boolean editor) {
        isEditor = editor;
    }

}
