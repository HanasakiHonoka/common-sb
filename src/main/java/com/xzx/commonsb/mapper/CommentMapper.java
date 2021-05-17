package com.xzx.commonsb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xzx.commonsb.entity.Comment;
import com.xzx.commonsb.vo.CommentVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("SELECT sum(tx_res_2 is null) as tx_res2, sum(tx_res_3 is null) as tx_res3, sum(ali_res is null) as ali_res " +
            "FROM `comment`")
    public CommentVO getCommentRes();
}
