package com.lrr.demo.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-10-16 00:35:27
 */
@Data
@TableName("user")
public class UserEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String userName;
	/**
	 * 
	 */
	private String password;
	/**
	 * 
	 */
	private String pictureUrl;
	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;

}
