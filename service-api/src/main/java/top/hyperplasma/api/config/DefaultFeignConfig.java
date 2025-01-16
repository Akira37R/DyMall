package top.hyperplasma.api.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import top.hyperplasma.api.client.fallback.ProductClientFallbackFactory;
import top.hyperplasma.common.utils.UserContext;

public class DefaultFeignConfig {
    @Bean
    public Logger.Level fullFeignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor userInfoRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                Long userId = UserContext.getUser();
                if (userId != null) {
                    template.header("user-info", userId.toString());
                }
            }
        };
    }

    @Bean
    public ProductClientFallbackFactory itemClientFallbackFactory() {
        return new ProductClientFallbackFactory();
    }
}
