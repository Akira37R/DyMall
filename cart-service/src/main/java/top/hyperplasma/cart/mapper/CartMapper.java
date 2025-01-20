package top.hyperplasma.cart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import top.hyperplasma.cart.domain.po.Cart;

public interface CartMapper extends BaseMapper<Cart> {

    @Update("UPDATE cart SET num = num + 1 WHERE user_id = #{userId} AND product_id = #{productId}")
    void updateNum(@Param("productId") Long productId, @Param("userId") Long userId);

}
