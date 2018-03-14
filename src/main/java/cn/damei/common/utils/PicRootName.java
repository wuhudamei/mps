package cn.damei.common.utils;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 本地和服务器获取当前环境根目录
 * @author llp
 * 20161104
 */
public class PicRootName {
	private static Logger logger = LoggerFactory.getLogger(PicRootName.class);
	
	/**
	 * 根据键获的config.properties对应值
	 * @param key
	 * @return value
	 */
	public static String getConfigValue(String key) throws IOException{
		Properties props = new Properties();  
        props.load(PicRootName.class.getClassLoader().getResourceAsStream("config.properties"));
        String result = props.getProperty(key);
        logger.info("config.properties对应的value："+result);
        return result;
	}
	
	//根据当前系统环境选择调用哪个路径
	public static String SystemEnvironment(HttpServletRequest request){
		String result = "";
		String sys = System.getProperty("os.name");//获取当前系统
		if(sys.equals("Windows 7")){
			result = local(request);
		}else{//非Windows 7
			result = tomcatServer(request);
		}
		
		logger.info("最终返回的路径："+result);
		return result;
	}
	
	public static String local(HttpServletRequest request){
		String root = request.getSession().getServletContext().getRealPath("/");
		String localRoot = root.substring(0, root.length()-16);
		//logger.info("返回本地路径："+localRoot);
		return localRoot;
	}
	
	public static String tomcatServer(HttpServletRequest request){
		String root = request.getSession().getServletContext().getRealPath("/");
		String serverRoot = root.substring(0, root.length()-4);
		//logger.info("返回服务器路径："+serverRoot);
		return serverRoot;
	}
	
	public static void main(String[] args) throws IOException {
		System.getProperty("os.name");
		System.out.println(System.getProperty("os.name"));
		
		Properties props = new Properties();  
        props.load(PicRootName.class.getClassLoader().getResourceAsStream("config.properties"));  
        System.out.println(props.getProperty("pic_url_base")); 
	}
	
	/**
	 * 返回图片路径前缀名
	 */
	public static String picPrefixName() throws IOException{
		Properties props = new Properties();  
        props.load(PicRootName.class.getClassLoader().getResourceAsStream("config.properties"));  
        String path = props.getProperty("pic_url_base");
        logger.info("返回图片的前缀名：" + path);
		return path.trim();
	}
	
	/**
	 * 返回新版上报复尺图片路径
	 */
	public static String NewRecheckPath() throws IOException{
		Properties props = new Properties();  
        props.load(PicRootName.class.getClassLoader().getResourceAsStream("config.properties"));  
        String recheck = props.getProperty("recheck");
        logger.info("获取复尺上传图片路径值：" + recheck);
		return recheck.trim();
	}

	/**
	 * 获取复尺类型
	 * @param key
	 * @return string
	 */
	public static String RecheckType(String key) throws IOException {
		String result = "";
		Properties props = new Properties();
		props.load(PicRootName.class.getClassLoader().getResourceAsStream("config.properties"));  
		if(key.equals("1")){//1-套口
			result = props.getProperty("recheck_taokou_key");
		}else if(key.equals("2")){//2-窗帘
			result = props.getProperty("recheck_curtain_key");
		}else if(key.equals("3")){//3-推拉门
			result = props.getProperty("recheck_pushdoor_key");
		}else if(key.equals("4")){//4-平开门
			result = props.getProperty("recheck_flatopen_key");
		}else if(key.equals("5")){//5-马桶
			result = props.getProperty("recheck_closetool_key");
		}else if(key.equals("6")){//6-浴室柜
			result = props.getProperty("recheck_roomcabinet_key");
		}
		logger.info("返回的复尺类型："+result);
		return result;
	}
}
