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
public class Evaluation extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer bookId;

    private Integer level;

    private String comment;

    private String additional;

    private String createTime;

}
