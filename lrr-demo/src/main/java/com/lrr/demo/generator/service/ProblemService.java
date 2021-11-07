package com.lrr.demo.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import com.lrr.demo.generator.entity.ProblemEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-10-16 00:35:27
 */
public interface ProblemService extends IService<ProblemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

