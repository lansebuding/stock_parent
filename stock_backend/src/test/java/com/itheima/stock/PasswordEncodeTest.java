package com.itheima.stock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncodeTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 加密测试
     */
    @Test
    public void test1(){
        String s1 = "123456789";
        String s2 = passwordEncoder.encode(s1);
        System.out.println(s2);
        // $2a$10$NqU0UfdgAUsBGSW7OLxOSOq6NUPRPRrXuZheScfK1HJzftMKvTik.
    }

    /**
     * 判断数据是否一致
     */
    @Test
    public void test2(){
        String s1 = "123456789";
        String s2 = "$2a$10$NqU0UfdgAUsBGSW7OLxOSOq6NUPRPRrXuZheScfK1HJzftMKvTik.";
        boolean b = passwordEncoder.matches(s1, s2);
        System.out.println(b);
    }
}
