package com.pt.ptportal.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class file {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    //在后端静态文件中文件的实际名称
    private String model;

}
