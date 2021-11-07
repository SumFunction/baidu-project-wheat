package com.lrr.demo.generator.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lrr.demo.generator.dao.ProblemDao;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lrr.demo.generator.entity.ProblemEntity;
import com.lrr.demo.generator.service.ProblemService;
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
@RequestMapping("generator/problem")
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    @Autowired
    private ProblemDao problemDao;
    /**
     * 列表
     */

    //分页查询
    @RequestMapping("/pagequery")
    public R listBypage(@RequestParam(value = "page") long page,@RequestParam(value = "limit") long limit){
            IPage<ProblemEntity> userPage = new Page<>( page, limit);//参数一是当前页，参数二是每页个数
            userPage = problemDao.selectPage(userPage, null);
            List<ProblemEntity> list = userPage.getRecords();
            return R.ok().put("page",list);
    }
    @RequestMapping("/moulist")
    public R moulist(@RequestParam(value = "title") String title){
//        List<ProblemEntity> userInfos = problemDao.selectList(new EntityWrapper<>(userInfo).like("name", title));
        try{
            QueryWrapper<ProblemEntity> problemEntityQueryWrapper = new QueryWrapper<>();
            problemEntityQueryWrapper.like("name",title);
            List<ProblemEntity> problemEntities = problemDao.selectList(problemEntityQueryWrapper);
            return R.ok().put("page",problemEntities);
        }catch (Exception e){
            e.printStackTrace();
            return R.error().put("haah","你个小菜鸡");
        }

    }

    @RequestMapping("/list")
    public R list(@RequestBody ProblemEntity problemEntity){
        HashMap<String, Object> map = new HashMap<>();
        map.put("uid",problemEntity.getUid());
        List<ProblemEntity> page = problemService.listByMap(map);

        return R.ok().put("page", page);
    }
    @RequestMapping("/listbyid")
    public R listById(@RequestBody ProblemEntity problemEntity){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",problemEntity.getId());
        List<ProblemEntity> page = problemService.listByMap(map);

        return R.ok().put("page", page);
    }
// 查询全部的
    @RequestMapping("/listall")
    public R listAll(@RequestParam Map<String,Object> params){
        PageUtils page = problemService.queryPage(params);

        return R.ok().put("page", page);

    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		ProblemEntity problem = problemService.getById(id);

        return R.ok().put("problem", problem);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ProblemEntity problem){
        try{
            problemService.save(problem);

        }catch (Exception e){
            return R.error();
        }

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ProblemEntity problem){
		problemService.updateById(problem);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody ProblemEntity problemEntity){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",problemEntity.getId());
		problemService.removeByMap(map);

        return R.ok();
    }

}
