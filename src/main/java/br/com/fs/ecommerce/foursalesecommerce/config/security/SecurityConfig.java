package br.com.fs.ecommerce.foursalesecommerce.config.security;

import br.com.fs.ecommerce.foursalesecommerce.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityFilter securityFilter;
    private static final String ROTA_PRODUTOS = "/produtos/**";
    private static final String ROTA_PEDIDOS = "/pedidos/**";
    private static final String AUTH_LOGIN = "/auth/login";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, AUTH_LOGIN).permitAll()
                        .requestMatchers(HttpMethod.POST, ROTA_PRODUTOS).hasRole(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, ROTA_PRODUTOS).hasRole(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, ROTA_PRODUTOS).hasRole(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, ROTA_PRODUTOS).hasAnyRole(UserRole.ADMIN.name(), UserRole.USUARIO.name())
                        .requestMatchers(HttpMethod.POST, ROTA_PEDIDOS).hasRole(UserRole.USUARIO.name())
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
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
}