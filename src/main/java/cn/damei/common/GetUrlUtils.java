package cn.damei.common;

public class GetUrlUtils {
	
	public static String getUrl(String url){
		
		String[] split = url.split("/");
		split[split.length -1]="toLogin";
		StringBuffer sb = new StringBuffer();
		for (int i=1 ; i<split.length;i++) {
			sb.append("/").append(split[i]);
		}
		return sb.toString().trim();
	}
}
