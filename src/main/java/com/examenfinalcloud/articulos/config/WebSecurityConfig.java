package com.examenfinalcloud.articulos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{     //seguridad a los endPoint
        http.cors().disable().csrf().disable()
                .authorizeRequests()
                //.antMatchers("/**").permitAll()
                .antMatchers(HttpMethod.POST,"/usuario").permitAll()   //permite a todos consumir este endPoint
                .antMatchers(HttpMethod.POST,"/auth/login").permitAll()//permite a todos consumir este endPoint
                .antMatchers(HttpMethod.POST,"/articulo").permitAll()  //permite a todos consumir este endPoint
                .anyRequest().permitAll();
        return http.build();
    }
}
