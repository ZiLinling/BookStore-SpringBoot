package com.edu.bookstore.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.bookstore.entity.Cart;
import com.edu.bookstore.entity.Favor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-11-6
 */
public interface FavorService extends IService<Favor> {

    List<Favor> getByUserid(int userId);

    Boolean delete(int userId,int bookId);

    Boolean isFavor(int userId,int bookId);
}
