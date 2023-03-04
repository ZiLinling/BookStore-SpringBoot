package com.edu.bookstore.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.bookstore.Utils.DateTool;
import com.edu.bookstore.entity.Book;
import com.edu.bookstore.entity.Result;
import com.edu.bookstore.service.BookService;
import com.edu.bookstore.service.CategoryService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private HttpServletRequest request;

    @Value("${path.backstage}")
    private String backstage;

    @Value("${path.forestage}")
    private String forestage;

    @GetMapping("/all")
    public Result getAll() {
        Result result = new Result();
        List<Book> booklist = bookService.list();
        if (booklist.isEmpty()) {
            result.fail("查询失败");
        } else {
            for (Book book : booklist) {
                book.put("category", categoryService.getCategory(book.getCategory()));
            }
            result.success("查询成功");
            result.setData(booklist);
        }
        return result;
    }

    @GetMapping(value = "/page")
    public Result page(Integer pageNum, Integer pageSize, Integer CategoryId, String key) {
        Result result = new Result<>();
        Page<Book> bookPage = bookService.page(pageNum, pageSize, CategoryId, key);
        List<Book> booklist = bookPage.getRecords();
        if (booklist.isEmpty()) {
            result.fail("查询失败");
        } else {
            for (Book book : booklist) {
                book.put("category", categoryService.getCategory(book.getCategory()));
            }
            result.success("查询成功");
            result.setData(booklist);
        }
        result.put("total", bookPage.getTotal());
        return result;
    }

    @GetMapping(value = "/search")
    public Result page(Integer pageNum, Integer pageSize, String name,String author,String publisher,Integer category) {
        Result result = new Result<>();
        Page<Book> bookPage = bookService.page(pageNum, pageSize, name, author, publisher, category);
        List<Book> booklist = bookPage.getRecords();
        for (Book book : booklist) {
            book.put("category", categoryService.getCategory(book.getCategory()));
        }
        result.success("查询成功");
        result.setData(booklist);
        result.put("total", bookPage.getTotal());
        return result;
    }

    @GetMapping("/selectByCategory")
    public Result getOneCategoryBooks(int CategoryId) {
        Result result = new Result();
        List<Book> booklist = bookService.getByCategory(CategoryId);
        if (booklist.isEmpty()) {
            result.fail("查询失败");
        } else {
            for (Book book : booklist) {
                book.put("category", categoryService.getCategory(book.getCategory()));
            }
            result.success("查询成功");
            result.setData(booklist);
        }
        return result;
    }

    @GetMapping("/select")
    public Result getBook(int BookId) {
        Result result = new Result();
        Book book = bookService.getById(BookId);
        if (book == null) {
            result.fail("查询失败");
        } else {
            book.put("category", categoryService.getCategory(book.getCategory()));
            result.success("查询成功");
            result.setData(book);
        }
        return result;
    }

    @GetMapping("/getCount")
    public Result getCount() {
        Result result=new Result<>();
        result.success("书籍:请求书籍册数成功");
        result.setData(bookService.count());
        return result;
    }
    @PostMapping("/update")
    public Result update(@RequestBody Book book) {
        Result result=new Result<>();
        result.success("书籍:更新书籍信息成功");
        result.setData(bookService.updateById(book));
        return result;
    }

    @PostMapping("/save")
    public Result save(@RequestBody Book book) {
        Result result=new Result<>();
        result.success("书籍:新增书籍信息成功");
        result.setData(bookService.save(book));
        return result;
    }

    @PostMapping("/delete")
    public Result delete(String ids) {
        Result result=new Result<>();
        result.success("书籍:删除书籍成功");
        bookService.deleteByIds(ids);
        return result;
    }

    @PostMapping("/uploadImg")
    public Result uploadImg(Book book,MultipartFile file) throws Exception{
        //判断文件类型
        Result result=new Result<>();
        //表示获得服务器的绝对路径

        //得到上传时的文件名字
        String originalname=file.getOriginalFilename();
        //substring(originalname.lastIndexOf(".")截取图片名后缀
        String name="";
        if(book.getImg()!=null)
        {
            name=book.getImg();
            System.out.println(name);
        }else{
            name=originalname.substring(originalname.lastIndexOf("."));
            name="image/"+System.currentTimeMillis()+name;
            System.out.println(name);
        }
        File backFile=new File(backstage,name);
        File foreFile=new File(forestage,"/image/");
        //获得文件目录，判断是否存在
        if(!foreFile.getParentFile().exists()) {
            backFile.getParentFile().mkdirs();
        }
        if(!backFile.getParentFile().exists()) {
            backFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(backFile);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        FileUtils.copyFileToDirectory(
                backFile,
                foreFile
        );

        result.success("书籍:更新图片");
        result.setData(name);
        return result;
    }
}
