package com.mysite.MyNewSite.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Message {


    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private String tag;

    @Getter
    @Setter
    private String userName;

    public Message(String text, String tag, String userName) {
        this.text = text;
        this.tag = tag;
        this.userName = userName;
    }

    public Message() {
    }

}
