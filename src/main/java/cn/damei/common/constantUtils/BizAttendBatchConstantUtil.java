package cn.damei.common.constantUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 批次常量
 * @author cgh
 *
 */
public class BizAttendBatchConstantUtil {
	
	/**
	 * 待审核
	 */
	public static final String BATCH_AUDIT="1";
	
	/**
	 * 通过
	 */
	public static final String BATCH_PASS="2";
	
	/**
	 * 作废
	 */
	public static final String BATCH_INVALID="3";
	
	/**
	 * 考勤批次编号
	 */
	public static final String KQPC_NO = "KQPC";
	
	/**
	 * 时间匹配
	 * @param date
	 * @return
	 */
	public static String getDate(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		return df.format(date);
	}
}
