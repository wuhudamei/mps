package cn.damei.web.mobile.Manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.damei.common.config.Global;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.service.mobile.Manager.NodePlanService;
import cn.damei.entity.mobile.Manager.NodePlanPic;
import cn.damei.service.mobile.Manager.NodePlanPicService;

@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class NodePlanPicController {
	private static Logger logger = LoggerFactory.getLogger(NodePlanPicController.class);
	
	@Autowired
	private NodePlanPicService nodePlanPicService;
	
	@Autowired
	private NodePlanService nodePlanService;
	
	@RequestMapping(value = "uploadNodePlanPic", method = RequestMethod.POST)
	public @ResponseBody List<String> uploadNodePlanPic(HttpServletRequest req,
			@RequestParam(value = "pic", required = false) MultipartFile[] pic, String orderId)
			throws IllegalStateException, IOException {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;
		String nodePlanId = req.getParameter("nodePlanId");
		logger.info("节点编号：" + nodePlanId);
		
		// 获得物理路径webapp所在路径
		String pathRoot = request.getSession().getServletContext().getRealPath("");
		String path = "";
		File file = new File(ConstantUtils.UPLOAD_PROGRESS);//进度通报图片路径
		List<String> listImagePath = new ArrayList<String>();
		for (MultipartFile mf : pic) {
			if (!mf.isEmpty()) {
				// 生成uuid作为文件名称
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				// 获得文件类型（可以判断如果不是图片，禁止上传）
				String contentType = mf.getContentType();
				String picName = contentType.substring(contentType.indexOf("/") + 1);
				path = (file + file.separator + uuid + "." + picName).trim();
				
				logger.info("返回进度通报图片的途径："+path);
				mf.transferTo(new File(pathRoot + path));
				listImagePath.add(path);
			}
		}

		return listImagePath;
	}
	
	/**
	 * 上传图片以及页面中的数据
	 * 提交数据到后台
	 */
	@RequestMapping(value = "submitData", method = RequestMethod.POST)
	public String submitData(HttpServletRequest request,NodePlanPic nodePlanPic , String pics, String nodePlanId) {
		String realDoneDate = request.getParameter("realDoneDate");//实际完成日期
		String nodeIndex = request.getParameter("nodeIndex");
		String delayReason = request.getParameter("delayReason");//延期原因描述
		String delayType = request.getParameter("delayType");//延期类型
		String picsValue1 = request.getParameter("picsValue1");//
		String picsValue2 = request.getParameter("picsValue2");
		
		logger.info("实际完成日期：" + realDoneDate + "\t 延迟原因说明：" + delayReason +"\t 序列编号：" + nodeIndex);
		System.out.println("picsValue1===="+picsValue1);
		
		boolean picflag = false;
		boolean flag = false;
		pics = pics.substring(0, pics.length()-2);
		
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(pics,"||");
		while(st.hasMoreTokens()){
			list.add(st.nextToken());
		}
		
		//把图片信息写入biz_node_plan_pic
		for(String donePic : list){
			logger.info("图片完整路径："+donePic);
			nodePlanPic.setNodePlanId(Integer.valueOf(nodePlanId));
			nodePlanPic.setPicUrl(donePic);
			//picflag = nodePlanPicService.insertNodePlanPic(nodePlanPic);
			logger.info("insert node_plan_pic flag(true:success;false:false)："+flag);
		}
		
		//NodePlan = node = nodePlanService.getByMaxId(nodePlanId);
		//flag = nodePlanService.updateByDate(realDoneDate,nodePlanId,delayType,delayReason);

		return "redirect:" + Global.getAdminPath() + "/app/manager/progressBuiletin";
	}
}
