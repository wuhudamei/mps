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
	//获取access_token 
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
		/*String token = getToken(GET_TOKEN_URL,APP_ID,APP_SECRET);
		String code = "";
		Map<String,Object> productMap = getOpenId(GET_TOKEN_URL,APP_ID,APP_SECRET,code,GRANT_AYPE);*/
		/*try {
			authorization();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
	}
	
	public static Map<String,Object> getOpenId(String url ,String appId,String appSecret,String code,String grant_type ){
		//https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
		String turl = String.format("%s?appid=%s&secret=%s&code=%s&grant_type=%s",url,appId,appSecret,code,grant_type);
		//JSONObject jsonObject = httpRequest("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+appSecret+"&code="+code+"&grant_type=authorization_code","POST", "" );
		String get = HttpConnection.get(turl);
		Map<String,Object> productMap = null;
		try{
			 productMap = mapper.readValue(get, Map.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		return productMap;
	}
	
	/*public static void authorization(HttpServletRequest request) throws UnsupportedEncodingException{
		//String redirect_uri = "http%3a%2f%2fmpst.mdni.cn%2fa%2fapp%2fhome%2findex";
		String redirect_uri = URLEncoder.encode("http://mpst.mdni.cn/a/app/home/index", "utf-8");
		String response_type = "code";
		String scope = "snsapi_userinfo";
		String state ="123#wechat_redirect";
		String appid ="wxbd2b4cb23a053488";
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("appid", appid);
		map.put("redirect_uri", redirect_uri);
		map.put("response_type", response_type);
		map.put("scope", scope);
		map.put("state", state);
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxbd2b4cb23a053488&redirect_uri="+redirect_uri
				   + "&response_type"+response_type
				   + "&scope"+scope+"#wechat_redirect";
		
		//String post = HttpConnection.post("https://open.weixin.qq.com/connect/oauth2/authorize", map);
		//String post = HttpConnection.post("url", null);
	}*/

}
