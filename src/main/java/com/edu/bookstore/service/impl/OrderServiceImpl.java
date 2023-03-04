package com.edu.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.bookstore.Utils.DateTool;
import com.edu.bookstore.entity.Order;
import com.edu.bookstore.entity.User;
import com.edu.bookstore.mapper.OrderMapper;
import com.edu.bookstore.service.BookService;
import com.edu.bookstore.service.OrderService;
import com.edu.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2022-09-23
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private UserService userService;
    @Override
    public String create(int userId,double totalprice,String ps,String name,String tel,String address) {
        Order order=new Order();
        order.setUserId(userId);
        order.setNo(DateTool.getCurrNoTime());
        order.setTotalprice(totalprice);
        order.setCreateTime(DateTool.getCurrTime());
        order.setName(name);
        order.setAddress(address);
        order.setTel(tel);
        order.setStatus(1);
        order.setPostscript(ps);
        this.save(order);
        return order.getNo();
    }

    @Override
    public void setReason(int id,String reason) {
        Order order = this.getById(id);
        order.setCancelReason(reason);
        this.updateById(order);
    }

    @Override
    public List<Order> getByStatus(Integer userId, int status) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time").ne("status",-1);
        if(userId!=null)
            queryWrapper.eq("user_id", userId);
        if (status != 0)
            queryWrapper.eq("status", status);
        return this.list(queryWrapper);
    }

    @Override
    public void updateStatus(int id, int status) {
        Order order = this.getById(id);
        order.setStatus(status);
        this.updateById(order);
    }

    @Override
    public Integer getCountByStatus(int status) {
        QueryWrapper<Order> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("status",status);
        return this.count(queryWrapper);
    }

    @Override
    public Page<Order> page(Integer pageNum, Integer pageSize, String no,String username,Integer status) {
        Page<Order> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Order> queryWrapper=new QueryWrapper<>();
        if(!Objects.equals(username, "")) {
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("username", username);
            User user=userService.getOne(userQueryWrapper);
            if (user != null)
                queryWrapper.eq("user_id", user.getId());
            else
                queryWrapper.eq("user_id", 0);
        }
        queryWrapper.orderByDesc("create_time").like("no",no);
        if (status != 0)
            queryWrapper.eq("status", status);
        return this.page(page,queryWrapper);
    }
}
