package com.wangzy.webfluxdemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "company")
@Data
public class Company {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
}
