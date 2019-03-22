package com.atguigu.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.pms.entity.Brand;
import com.atguigu.gmall.pms.mapper.BrandMapper;
import com.atguigu.gmall.pms.service.BrandService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-03-19
 */
@Service
@Component
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Override
    public Map<String, Object> psgeBrand(String keyword, Integer pageNum, Integer pageSize) {

        QueryWrapper<Brand> eq =null;
          //keyword按照品牌名 或者 首字母
        if(!StringUtils.isEmpty(keyword)){
           eq = new QueryWrapper<Brand>().like("name", keyword).eq("first_letter", keyword);
        }

        BrandMapper brandMapper = getBaseMapper();
        IPage<Brand> brandIPage = brandMapper.selectPage(new Page<Brand>(pageNum, pageSize), eq);
        Map<String, Object > map =new HashMap();
        map.put("pageSize",pageSize);
        map.put("totalPage",brandIPage.getPages());
        map.put("total",brandIPage.getTotal());
        map.put("pageNum",pageNum);
        map.put("list",brandIPage.getRecords());

        return map;
    }
}
