package com.xzx.commonsb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzx.commonsb.entity.Comment;
import com.xzx.commonsb.mapper.CommentMapper;
import com.xzx.commonsb.service.ICommentService;
import com.xzx.commonsb.util.RedisUtil;
import com.xzx.commonsb.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/noRes")
    public String getNoRes() {
        CommentVO commentVO = commentMapper.getCommentRes();
        return String.format("腾讯2class还剩%d, 腾讯3class还剩%d, 阿里还剩%d", commentVO.getTxRes2(), commentVO.getTxRes3(), commentVO.getAliRes());
    }

    @PostMapping("/corn")
    public void enableCorn(Boolean enable) {
        redisUtil.set("nlpCorn", enable);
    }
}
