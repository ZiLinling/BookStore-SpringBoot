package com.edu.bookstore.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.bookstore.Utils.JwtUtil;
import com.edu.bookstore.entity.*;
import com.edu.bookstore.service.BookService;
import com.edu.bookstore.service.EvaluationService;
import com.edu.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2022-09-23
 */
@RestController
@RequestMapping("/evaluation")
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public Result getByBookId(int bookId) {
        Result result = new Result();
        result.success("评论:类别列表请求成功");
        List<Evaluation> list=evaluationService.getByBookId(bookId);
        for (Evaluation evaluation : list) {
            evaluation.put("nickname", userService.getNickname(evaluation.getUserId()));
        }
        result.setData(list);
        return result;
    }

    @GetMapping("/getByUserId")
    public Result getByUserId() {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        result.success("评论:类别列表请求成功");
        List<Evaluation> list=evaluationService.getByUserId(userId);
        for (Evaluation evaluation : list) {
            evaluation.put("book", bookService.getById(evaluation.getBookId()));
        }
        result.setData(list);
        return result;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Evaluation evaluation) {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        evaluation.setUserId(userId);
        if (evaluationService.add(evaluation)) {
            result.success("评论:发送评论成功");
        } else {
            result.fail("评论:发送评论失败");
        }
        return result;
    }

    @GetMapping("/getCount")
    public Result getCount() {
        Result result=new Result<>();
        result.success("评论:请求评论条数成功");
        result.setData(evaluationService.count());
        return result;
    }

    @GetMapping(value = "/page")
    public Result page(Integer pageNum, Integer pageSize) {
        Result result = new Result<>();
        Page<Evaluation> evaluationPage = evaluationService.page(pageNum, pageSize);
        List<Evaluation>commentList=evaluationPage.getRecords();
        for (Evaluation comment : commentList) {
            List<Book> book = new ArrayList<>();
            comment.put("book", bookService.getById(comment.getBookId()));
            comment.put("user",userService.getById(comment.getUserId()));
        }
        result.success("评论:请求评论列表成功");
        result.setData(commentList);
        result.put("total", evaluationPage.getTotal());
        return result;
    }

    @PostMapping("/delete")
    public Result delete(String ids) {
        Result result=new Result<>();
        result.success("评论:删除评论成功");
        evaluationService.deleteByIds(ids);
        return result;
    }

    @PostMapping("/setAdditional")
    public Result setAdditional(int id,String additional) {
        Result result=new Result<>();
        result.success("评论:追评成功");
        evaluationService.setAdditional(id,additional);
        return result;
    }
}

