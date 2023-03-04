package com.edu.bookstore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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

@TableName(value ="`order`")
public class Order extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private String no;

    private Integer userId;

    private Double totalprice;

    private Integer status;

    private String createTime;

    private String postscript;

    private String cancelReason;

    private String name;

    private String tel;

    private String address;

}
