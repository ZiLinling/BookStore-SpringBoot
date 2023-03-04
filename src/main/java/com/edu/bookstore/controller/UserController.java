package com.edu.bookstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.bookstore.Utils.JwtUtil;
import com.edu.bookstore.entity.Book;
import com.edu.bookstore.entity.Result;
import com.edu.bookstore.entity.User;
import com.edu.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/userLogin")
    public Result userLogin(@RequestBody User user) {
        Result result = new Result<>();
        user = userService.logincheck(user);
        if (user == null) {
            result.fail("用户:登录失败");
            result.setData("账号或密码错误,请重新登录");
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("userid", user.getId());
            map.put("token", JwtUtil.generateToken(user));
            result.success("用户:登录成功");
            result.setData(map);
        }
        return result;
    }

    @PostMapping("/adminLogin")
    public Result adminLogin(@RequestBody User user) {
        Result result = new Result<>();
        user = userService.logincheck(user);
        if (user == null) {
            result.fail("用户:登录失败");
            result.setData("账号或密码错误,请重新登录");
        } else {
            if (user.getPrivilege() != 1) {
                result.fail("用户:登录失败");
                result.setData("当前账号权限不足,请使用管理员账号登录");
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("userid", user.getId());
                map.put("token", JwtUtil.generateToken(user));
                result.success("登录:登录成功");
                result.setData(map);
            }
        }
        return result;
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        Result result = new Result<>();
        if (userService.register(user)) {
            Map<String, Object> map = new HashMap<>();
            map.put("userid", user.getId());
            map.put("token", JwtUtil.generateToken(user));
            result.success("注册成功,自动登录");
            result.setData(map);
        } else {
            result.fail("注册失败,用户名重复");
        }
        return result;
    }

    @GetMapping("/person")
    public Result getPersonInformation() {
        Result result = new Result<>();
        String token = request.getHeader("token");
        User user = userService.getById(JwtUtil.parseToken(token));
        if (user != null) {
            user.setPassword(null);
            user.setPrivilege(null);
            result.setData(user);
            result.success("请求成功");
        } else {
            result.fail("请求失败");
        }
        return result;
    }

    @GetMapping("/getCount")
    public Result getCount() {
        Result result = new Result<>();
        result.success("用户:请求用户人数成功");
        result.setData(userService.getCount(0));
        return result;
    }

    @GetMapping(value = "/page")
    public Result page(Integer pageNum, Integer pageSize, Integer privilege) {
        Result result = new Result<>();
        Page<User> userPage = userService.page(pageNum, pageSize, privilege);
        result.success("用户:请求用户列表成功");
        result.setData(userPage);
        return result;
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        Result result = new Result<>();
        result.success("用户:更新用户信息成功");
        result.setData(userService.updateById(user));
        return result;
    }

    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        Result result = new Result<>();
        if (userService.finduser(user.getUsername())) {
            result.fail("用户:新增用户信息失败");
            result.setData("当前用户名已存在");
        }else{
            result.success("用户:新增用户信息成功");
            result.setData(userService.save(user));
        }
        return result;
    }

    @PostMapping("/delete")
    public Result delete(String ids) {
        Result result=new Result<>();
        result.success("用户:删除用户成功");
        userService.deleteByIds(ids);
        return result;
    }
}

