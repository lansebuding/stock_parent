package com.itheima.stock;

import org.joda.time.format.DateTimeFormat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author by itheima
 * @Date 2021/12/30
 * @Description
 */
//@SpringBootTest
public class TestRedis {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void test01() {
        //存入值
        redisTemplate.opsForValue().set("myname", "zhangsan");
        //获取值
        String myname = redisTemplate.opsForValue().get("myname");
        System.out.println(myname);
    }

    @Test
    public void test02(){
//        long l = System.currentTimeMillis();
//        System.out.println(l);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);
        System.out.println(format);
    }
}