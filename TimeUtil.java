package com.example.test.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * 时间工具
 * @author guoxian
 * @date 2024/7/26
 */
@Component
public class TimeUtil {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final LocalDate TODAY = LocalDate.now();
    private static final LocalDateTime TODATE_TIME = LocalDateTime.now();

    /**
     * 获取当前时间，并且格式化
     * @param format 此参数可为null,格式化格式,默认格式为yyyy-MM-dd HH:mm:ss
     * @return String 格式化后的时间
     */
    public String getCurrentDateTime(String format) {
        if (!StringUtils.hasLength(format)){
            format = DATE_TIME_FORMAT;
        }
        return TODATE_TIME.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 获取当前年份
     * @return Integer 年份
     */
    public Integer getCurrentYear(){
        return TODAY.getYear();
    }

    /**
     * 获取当前月份
     * @return Integer 月份
     */
    public Integer getCurrentMonth(){
        return TODAY.getMonthValue();
    }

    /**
     * 获取当前月份日期
     * @return Integer 日期
     */
    public Integer getCurrentDay(){
        return TODAY.getDayOfMonth();
    }

    /**
     * 获取当前日期的前几个月的第一天
     * @param num 前几个月
     * @return LocalDate
     */
    public LocalDate getTodayBeforeFirstDayOfMonth(int num) {
        return TODAY.minusMonths(num).with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取指定日期的前几个月的第一天
     * @param date 指定日期
     * @param num 前几个月
     * @return LocalDate
     */
    public LocalDate getDayBeforeFirstDayOfMonth(LocalDate date, int num) {
        return date.minusMonths(num).with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取当前日期的前几个月的最后一天
     * @param num 前几个月
     * @return LocalDate
     */
    private LocalDate getTodayBeforeLastDayOfMonth(int num) {
        return TODAY.minusMonths(num).with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取指定日期的前几个月的最后一天
     * @param date 指定日期
     * @param num 前几个月
     * @return LocalDate
     */
    private LocalDate getDayBeforeLastDayOfMonth(LocalDate date, int num) {
        return date.minusMonths(num).with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取当前日期所在季度的开始日期和结束日期
     * 季度一年四季， 第一季度：1月-3月， 第二季度：4月-6月， 第三季度：7月-9月， 第四季度：10月-12月
     * @param isFirst  true表示查询本季度开始日期  false表示查询本季度结束日期
     * @return LocalDate
     */
    public LocalDate getStartOrEndDayOfQuarter(Boolean isFirst){
        LocalDate resDate = TODAY;
        Month month = TODAY.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        if (isFirst) {
            resDate = LocalDate.of(TODAY.getYear(), firstMonthOfQuarter, 1);
        } else {
            resDate = LocalDate.of(TODAY.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(TODAY.isLeapYear()));
        }
        return resDate;
    }

    /**
     * 获取指定日期所在季度的开始日期和结束日期
     * 季度一年四季， 第一季度：1月-3月， 第二季度：4月-6月， 第三季度：7月-9月， 第四季度：10月-12月
     * @param date 指定日期
     * @param isFirst  true表示查询本季度开始日期  false表示查询本季度结束日期
     * @return LocalDate
     */
    public LocalDate getStartOrEndDayOfQuarter(LocalDate date, Boolean isFirst){
        LocalDate resDate = date;
        Month month = date.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        if (isFirst) {
            resDate = LocalDate.of(date.getYear(), firstMonthOfQuarter, 1);
        } else {
            resDate = LocalDate.of(date.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(date.isLeapYear()));
        }
        return resDate;
    }

    /**
     * 获取指定日期所在季度的开始时间和结束时间
     * 季度一年四季， 第一季度：1月-3月， 第二季度：4月-6月， 第三季度：7月-9月， 第四季度：10月-12月
     * @param date 指定日期
     * @param isFirst  true表示查询本季度开始时间  false表示查询本季度结束时间
     * @return LocalDateTime
     */
    public LocalDateTime getStartOrEndDayTimeOfQuarter(LocalDateTime date, Boolean isFirst){
        LocalDateTime resDate = date;
        Month month = date.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        if (isFirst) {
            resDate = LocalDateTime.of(date.getYear(), firstMonthOfQuarter, 1, 0, 0, 0);
        } else {
            resDate = LocalDateTime.of(date.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(date.toLocalDate().isLeapYear()) , 23, 59, 59);
        }
        return resDate;
    }
}
