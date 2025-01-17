package top.hyperplasma.product.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer price;

    private Integer stock;

    private String picture;

    private String category;

    private String description;

    private Integer sales;

    private Integer status; // 1-正常 2-下架 3-删除

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long creator;

    private Long updater;
}
