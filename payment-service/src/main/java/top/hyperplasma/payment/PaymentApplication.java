package top.hyperplasma.payment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import top.hyperplasma.api.config.DefaultFeignConfig;

@EnableFeignClients(basePackages = "top.hyperplasma.api.client", defaultConfiguration = DefaultFeignConfig.class)
@MapperScan("top.hyperplasma.payment.mapper")
@SpringBootApplication
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }
}
