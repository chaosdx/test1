package com.example.demo;

import com.example.demo.bean.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class TestsRedis
{
    //依赖注入：Redis模板---API
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private Emp emp;

    @Test
    void testset()
    {
        //①、添加单个的key
        redisTemplate.opsForValue().set( "sal", 20000.87 );
        System.out.println( "数据已经添加到Redis数据库中，请确认！" );
    }

    @Test
    void testSetEmp()
    {
        //①、添加对象
        redisTemplate.opsForValue().set( "emp", emp );
        System.out.println( "对象已经添加到Redis数据库中，请确认！" );
    }

    @Test
    void testget()
    {
//        Object sal = redisTemplate.opsForValue().get( "sal" );
//        Object emp = redisTemplate.opsForValue().get( "emp" );
        Object list = redisTemplate.opsForValue().get( "list" );
        System.out.println( "list = " + list );
    }

    @Test
    void testMap()
    {
        //添加集合:Map
        Map <String, Object> map = new HashMap <>();
        map.put( "empno", 8000 );
        map.put( "ename", "zhangs" );
        map.put( "hiredate", new Date() );
        map.put( "sal", 30000.45 );
        redisTemplate.opsForValue().set( "map", map );
        System.out.println( "Map已经添加到Redis数据库中，请确认！" );
    }

    @Test
    void testList()
    {
        //添加集合:List
        List <Emp> list = new ArrayList <>();
        list.add( emp );
        list.add( emp );
        list.add( emp );
        list.add( emp );
        list.add( emp );
        redisTemplate.opsForValue().set( "list", list );
        System.out.println( "List已经添加到Redis数据库中，请确认！" );
    }

    @Test
    void testdel()
    {
        Object sal = redisTemplate.delete( "sal" );
        System.out.println( "数据已经删除！" );
    }

    @Test
    void testexpire()
    {
        redisTemplate.expire( "list", 60, TimeUnit.SECONDS );
        System.out.println( "key的有效期设置结束！" );
    }

    @Test
    void testexpire2()
    {

        redisTemplate.opsForValue().set( "message", "同学们好！", 60, TimeUnit.SECONDS );
        System.out.println( "key的有效期设置结束！" );
    }

}
