package br.com.fs.ecommerce.foursalesecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Configuration
public class AuditorAwareConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (nonNull(authentication) && authentication.isAuthenticated()) {
                return Optional.of(authentication.getName());
            }

            return Optional.of("SISTEMA");
        };
    }
}