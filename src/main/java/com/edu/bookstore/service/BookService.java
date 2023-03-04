package com.edu.bookstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.bookstore.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-09-23
 */
public interface BookService extends IService<Book> {
    List<Book> getByCategory(int CategoryId);

    List<Book> search(String key);

    Page<Book> page(Integer pageNum, Integer pageSize, Integer CategoryId,String key);

    Page<Book> page(Integer pageNum, Integer pageSize, String name,String author,String publisher,Integer category);

    void deleteByIds(String ids);
}
