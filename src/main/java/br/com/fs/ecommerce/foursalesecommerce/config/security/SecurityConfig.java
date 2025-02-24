package br.com.fs.ecommerce.foursalesecommerce.config.security;

import br.com.fs.ecommerce.foursalesecommerce.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
    public static final String ROTA_PRODUTOS = "/produtos/**";
    public static final String ROTA_CATEGORIAS = "/categorias/**";
    public static final String AUTH_LOGIN = "/auth/login";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/swagger-ui/**", "/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, AUTH_LOGIN).permitAll()
                        .requestMatchers(HttpMethod.POST, ROTA_PRODUTOS).hasAuthority(UserRole.ROLE_ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, ROTA_PRODUTOS).hasAuthority(UserRole.ROLE_ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, ROTA_PRODUTOS).hasAuthority(UserRole.ROLE_ADMIN.name())
                        .requestMatchers(HttpMethod.POST, ROTA_CATEGORIAS).hasAuthority(UserRole.ROLE_ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, ROTA_CATEGORIAS).hasAuthority(UserRole.ROLE_ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, ROTA_CATEGORIAS).hasAuthority(UserRole.ROLE_ADMIN.name())
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