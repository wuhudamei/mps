package cn.damei.common.utils;

import java.util.Arrays;

import cn.damei.common.MD5Utils;

/**
 * 签名KEY的工具类
 * @author hyh
 *
 */
public class KeyUtil {
    /**
     * 
     * @param paramArr    参数数组 如:[orderId:1001,amount:100,...]
     * @param appoIntKey  双方约定key
     * @return
     */
	public static String getKey(String[] paramArr,String appoIntKey) throws Exception{
		String keyStr = "";
		//对数组进行排序
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
		//对数组进行排序
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
	
	/**
	 * 
	 * @param paramJsonStr 参数的JSON字符串
	 * @param key  双方约定key
	 * @return
	 */
	public static String getKey(String paramJsonStr,String appoIntKey) throws Exception{
		paramJsonStr = paramJsonStr.substring(2,paramJsonStr.length()-2);
		String[] paramArr = paramJsonStr.split(",");
		return getKey(paramArr,appoIntKey);
	}

	/**
	 *
	 * @param paramJsonStr 参数的JSON字符串
	 * @param key  双方约定key
	 * @return
	 */
	public static String getKey1(String paramJsonStr,String appoIntKey) throws Exception{
		paramJsonStr = paramJsonStr.substring(2,paramJsonStr.length()-2);
		String[] paramArr = paramJsonStr.split(",");
		return getKey(paramArr,appoIntKey);
	}
	
}
