package com.edu.bookstore.entity;

import com.edu.bookstore.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class Favor extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer bookId;

    public Favor(Integer userId, Integer bookId) {
        this.bookId=bookId;
        this.userId=userId;
    }
}
