package com.example.demo.core.convert;

import cn.hutool.core.convert.Convert;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanghuafeng
 * @date 2020/6/12 18:33
 */
@Slf4j
public class ConvertTest {

    /**
     * 转换为字符串
     */
    @Test
    public void convertToString() {
        int a = 1;
        //aStr为"1"
        String aStr = Convert.toStr(a);
        log.info("astr : {}", aStr);
        // astr : 1
        long[] b = {1, 2, 3, 4, 5};
        //bStr为："[1, 2, 3, 4, 5]"
        String bStr = Convert.toStr(b);
        log.info("bStr : {}", bStr);
        // bStr : [1, 2, 3, 4, 5]
    }

    /**
     * 转换为日期对象
     */
    @Test
    public void convertToArray() {
        String[] b = {"1", "2", "3", "4"};
        //结果为Integer数组
        Integer[] intArray = Convert.toIntArray(b);

        long[] c = {1, 2, 3, 4, 5};
        //结果为Integer数组
        Integer[] intArray2 = Convert.toIntArray(c);
    }

    /**
     * 转换为日期对象
     */
    @Test
    public void convertToDate() {
        String a = "2017-05-06";
        Date value = Convert.toDate(a);
        System.out.println(value);
        // Sat May 06 00:00:00 CST 2017
        String b = "2017-05-06 12:23:12";
        Date value1 = Convert.toDate(b);
        System.out.println(value1);
        // Sat May 06 12:23:12 CST 2017
    }

    /**
     * 转换为集合
     */
    @Test
    public void convertToList() {
        Object[] a = {"a", "你", "好", "", 1};
        List<?> list = Convert.convert(List.class, a);
        System.out.println(list);
        // [a, 你, 好, , 1]
        // 从4.1.11开始可以这么用
        List<?> list1 = Convert.toList(a);
        System.out.println(list1);
        // [a, 你, 好, , 1]
    }

    /**
     * 半角和全角转换
     */
    @Test
    public void SDBC() {
        String a = "123456789";
        // 结果为："１２３４５６７８９"
        String sbc = Convert.toSBC(a);
        System.out.println(sbc);
        String b = "１２３４５６７８９";
        // 结果为"123456789"
        String dbc = Convert.toDBC(b);
        System.out.println(dbc);
    }

    /**
     * 时间单位转换
     */
    @Test
    public void convertTime() {
        long a = 4535345;
        //结果为：75
        // 第一个参数为传人值 第二个参数为传入值时间单位 第三个参数为转换后的出参单位  会四舍五入
        long minutes = Convert.convertTime(a, TimeUnit.MILLISECONDS, TimeUnit.MINUTES);
        System.out.println(minutes);
    }

    /**
     * 金额大小写转换
     */
    @Test
    public void digitToChinese() {
        double a = 67556.32;
        // 涉及金钱计算 强制使用BigDecimal
        //结果为："陆万柒仟伍佰伍拾陆元叁角贰分"
        String digitUppercase = Convert.digitToChinese(new BigDecimal("67556.32"));
        System.out.println(digitUppercase);
    }
}