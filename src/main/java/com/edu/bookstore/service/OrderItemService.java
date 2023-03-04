package com.edu.bookstore.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.bookstore.entity.OrderItem;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-09-23
 */
public interface OrderItemService extends IService<OrderItem> {

    void addByIds(String orderNo,String ids,String nums);

    List<OrderItem> getBookId(int orderId);

    void updateIsComment(int orderId,int bookId,Boolean isComment);
}
