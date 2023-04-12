package com.example.fitnessapp_back.security;

import com.example.fitnessapp_back.service.CustomUserDetailsService;
import com.example.fitnessapp_back.util.CustomPasswordEncoder;
import com.example.fitnessapp_back.util.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
//@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomPasswordEncoder passwordEncoder;

    @Autowired
    private JwtFilter jwtFilter;
    @Override @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder.getPasswordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http.csrf().disable().cors().disable();

        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();
        http = http.exceptionHandling()
                .authenticationEntryPoint((request, response, exception)->{
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
                }).and();
         http.authorizeHttpRequests()
                 .antMatchers("/myProfile/api/auth/**").permitAll()
                 .antMatchers("/dishes/**").permitAll()
                 .antMatchers("/api/auth/**").permitAll()
                 .anyRequest().authenticated();
         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
