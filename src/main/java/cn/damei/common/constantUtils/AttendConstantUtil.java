package cn.damei.common.constantUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AttendConstantUtil {
	

	public static final String MIN_DATE=" 09:00:00";
	

	public static final String MAX_DATE=" 18:00:00";

	public static final String ATTEND_EMPLOYEE_ROLE ="1";

	public static final String ATTEND_ALL = "1";

	public static final String ATTEND_HALF = "2";

	public static final String ATTEND_LACK = "3";
	

	public static final Double ATTEND_ERROR =2000.00;
	

	public static final String IS_CREATE_SHEET="1";
	

	public static String getDate(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		return df.format(date);
	}
	

	public static String getDate2(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		return df.format(date);
	}
}
