package com.edu.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.bookstore.entity.Book;
import com.edu.bookstore.entity.Cart;
import com.edu.bookstore.mapper.CartMapper;
import com.edu.bookstore.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    @Override
    public List<Cart> getByUserid(int userId) {
        QueryWrapper<Cart> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_Id",userId);
        return this.list(queryWrapper);
    }

    @Override
    public Boolean updateNumById(int id,int num) {
        Cart cart=this.getById(id);
        cart.setNum(num);
        return this.updateById(cart);
    }

    @Override
    public String addItem(int userId, int bookId) {
        QueryWrapper<Cart> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("book_id",bookId);
        Cart cart=this.getOne(queryWrapper);
        if(cart!=null) {
            cart.setNum(cart.getNum()+1);
            this.updateById(cart);
            return "当前书籍已存在购物车,数量+1";
        }else{
            cart=new Cart();
            cart.setUserId(userId);
            cart.setBookId(bookId);
            cart.setNum(1);
            cart.setSelected(false);
            this.save(cart);
            return "当前书籍不存在购物车,现已添加,数量为1";
        }
    }

    @Override
    public Boolean updateSelectedById(int id, boolean selected) {
        Cart cart=this.getById(id);
        cart.setSelected(selected);
        return this.updateById(cart);
    }

    @Override
    public void updateSelectedByIds(String ids, boolean selected) {
        String[] aryIds = ids.split(",");
        for (String id : aryIds) {
            Cart cart=this.getById(id);
            cart.setSelected(selected);
            this.updateById(cart);
        }
    }

    @Override
    public List<Cart> getSelectedByUserid(int userId) {
        QueryWrapper<Cart> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_Id",userId).eq("selected",1);
        return this.list(queryWrapper);
    }

    @Override
    public void deleteByIds(String ids) {
        String[] aryIds = ids.split(",");
        for (String id : aryIds) {
            this.removeById(id);
        }
    }
}
