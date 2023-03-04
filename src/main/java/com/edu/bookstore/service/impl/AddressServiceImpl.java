package com.edu.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.bookstore.entity.Address;
import com.edu.bookstore.entity.Favor;
import com.edu.bookstore.mapper.AddressMapper;
import com.edu.bookstore.mapper.FavorMapper;
import com.edu.bookstore.service.AddressService;
import com.edu.bookstore.service.FavorService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2022-09-23
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Override
    public List<Address> getByUserid(int userId) {
        QueryWrapper<Address> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_Id",userId);
        return this.list(queryWrapper);
    }

}
