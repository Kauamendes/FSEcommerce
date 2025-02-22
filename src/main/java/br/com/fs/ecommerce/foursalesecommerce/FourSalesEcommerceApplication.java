package br.com.fs.ecommerce.foursalesecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.fs.ecommerce.foursalesecommerce.repository")
@EnableJpaAuditing
public class FourSalesEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FourSalesEcommerceApplication.class, args);
    }

}
