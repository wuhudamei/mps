package cn.damei.common.constantUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BizAttendBatchConstantUtil {
	

	public static final String BATCH_AUDIT="1";
	

	public static final String BATCH_PASS="2";
	

	public static final String BATCH_INVALID="3";
	

	public static final String KQPC_NO = "KQPC";
	

	public static String getDate(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		return df.format(date);
	}
}
