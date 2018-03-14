package cn.damei.common.constantUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AttendConstantUtil {
	
	/**
	 * 最早签到时间
	 */
	public static final String MIN_DATE=" 09:00:00";
	
	/**
	 * 最晚签到时间
	 */
	public static final String MAX_DATE=" 18:00:00";
	/**
	 * 项目经理角色
	 */
	public static final String ATTEND_EMPLOYEE_ROLE ="1";
	/**
	 * 全勤
	 */
	public static final String ATTEND_ALL = "1";
	/**
	 * 半勤
	 */
	public static final String ATTEND_HALF = "2";
	/**
	 * 缺勤
	 */
	public static final String ATTEND_LACK = "3";
	
	/**
	 * 签到误差
	 */
	public static final Double ATTEND_ERROR =2000.00;
	
	/**
	 * 已生成考勤单
	 */
	public static final String IS_CREATE_SHEET="1";
	
	/**
	 * 时间匹配
	 * @param date
	 * @return
	 */
	public static String getDate(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		return df.format(date);
	}
	
	/**
	 * 获取分
	 * @param date
	 * @return
	 */
	public static String getDate2(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		return df.format(date);
	}
}
