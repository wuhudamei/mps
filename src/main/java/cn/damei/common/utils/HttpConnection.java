package cn.damei.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpConnection {
	private static Logger logger = LoggerFactory.getLogger(HttpConnection.class);

	public static String post(String url, Map<String, String> params) throws UnsupportedEncodingException {  
        DefaultHttpClient httpclient = new DefaultHttpClient();  
        String body = null;  
          
        logger.info("create httppost:" + url);
        HttpPost post =  postForm(url, params);  
          
        body = invoke(httpclient, post);  
          
        httpclient.getConnectionManager().shutdown();  
          
        return body;  
    }  
      
    public static String get(String url) {  
        DefaultHttpClient httpclient = new DefaultHttpClient();  
        String body = null;  
          
        logger.info("create httppost:" + url);  
        HttpGet get = new HttpGet(url);  
        body = invoke(httpclient, get);  
          
        httpclient.getConnectionManager().shutdown();  
          
        return body;  
    }  
          
      
    private static String invoke(DefaultHttpClient httpclient,  
            HttpUriRequest httpost) {  
        HttpResponse response = sendRequest(httpclient, httpost);  
        String body = paseResponse(response);  
          
        return body;  
    }  
  
    private static String paseResponse(HttpResponse response) {  
    	logger.info("get response from http server..");  
        HttpEntity entity = response.getEntity();  
          
        logger.info("response status: " + response.getStatusLine());  
        String charset = EntityUtils.getContentCharSet(entity);  
        logger.info(charset);  
          
        String body = null;  
        try {  
            body = EntityUtils.toString(entity);  
            logger.info(body);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
        return body;  
    }  
  
    private static HttpResponse sendRequest(DefaultHttpClient httpclient,  
            HttpUriRequest httpost) {  
    	logger.info("execute post...");  
        HttpResponse response = null;  
          
        try {  
            response = httpclient.execute(httpost);  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return response;  
    }  
  
    private static HttpPost postForm(String url,Map<String, String> params) throws UnsupportedEncodingException{  
          
        HttpPost httpost = new HttpPost(url);  
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
          
        Set<String> keySet = params.keySet();  
        for(String key : keySet) {  
        	nvps.add(new BasicNameValuePair(key, params.get(key)));
        }  
          
        logger.info("set utf-8 form entity to httppost");  
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));    
          
        return httpost;
    }  
    
    public static void main(String[] args) throws UnsupportedEncodingException {
    	String content = "项目经理韩振刚为公司带来回单一个，经领导同意给予合同额3%(10万*3%=3000)奖励。回单奖励制度适用所有人(含产业工人)，大家共同努力";
    	//List<String> phones = new ArrayList<String>();
    	//18974994500,15811305860,13141259487,13552491486,13810471230,15810813509,18645060387,18519719571,13439293861,13393163996,
  /*  	18974994500
    	13683502005
    	15210812970
    	15810284461
    	13522499282
    	13520911459
    	13718121590
    	13241781229
    	18031209253
    	18910270566
    	13439632394
    	13803285094
    	13263359425
    	18801310826
    	18639429237
    	15031982646
    	15132279618
    	13666139209
    	13696112909
    	13366099178
    	13120382237
    	15011595961
    	15712998155*/
    	
    	//phones.add("18974994500");
    	/*phones.add("15811305860");
    	phones.add("13141259487");
    	phones.add("13552491486");
    	phones.add("13810471230");
    	phones.add("15810813509");
    	phones.add("18645060387");
    	phones.add("18519719571");
    	phones.add("13439293861");
    	phones.add("13393163996");*/
    	
    	/*phones.add("13683502005");
    	phones.add("15210812970");
    	phones.add("15810284461");
    	phones.add("13522499282");
    	phones.add("13520911459");
    	phones.add("13718121590");
    	phones.add("13241781229");
    	phones.add("18031209253");
    	phones.add("18910270566");
    	phones.add("13439632394");
    	phones.add("13803285094");
    	phones.add("13263359425");
    	phones.add("18801310826");
    	phones.add("18639429237");
    	phones.add("15031982646");
    	phones.add("15132279618");
    	phones.add("13666139209");
    	phones.add("13696112909");
    	phones.add("13366099178");
    	phones.add("13120382237");
    	phones.add("15011595961");
    	phones.add("15712998155");*/
    	/*Map<String, String> params = new HashMap<String, String>();
    	params.put("source", "1");
    	params.put("content", content);
    	params.put("sendtime", "");
    	params.put("mobile", "156186052");
    	String xml = HttpConnection.post(PATH, params);
		logger.info("qqqq" + xml);
		JSONObject jsonObj = JSONObject.fromObject(xml);
		System.out.println("IsSuccessful:" + jsonObj.getString("IsSuccessful"));
		System.out.println("Message:" + jsonObj.getString("Message"));*/
    	/*for (String phone : phones) {
    		params.put("mobile", phone);
    		String xml = HttpConnection.post(PATH, params); 
		}*/
    	/*String xml = HttpConnection.post(PATH, params);  
    	logger.info(xml); */
	}
}  

