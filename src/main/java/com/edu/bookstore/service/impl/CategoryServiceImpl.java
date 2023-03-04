package com.edu.bookstore.service.impl;

import com.edu.bookstore.entity.Category;
import com.edu.bookstore.mapper.CategoryMapper;
import com.edu.bookstore.service.CategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Override
    public String getCategory(int CategoryId) {
        return this.getById(CategoryId).getName();
    }
}
