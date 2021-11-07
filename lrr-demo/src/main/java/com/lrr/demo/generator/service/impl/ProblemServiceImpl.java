package com.lrr.demo.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import com.lrr.demo.generator.dao.ProblemDao;
import com.lrr.demo.generator.entity.ProblemEntity;
import com.lrr.demo.generator.service.ProblemService;


@Service("problemService")
public class ProblemServiceImpl extends ServiceImpl<ProblemDao, ProblemEntity> implements ProblemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProblemEntity> page = this.page(
                new Query<ProblemEntity>().getPage(params),
                new QueryWrapper<ProblemEntity>()
        );

        return new PageUtils(page);
    }

}