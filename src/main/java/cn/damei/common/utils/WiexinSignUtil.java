package cn.damei.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public class WiexinSignUtil {


    private static String token = "ecjeesitecom";  
  

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
    	
    	if (StringUtils.isNotBlank(signature) && StringUtils.isNotBlank(timestamp) && StringUtils.isNotBlank(nonce)){
    		
	        String[] arr = new String[] { token, timestamp, nonce };  

	        Arrays.sort(arr);  
	        StringBuilder content = new StringBuilder();  
	        for (int i = 0; i < arr.length; i++) {  
	            content.append(arr[i]);  
	        }  
	        MessageDigest md = null;  
	        String tmpStr = null;  
	  
	        try {  
	            md = MessageDigest.getInstance("SHA-1");  

	            byte[] digest = md.digest(content.toString().getBytes("UTF-8"));
	            tmpStr = byteToStr(digest);  
	        } catch (NoSuchAlgorithmException e) {  
	            e.printStackTrace();  
	        }
	        catch (Exception exp){
                exp.printStackTrace();
            }
	  
	        content = null;
        

	        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	        
    	}
    	return false;
    }  
  

    private static String byteToStr(byte[] byteArray) {  
        StringBuffer strDigest = new StringBuffer("");
        for (int i = 0; i < byteArray.length; i++) {
            strDigest.append(byteToHexStr(byteArray[i]));
        }  
        return strDigest.toString();
    }  
  

    private static String byteToHexStr(byte mByte) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
        tempArr[1] = Digit[mByte & 0X0F];  
  
        String s = new String(tempArr);  
        return s;  
    }  
    
}
