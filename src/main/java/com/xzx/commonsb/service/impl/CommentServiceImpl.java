package com.xzx.commonsb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzx.commonsb.entity.Comment;
import com.xzx.commonsb.mapper.CommentMapper;
import com.xzx.commonsb.service.ICommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
}
