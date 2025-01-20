package top.hyperplasma.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import top.hyperplasma.api.client.fallback.ProductClientFallbackFactory;
import top.hyperplasma.api.dto.OrderDetailDTO;
import top.hyperplasma.api.dto.ProductDTO;

import java.util.Collection;
import java.util.List;

@FeignClient(value = "product-service", fallbackFactory = ProductClientFallbackFactory.class)
public interface ProductClient {
    @GetMapping("/products")
    List<ProductDTO> queryItemByIds(@RequestParam("ids") Collection<Long> ids);

    @PutMapping("/products/stock/deduct")
    void deductStock(@RequestBody List<OrderDetailDTO> products);
}
