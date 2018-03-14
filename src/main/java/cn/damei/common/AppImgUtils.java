package cn.damei.common;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.PicRootName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;



@Controller
@RequestMapping(value = "${adminPath}/app/img")
public class AppImgUtils {
	private static Logger logger = LoggerFactory.getLogger(AppImgUtils.class);

	@RequestMapping("/test")
	@ResponseBody
	public String test(){
		System.out.print("aaaaaaaaa");
		return "aaaa";
	}


	@RequestMapping("/method/upload")
	@ResponseBody
	public static String updataImgByBase(HttpServletRequest request, String baseImg, String businessType) throws IOException {
		
		if(null==baseImg||baseImg.length()==0){
			return "";
		}
		logger.info("img length="+baseImg.length()+"businessType="+businessType);
		
		String root = request.getSession().getServletContext().getRealPath("/");
		String imgUrl = PicRootName.getConfigValue(ConstantUtils.UPLOAD_DISCLOSE);
		logger.info("root=" + root);
		File filePath = new File(root + imgUrl + DateUtils.getDate1());
		logger.info("file 路径"+filePath);

		if(!filePath.exists()){
			filePath.mkdirs();
		}
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String picUrl = imgUrl + DateUtils.getDate1() + "/" + uuid + ".jpeg";
		String fullPath = filePath + filePath.separator + uuid + ".jpeg";
		logger.info("完整路径："+fullPath);

		Base64Util.generateImage(baseImg, fullPath.toString());

		return picUrl;

	}


	@RequestMapping("/method/removepic")
	@ResponseBody
	public String removePic(HttpServletRequest request,String oldUrl){
		String root = request.getSession().getServletContext().getRealPath("/");
		String result="0";
		if(null==oldUrl||oldUrl.length()==0)
		return result;
		File pic = new File(root+oldUrl);
		if(pic.exists()){
			pic.delete();
			result="1";
		}
	return result;
	}
}
