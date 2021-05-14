package com.xzx.commonsb.corn;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzx.commonsb.entity.Comment;
import com.xzx.commonsb.service.ICommentService;
import com.xzx.commonsb.util.SentimentAnalysisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CommentNLPCorn {

    @Autowired
    private ICommentService commentService;

    @Scheduled(cron = "0 0/10 * * * ? ")
    public void getNLPRes() {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("res");
        queryWrapper.last("limit 1000");
        List<Comment> commentList = commentService.list(queryWrapper);
        if (commentList.size() > 0) {
            for (Comment comment : commentList) {
                comment.setRes(SentimentAnalysisUtil.getAnalysisRes(comment.getComment(), "3class"));
                log.info(comment.getRes());
            }
            commentService.updateBatchById(commentList);
        }
    }
}
