package com.xzx.commonsb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzx.commonsb.dto.query.MovieQueryDTO;
import com.xzx.commonsb.entity.Movie;
import com.xzx.commonsb.mapper.MovieMapper;
import com.xzx.commonsb.service.IMovieService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xzx
 * @since 2021-07-04
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements IMovieService {

    @Override
    public Page<Movie> getMoviePage(MovieQueryDTO movieQueryDTO) {
        Page<Movie> moviePage = baseMapper.selectPage(new Page<>(movieQueryDTO.getPageIndex(), movieQueryDTO.getPageSize()), new QueryWrapper<>());
        return moviePage;
    }
}
