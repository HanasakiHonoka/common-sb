package com.xzx.commonsb.corn;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzx.commonsb.entity.Comment;
import com.xzx.commonsb.entity.Config;
import com.xzx.commonsb.service.ICommentService;
import com.xzx.commonsb.service.IConfigService;
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

    @Autowired
    private IConfigService configService;

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void getNLPRes() {
        Config config = configService.getById(1);
        if (!config.getEnable()) return;
        String mode = config.getMode();
        String origin = config.getOrigin();
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        if (origin.equals("tx")) {
            if (mode.equals("3class")) {
                queryWrapper.isNull("tx_res_3");
            } else {
                queryWrapper.isNull("tx_res_2");
            }
        } else {
            queryWrapper.isNull("ali_res");
        }
        queryWrapper.last("limit 1000");
        List<Comment> commentList = commentService.list(queryWrapper);
        if (commentList.size() > 0) {
            for (Comment comment : commentList) {
                if (origin.equals("tx")) {
                    if (mode.equals("3class")) {
                        comment.setTxRes3(SentimentAnalysisUtil.getTxAnalysisRes(comment.getComment(), "3class"));
                    } else {
                        comment.setTxRes2(SentimentAnalysisUtil.getTxAnalysisRes(comment.getComment(), "2class"));
                    }
                } else {
                    comment.setAliRes(SentimentAnalysisUtil.getAliAnalysisRes(comment.getComment()));
                }
                log.info(comment.toString());
            }
            commentService.updateBatchById(commentList);
        }
    }
}
