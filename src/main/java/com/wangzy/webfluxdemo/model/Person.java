package com.wangzy.webfluxdemo.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collection;

@Document(collection = "person")
@Data
public class Person {

    /**
     * @Id注解表明这个属性为文档的Id
     */
    @Id
    private String id;
    private String name;
    private Integer age;
    @DBRef
    private Company company;
    private Double salary;


    /**
     * @Field注解此属性在文档中的名称为locs，location属性将以数组形式存在当前数据记录中
     */
    @Field("locs")
    private Collection<Location> locations;

}
