package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.bean.Emp;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * 数据访问层
 */
public interface EmpMapper extends BaseMapper <Emp>
{
    //查询信息
    @Select("select * from emp")
    List <Emp> select();
}
