package top.hyperplasma.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hyperplasma.order.domain.dto.OrderFormDTO;
import top.hyperplasma.order.domain.po.Order;

public interface IOrderService extends IService<Order> {

    Long createOrder(OrderFormDTO orderFormDTO);

    void markOrderPaySuccess(Long orderId);

    void cancelOrder(Long orderId);
}
