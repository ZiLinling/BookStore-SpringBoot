package com.edu.bookstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.bookstore.entity.Book;
import com.edu.bookstore.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-09-23
 */
public interface UserService extends IService<User> {

    User logincheck(User user);

    Boolean register(User user);

    Boolean finduser(String username);

    String getNickname(int userId);

    Integer getCount(int privilege);

    Page<User> page(Integer pageNum, Integer pageSize, Integer privilege);

    void deleteByIds(String ids);

}
