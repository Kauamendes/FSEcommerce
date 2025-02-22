package br.com.fs.ecommerce.foursalesecommerce;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.NestedTestConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@NestedTestConfiguration(NestedTestConfiguration.EnclosingConfiguration.OVERRIDE)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
class FourSalesEcommerceApplicationTests {

    @Test
    void contextLoads() {
    }
}