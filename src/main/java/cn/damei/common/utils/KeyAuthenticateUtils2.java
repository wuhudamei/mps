package cn.damei.common.utils;

import cn.damei.common.config.Global;
import cn.damei.common.MD5Utils;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 
 *  @author lft
 * @dete 2017-5-9 
 *	用于远程调用的key的验证与生成
 */

public class KeyAuthenticateUtils2 {
	/**
	 * key 生成
	 * 参数格式：{"storeId:"+storeId,"timeStamp:"+timeStamp}
	 */
	
	public static String getKey(String[] paramArr,String appoIntKey){

 		Arrays.sort(paramArr);
		String s="";
		for (String str : paramArr) {
			String temp = str;
			//判断等号出现的次数
			int aLength = temp.length() - temp.replace("=", "").length();
			if(aLength>1){
				//如果次数大于1，就截取等号后的字符串
				int indexOf = str.indexOf("=");
				String substring = str.substring(indexOf+1, str.length());
				s+=substring;
			}else{
				String[] split = str.split("=");
				if(split.length > 1){
					if(!split[1].equals("null")){
						s+=split[1];
					}

                }

			}
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
	* @Description: 获取MD5密文
	* @param @param map
	* @param @param appoIntKey
	* @param @return
	* @author zkj 
	* @date 2017年11月6日 下午4:08:52 
	*/
	public static String getKey(Map<String,String> map){
		//获取所有的key
		Set<String> keySet = map.keySet();
		Iterator<String> iter = keySet.iterator();
		List<String> list = new ArrayList<>();
		//遍历key
		while (iter.hasNext()) {
			String key = iter.next();
            String string = null;
            Object object = map.get(key);
			if(object instanceof  Integer){
                string = String.valueOf(object);
            }else if("effectDate".equals(key)) {
                string = map.get("effectDateString");
//                map.remove("effectDateString");
            }else if("effectDateString".equals(key)){

            }else{
                string = map.get(key).toString();
            }
			//key 和 value 拼接到一起 添加到集合里
			list.add(key+"="+string);
        }
//		集合转数组
		String[] array = (String[])list.toArray(new String[list.size()]);
//		获取MD5密文
		return getKey(array,Global.getMD5key());
}

}

	

