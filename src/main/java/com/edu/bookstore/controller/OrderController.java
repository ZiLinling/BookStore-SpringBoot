package com.edu.bookstore.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.bookstore.Utils.JwtUtil;
import com.edu.bookstore.entity.*;
import com.edu.bookstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2022-09-23
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/create")
    public Result create(double totalprice, String cartIds, String bookIds, String nums, String ps,String name,String tel,String address) {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        String orderNo = orderService.create(userId, totalprice, ps,name,tel,address);
        System.out.println(orderNo);
        orderItemService.addByIds(orderNo, bookIds, nums);
        cartService.deleteByIds(cartIds);
        result.success("订单:生成订单成功");
        return result;
    }

    @GetMapping("/userOrder")
    public Result getUserOrder(int status) {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        List<Order> orderList = orderService.getByStatus(userId, status);
        for (Order order : orderList) {
            List<Book> bookList = new ArrayList<>();
            List<Integer> nums = new ArrayList<>();
            List<Boolean> isComment=new ArrayList<>();
            List<OrderItem> orderItems = orderItemService.getBookId(order.getId());
            for (OrderItem item : orderItems) {
                bookList.add(bookService.getById(item.getBookId()));
                nums.add(item.getNum());
                isComment.add(item.getIsComment());
            }
            order.put("book", bookList);
            order.put("nums", nums);
            order.put("isComment", isComment);
        }
        result.success("订单:订单列表请求成功");
        result.setData(orderList);
        return result;
    }

    @GetMapping("/totalOrder")
    public Result getTotalOrder(int status) {
        Result result = new Result();
        List<Order> orderList = orderService.getByStatus(null, status);
        for (Order order : orderList) {
            order.put("user",userService.getById(order.getUserId()));
        }
        result.success("订单:订单列表请求成功");
        result.setData(orderList);
        return result;
    }

    @PostMapping("/delete")
    public Result delete(int id) {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        orderService.updateStatus(id, -1);
        result.success("订单:订单删除成功");
        result.setData("订单删除成功");
        return result;
    }

    @PostMapping("/cancel")
    public Result cancel(int id, String reason) {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        orderService.updateStatus(id, 6);
        orderService.setReason(id,reason);
        result.success("订单:订单取消成功");
        result.setData("订单取消成功");
        return result;
    }

    @PostMapping("/pay")
    public Result pay(int id) {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        orderService.updateStatus(id, 2);
        result.success("订单:支付成功,订单转为待发货状态");
        result.setData("已支付,正在等待发货");
        return result;
    }
    @PostMapping("/deliver")
    public Result deliver(int id) {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        orderService.updateStatus(id, 3);
        result.success("订单:商品已发货,订单转为待收货");
        result.setData("商品已发货,正在等待收货");
        return result;
    }

    @PostMapping("/receipt")
    public Result receipt(int id) {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        orderService.updateStatus(id, 4);
        result.success("订单:商品已收货,订单转为待评价状态");
        result.setData("商品已收货");
        return result;
    }


    @PostMapping("/finish")
    public Result finish(int id) {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        orderService.updateStatus(id, 5);
        result.success("订单:商品已评价,订单转为已完成状态");
        result.setData("商品已评价");
        return result;
    }

    @GetMapping(value = "/page")
    public Result page(Integer pageNum, Integer pageSize, String no,String username,Integer status) {
        Result result = new Result<>();
        Page<Order> orderPage = orderService.page(pageNum, pageSize,no,username,status);
        List<Order> orderList = orderPage.getRecords();
        for (Order order : orderList) {
            List<Book> bookList = new ArrayList<>();
            List<Integer> nums = new ArrayList<>();
            List<OrderItem> orderItems = orderItemService.getBookId(order.getId());
            for (OrderItem item : orderItems) {
                bookList.add(bookService.getById(item.getBookId()));
                nums.add(item.getNum());
            }
            order.put("book", bookList);
            order.put("nums", nums);
            order.put("user",userService.getById(order.getUserId()));
        }
        result.success("订单:请求订单列表成功");
        result.setData(orderList);
        result.put("total", orderPage.getTotal());
        return result;
    }

    @GetMapping("/getCount")
    public Result getCount() {
        Result result=new Result<>();
        Map<String, Object> map = new HashMap<>();
        List<Integer>counts=new ArrayList<>();
        for(int i=0;i<8;i++)
        {
            counts.add(orderService.getCountByStatus(i));
        }
        map.put("count", counts);
        result.success("订单:请求订单总数成功");
        result.setData(map);
        return result;
    }
}
