package com.lrr.demo.generator.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import org.bouncycastle.util.Times;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-10-16 00:35:27
 */
@Data
@TableName("comment")
public class CommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private Integer thumbsUp;
	/**
	 * 
	 */
	private Integer pid;
	/**
	 * 
	 */
	private Integer uid;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private long publishTime;

	@TableId(type = IdType.AUTO)
	private Integer id;

	private String username;

}
