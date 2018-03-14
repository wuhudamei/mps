package cn.damei.common.utils;


public class DistanceCnvter {
	
	 private final static double PI = 3.14159265358979323;
	    private final static double R = 6371229;


	    public static double getDistance(double long1, double lat1, double long2,double lat2) {  
	        double a, b, R;  
	        R = 6378137;
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
