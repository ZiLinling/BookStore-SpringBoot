package com.edu.bookstore.controller;


import com.edu.bookstore.Utils.JwtUtil;
import com.edu.bookstore.entity.*;
import com.edu.bookstore.service.AddressService;
import com.edu.bookstore.service.BookService;
import com.edu.bookstore.service.CategoryService;
import com.edu.bookstore.service.FavorService;
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
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AddressService addressService;

    @GetMapping("/list")
    public Result list() {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        List<Address> list = addressService.getByUserid(userId);
        result.success("地址:列表请求成功");
        result.setData(list);
        return result;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Address address) {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        address.setUserId(userId);
        if(addressService.save(address)){
            result.success("地址:添加地址成功");
        } else{
            result.fail("地址:添加地址失败");
        }
        return result;
    }

    @PostMapping("/update")
    public Result update(@RequestBody Address address) {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        address.setUserId(userId);
        if(addressService.updateById(address)){
            result.success("地址:更新地址成功");
        } else{
            result.fail("地址:更新地址失败");
        }
        return result;
    }

    @PostMapping("/delete")
    public Result delete(int addressId) {
        Result result = new Result();
        if(addressService.removeById(addressId)){
            result.success("地址:删除地址成功");
        } else{
            result.fail("地址:删除地址失败");
        }
        return result;
    }

}

