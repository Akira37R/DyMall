package top.hyperplasma.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "dymall.auth")
public class AuthProperties {
    private List<String> includePaths;
    private List<String> excludePaths;
}
