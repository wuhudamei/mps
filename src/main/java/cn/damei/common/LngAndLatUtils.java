package cn.damei.common;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年9月23日 上午11:31:36 
* 根据地址得到经纬度
* 	Map<String,Double> map=LngAndLatUtil.getLngAndLat("北京市丰台区莲花池南里27号");
	System.out.println("经度："+map.get("lng")+"---纬度："+map.get("lat"));
*/

public class LngAndLatUtils {
	public static Map<String,Double> getLngAndLat(String address){
		Map<String,Double> map=new HashMap<String, Double>();
		String url = "http://api.map.baidu.com/geocoder/v2/?address="+address+"&output=json&ak=9sTsTQyg2l9Y8GIo5uk2a5Be";
	       String json = loadJSON(url);
	   JSONObject obj = JSONObject.fromObject(json);
	   
	     if(obj.get("status").toString().equals("0")){
	      	double lng=obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
	       	double lat=obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
	       	map.put("lng", lng);
	       	map.put("lat", lat);
	       	//System.out.println("经度："+lng+"---纬度："+lat);
	       }else{
	       	//System.out.println("未找到相匹配的经纬度！");
	       }
		return map;
	}
	
	public static String loadJSON (String url) {
	       StringBuilder json = new StringBuilder();
	       try {
	           URL oracle = new URL(url);
	           URLConnection yc = oracle.openConnection();
	           BufferedReader in = new BufferedReader(new InputStreamReader(
	                                       yc.getInputStream()));
	           String inputLine = null;
	           while ( (inputLine = in.readLine()) != null) {
	               json.append(inputLine);
	           }
	           in.close();
	       } catch (MalformedURLException e) {
	       } catch (IOException e) {
	       }
	       return json.toString();
	   }
	
	public static void main(String[] args) {
		Map<String, Double> map = LngAndLatUtils.getLngAndLat("北京市丰台区摩尔创业公社莲花池南里摩尔创业公社");
		System.out.println("经度: "+map.get("lng"));
		System.out.println("纬度: "+map.get("lat"));
		
		
	}

}
