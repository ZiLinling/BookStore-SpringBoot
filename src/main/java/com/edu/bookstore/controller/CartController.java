package com.edu.bookstore.controller;


import com.edu.bookstore.Utils.JwtUtil;
import com.edu.bookstore.entity.Cart;
import com.edu.bookstore.entity.Result;
import com.edu.bookstore.service.BookService;
import com.edu.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2022-09-23
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/list")
    public Result list() {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        List<Cart> list = cartService.getByUserid(userId);
        for (Cart item : list) {
            item.put("info", bookService.getById(item.getBookId()));
        }
        result.success("请求成功");
        result.setData(list);
        return result;
    }

    @GetMapping("/getselected")
    public Result getSelected() {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        List<Cart> list = cartService.getSelectedByUserid(userId);
        for (Cart item : list) {
            item.put("info", bookService.getById(item.getBookId()));
        }
        result.success("请求成功");
        result.setData(list);
        return result;
    }

    @GetMapping("/add")
    public Result add(int bookId) {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        result.success("请求成功");
        result.setData(cartService.addItem(userId, bookId));
        return result;
    }

    @PostMapping("/deleteById")
    public Result delete(int id) {
        Result result = new Result();
        if (cartService.removeById(id)) {
            result.success("购物车:删除购物车物品成功");
        } else {
            result.fail("购物车:删除购物车物品失败");
        }
        return result;
    }

    @PostMapping("/deleteByIds")
    public Result delete(String ids) {
        Result result = new Result();
        cartService.deleteByIds(ids);
        result.success("购物车:删除购物车物品成功");
        return result;
    }

    @GetMapping("/updateNum")
    public Result updateNum(int id, int num) {
        Result result = new Result();
        if (cartService.updateNumById(id, num)) {
            result.success("请求成功");
        } else {
            result.fail("请求失败");
        }
        return result;
    }

    @GetMapping("/updateSelectedById")
    public Result updateSelected(int id, boolean selected) {
        Result result = new Result();
        if (cartService.updateSelectedById(id, selected)) {
            result.success("请求成功");
        } else {
            result.fail("请求失败");
        }
        return result;
    }

    @GetMapping("/updateSelectedByIds")
    public Result updateSelected(String ids, boolean selected) {
        Result result = new Result();
        cartService.updateSelectedByIds(ids, selected);
        result.success("购物车:物品选择状态修改成功");
        return result;
    }
}


