package cn.damei.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import cn.damei.common.MD5Utils;



public class KeyAuthenticateUtils {

	
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
