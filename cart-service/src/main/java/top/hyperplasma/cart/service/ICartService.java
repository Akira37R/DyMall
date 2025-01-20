package top.hyperplasma.cart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hyperplasma.cart.domain.dto.CartFormDTO;
import top.hyperplasma.cart.domain.po.Cart;
import top.hyperplasma.cart.domain.vo.CartVO;

import java.util.Collection;
import java.util.List;

public interface ICartService extends IService<Cart> {

    void addItem2Cart(CartFormDTO cartFormDTO);

    List<CartVO> queryMyCarts();

    void removeByProductIds(Collection<Long> productIds);
}
