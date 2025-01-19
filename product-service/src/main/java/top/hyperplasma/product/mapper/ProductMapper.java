package top.hyperplasma.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;
import top.hyperplasma.api.dto.OrderDetailDTO;
import top.hyperplasma.product.domain.po.Product;

public interface ProductMapper extends BaseMapper<Product> {

    @Update("UPDATE product SET stock = stock - #{num} WHERE id = #{productId}")
    void updateStock(OrderDetailDTO orderDetail);

}
