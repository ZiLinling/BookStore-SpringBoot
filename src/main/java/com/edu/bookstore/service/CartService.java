package com.edu.bookstore.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.bookstore.entity.Cart;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-09-23
 */
public interface CartService extends IService<Cart> {

    List<Cart> getByUserid(int userId);

    Boolean updateNumById(int id,int num);

    String addItem(int userId,int bookId);

    Boolean updateSelectedById(int id,boolean selected);

    void updateSelectedByIds(String ids,boolean selected);

    List<Cart> getSelectedByUserid(int userId);

    void deleteByIds(String ids);

}
