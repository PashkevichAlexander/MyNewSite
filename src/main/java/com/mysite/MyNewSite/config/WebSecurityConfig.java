package com.mysite.MyNewSite.config;

import com.mysite.MyNewSite.domian.Roles;
import com.mysite.MyNewSite.repositories.UserRepository;
import com.mysite.MyNewSite.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@ComponentScan
public class WebSecurityConfig {

    private final UserRepository userRepository;

    public WebSecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService(userRepository);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //Some magic stuff
                .csrf().disable()
                //require authorisation
                .authorizeRequests()
                //add required roles here
                //Public pages
                .antMatchers("/error").permitAll()

                .antMatchers("/greeting").anonymous()
                .antMatchers("/login").anonymous()
                .antMatchers("/registration").anonymous()
                .antMatchers("/main").permitAll()

                .antMatchers("/addMessage").hasAnyAuthority(Roles.USER.getName())

                .antMatchers("/filter").hasAnyAuthority(Roles.USER.getName())
                //blacklist others
                //and
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/main")
                    .permitAll()
                //and
                .and().logout().permitAll().logoutSuccessUrl("/")
                .and().exceptionHandling().accessDeniedPage("/403")

        //and

        ;
        return http.build();
    }
}
