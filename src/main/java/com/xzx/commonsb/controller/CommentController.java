package com.xzx.commonsb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzx.commonsb.entity.Comment;
import com.xzx.commonsb.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    ICommentService commentService;

    @GetMapping("/noRes")
    public String getNoRes() {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("res");
        int noResCount = commentService.count(queryWrapper);
        return "还剩"+ noResCount;
    }
}
