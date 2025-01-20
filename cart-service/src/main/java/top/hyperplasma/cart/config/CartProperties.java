package top.hyperplasma.cart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "dymall.cart")
public class CartProperties {
    private Integer maxSize;
}
