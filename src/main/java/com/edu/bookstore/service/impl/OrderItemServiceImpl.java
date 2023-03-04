package com.edu.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.bookstore.entity.Order;
import com.edu.bookstore.entity.OrderItem;
import com.edu.bookstore.mapper.OrderItemMapper;
import com.edu.bookstore.service.CartService;
import com.edu.bookstore.service.OrderItemService;
import com.edu.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

    @Autowired
    private OrderService orderService;

    @Override
    public void addByIds(String orderNo, String ids,String nums) {
        QueryWrapper<Order> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("no",orderNo);
        Order order=orderService.getOne(queryWrapper);
        String[] aryIds = ids.split(",");
        String[] num=nums.split(",");
        for(int i=0;i<aryIds.length;i++)
        {
            OrderItem orderItem=new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setBookId(Integer.parseInt(aryIds[i]));
            orderItem.setNum(Integer.parseInt(num[i]));
            this.save(orderItem);
        }
    }

    @Override
    public List<OrderItem> getBookId(int orderId) {
        List<Integer> bookIds = new ArrayList<>();
        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        return this.list(queryWrapper);
    }

    @Override
    public void updateIsComment(int orderId, int bookId,Boolean isComment) {
        QueryWrapper<OrderItem> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("order_id",orderId).eq("book_id",bookId);
        OrderItem orderItem=this.getOne(queryWrapper);
        orderItem.setIsComment(true);
        this.updateById(orderItem);
    }
}
