package com.example.demo.model;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Ariticle {
    private Integer id; // 主键ID
    private String title; // 文章标题
    private String content; // 文章内容
    private String coverImg; // 封面图
    private String state; // 发布状态 已发布/草稿
    private Integer categoryId; // 文章分类ID
    private Integer createUser; // 创建人ID
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
