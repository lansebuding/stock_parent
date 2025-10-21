package com.itheima.stock;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.itheima.stock.pojo.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestEasyExcel {
    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUserName("张三"+i);
            user.setAge(i);
            user.setAddress("神圣泰拉"+i);
            user.setBirthday(new Date());
            users.add(user);
        }
        return users;
    }

    @Test
    public void test1(){
        List<User> users = getUsers();
        EasyExcel.write("C:\\Users\\Admin\\Downloads\\test\\1.xlsx", User.class).sheet("小黑子").doWrite(users);
    }

    @Test
    public void test2(){
        List<User> users = new ArrayList<>();
        EasyExcel.read("C:\\Users\\Admin\\Downloads\\test\\1.xlsx", User.class, new AnalysisEventListener<User>() {
            @Override
            public void invoke(User o, AnalysisContext analysisContext) {
                users.add(o);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("读取完毕！");
            }
        }).sheet("小黑子").doRead();
        System.out.println(users);
    }
}
