package top.hyperplasma.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import top.hyperplasma.api.config.DefaultFeignConfig;

@EnableFeignClients(basePackages = "top.hyperplasma.api.client", defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication
@MapperScan("top.hyperplasma.order.mapper")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
