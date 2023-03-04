package com.edu.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.bookstore.entity.Cart;
import com.edu.bookstore.entity.Favor;
import com.edu.bookstore.mapper.FavorMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.bookstore.service.FavorService;
import org.springframework.stereotype.Service;

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
public class FavorServiceImpl extends ServiceImpl<FavorMapper, Favor> implements FavorService {
    @Override
    public List<Favor> getByUserid(int userId) {
        QueryWrapper<Favor> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_Id",userId);
        return this.list(queryWrapper);
    }

    @Override
    public Boolean isFavor(int userId, int bookId) {
        QueryWrapper<Favor> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_Id",userId).eq("book_id",bookId);
        return !this.list(queryWrapper).isEmpty();
    }

    @Override
    public Boolean delete(int userId, int bookId) {
        QueryWrapper<Favor> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_Id",userId).eq("book_id",bookId);
        return this.remove(queryWrapper);
    }
}
