package com.lrr.demo.generator.dao;

import com.lrr.demo.generator.entity.CommentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-10-16 00:35:27
 */
@Mapper
public interface CommentDao extends BaseMapper<CommentEntity> {
	
}
