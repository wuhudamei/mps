package cn.damei.common.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.junit.Test;

import cn.damei.common.LngAndLatUtils;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年9月23日 下午12:02:57 
* 类说明 
*/

public class test1 {

	
	
	public static void main(String[] args) {
		
		Map<String, Double> map = LngAndLatUtils.getLngAndLat("北京市莲花池南里27号");
		
		System.out.println("经度"+map.get("lng")+"----"+"纬度"+map.get("lat"));
		
		
	}
	
	@Test
	public void t () throws ParseException{
		String hopeForTime = "2016-12-7";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
		java.util.Date date = sdf.parse(hopeForTime);
System.out.println(date.toString());
	}
}
