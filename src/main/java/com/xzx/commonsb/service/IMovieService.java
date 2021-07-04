package com.xzx.commonsb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzx.commonsb.dto.query.MovieQueryDTO;
import com.xzx.commonsb.entity.Movie;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xzx
 * @since 2021-07-04
 */
public interface IMovieService extends IService<Movie> {

    Page<Movie> getMoviePage(MovieQueryDTO movieQueryDTO);

}
