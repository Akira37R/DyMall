package top.hyperplasma.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.hyperplasma.api.dto.OrderDetailDTO;
import top.hyperplasma.api.dto.ProductDTO;
import top.hyperplasma.common.exceptions.BizIllegalException;
import top.hyperplasma.common.utils.BeanUtils;
import top.hyperplasma.product.domain.po.Product;
import top.hyperplasma.product.mapper.ProductMapper;
import top.hyperplasma.product.service.IProductService;

import java.util.Collection;
import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Override
    @Transactional
    public void deductStock(List<OrderDetailDTO> products) {
        String sqlStatement = "top.hyperplasma.product.mapper.ProductMapper.updateStock";
        boolean r = false;
        try {
            r = executeBatch(products, (sqlSession, entity) -> sqlSession.update(sqlStatement, entity));
        } catch (Exception e) {
            throw new BizIllegalException("更新库存异常，可能是库存不足", e);
        }
        if (!r) {
            throw new BizIllegalException("库存不足！");
        }
    }

    @Override
    public List<ProductDTO> queryProductByIds(Collection<Long> ids) {
        return BeanUtils.copyList(listByIds(ids), ProductDTO.class);
    }

}
