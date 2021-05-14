package com.xzx.commonsb;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzx.commonsb.corn.CommentNLPCorn;
import com.xzx.commonsb.entity.Comment;
import com.xzx.commonsb.service.ICommentService;
import com.xzx.commonsb.util.SentimentAnalysisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommonSbApplicationTests {

    @Autowired
    private ICommentService commentService;

    @Test
    void contextLoads() {
        System.out.println(commentService.getById(50));
    }

    @Test
    void analysisTest() {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("id", 50);
        List<Comment> commentList = commentService.list(queryWrapper);
        for (Comment comment : commentList) {
            comment.setRes(SentimentAnalysisUtil.getAnalysisRes(comment.getComment(), "3class"));
            System.out.println(comment.getRes());
        }
        commentService.updateBatchById(commentList);
    }

    @Autowired
    CommentNLPCorn corn;

    @Test
    void cornTest() {
        corn.getNLPRes();
    }

}
