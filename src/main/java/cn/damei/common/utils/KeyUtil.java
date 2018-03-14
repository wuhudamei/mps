package cn.damei.common.utils;

import java.util.Arrays;

import cn.damei.common.MD5Utils;


public class KeyUtil {

	public static String getKey(String[] paramArr,String appoIntKey) throws Exception{
		String keyStr = "";

		Arrays.sort(paramArr);
		for(String param : paramArr){
			param = param.replace("'", "");
			param = param.replace("\"", "");
			int lastIndex = param.lastIndexOf(":");
			String str1 = param.substring(0, lastIndex);
			if(str1 != null && !str1.equals("key")){
				String str = param.substring(lastIndex+1);
				if(str != null && !str.equals("")){
					keyStr=keyStr+str+"&";
				}
			}
			
		}
		keyStr = keyStr+appoIntKey;
		System.out.println(keyStr);
		String MD5Key = MD5Utils.getMD5(keyStr).toUpperCase();
		return MD5Key;
	}

	public static String getKey1(String[] paramArr,String appoIntKey) throws Exception{
		String keyStr = "";

		Arrays.sort(paramArr);
		for(String param : paramArr){
			param = param.replace("'", "");
			param = param.replace("\"", "");
			int lastIndex = param.lastIndexOf(":");
			String str1 = param.substring(0, lastIndex);
			if(str1 != null && !str1.equals("key")){
				String str = param.substring(lastIndex+1);
				if(str != null && !str.equals("")){
					keyStr=keyStr+str;
				}
			}

		}
		keyStr = keyStr+appoIntKey;
		System.out.println(keyStr);
		String MD5Key = MD5Utils.getMD5(keyStr).toUpperCase();
		return MD5Key;
	}
	

	public static String getKey(String paramJsonStr,String appoIntKey) throws Exception{
		paramJsonStr = paramJsonStr.substring(2,paramJsonStr.length()-2);
		String[] paramArr = paramJsonStr.split(",");
		return getKey(paramArr,appoIntKey);
	}


	public static String getKey1(String paramJsonStr,String appoIntKey) throws Exception{
		paramJsonStr = paramJsonStr.substring(2,paramJsonStr.length()-2);
		String[] paramArr = paramJsonStr.split(",");
		return getKey(paramArr,appoIntKey);
	}
	
}
