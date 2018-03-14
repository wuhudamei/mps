package cn.damei.common.utils;

import cn.damei.common.config.Global;
import cn.damei.common.MD5Utils;

import java.io.UnsupportedEncodingException;
import java.util.*;



public class KeyAuthenticateUtils2 {

	
	public static String getKey(String[] paramArr,String appoIntKey){

 		Arrays.sort(paramArr);
		String s="";
		for (String str : paramArr) {
			String temp = str;

			int aLength = temp.length() - temp.replace("=", "").length();
			if(aLength>1){

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
	

	public static String getKey(Map<String,String> map){

		Set<String> keySet = map.keySet();
		Iterator<String> iter = keySet.iterator();
		List<String> list = new ArrayList<>();

		while (iter.hasNext()) {
			String key = iter.next();
            String string = null;
            Object object = map.get(key);
			if(object instanceof  Integer){
                string = String.valueOf(object);
            }else if("effectDate".equals(key)) {
                string = map.get("effectDateString");

            }else if("effectDateString".equals(key)){

            }else{
                string = map.get(key).toString();
            }

			list.add(key+"="+string);
        }

		String[] array = (String[])list.toArray(new String[list.size()]);

		return getKey(array,Global.getMD5key());
}

}

	

