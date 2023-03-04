package com.edu.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.bookstore.Utils.DateTool;
import com.edu.bookstore.entity.Evaluation;
import com.edu.bookstore.entity.Order;
import com.edu.bookstore.entity.User;
import com.edu.bookstore.mapper.EvaluationMapper;
import com.edu.bookstore.service.EvaluationService;
import com.edu.bookstore.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class EvaluationServiceImpl extends ServiceImpl<EvaluationMapper, Evaluation> implements EvaluationService {

    @Autowired
    private OrderItemService orderItemService;
    @Override
    public List<Evaluation> getByBookId(int bookId) {
        QueryWrapper<Evaluation> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("book_id",bookId);
        return this.list(queryWrapper);
    }

    @Override
    public List<Evaluation> getByUserId(int UserId) {
        QueryWrapper<Evaluation> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",UserId);
        return this.list(queryWrapper);
    }

    @Override
    public Boolean add(Evaluation evaluation) {
        evaluation.setCreateTime(DateTool.getCurrTime());
        return this.save(evaluation);
    }

    @Override
    public Page<Evaluation> page(Integer pageNum, Integer pageSize) {
        Page<Evaluation> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Evaluation> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        return this.page(page,queryWrapper);
    }

    @Override
    public void deleteByIds(String ids) {
        String[] aryIds = ids.split(",");
        for (String id : aryIds) {
            this.removeById(id);
        }
    }

    @Override
    public void setAdditional(int id, String additional) {
        Evaluation evaluation=this.getById(id);
        evaluation.setAdditional(additional);
        this.updateById(evaluation);
    }
}
