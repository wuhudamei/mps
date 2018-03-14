package cn.damei.common.constantUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 星级常量
 * @author chenguanhua
 *
 */
public class BizAttendBillConstantUtil {
	
	/**
	 * 考勤单编号
	 */
	public static final String KQ_NO = "KQ";
	
	/**
	 *考勤批次编号 
	 */
	
	public static final String KQPC_NO = "KQPC";
	
	/**
	 * 工资单编号
	 */
	public static final String GZ_NO = "GZ";
	
	/**
	 * 工资批次编号
	 */
	public static final String GZPC_NO = "GZPC";
	
	/**
	 * 未生成考勤批次
	 */
	public static final Integer NO_KQPCNO=0;
	
	/**
	 * 以生成考勤单
	 */
	public static final String ALREADY_ATTEND_SHEET ="20"; 
	
	/**
	 * 已生成考勤批次
	 */
	public static final String ALREADY_ATTEND_BATCH ="30";
	
	/**
	 * 时间匹配
	 * @param date
	 * @return
	 */
	public static String getDate(Date date){
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		
		return df.format(date);
	}
}
