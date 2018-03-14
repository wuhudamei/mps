package cn.damei.common;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

public class MD5Utils {
	public static String md5key = "xxxxxxx";
	public static String key = "7b5df6aq2we4r3t6y1vxnmhjklpewd23";
public static String MD5(Map<String,String> map) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		
		Set<String> keySet = map.keySet();
		List<String> list = new ArrayList<String>(keySet);  
		Collections.sort(list);
		StringBuffer sb = new StringBuffer() ;
		if(list.size()>0){
			for (int i=0;i<list.size();i++ ) {
				if(null != map.get(list.get(i))){
					sb.append(map.get(list.get(i))).append("&");
				}
			}
		}
		sb.append(md5key);

		MessageDigest md5 = MessageDigest.getInstance("MD5");  
		md5.update((sb.toString()).getBytes("UTF-8"));  
		byte b[] = md5.digest();  
		  
		int i;  
		StringBuffer buf = new StringBuffer("");  
		for(int offset=0; offset<b.length; offset++){  
		    i = b[offset];  
		    if(i<0){  
		        i+=256;  
		    }  
		    if(i<16){  
		        buf.append("0");  
		    }  
		    buf.append(Integer.toHexString(i));  
		} 

		return buf.toString() ;
	}
	


public static String MD5Secret(Map<String,String> map) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		
	String str = (String) map.get("time");
	int lastIndexOf2 = str.lastIndexOf(" ");
	String substring3 = str.substring(0, lastIndexOf2);
	map.put("time", substring3);
	
	JSONArray fromObject = JSONArray.fromObject(map);
	String string = fromObject.toString();
	

	

	String substring2 = string.substring(2, string.length()-2);

	String[] split = substring2.split(",\"");
	List<String> list = new ArrayList<>();
	for (int i = 0; i < split.length; i++) {
		String replaceFirst = split[i].replaceFirst("\"", "");
		list.add(replaceFirst);
	}
	String[] targetArr=new String[list.size()];
	String[] array = list.toArray(targetArr);

	Arrays.sort(array);

	String count = "";
	for (String sp : array) {

		int lastIndexOf = sp.lastIndexOf(':') +2;
		String substring = sp.substring(lastIndexOf, sp.length()-1);
		if(!substring.equals("")&&substring!=null){
			count = count+substring;
		}
	}
	count = count + key;

	return  MD5Utils.getMD5(count).toUpperCase();
	}


public static String getMD5(String plainText) throws UnsupportedEncodingException {
	byte[] secretBytes = null;
	try {
		secretBytes = MessageDigest.getInstance("md5").digest(
				plainText.getBytes("UTF-8"));
	} catch (NoSuchAlgorithmException e) {
		throw new RuntimeException("没有md5这个算法！");
	}
	String md5code = new BigInteger(1, secretBytes).toString(16);
Integer md5Length =md5code.length();

	for (int i = 0; i < 32 -md5Length ; i++) {
		md5code = "0" + md5code;
	}
	return md5code;
}

}


