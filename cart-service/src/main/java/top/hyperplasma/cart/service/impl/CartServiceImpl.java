package top.hyperplasma.cart.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.hyperplasma.api.client.ProductClient;
import top.hyperplasma.api.dto.ProductDTO;
import top.hyperplasma.cart.config.CartProperties;
import top.hyperplasma.cart.domain.dto.CartFormDTO;
import top.hyperplasma.cart.domain.po.Cart;
import top.hyperplasma.cart.domain.vo.CartVO;
import top.hyperplasma.cart.mapper.CartMapper;
import top.hyperplasma.cart.service.ICartService;
import top.hyperplasma.common.exceptions.BizIllegalException;
import top.hyperplasma.common.utils.BeanUtils;
import top.hyperplasma.common.utils.CollUtils;
import top.hyperplasma.common.utils.UserContext;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

    // private final RestTemplate restTemplate;
    //
    // private final DiscoveryClient discoveryClient;

    private final ProductClient productClient;

    private final CartProperties cartProperties;

    @Override
    public void addItem2Cart(CartFormDTO cartFormDTO) {
        // 1.获取登录用户
        Long userId = UserContext.getUser();

        // 2.判断是否已经存在
        if(checkItemExists(cartFormDTO.getProductId(), userId)){
            // 2.1.存在，则更新数量
            baseMapper.updateNum(cartFormDTO.getProductId(), userId);
            return;
        }
        // 2.2.不存在，判断是否超过购物车数量
        checkCartsFull(userId);

        // 3.新增购物车条目
        // 3.1.转换PO
        Cart cart = BeanUtils.copyBean(cartFormDTO, Cart.class);
        // 3.2.保存当前用户
        cart.setUserId(userId);
        // 3.3.保存到数据库
        save(cart);
    }

    @Override
    public List<CartVO> queryMyCarts() {
        // 1.查询我的购物车列表
        List<Cart> carts = lambdaQuery().eq(Cart::getUserId,  UserContext.getUser()).list();
        if (CollUtils.isEmpty(carts)) {
            return CollUtils.emptyList();
        }

        // 2.转换VO
        List<CartVO> vos = BeanUtils.copyList(carts, CartVO.class);

        // 3.处理VO中的商品信息
        handleCartItems(vos);

        // 4.返回
        return vos;
    }

    private void handleCartItems(List<CartVO> vos) {
        // 1.获取商品id
        Set<Long> itemIds = vos.stream().map(CartVO::getProductId).collect(Collectors.toSet());
        // 2.查询商品
        /*
        // 2.1.根据服务名称获取服务的实例列表
        List<ServiceInstance> instances = discoveryClient.getInstances("item-service");
        if (CollUtil.isEmpty(instances)) {
            return;
        }
        // 2.2.手写负载均衡，从实例列表中挑选一个实例
        ServiceInstance instance = instances.get(RandomUtil.randomInt(instances.size()));
        // 2.3.利用RestTemplate发起http请求，得到http的响应
        ResponseEntity<List<ItemDTO>> response = restTemplate.exchange(
                instance.getUri() + "/items?ids={ids}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ItemDTO>>() {
                },
                Map.of("ids", CollUtil.join(itemIds, ","))
        );
        // 2.3.解析响应
        if(!response.getStatusCode().is2xxSuccessful()){
            // 查询失败，直接结束
            return;
        }
        List<ItemDTO> items = response.getBody();
        */
        List<ProductDTO> items = productClient.queryItemByIds(itemIds);
        if (CollUtils.isEmpty(items)) {
            return;
        }
        // 3.转为 id 到 item的map
        Map<Long, ProductDTO> itemMap = items.stream().collect(Collectors.toMap(ProductDTO::getId, Function.identity()));
        // 4.写入vo
        for (CartVO v : vos) {
            ProductDTO item = itemMap.get(v.getProductId());
            if (item == null) {
                continue;
            }
            v.setNewPrice(item.getPrice());
            v.setStatus(item.getStatus());
            v.setStock(item.getStock());
        }
    }

    @Override
    @Transactional
    public void removeByProductIds(Collection<Long> itemIds) {
        // 1.构建删除条件，userId和itemId
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Cart::getUserId, UserContext.getUser())
                .in(Cart::getProductId, itemIds);
        // 2.删除
        remove(queryWrapper);
    }

    private void checkCartsFull(Long userId) {
        long count = lambdaQuery().eq(Cart::getUserId, userId).count();
        if (count >= cartProperties.getMaxSize()) {
            throw new BizIllegalException(
                    StrUtil.format("用户购物车课程不能超过{}", cartProperties.getMaxSize()));
        }
    }

    private boolean checkItemExists(Long itemId, Long userId) {
        long count = lambdaQuery()
                .eq(Cart::getUserId, userId)
                .eq(Cart::getProductId, itemId)
                .count();
        return count > 0;
    }
}
