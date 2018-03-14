package cn.damei.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import cn.damei.common.MD5Utils;

/**
 * 
 *  @author lft
 * @dete 2017-5-9 
 *	用于远程调用的key的验证与生成
 */

public class KeyAuthenticateUtils {
	/**
	 * key 生成
	 * 参数格式：{"storeId:"+storeId,"timeStamp:"+timeStamp}
	 */
	
	public static String getKey(String[] paramArr,String appoIntKey){
		Arrays.sort(paramArr);
		String s="";
		for (String str : paramArr) {
		String[] split = str.split(":");
			s+=split[1];
		}
		String paramString=s+appoIntKey;
		String key2="";
		try {
			key2 = MD5Utils.getMD5(paramString).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return key2;
	}  
	
	/**
	 * key生成（有&连接）
	 * @param paramArr
	 * @param appoIntKey
	 * @return
	 */
	public static String getAndKey(String[] paramArr,String appoIntKey){
		Arrays.sort(paramArr);
		String s="";
		for (String str : paramArr) {
			int lastIndexOf = str.indexOf(':')+1;
			String substring = str.substring(lastIndexOf);
			if(!substring.equals("")&&substring!=null){
				s = s+substring;
			}
		}
		String paramString=s+appoIntKey;
		String key="";
		try {
			key = MD5Utils.getMD5(paramString).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return key;
	}  
	
}
