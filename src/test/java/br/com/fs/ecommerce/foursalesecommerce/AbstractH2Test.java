package br.com.fs.ecommerce.foursalesecommerce;

import br.com.fs.ecommerce.foursalesecommerce.support.TenantContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public abstract class AbstractH2Test {

    public static final Long DEFAULT_TENANT_ID = 1L;
    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());

        TenantContext.setCurrentTenant(DEFAULT_TENANT_ID);

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    protected String asJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void clearTenantContext() {
        // Limpa o contexto para evitar vazamento de estado entre threads de teste
        TenantContext.clear();
    }
}