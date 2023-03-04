package com.edu.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.bookstore.entity.Book;
import com.edu.bookstore.entity.User;
import com.edu.bookstore.mapper.UserMapper;
import com.edu.bookstore.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2022-09-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User logincheck(User user) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername()).eq("password",user.getPassword());
        return this.getOne(queryWrapper);
    }

    @Override
    public Boolean register(User user) {
        user.setPrivilege(0);
        if(finduser(user.getUsername())) {
            return false;
        }else {
            this.save(user);
            return true;
        }
    }

    @Override
    public Boolean finduser(String username) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user=this.getOne(queryWrapper);
        return user != null;
    }

    @Override
    public String getNickname(int userId) {
        return this.getById(userId).getNickname();
    }

    @Override
    public Integer getCount(int privilege) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("privilege",privilege);
        return this.count(queryWrapper);
    }

    @Override
    public Page<User> page(Integer pageNum, Integer pageSize, Integer privilege) {
        Page<User> page=new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.ne("privilege",1).eq("privilege",privilege);
        return this.page(page,queryWrapper);
    }

    @Override
    public void deleteByIds(String ids) {
        String[] aryIds = ids.split(",");
        for (String id : aryIds) {
            this.removeById(id);
        }
    }
}
