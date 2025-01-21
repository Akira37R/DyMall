package top.hyperplasma.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.hyperplasma.order.domain.po.OrderLogistics;
import top.hyperplasma.order.mapper.OrderLogisticsMapper;
import top.hyperplasma.order.service.IOrderLogisticsService;

@Service
public class OrderLogisticsServiceImpl extends ServiceImpl<OrderLogisticsMapper, OrderLogistics> implements IOrderLogisticsService {
}
