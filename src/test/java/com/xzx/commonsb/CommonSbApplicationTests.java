package com.xzx.commonsb;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzx.commonsb.corn.CommentNLPCorn;
import com.xzx.commonsb.entity.Comment;
import com.xzx.commonsb.mapper.CommentMapper;
import com.xzx.commonsb.service.ICommentService;
import com.xzx.commonsb.service.IConfigService;
import com.xzx.commonsb.util.SentimentAnalysisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
// 这里以词性标注为例，其它算法的API名称和参数请参考文档
import com.aliyuncs.alinlp.model.v20200629.GetPosChEcomRequest;
import com.aliyuncs.alinlp.model.v20200629.GetPosChEcomResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

@SpringBootTest
class CommonSbApplicationTests {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IConfigService configService;

    @Autowired
    private CommentMapper commentMapper;

    @Test
    void contextLoads() {
        System.out.println(commentService.getById(50));
        System.out.println(configService.getById(1));
    }

    @Test
    void getCmtRes() {
        System.out.println(commentMapper.getCommentRes());
    }

    @Test
    void analysisTest() {
        //QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        //queryWrapper.le("id", 50);
        //List<Comment> commentList = commentService.list(queryWrapper);
        //for (Comment comment : commentList) {
        //    comment.setRes(SentimentAnalysisUtil.getTxAnalysisRes(comment.getComment(), "3class"));
        //    System.out.println(comment.getRes());
        //}
        //commentService.updateBatchById(commentList);
    }

    @Autowired
    CommentNLPCorn corn;

    @Test
    void cornTest() {
        corn.getNLPRes();
    }

    @Test
    void fix() {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        queryWrapper.last("limit 1000");
        List<Comment> fixList = commentService.list(queryWrapper);
        for (Comment comment : fixList) {
            String res = null;
            while ((res = SentimentAnalysisUtil.getTxAnalysisRes(comment.getComment(), "3class")) == null) ;
            comment.setTxRes3(res);
            System.out.println(comment);
        }
        commentService.updateBatchById(fixList);
    }




}
