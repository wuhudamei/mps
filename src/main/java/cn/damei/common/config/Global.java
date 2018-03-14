
package cn.damei.common.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.core.io.DefaultResourceLoader;

import com.ckfinder.connector.ServletContextFactory;
import com.google.common.collect.Maps;
import cn.damei.common.utils.PropertiesLoader;
import cn.damei.common.utils.StringUtils;


public class Global {


	private static Global global = new Global();
	

	private static Map<String, String> map = Maps.newHashMap();
	

	private static PropertiesLoader loader = new PropertiesLoader("jeesite.properties");


	public static final String SHOW = "1";
	public static final String HIDE = "0";


	public static final String YES = "1";
	public static final String NO = "0";
	

	public static final String TRUE = "true";
	public static final String FALSE = "false";
	

	public static final String USERFILES_BASE_URL = "/upload/userfiles/";
	

	public static Global getInstance() {
		return global;
	}
	

	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}
	

	public static String getAdminPath() {
		return getConfig("adminPath");
	}
	
	public static String getRootPath() {
		return getConfig("root");
	}
	
	

	public static String getMD5key() {
		return getConfig("MD5key");
	}
	
	

	public static String getFrontPath() {
		return getConfig("frontPath");
	}
	

	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}
	

	public static Boolean isDemoMode() {
		String dm = getConfig("demoMode");
		return "true".equals(dm) || "1".equals(dm);
	}
	

	public static Boolean isSynActivitiIndetity() {
		String dm = getConfig("activiti.isSynActivitiIndetity");
		return "true".equals(dm) || "1".equals(dm);
	}
    

	public static Object getConst(String field) {
		try {
			return Global.class.getField(field).get(null);
		} catch (Exception e) {

		}
		return null;
	}


	public static String getUserfilesBaseDir() {
		String dir = getConfig("userfiles.basedir");
		if (StringUtils.isBlank(dir)){
			try {
				dir = ServletContextFactory.getServletContext().getRealPath("/");
			} catch (Exception e) {
				return "";
			}
		}
		if(!dir.endsWith("/")) {
			dir += "/";
		}

		return dir;
	}
	

    public static String getProjectPath(){

		String projectPath = Global.getConfig("projectPath");
		if (StringUtils.isNotBlank(projectPath)){
			return projectPath;
		}
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null){
				while(true){
					File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
					if (f == null || f.exists()){
						break;
					}
					if (file.getParentFile() != null){
						file = file.getParentFile();
					}else{
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return projectPath;
    }
	
}
