package com.lrr.demo.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import com.lrr.demo.generator.entity.PictureEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-10-16 00:35:27
 */
public interface PictureService extends IService<PictureEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

