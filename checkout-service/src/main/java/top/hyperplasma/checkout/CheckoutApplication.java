package top.hyperplasma.checkout;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import top.hyperplasma.api.config.DefaultFeignConfig;

@EnableFeignClients(basePackages = "top.hyperplasma.api.client", defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication
@MapperScan("top.hyperplasma.checkout.mapper")
public class CheckoutApplication {
    public static void main(String[] args) {
        SpringApplication.run(CheckoutApplication.class, args);
    }
}
