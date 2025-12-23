package br.com.fs.ecommerce.foursalesecommerce.support;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Aspect
@Component
@RequiredArgsConstructor
public class TenantAspect {

    @PersistenceContext
    private final EntityManager entityManager;

    @Before("execution(* br.com.fs.ecommerce.foursalesecommerce.service.*.*(..))")
    public void activateTenantFilter() {

        // Obtém o ID do tenant do contexto que definiste (via Header ou Token)
        Long tenantId = TenantContext.getCurrentTenant();

        if (nonNull(tenantId)) {
            Session session = entityManager.unwrap(Session.class);
            session.enableFilter("tenantFilter")
                    .setParameter("tenantId", tenantId);
        }
    }
}
