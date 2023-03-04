package com.edu.bookstore.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.bookstore.entity.Order;
import com.edu.bookstore.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-09-23
 */
public interface OrderService extends IService<Order> {

    String create(int userId, double totalprice, String ps,String name,String tel,String address);

    List<Order> getByStatus(Integer userId,int status);

    void setReason(int id,String reason);

    void updateStatus(int id,int status);

    Integer getCountByStatus(int status);

    Page<Order> page(Integer pageNum, Integer pageSize, String no,String username,Integer status);

}
