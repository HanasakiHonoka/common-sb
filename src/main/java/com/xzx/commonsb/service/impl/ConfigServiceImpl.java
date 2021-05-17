package com.xzx.commonsb.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzx.commonsb.entity.Config;
import com.xzx.commonsb.mapper.ConfigMapper;
import com.xzx.commonsb.service.IConfigService;
import org.springframework.stereotype.Service;

/**
 * @Classname ConfigServiceImpl
 * @Description
 * @Date 2021/5/17 23:04
 * @Author XZX
 * @Version 1.0
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {
}
