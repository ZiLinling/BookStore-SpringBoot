package com.edu.bookstore.entity;

import com.edu.bookstore.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class Address extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String tel;

    private String address;

    private Integer userId;
}
