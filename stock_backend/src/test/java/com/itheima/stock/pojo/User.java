package com.itheima.stock.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author by itheima
 * @Date 2021/12/19
 * @Description easyExcel测试实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    @ExcelProperty(value = {"用户名"}, index = 0)
    private String userName;
    @ExcelProperty(value = {"用户基本信息","年龄"}, index = 1)
    private Integer age;
    @ExcelProperty(value = {"用户基本信息","地址"}, index = 2)
    private String address;
    @ExcelProperty(value = {"生日"}, index = 3)
//    @ExcelIgnore
    @DateTimeFormat("yyyy/MM/dd")
    private Date birthday;
}