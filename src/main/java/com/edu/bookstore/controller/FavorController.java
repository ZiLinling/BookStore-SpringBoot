package com.edu.bookstore.controller;


import com.edu.bookstore.Utils.JwtUtil;
import com.edu.bookstore.entity.Book;
import com.edu.bookstore.entity.Cart;
import com.edu.bookstore.entity.Favor;
import com.edu.bookstore.entity.Result;
import com.edu.bookstore.service.BookService;
import com.edu.bookstore.service.CategoryService;
import com.edu.bookstore.service.FavorService;
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
@RequestMapping("/favor")
public class FavorController {
    @Autowired
    private FavorService favorService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public Result list() {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        List<Favor> list = favorService.getByUserid(userId);
        List<Book> bookList=new ArrayList<>();
        for (Favor item : list) {
            Book book=bookService.getById(item.getBookId());
            book.put("category",categoryService.getCategory(book.getCategory()));
            bookList.add(book);
        }
        result.success("收藏:列表请求成功");
        result.setData(bookList);
        return result;
    }

    @PostMapping("/add")
    public Result add(int bookId) {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        Favor favor=new Favor(userId,bookId);
        if(favorService.save(favor)){
            result.success("收藏:添加收藏请求成功");
        } else{
            result.fail("收藏:添加收藏请求失败");
        }
        return result;
    }

    @PostMapping("/delete")
    public Result delete(int bookId) {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        if(favorService.delete(userId,bookId)){
            result.success("收藏:取消收藏请求成功");
        } else{
            result.fail("收藏:取消收藏请求失败");
        }
        return result;
    }

    @GetMapping("/isFavor")
    public Result isFavor(int bookId)
    {
        Result result = new Result();
        String token = request.getHeader("token");
        int userId = JwtUtil.parseToken(token);
        if(favorService.isFavor(userId,bookId)){
            result.success("收藏:当前书籍为收藏状态");
        }else{
            result.fail("收藏:当前书籍为未收藏状态");
        }
        return result;
    }
}

