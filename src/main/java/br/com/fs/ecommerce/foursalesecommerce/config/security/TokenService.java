package br.com.fs.ecommerce.foursalesecommerce.config.security;

import br.com.fs.ecommerce.foursalesecommerce.domain.Usuario;
import br.com.fs.ecommerce.foursalesecommerce.exception.AuthException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("fsecommerceApiJwt")
                    .withSubject(usuario.getEmail())
                    .withAudience("fsecommerce-api")
                    .withIssuedAt(Date.from(Instant.now()))
                    .withClaim("userId", usuario.getId())
                    .withClaim("roles", usuario.getAuthorities().toString())
                    .withExpiresAt(Date.from(this.generateExpirationDate()))
                    .withJWTId(UUID.randomUUID().toString())
                    .withNotBefore(Date.from(Instant.now()))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new AuthException("Erro ao gerar token JWT: " + exception.getMessage());
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("fsecommerceApiJwt")
                    .withAudience("fsecommerce-api")
                    .build();

            DecodedJWT jwt = verifier.verify(token);
            if (jwt.getExpiresAt().before(new Date())) {
                throw new JWTVerificationException("Token expirado");
            }

            if (jwt.getNotBefore().after(new Date())) {
                throw new JWTVerificationException("Token não pode ser usado ainda");
            }

            return jwt.getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }
}