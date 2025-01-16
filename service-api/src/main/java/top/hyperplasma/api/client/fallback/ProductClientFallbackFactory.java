package top.hyperplasma.api.client.fallback;

import org.springframework.cloud.openfeign.FallbackFactory;
import top.hyperplasma.api.client.ProductClient;

public class ProductClientFallbackFactory implements FallbackFactory<ProductClient> {
    @Override
    public ProductClient create(Throwable cause) {
        return null;
    }
}
