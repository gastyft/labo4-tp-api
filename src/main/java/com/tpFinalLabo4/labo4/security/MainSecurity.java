
package com.tpFinalLabo4.labo4.security;

import com.tpFinalLabo4.labo4.security.jwt.JwtEntryPoint;
import com.tpFinalLabo4.labo4.security.jwt.JwtTokenFilter;
import com.tpFinalLabo4.labo4.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MainSecurity {

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Construye el `AuthenticationManager` con el `UserDetailsService` y el `PasswordEncoder`
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
        authenticationManager = builder.build();
        http.authenticationManager(authenticationManager);

        // Configuración de seguridad
        http
                .csrf(csrf -> csrf.disable())  // Desactiva CSRF
                .cors(withDefaults())           // Activa CORS usando los valores predeterminados
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Política de sesiones sin estado
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/**").permitAll()   // Permite el acceso público a todos los endpoints
                        .anyRequest().authenticated())        // Requiere autenticación para el resto de las solicitudes
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(jwtEntryPoint))  // Configura el punto de entrada en caso de fallo de autenticación
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);  // Agrega el filtro JWT antes de UsernamePasswordAuthenticationFilter

        return http.build();
    }
}

