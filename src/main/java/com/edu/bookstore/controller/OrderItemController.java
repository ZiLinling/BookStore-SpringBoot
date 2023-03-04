package com.edu.bookstore.controller;

import com.edu.bookstore.Utils.JwtUtil;
import com.edu.bookstore.entity.OrderItem;
import com.edu.bookstore.entity.Result;
import com.edu.bookstore.service.OrderItemService;
import com.edu.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2022-09-23
 */
@RestController
@RequestMapping("/orderitem")
public class OrderItemController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/updateIsComment")
    public Result updateIsComment(int orderId,int bookId,Boolean isComment) {
        Result result = new Result();
        orderItemService.updateIsComment(orderId,bookId,isComment);
        result.success("订单:当前商品评价成功");
        return result;
    }
}
