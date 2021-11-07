package com.lrr.demo.generator.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-10-16 00:35:27
 */
@Data
@TableName("problem")
public class ProblemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String content;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date publishTime;
	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;

	private Integer uid;

	private String uname;
}
