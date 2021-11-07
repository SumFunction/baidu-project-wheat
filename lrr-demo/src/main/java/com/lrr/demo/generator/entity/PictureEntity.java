package com.lrr.demo.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-10-16 00:35:27
 */
@Data
@TableName("picture")
public class PictureEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String pictureUrl;
	/**
	 * 
	 */
	private Integer pid;
	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;

}
