package br.com.fs.ecommerce.foursalesecommerce.config.security;

import br.com.fs.ecommerce.foursalesecommerce.exception.UsuarioNaoEncontradoPorEmailException;
import br.com.fs.ecommerce.foursalesecommerce.repository.UsuarioRepository;
import br.com.fs.ecommerce.foursalesecommerce.support.TenantContext;
import com.auth0.jwt.JWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    public static final String BEARER_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (Objects.equals(SecurityConfig.AUTH_LOGIN, request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = this.recoverToken(request);
        if (nonNull(token)) {
            var email = tokenService.validateToken(token);
            var tenantId = JWT.decode(token).getClaim("tenantId").asLong();

            if (nonNull(email)) {
                UserDetails usuario = usuarioRepository.findByEmail(email)
                        .orElseThrow(() -> new UsuarioNaoEncontradoPorEmailException(email));

                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            if (nonNull(tenantId)) {
                TenantContext.setCurrentTenant(tenantId);
            }
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (isNull(authHeader) || !authHeader.startsWith(BEARER_PREFIX)) return null;
        return authHeader.replace(BEARER_PREFIX, "");
    }
}