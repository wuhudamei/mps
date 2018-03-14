package cn.damei.web.mobile.Manager;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.service.mobile.Manager.OrderConfirmStartworkPicService;

/**
 * @author llp
 * @version 创建时间：2016年10月27日 下午5:05:38 类说明
 */
@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class OrderConfirmStartworkPicController {

	private static Logger logger = LoggerFactory.getLogger(OrderConfirmStartworkPicController.class);
	
	@Autowired
	private OrderConfirmStartworkPicService orderConfirmStartworkPicService;
	
	/**
	 * 确认开工上传图片
	 * @param id
	 * @param request
	 * @param doneDemandPic
	 * @return path
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	/*@RequestMapping(value = "conStartPhoto", method = RequestMethod.POST)
	public @ResponseBody List<String> savePhoto(HttpServletRequest request,
			@RequestParam(value = "pic", required = false) MultipartFile[] pic,String OrderID)
			throws IllegalStateException, IOException {
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		// 获得物理路径webapp所在路径
		String pathRoot = req.getSession().getServletContext().getRealPath("");
		String path = "";
		logger.info("传入图片的值："+pic);
		File file = new File(ConstantUtils.UPLOAD_CONFIRMSTART);//确认开工上传图片路径
		if(!file.exists()){
			file.mkdirs();
		}
		List<String> picPath = new ArrayList<String>();
		for (MultipartFile mf : pic) {
			if (!mf.isEmpty()) {
				// 生成uuid作为文件名称
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				// 获得文件类型（可以判断如果不是图片，禁止上传）
				String contentType = mf.getContentType();
				String picName = contentType.substring(contentType.indexOf("/") + 1);
				path = (file + file.separator + uuid + "." + picName).trim();
				logger.info("返回图片的途径photos2："+path);
				mf.transferTo(new File(pathRoot + path));
				picPath.add(path);
			}

		}

		return picPath;
	}*/
}
