package com.example.security.config;

import com.example.security.service.CustomUserDetailsService;
import com.example.security.jwt.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Configuración de seguridad para la aplicación Spring Boot.
 * Define las reglas de autorización, la gestión de sesiones y la integración de JWT.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/login").permitAll() // Permitir acceso público al login
                        .requestMatchers("/auth/register").permitAll() // Permitir acceso público al registro
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Swagger UI
                        .requestMatchers("/public/**").permitAll() // Existing public access

                        // New public access
                        .requestMatchers("/articulos-blog/**").permitAll()
                        .requestMatchers("/historias-inspiradoras/**").permitAll()
                        .requestMatchers("/discusiones/**").permitAll() // Assuming public view for discussions

                        // User-specific access (authenticated users)
                        .requestMatchers("/usuarios/**").hasRole("USER") // Existing
                        .requestMatchers("/historial-reciclaje/**").hasRole("USER")
                        .requestMatchers("/usuario-logros/**").hasRole("USER")

                        // Admin-specific access
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Existing
                        .requestMatchers("/auth/**").hasRole("ADMIN") // Existing
                        .requestMatchers("/empresas/**").hasRole("ADMIN") // Existing
                        .requestMatchers("/empresas-recicladoras/**").hasRole("ADMIN")
                        .requestMatchers("/recompensas/**").hasRole("ADMIN")
                        .requestMatchers("/logros/**").hasRole("ADMIN")
                        .requestMatchers("/materiales/**").hasRole("ADMIN") // Changed from permitAll to ADMIN for management

                        .anyRequest().authenticated() // All other requests require authentication
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5175"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}