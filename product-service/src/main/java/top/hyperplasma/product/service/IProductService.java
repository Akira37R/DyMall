package top.hyperplasma.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hyperplasma.api.dto.OrderDetailDTO;
import top.hyperplasma.api.dto.ProductDTO;
import top.hyperplasma.product.domain.po.Product;

import java.util.Collection;
import java.util.List;

public interface IProductService extends IService<Product> {

    void deductStock(List<OrderDetailDTO> products);

    List<ProductDTO> queryProductByIds(Collection<Long> ids);

}
