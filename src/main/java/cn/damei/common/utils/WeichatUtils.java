package cn.damei.common.utils;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import cn.damei.common.utils.HttpConnection;

public class WeichatUtils {
	public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
	public static final String APP_ID ="wx3e72812b0195984d";
	public static final String APP_SECRET ="ae7d63a968d1c50c3f2801a4b114d119";
	public static final String URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	public static final String GRANT_AYPE = "authorization_code";
	static ObjectMapper mapper = new ObjectMapper();  

	@SuppressWarnings("unchecked")
	public static String getToken(String url,String appId, String appSecret){
		String turl = String.format("%s?grant_type=client_credential&appid=%s&secret=%s", url,appId, appSecret);
		String get = HttpConnection.get(turl);
		Map<String,Object> productMap = null;
		try{
			 productMap = mapper.readValue(get, Map.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(productMap!= null ){
			return productMap.get("access_token").toString();	
		}else{
			return null;
		}
		
	}
	
	
	public static void main(String[] args){


	}
	
	public static Map<String,Object> getOpenId(String url ,String appId,String appSecret,String code,String grant_type ){

		String turl = String.format("%s?appid=%s&secret=%s&code=%s&grant_type=%s",url,appId,appSecret,code,grant_type);

		String get = HttpConnection.get(turl);
		Map<String,Object> productMap = null;
		try{
			 productMap = mapper.readValue(get, Map.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		return productMap;
	}
	


}
