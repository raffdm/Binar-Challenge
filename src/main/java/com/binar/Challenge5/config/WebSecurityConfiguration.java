package com.binar.Challenge5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UserDetailsService userService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/api/v1/user/login").permitAll()
                .antMatchers("/api/v1/user/register").permitAll()
                .antMatchers("/api/v1/product/{name}", "/api/v1/product/get/{page}").permitAll()
                .antMatchers("/api/v1/merchant/{page}").permitAll()
                .antMatchers("/api/v1/user", "/api/v1/user/{usercode}").hasAuthority("ADMIN")
                .antMatchers("/api/v1/role").hasAuthority("ADMIN")
                .antMatchers("/api/v1/product").hasAuthority("ADMIN")
                .antMatchers("/api/v1/merchant", "/api/v1/merchant/**").hasAuthority("ADMIN")
                .antMatchers("/api/v1/user/{usercodeupdate}").hasAuthority("USER")
                .antMatchers("/api/v1/user/{usercode}").hasAuthority("USER")
                .antMatchers("/api/v1/order").hasAuthority("USER")
                .antMatchers(HttpHeaders.ALLOW).permitAll()
                .anyRequest().authenticated();
//                .and()
//                .exceptionHandling()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        httpSecurity.addFilterBefore();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
