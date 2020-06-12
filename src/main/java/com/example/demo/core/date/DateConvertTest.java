package com.example.demo.core.date;

import cn.hutool.core.date.*;
import cn.hutool.core.lang.Console;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @author zhanghuafeng
 * @date 2020/6/12 19:25
 */
public class DateConvertTest {

    /**
     * Date、long、Calendar之间的相互转换
     * 对于Date对象，为了便捷，使用了一个DateTime类来代替之，继承自Date对象
     * 主要的便利在于，覆盖了toString()方法，返回yyyy-MM-dd HH:mm:ss形式的字符串，方便在输出时的调用（例如日志记录等）
     */
    @Test
    public void DateTypeConvert() {
        //当前时间
        Date date = DateUtil.date();
        System.out.println(date);
        // 2020-06-12 19:29:34
        //当前时间
        Date date2 = DateUtil.date(Calendar.getInstance());
        System.out.println(date2);
        // 2020-06-12 19:29:34
        //当前时间
        Date date3 = DateUtil.date(System.currentTimeMillis());
        System.out.println(date3);
        // 2020-06-12 19:29:34
        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        System.out.println(now);
        // 2020-06-12 19:29:34
        //当前日期字符串，格式：yyyy-MM-dd
        String today = DateUtil.today();
        System.out.println(today);
        // 2020-06-12
    }

    /**
     * 字符串转日期
     * DateUtil.parse方法会自动识别一些常用格式，包括：
     * yyyy-MM-dd HH:mm:ss
     * yyyy-MM-dd
     * HH:mm:ss
     * yyyy-MM-dd HH:mm
     * yyyy-MM-dd HH:mm:ss.SSS
     */
    @Test
    public void string2Date() {
        String dateStr = "2017-03-01";
        Date date = DateUtil.parse(dateStr);
        System.out.println(date);
        //2017-03-01 00:00:00
        // 也可以使用自定义日期格式转化
        String dateStr1 = "2020-03-01";
        Date date1 = DateUtil.parse(dateStr1, "yyyy-MM-dd");
        System.out.println(date1);
        //2020-03-01 00:00:00
    }

    /**
     * 格式化日期输出
     */
    @Test
    public void dateFormat() {
        String dateStr = "2017-03-01";
        Date date = DateUtil.parse(dateStr);
        //结果 2017/03/01
        String format = DateUtil.format(date, "yyyy/MM/dd");
        System.out.println(format);
        //常用格式的格式化，结果：2017-03-01
        String formatDate = DateUtil.formatDate(date);
        System.out.println(formatDate);
        //结果：2017-03-01 00:00:00
        String formatDateTime = DateUtil.formatDateTime(date);
        System.out.println(formatDateTime);
        //结果：00:00:00
        String formatTime = DateUtil.formatTime(date);
        System.out.println(formatTime);
    }

    /**
     * 获取Date对象的某个部分
     */
    @Test
    public void getPartOfDate() {
        Date date = DateUtil.date();
        //获得年的部分
        int year = DateUtil.year(date);
        System.out.println(year);
        //2020
        //获得月份，从0开始计数
        int month1 = DateUtil.month(date);
        System.out.println(month1);
        //5
        //获得月份枚举
        Month month = DateUtil.monthEnum(date);
        System.out.println(month);
        //JUNE
        //.....
    }

    /**
     * 获得每天的开始时间、结束时间，每月的开始和结束时间等等，DateUtil也提供了相关方法
     */
    @Test
    public void startEndDate() {
        String dateStr = "2017-03-01 22:33:23";
        Date date = DateUtil.parse(dateStr);

        //一天的开始，结果：2017-03-01 00:00:00
        Date beginOfDay = DateUtil.beginOfDay(date);

        //一天的结束，结果：2017-03-01 23:59:59
        Date endOfDay = DateUtil.endOfDay(date);
    }

    /**
     * 日期或时间的偏移指针对某个日期增加或减少分、小时、天等等，达到日期变更的目的
     */
    @Test
    public void dateOffset() {
        String dateStr = "2017-03-01 22:33:23";
        Date date = DateUtil.parse(dateStr);

        //结果：2017-03-03 22:33:23
        Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, 2);

        //常用偏移，结果：2017-03-04 22:33:23
        DateTime newDate2 = DateUtil.offsetDay(date, 3);

        //常用偏移，结果：2017-03-01 19:33:23
        DateTime newDate3 = DateUtil.offsetHour(date, -3);

        //昨天
        DateTime yesterday = DateUtil.yesterday();
        //明天
        DateTime tomorrow = DateUtil.tomorrow();
        //上周
        DateTime lastWeek = DateUtil.lastWeek();
        //下周
        DateTime nextWeek = DateUtil.nextWeek();
        //上个月
        DateTime lastMonth = DateUtil.lastMonth();
        //下个月
        DateTime nextMonth = DateUtil.nextMonth();
    }

    /**
     * 需要计算两个日期之间的时间差（相差天数、相差小时数等等），Hutool将此类方法封装为between方法
     */
    @Test
    public void dateBetween() {
        String dateStr1 = "2017-03-01 22:33:23";
        Date date1 = DateUtil.parse(dateStr1);

        String dateStr2 = "2017-04-01 23:33:23";
        Date date2 = DateUtil.parse(dateStr2);

        //相差一个月，31天
        long betweenDay = DateUtil.between(date1, date2, DateUnit.WEEK);
        System.out.println(betweenDay);
        //31
    }

    /**
     * 我们希望看到易读的时间差，比如XX天XX小时XX分XX秒，此时使用DateUtil.formatBetween方法
     */
    @Test
    public void formatDateBetween() {
        //Level.MINUTE表示精确到分
        String formatBetween = DateUtil.formatBetween(310000000, BetweenFormater.Level.MINUTE);
        //输出：3天14小时6分
        Console.log(formatBetween);
    }

    /**
     * 计时器用于计算某段代码或过程花费的时间
     */
    @Test
    public void calculateDate() {
        TimeInterval timer = DateUtil.timer();

        //---------------------------------
        //-------这是执行过程
        //---------------------------------

        int count = 0;

        for (int i = 0; i < 1000000 ; i++) {
            count = count + i;
        }
        System.out.println(count);

        long interval = timer.interval();//花费毫秒数
        System.out.println(interval);
        long l = timer.intervalRestart();//返回花费时间，并重置开始时间
        System.out.println(l);
        long l1 = timer.intervalMinute();//花费分钟数
        System.out.println(l1);
    }

    /**
     * 其他
     */
    @Test
    public void otherDate() {
        //年龄
        int age = DateUtil.ageOfNow("1996-08-29");
        System.out.println(age);
        //23
        //是否闰年
        boolean leapYear = DateUtil.isLeapYear(2017);
        System.out.println(leapYear);
        //false
    }
}
