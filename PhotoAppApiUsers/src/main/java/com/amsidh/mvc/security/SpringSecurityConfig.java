package com.amsidh.mvc.security;

import com.amsidh.mvc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private Environment environment;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserService userService;

    @Autowired
    public SpringSecurityConfig(Environment environment, BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        log.info("SpringSecurityConfig loading!!!");
        this.environment = environment;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        log.info("configure(HttpSecurity httpSecurity) method of class SpringSecurityConfig called");
        httpSecurity.csrf().disable();

        httpSecurity.authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/**")
                .hasIpAddress(environment.getProperty("ip.address.security.allow"))
                .and()
                .addFilter(getAuthenticationFilter());

        httpSecurity.headers().frameOptions().disable();
    }


    public AuthenticationFilter getAuthenticationFilter() throws Exception {
        log.info("getAuthenticationFilter method of class SpringSecurityConfig called");
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(userService, environment, authenticationManager());
        authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
        return authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("configure(AuthenticationManagerBuilder auth) method of class SpringSecurityConfig called");
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);

    }
}
