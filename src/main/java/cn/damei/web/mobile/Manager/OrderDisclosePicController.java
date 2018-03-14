package cn.damei.web.mobile.Manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.service.mobile.Manager.OrderDisclosePicService;


@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class OrderDisclosePicController {
	private static Logger logger = LoggerFactory.getLogger(OrderDisclosePicController.class);

	@Autowired
	private OrderDisclosePicService orderDisclosePicService;
	

	  /*
	@SuppressWarnings("static-access")
	@RequestMapping(value={"orderDisclosePicUploadPhoto2",""})
	public @ResponseBody List<String> uploadPhoto2(String id, HttpServletRequest request,
			@RequestParam(value = "photos2", required = false) MultipartFile[] photos)
			throws IllegalStateException, IOException {
		logger.info("调用的类型：" +id.trim());
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		// 获得物理路径webapp所在路径
		String pathRoot = req.getSession().getServletContext().getRealPath("");
		String path = "";
		logger.info("传入图片的值："+photos);
		File file = new File(ConstantUtils.UPLOAD_DISCLOSE);//现场交底上传图片路径
		List<String> picPath = new ArrayList<String>();
		for (MultipartFile mf : photos) {
			if (!mf.isEmpty()) {
				// 生成uuid作为文件名称
				String uuid = UUID.randomUUID().toString().replaceAll("-", "").trim();
				// 获得文件类型（可以判断如果不是图片，禁止上传）
				String contentType = mf.getContentType();
				String picName = contentType.substring(contentType.indexOf("/") + 1);
				if(!file.exists()){
					logger.info("文件夹不存在!");
					file.mkdirs();
					logger.info("新建文件后的路径："+file);
					path = (file + file.separator + uuid + "." + picName).trim();
				}else{
					path = (file + file.separator + uuid + "." + picName).trim();
				}
				logger.info("返回图片的途径photos2："+path);
				mf.transferTo(new File(pathRoot + path));
				picPath.add(path);
			}

		}

		return picPath;
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value={"orderDisclosePicUploadPhoto3",""})
	public @ResponseBody List<String> uploadPhoto3(String id, HttpServletRequest request,
			@RequestParam(value = "photos3", required = false) MultipartFile[] photos)
			throws IllegalStateException, IOException {
		logger.info("调用的类型：" +id.trim());
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		// 获得物理路径webapp所在路径
		String pathRoot = req.getSession().getServletContext().getRealPath("");
		String path = "";
		logger.info("传入图片的值："+photos);
		File file = new File(ConstantUtils.UPLOAD_DISCLOSE);//现场交底上传图片路径
		List<String> picPath = new ArrayList<String>();
		for (MultipartFile mf : photos) {
			if (!mf.isEmpty()) {
				// 生成uuid作为文件名称
				String uuid = UUID.randomUUID().toString().replaceAll("-", "").trim();
				// 获得文件类型（可以判断如果不是图片，禁止上传）
				String contentType = mf.getContentType();
				String picName = contentType.substring(contentType.indexOf("/") + 1);
				if(!file.exists()){
					logger.info("文件夹不存在!");
					file.mkdirs();
					logger.info("新建文件后的路径："+file);
					path = (file +file.separator + uuid + "." + picName).trim();
				}else{
					path = (file + file.separator + uuid + "." + picName).trim();
				}
				logger.info("返回图片的途径photos3："+path);
				mf.transferTo(new File(pathRoot + path));
				picPath.add(path);
			}

		}

		return picPath;
	}*/
}
