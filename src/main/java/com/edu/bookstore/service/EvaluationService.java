package com.edu.bookstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.bookstore.entity.Evaluation;
import com.edu.bookstore.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-09-23
 */
public interface EvaluationService extends IService<Evaluation> {

    List<Evaluation> getByBookId(int bookId);

    List<Evaluation> getByUserId(int UserId);


    Boolean add(Evaluation evaluation);

    Page<Evaluation> page(Integer pageNum, Integer pageSize);
    void deleteByIds(String ids);

    void setAdditional(int id,String additional);

}
