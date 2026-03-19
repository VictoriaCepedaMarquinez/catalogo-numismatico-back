package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desactivamos CSRF para permitir POST, PUT y DELETE desde el exterior
                .csrf(csrf -> csrf.disable())

                // 2. Configuración de CORS para permitir a Vercel hablar con Railway
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfiguration = new CorsConfiguration();

                    // IMPORTANTE: Agregamos localhost y tu URL de Vercel
                    corsConfiguration.setAllowedOrigins(List.of(
                            "http://localhost:5173",
                            "https://catalogo-numismatico.vercel.app/"
                    ));

                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }))

                // 3. Reglas de autorización
                .authorizeHttpRequests(auth -> auth
                        // Permitimos que el navegador haga el "chequeo de seguridad" (OPTIONS) sin trabas
                        .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
                        // El resto de las rutas requieren el usuario 'admin'
                        .anyRequest().authenticated()
                )

                // 4. Activamos la autenticación básica (el cartelito de usuario y clave)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // Usuario administrador para gestionar el catálogo
        UserDetails user = User.withUsername("admin")
                .password("{noop}1234")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}