package com.example.demo.service;

import com.example.demo.bean.Emp;
import java.util.Map;

/**
 * 业务层
 */
public interface EmpService
{
    //查询信息:演示使用Redis作为数据缓存
    Map <String, Object> getEmpInfo(Emp emp);
}
