package top.hyperplasma.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.hyperplasma.user.domain.po.Address;
import top.hyperplasma.user.mapper.AddressMapper;
import top.hyperplasma.user.service.IAddressService;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {
}
