package com.edu.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.bookstore.entity.Address;
import com.edu.bookstore.entity.Favor;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-11-6
 */
public interface AddressService extends IService<Address> {

    List<Address> getByUserid(int userId);

}
