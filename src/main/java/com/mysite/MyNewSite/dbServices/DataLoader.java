package com.mysite.MyNewSite.dbServices;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Component
public class DataLoader {
    private final BCryptPasswordEncoder encoder;

    public DataLoader(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        try {
            String url = "jdbc:mysql://localhost:3306/springboottest";
            String username = "root";
            String password = "12345";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            String a = encoder.encode("12345");

//            Connection connection = DriverManager.getConnection(url,username,password);
//
//            String command = "INSERT INTO users(user_id,enabled,first_name,last_name,password,username) VALUES (?,?,?,?,?,?)";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(command);
//
//            preparedStatement.setString();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                Statement statement = conn.createStatement();

                String string = "INSERT INTO users(user_id,enabled,first_name,last_name,password,username) VALUES (1,true,'admin','test','" + a + "','admin')";

                statement.executeUpdate(string);

                statement.executeUpdate("INSERT INTO users_roles(user_id,role_id) VALUES (1,1)");
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }

}
