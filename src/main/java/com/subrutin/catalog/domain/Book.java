package com.subrutin.catalog.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {

    private Long id;

    private String title;

    private String description;

//    private Author author = new Author(); ini spring core dependency
    private Author author;


}
