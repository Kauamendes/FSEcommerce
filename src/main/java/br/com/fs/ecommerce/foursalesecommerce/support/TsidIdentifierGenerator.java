package br.com.fs.ecommerce.foursalesecommerce.support;

import io.hypersistence.tsid.TSID;
import lombok.NoArgsConstructor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

@NoArgsConstructor
public class TsidIdentifierGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        return TSID.fast().toLong();
    }
}
