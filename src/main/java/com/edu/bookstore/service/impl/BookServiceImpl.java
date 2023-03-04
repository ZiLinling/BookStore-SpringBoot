package com.edu.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.bookstore.entity.Book;
import com.edu.bookstore.entity.User;
import com.edu.bookstore.mapper.BookMapper;
import com.edu.bookstore.service.BookService;
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
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Override
    public List<Book> getByCategory(int CategoryId) {
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("category",CategoryId);
        return this.list(queryWrapper);
    }

    @Override
    public List<Book> search(String key) {
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name",key).or().like("author",key).or().like("publisher",key);
        return this.list(queryWrapper);
    }

    @Override
    public Page<Book> page(Integer pageNum, Integer pageSize, Integer CategoryId,String key) {
        Page<Book> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        if(CategoryId!=0)
            queryWrapper.like("category",CategoryId);
        if(key!=null)
            queryWrapper.like("name",key).or().like("author",key).or().like("publisher",key);
        return this.page(page,queryWrapper);
    }

    @Override
    public Page<Book> page(Integer pageNum, Integer pageSize, String name,String author,String publisher,Integer category) {
        Page<Book> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        if(category!=0)
            queryWrapper.like("category",category);
        queryWrapper.like("name",name).like("author",author).like("publisher",publisher);
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
