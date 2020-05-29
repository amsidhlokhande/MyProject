package com.amsidh.mvc.security;

import com.amsidh.mvc.security.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private Environment environment;

    @Autowired
    public WebSecurity(Environment environment){
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,environment.getProperty("api.login.url.path")).permitAll()
                .antMatchers(HttpMethod.POST,environment.getProperty("api.registration.url.path")).permitAll()
                .antMatchers(environment.getProperty("api.h2console.url.path")).permitAll()
                .antMatchers(environment.getProperty("api.zuul.actuator.url.path")).permitAll()
                .antMatchers(environment.getProperty("api.users-ws.actuator.url.path")).permitAll()
                .antMatchers(environment.getProperty("api.account-ws.actuator.url.path")).permitAll()
                .anyRequest().authenticated()
        .and().addFilter(getAuthenticationFilter());

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    private Filter getAuthenticationFilter() throws Exception {
        AuthFilter authFilter = new AuthFilter(environment,authenticationManager());
        return authFilter;
    }

}
