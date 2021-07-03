package com.xzx.commonsb.corn;

import cn.hutool.Hutool;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzx.commonsb.dto.AliAccessKeyDTO;
import com.xzx.commonsb.dto.TxSecretKeyDTO;
import com.xzx.commonsb.entity.Comment;
import com.xzx.commonsb.service.ICommentService;
import com.xzx.commonsb.util.RedisUtil;
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
    private SentimentAnalysisUtil sentimentAnalysisUtil;

    @Autowired
    private RedisUtil redisUtil;

    public CommentNLPCorn() {
        System.out.println("定时任务");
    }

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void getNLPRes() {
        if (!(Boolean) redisUtil.get("nlpCorn")) return;
        AliAccessKeyDTO accessKeyDTO = JSON.parseObject(redisUtil.get("aliKey").toString(), AliAccessKeyDTO.class);
        TxSecretKeyDTO secretKeyDTO = JSON.parseObject(redisUtil.get("txKey").toString(), TxSecretKeyDTO.class);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.or().isNull("tx_res_3").or().isNull("tx_res_2").or().isNull("ali_res");
        queryWrapper.last("limit 1000");

        List<Comment> commentList = commentService.list(queryWrapper);
        if (commentList.size() > 0) {
            for (Comment comment : commentList) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (StrUtil.isBlank(comment.getTxRes3())) comment.setTxRes3(sentimentAnalysisUtil.getTxAnalysisRes(comment.getComment(), "3class", secretKeyDTO.getSecretId(), secretKeyDTO.getSecretKey()));
                if (StrUtil.isBlank(comment.getTxRes2())) comment.setTxRes2(sentimentAnalysisUtil.getTxAnalysisRes(comment.getComment(), "2class", secretKeyDTO.getSecretId(), secretKeyDTO.getSecretKey()));
                if (StrUtil.isBlank(comment.getAliRes())) comment.setAliRes(sentimentAnalysisUtil.getAliAnalysisRes(comment.getComment(), accessKeyDTO.getAccessKeyId(), accessKeyDTO.getSecret()));
                log.info(comment.toString());
            }
            commentService.updateBatchById(commentList);
        }
    }
}
