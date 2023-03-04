package com.edu.bookstore.service;

import com.edu.bookstore.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-09-23
 */
public interface CategoryService extends IService<Category> {

    String getCategory(int CategoryId);

}
