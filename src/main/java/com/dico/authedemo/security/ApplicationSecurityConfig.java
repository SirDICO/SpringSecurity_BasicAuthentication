package com.dico.authedemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.dico.authedemo.security.UserRole.STUDENT;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //ensure authourization
                .authorizeRequests()
                //disable authourization for this set of guys
                .antMatchers("/", "index","/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                //ensure it's done on any request
                .anyRequest()
                //then authenticate the request
                .authenticated()
                //and
                .and()
                //then use basic authentication type for access
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails UserIkenna =  User.builder()
                .username("ikenna")
                .password(passwordEncoder.encode("password"))
                .roles(UserRole.ADMIN.name())
                .build();

        UserDetails UserLinda = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("password1"))
                .roles(STUDENT.name())
                .build();
                return new InMemoryUserDetailsManager(
                        UserLinda,
                        UserIkenna
    );

    }
}
