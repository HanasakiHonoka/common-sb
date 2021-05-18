package com.xzx.commonsb.corn;

import cn.hutool.Hutool;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzx.commonsb.entity.Comment;
import com.xzx.commonsb.entity.Config;
import com.xzx.commonsb.service.ICommentService;
import com.xzx.commonsb.service.IConfigService;
import com.xzx.commonsb.util.SentimentAnalysisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@ConditionalOnProperty(prefix = "scheduling", name = "enable", havingValue = "true")
public class CommentNLPCorn {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IConfigService configService;

    public CommentNLPCorn() {
        System.out.println("定时任务");
    }

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void getNLPRes() {
        Config config = configService.getById(1);
        if (!config.getEnable()) return;
        String mode = config.getMode();
        String origin = config.getOrigin();
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.or().isNull("tx_res_3").or().isNull("tx_res_2").or().isNull("ali_res");
        queryWrapper.last("limit 1000");
        List<Comment> commentList = commentService.list(queryWrapper);
        if (commentList.size() > 0) {
            for (Comment comment : commentList) {
                if (StrUtil.isBlank(comment.getTxRes3())) comment.setTxRes3(SentimentAnalysisUtil.getTxAnalysisRes(comment.getComment(), "3class"));
                if (StrUtil.isBlank(comment.getTxRes2())) comment.setTxRes2(SentimentAnalysisUtil.getTxAnalysisRes(comment.getComment(), "2class"));
                if (StrUtil.isBlank(comment.getAliRes())) comment.setAliRes(SentimentAnalysisUtil.getAliAnalysisRes(comment.getComment()));
                log.info(comment.toString());
            }
            commentService.updateBatchById(commentList);
        }
    }
}
