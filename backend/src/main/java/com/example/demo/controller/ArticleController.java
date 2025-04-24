package com.example.demo.controller;

import com.example.demo.model.Article;
import com.example.demo.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/article")
@CrossOrigin // 允许前端跨域请求
public class ArticleController {

    // 假数据列表，模拟数据库
    private List<Article> articleList = new ArrayList<>();

    {
        articleList.add(new Article("医疗反腐绝非针对医护收入", "时事", "2023-09-5", "已发布"));
        articleList.add(new Article("中国男篮再负一败到底", "篮球", "2023-09-5", "草稿"));
        articleList.add(new Article("华山景区已发出雷雨大风7-8级", "旅游", "2023-09-5", "已发布"));
    }

    // 新增文章接口
    @PostMapping("/add")
    public String add(@RequestBody Article article) {
        articleList.add(article);
        return "新增成功";
    }

    // 获取所有文章信息
    @GetMapping("/getAll")
    public List<Article> getAll() {
        return articleList;
    }

    // 根据分类和状态查询文章
    @GetMapping("/search")
    public List<Article> search(@RequestParam String category,
                                @RequestParam String state) {
        return articleList.stream()
                .filter(a -> a.getCategory().equals(category) && a.getState().equals(state))
                .collect(Collectors.toList());
    }
}
