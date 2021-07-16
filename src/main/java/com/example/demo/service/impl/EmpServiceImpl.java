package com.example.demo.service.impl;

import com.example.demo.bean.Emp;
import com.example.demo.mapper.EmpMapper;
import com.example.demo.service.EmpService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务实现
 */
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
@Service
public class EmpServiceImpl implements EmpService
{
    @Resource
    private EmpMapper empMapper;

    //使用缓存注解
    @Cacheable(cacheNames = "emps", key = "#emp")
    @Override
    public Map <String, Object> getEmpInfo(Emp emp)
    {
        Map <String, Object> map  = new HashMap <>();
        List <Emp>           list = new ArrayList <>();
        //获取数据访问层的信息
        list = empMapper.select();
        if (list.size() >= 1)
        {
            map.put( "cokde", "0" );
            map.put( "message", "数据查询成功！" );
            map.put( "list", list );
        } else
        {
            map.put( "cokde", "1" );
            map.put( "message", "数据查询失败！" );
            map.put( "list", list );
        }
        return map;
    }
}
