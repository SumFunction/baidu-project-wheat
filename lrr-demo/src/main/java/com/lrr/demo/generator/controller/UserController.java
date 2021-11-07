package com.lrr.demo.generator.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lrr.demo.generator.entity.UserEntity;
import com.lrr.demo.generator.service.UserService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-10-16 00:35:27
 */
@RestController
@RequestMapping("generator/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		UserEntity user = userService.getById(id);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserEntity user){
        try {
            userService.save(user);
        }catch(Exception e){
            return R.error().put("caiji","你个小菜鸡");
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 登陆
     * @return
     */
    @RequestMapping("/login")
    public R login(@RequestBody UserEntity userEntity){
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_name",userEntity.getUserName());
        map.put("password",userEntity.getPassword());
        List<UserEntity> entities = userService.listByMap(map);
//        System.out.println(entities);
        if(entities!=null&&entities.size()==1){
            return R.ok().put("userData",entities);
        }

        return R.error().put("Entity",entities);
    }
}
