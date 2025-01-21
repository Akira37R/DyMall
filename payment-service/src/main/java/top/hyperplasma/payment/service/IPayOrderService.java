package top.hyperplasma.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hyperplasma.payment.domain.dto.PayApplyDTO;
import top.hyperplasma.payment.domain.dto.PayOrderFormDTO;
import top.hyperplasma.payment.domain.po.PayOrder;

public interface IPayOrderService extends IService<PayOrder> {
    String applyPayOrder(PayApplyDTO applyDTO);

    void tryPayOrderByBalance(PayOrderFormDTO payOrderFormDTO);
}
