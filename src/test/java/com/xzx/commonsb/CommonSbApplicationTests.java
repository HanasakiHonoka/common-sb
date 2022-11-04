package com.xzx.commonsb;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xzx.commonsb.corn.CommentNLPCorn;
import com.xzx.commonsb.dto.AliAccessKeyDTO;
import com.xzx.commonsb.dto.TxSecretKeyDTO;
import com.xzx.commonsb.entity.Comment;
import com.xzx.commonsb.mapper.CommentMapper;
import com.xzx.commonsb.service.ICommentService;
import com.xzx.commonsb.service.IMovieService;
import com.xzx.commonsb.util.RedisUtil;
import com.xzx.commonsb.util.SentimentAnalysisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
// 这里以词性标注为例，其它算法的API名称和参数请参考文档
import com.aliyuncs.alinlp.model.v20200629.GetPosChEcomRequest;
import com.aliyuncs.alinlp.model.v20200629.GetPosChEcomResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.core.SpringVersion;

@SpringBootTest
class CommonSbApplicationTests {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IMovieService movieService;

    @Test
    void movieTest() {
        System.out.println(movieService.getById(100));
    }

    @Test
    void contextLoads() {
        System.out.println(commentService.getById(50));
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
    //
    //@Autowired
    //CommentNLPCorn corn;
    //
    //@Test
    //void cornTest() {
    //    corn.getNLPRes();
    //}

    //@Test
    //void fix() {
    //    QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
    //    queryWrapper.orderByAsc("id");
    //    queryWrapper.last("limit 1000");
    //    List<Comment> fixList = commentService.list(queryWrapper);
    //    for (Comment comment : fixList) {
    //        String res = null;
    //        while ((res = SentimentAnalysisUtil.getTxAnalysisRes(comment.getComment(), "3class")) == null) ;
    //        comment.setTxRes3(res);
    //        System.out.println(comment);
    //{"Negative":0.30078092,"Positive":0.6992191,"Sentiment":"positive","RequestId":"3eb93332-0e98-4ed5-a6e5-bc97ab5f6419"}
    //    }
    //    commentService.updateBatchById(fixList);
    //}

    @Test
    void redisTest() {
        //redisUtil.set("1", "value");
    }

    @Test
    void testSpringVersion() {
        String version = SpringVersion.getVersion();

        String version1 = SpringBootVersion.getVersion();

        System.out.println(version);

        System.out.println(version1);
    }

    @Test
    public void ccc(){
        //System.out.println(commentService.list().size());
        List<Comment> list = commentService.list();
        for (Comment com : list) {
            if (com.getScore() != null) continue;
            JSONObject json = JSONObject.parseObject(com.getTxRes2());
            double neg = json.getDoubleValue("Negative");
            double pos = json.getDoubleValue("Positive");
            if (pos > neg) {
                com.setScore(pos);
            } else {
                com.setScore(-neg);
            }
            try {
                commentService.updateById(com);
            } catch (Exception e) {
                System.out.println(com.getId());
            }

        }
    }

}
