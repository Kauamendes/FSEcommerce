package br.com.fs.ecommerce.foursalesecommerce.support;

import br.com.fs.ecommerce.foursalesecommerce.domain.TenantEntity;
import jakarta.persistence.PrePersist;

public class TenantEntityListener {

    @PrePersist
    public void setTenant(Object entity) throws NoSuchFieldException, IllegalAccessException {
        if (entity instanceof TenantEntity tenantEntity) {
            if (tenantEntity.getTenantId() == null) {
                Long currentTenant = TenantContext.getCurrentTenant();
                tenantEntity.setTenantId(currentTenant);
            }
        }
    }
}
