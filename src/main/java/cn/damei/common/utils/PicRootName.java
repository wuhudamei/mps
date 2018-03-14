package cn.damei.common.utils;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PicRootName {
	private static Logger logger = LoggerFactory.getLogger(PicRootName.class);
	

	public static String getConfigValue(String key) throws IOException{
		Properties props = new Properties();  
        props.load(PicRootName.class.getClassLoader().getResourceAsStream("config.properties"));
        String result = props.getProperty(key);
        logger.info("config.properties对应的value："+result);
        return result;
	}
	

	public static String SystemEnvironment(HttpServletRequest request){
		String result = "";
		String sys = System.getProperty("os.name");
		if(sys.equals("Windows 7")){
			result = local(request);
		}else{
			result = tomcatServer(request);
		}
		
		logger.info("最终返回的路径："+result);
		return result;
	}
	
	public static String local(HttpServletRequest request){
		String root = request.getSession().getServletContext().getRealPath("/");
		String localRoot = root.substring(0, root.length()-16);

		return localRoot;
	}
	
	public static String tomcatServer(HttpServletRequest request){
		String root = request.getSession().getServletContext().getRealPath("/");
		String serverRoot = root.substring(0, root.length()-4);

		return serverRoot;
	}
	
	public static void main(String[] args) throws IOException {
		System.getProperty("os.name");
		System.out.println(System.getProperty("os.name"));
		
		Properties props = new Properties();  
        props.load(PicRootName.class.getClassLoader().getResourceAsStream("config.properties"));  
        System.out.println(props.getProperty("pic_url_base")); 
	}
	

	public static String picPrefixName() throws IOException{
		Properties props = new Properties();  
        props.load(PicRootName.class.getClassLoader().getResourceAsStream("config.properties"));  
        String path = props.getProperty("pic_url_base");
        logger.info("返回图片的前缀名：" + path);
		return path.trim();
	}
	

	public static String NewRecheckPath() throws IOException{
		Properties props = new Properties();  
        props.load(PicRootName.class.getClassLoader().getResourceAsStream("config.properties"));  
        String recheck = props.getProperty("recheck");
        logger.info("获取复尺上传图片路径值：" + recheck);
		return recheck.trim();
	}


	public static String RecheckType(String key) throws IOException {
		String result = "";
		Properties props = new Properties();
		props.load(PicRootName.class.getClassLoader().getResourceAsStream("config.properties"));  
		if(key.equals("1")){
			result = props.getProperty("recheck_taokou_key");
		}else if(key.equals("2")){
			result = props.getProperty("recheck_curtain_key");
		}else if(key.equals("3")){
			result = props.getProperty("recheck_pushdoor_key");
		}else if(key.equals("4")){
			result = props.getProperty("recheck_flatopen_key");
		}else if(key.equals("5")){
			result = props.getProperty("recheck_closetool_key");
		}else if(key.equals("6")){
			result = props.getProperty("recheck_roomcabinet_key");
		}
		logger.info("返回的复尺类型："+result);
		return result;
	}
}
