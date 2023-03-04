package com.edu.bookstore.entity;

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
public class OrderItem extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer orderId;

    private Integer bookId;

    private Integer num;

    private Boolean isComment;
    
}
