package top.hyperplasma.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.hyperplasma.order.domain.po.OrderDetail;
import top.hyperplasma.order.mapper.OrderDetailMapper;
import top.hyperplasma.order.service.IOrderDetailService;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {
}
