package cn.damei.common.constantUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BizAttendBillConstantUtil {
	

	public static final String KQ_NO = "KQ";
	

	
	public static final String KQPC_NO = "KQPC";
	

	public static final String GZ_NO = "GZ";
	

	public static final String GZPC_NO = "GZPC";
	

	public static final Integer NO_KQPCNO=0;
	

	public static final String ALREADY_ATTEND_SHEET ="20"; 
	

	public static final String ALREADY_ATTEND_BATCH ="30";
	

	public static String getDate(Date date){
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		
		return df.format(date);
	}
}
