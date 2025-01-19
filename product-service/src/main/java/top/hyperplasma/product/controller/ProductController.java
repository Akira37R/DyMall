package top.hyperplasma.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.hyperplasma.api.dto.OrderDetailDTO;
import top.hyperplasma.api.dto.ProductDTO;
import top.hyperplasma.common.domain.PageDTO;
import top.hyperplasma.common.domain.PageQuery;
import top.hyperplasma.common.utils.BeanUtils;
import top.hyperplasma.product.domain.po.Product;
import top.hyperplasma.product.service.IProductService;

import java.util.List;

@Api(tags = "商品管理相关接口")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @ApiOperation("分页查询商品")
    @GetMapping("/page")
    public PageDTO<ProductDTO> queryProductByPage(
            PageQuery query,
            @RequestHeader(value = "truth", required = false) String truth) {
        System.out.println("truth = " + truth);
        // 1.分页查询
        Page<Product> result = productService.page(query.toMpPage("update_time", false));
        // 2.封装并返回
        return PageDTO.of(result, ProductDTO.class);
    }

    @ApiOperation("根据id批量查询商品")
    @GetMapping
    public List<ProductDTO> queryProductByIds(@RequestParam("ids") List<Long> ids) {
        return productService.queryProductByIds(ids);
    }

    @ApiOperation("根据id查询商品")
    @GetMapping("{id}")
    public ProductDTO queryProductById(@PathVariable("id") Long id) {
        return BeanUtils.copyBean(productService.getById(id), ProductDTO.class);
    }

    @ApiOperation("新增商品")
    @PostMapping
    public void saveProduct(@RequestBody ProductDTO Product) {
        // 新增
        productService.save(BeanUtils.copyBean(Product, Product.class));
    }

    @ApiOperation("更新商品状态")
    @PutMapping("/status/{id}/{status}")
    public void updateProductStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
        Product Product = new Product();
        Product.setId(id);
        Product.setStatus(status);
        productService.updateById(Product);
    }

    @ApiOperation("更新商品")
    @PutMapping
    public void updateProduct(@RequestBody ProductDTO Product) {
        // 不允许修改商品状态，所以强制设置为null，更新时，就会忽略该字段
        Product.setStatus(null);
        // 更新
        productService.updateById(BeanUtils.copyBean(Product, Product.class));
    }

    @ApiOperation("根据id删除商品")
    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable("id") Long id) {
        productService.removeById(id);
    }

    @ApiOperation("批量扣减库存")
    @PutMapping("/stock/deduct")
    public void deductStock(@RequestBody List<OrderDetailDTO> products) {
        productService.deductStock(products);
    }

}
