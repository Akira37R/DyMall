package top.hyperplasma.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import top.hyperplasma.api.config.DefaultFeignConfig;

@EnableFeignClients(basePackages = "top.hyperplasma.api.client", defaultConfiguration = DefaultFeignConfig.class)
@MapperScan("top.hyperplasma.user.mapper")
@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
