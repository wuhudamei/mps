package cn.damei.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;
public class Base64Util {
	public static void main(String[] args)  
    {  
		String imgFile = "d:\\3.jpg";
        String imgbese=getImgStr(imgFile);
        System.out.println(imgbese.length());
        System.out.println(imgbese);
        String imgFilePath = "d:\\332.jpg";
	    generateImage(imgbese,imgFilePath);
    }  


	    public static String getImgStr(String imgFile){

	 
	        
	       InputStream in = null;
	        byte[] data = null;

	        try 
	        {
	           in = new FileInputStream(imgFile);        
	             data = new byte[in.available()];
	             in.read(data);
	             in.close();
	       } 
	         catch (IOException e) 
	         {
	             e.printStackTrace();
	         }
	         return new String(Base64.encodeBase64(data));
	     }
	    

	     public static boolean generateImage(String imgStr,String imgFilePath){

	        if (imgStr == null)
	             return false;
	      
	         try 
	         {

	            byte[] b = Base64.decodeBase64(imgStr.getBytes());
	             for(int i=0;i<b.length;++i)
	            {
                if(b[i]<0)
	                 {
	                     b[i]+=256;
	                }
	             }

	 
	            OutputStream out = new FileOutputStream(imgFilePath);    
	             out.write(b);
	             out.flush();
	            out.close();
	             return true;
	         } 
	         catch (Exception e) 
	         {
	             return false;
	         }
	    }
 }