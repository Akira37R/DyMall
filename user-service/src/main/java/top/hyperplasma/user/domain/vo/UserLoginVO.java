package top.hyperplasma.user.domain.vo;

import lombok.Data;

@Data
public class UserLoginVO {
    private String token;
    private Long userId;
    private String username;
    private Integer balance;
}
