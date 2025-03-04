package top.hyperplasma.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import top.hyperplasma.api.config.DefaultFeignConfig;

@EnableFeignClients(basePackages = "top.hyperplasma.api.client", defaultConfiguration = DefaultFeignConfig.class)
@MapperScan("top.hyperplasma.product.mapper")
@SpringBootApplication
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
