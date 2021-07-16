package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.bean.Emp;
import com.example.demo.mapper.EmpMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class DemoApplicationTests
{
    //依赖注入：数据访问层的组件：API
    @Resource
    private EmpMapper empMapper;

    @Resource
    private Emp emp;

    //添加：
    @Test
    void testinsert()
    {
        int count = empMapper.insert( emp );
        System.out.println( "count = " + count );
    }

    //删除：
    @Test
    void testdeleteById()
    {
        int count = empMapper.deleteById( emp );
        System.out.println( "count = " + count );
    }

    //带条件删除：
    @Test
    void testdeleteQueryWrapper()
    {

        //创建条件构造器：
        QueryWrapper queryWrapper = new QueryWrapper <>();
        queryWrapper.eq( "deptno", 30 );
        queryWrapper.ge( "sal", 1000 );
        queryWrapper.like( "ename", "A" );
        queryWrapper.or();
        queryWrapper.isNull( "comm" );
        //调用delete
        int count = empMapper.delete( queryWrapper );
        System.out.println( "count = " + count );
    }

    //deleteBatchIds批量删除：
    @Test
    void testdeleteBatchIds()
    {
        //前端---数据到控制层
        List <Integer> list = new ArrayList <>();
        list.add( 7566 );
        list.add( 7788 );
        list.add( 7844 );
        Integer count = empMapper.deleteBatchIds( list );
        ///前端---推送数组的形式到控制层
        Integer[] arrays = new Integer[]{7566, 7788, 7899};
        Integer   count2 = empMapper.deleteBatchIds( Arrays.asList( arrays ) );
        System.out.println( "count = " + list );
    }

    //条件更新：
    @Test
    void testupdate()
    {
        //构造更新条件器
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.isNotNull( "comm" );
//        updateWrapper.ge( "sal", 1000 );
        updateWrapper.between( "sal", 1000, 3000 );
        Integer count = empMapper.update( emp, updateWrapper );
    }

    //动态查询+分页查询：selectMapsPage
    @Test
    void testselectMapsPage()
    {
        //该框架提供了分页组件:第一个参数：当前页码数 第二个参数：没有显示的条数
        //通过前端----推送到控制层
        Page         page         = new Page( 1, 5 );
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.ge( emp.getSal() != null, "sal", emp.getSal() );
        queryWrapper.eq( emp.getDeptno() != null, "detpno", emp.getDeptno() );
        queryWrapper.ge( emp.getSal() != null, "sal", emp.getSal() );
        queryWrapper.like( emp.getEname() != null, "ename", emp.getEname() );
        //返回是：IPage类型
        IPage <Emp> iPage = empMapper.selectMapsPage( page, queryWrapper );
        iPage.getPages();
        iPage.getRecords();
        iPage.getCurrent();
        iPage.getSize();
        //后续根据开发需求自行使用
    }

}
