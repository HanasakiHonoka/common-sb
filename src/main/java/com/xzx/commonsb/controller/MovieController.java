package com.xzx.commonsb.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzx.commonsb.dto.query.MovieQueryDTO;
import com.xzx.commonsb.entity.Movie;
import com.xzx.commonsb.service.IMovieService;
import com.xzx.commonsb.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname MovieController
 * @Description
 * @Date 2021/7/4 21:48
 * @Author XZX
 * @Version 1.0
 */
@RequestMapping("/movie")
@RestController
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @PostMapping("/page")
    public Response getMoviePage(@RequestBody MovieQueryDTO movieQueryDTO) {
        return Response.success(movieService.getMoviePage(movieQueryDTO));
    }
}
