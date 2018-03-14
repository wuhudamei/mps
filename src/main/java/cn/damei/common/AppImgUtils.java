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


/**
 * 图片ajax上传公共方法
 * 服务工具类
 * @author wtk
 * 2016/10/17
 */
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

	/**
	 * 图片上传接口
	 * @param request
	 * @param baseImg base64的图片字符串
	 * @param businessType 业务类型（交底／通报／**的code）
	 * @return 图片上传完成后服务器上的路径
	 * @throws IOException
	 */
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
		//判断该文件是否存在
		if(!filePath.exists()){
			filePath.mkdirs();
		}
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String picUrl = imgUrl + DateUtils.getDate1() + "/" + uuid + ".jpeg";
		String fullPath = filePath + filePath.separator + uuid + ".jpeg";
		logger.info("完整路径："+fullPath);
		//base64解析成图片并放到指定文件夹
		Base64Util.generateImage(baseImg, fullPath.toString());

		return picUrl;

	}

	/**
	 * 图片删除接口
	 * @param oldUrl 图片在服务器上的路径
	 * @return
	 */
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
