package top.hyperplasma.api.client.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import top.hyperplasma.api.client.ProductClient;
import top.hyperplasma.api.dto.OrderDetailDTO;
import top.hyperplasma.api.dto.ProductDTO;
import top.hyperplasma.common.utils.CollUtils;

import java.util.Collection;
import java.util.List;

@Slf4j
public class ProductClientFallbackFactory implements FallbackFactory<ProductClient> {

    @Override
    public ProductClient create(Throwable cause) {
        return new ProductClient() {
            @Override
            public List<ProductDTO> queryItemByIds(Collection<Long> ids) {
                log.error("查询商品失败！", cause);
                return CollUtils.emptyList();
            }

            @Override
            public void deductStock(List<OrderDetailDTO> items) {
                log.error("扣减商品库存失败！", cause);
                throw new RuntimeException(cause);
            }
        };
    }

}
