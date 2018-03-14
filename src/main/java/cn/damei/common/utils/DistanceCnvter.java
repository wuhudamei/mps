package cn.damei.common.utils;

/**
 * 根据经纬度计算两地的距离
 * @author wang
 * @version 2016-10-10
 */
public class DistanceCnvter {
	
	 private final static double PI = 3.14159265358979323; // 圆周率
	    private final static double R = 6371229; // 地球的半径
	    //参数：经度 纬度 经度 纬度
	   /* public static double getDistance(double longt1, double lat1, double longt2,double lat2) {
	        double x, y, distance;
	        x = (longt2 - longt1) * PI * R
	                * Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
	        y = (lat2 - lat1) * PI * R / 180;
	        distance = Math.hypot(x, y);
	        return distance;
	    }*/
	    public static double getDistance(double long1, double lat1, double long2,double lat2) {  
	        double a, b, R;  
	        R = 6378137; // 地球半径  
	        lat1 = lat1 * Math.PI/180.0;  
	        lat2 = lat2 * Math.PI/180.0;  
	        a = lat1 - lat2;  
	        b = (long1 - long2)*Math.PI/180.0;  
	        double d;  
	        double sa2, sb2;  
	        sa2 = Math.sin(a/2.0);  
	        sb2 = Math.sin(b/2.0);  
	        d = 2*R*Math.asin(Math.sqrt(sa2*sa2+Math.cos(lat1)*Math.cos(lat2)*sb2*sb2));  
	        return d;  
	    } 
	    
	    public static void main(String[] args){
	    	double distance = getDistance(39.932754,116.540326 ,116.5892,39.813015);
	    	System.out.println(distance);
	    }
}
