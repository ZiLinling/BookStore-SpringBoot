package com.edu.bookstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.edu.bookstore.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author author
 * @since 2022-09-23
 */
@Data
public class Cart extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer bookId;

    private Integer num;

    private Boolean selected;
}
