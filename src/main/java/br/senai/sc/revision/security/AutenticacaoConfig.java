package br.senai.sc.revision.security;

import br.senai.sc.revision.security.service.UsuarioServiceSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AutenticacaoConfig {

    @Autowired
    private UsuarioServiceSecurity uss;

    @Autowired
    public void configure(AuthenticationManagerBuilder amb) throws Exception {
        amb.userDetailsService(uss).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }


    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/usuario").permitAll()
                .anyRequest().authenticated();
        http.formLogin().permitAll();
        http.logout().permitAll();
        http.cors().disable();
        http.csrf().disable();
        return http.build();
    }
    
}
