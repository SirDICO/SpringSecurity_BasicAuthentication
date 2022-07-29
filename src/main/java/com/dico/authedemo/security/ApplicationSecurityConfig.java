package com.dico.authedemo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //ensure authourization
                .authorizeRequests()
                //disable authourization
                .antMatchers("/", "index","/css/*", "/js/*")
                //permit all url specified in antmatcher
                .permitAll()
                //ensure it's done on any request
                .anyRequest()
                //then authenticate the request
                .authenticated()
                //and
                .and()
                //then use basic authentication type for access
                .httpBasic();
    }
}
