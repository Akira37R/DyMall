package top.hyperplasma.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hyperplasma.user.domain.dto.LoginFormDTO;
import top.hyperplasma.user.domain.po.User;
import top.hyperplasma.user.domain.vo.UserLoginVO;

public interface IUserService extends IService<User> {

    UserLoginVO login(LoginFormDTO loginFormDTO);

    void deductMoney(String pw, Integer totalFee);
}
