package cn.damei.web.mobile.Manager;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.Base64Util;
import cn.damei.entity.mobile.Manager.NodePlan;
import cn.damei.service.mobile.Manager.NodePlanService;
import cn.damei.entity.mobile.Manager.NodePlanPic;
import cn.damei.service.mobile.Manager.NodePlanPicService;

/**
 * 进度通报
 * biz_node_plan
 * llp
 */
@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class NodePlanController {
	private static Logger logger = LoggerFactory.getLogger(NodePlanController.class);

	@Autowired
	private NodePlanService nodePlanService;
	
	@Autowired
	private NodePlanPicService nodePlanPicService;
	
	/**
	 * 进度通报
	 * 根据订单Id查询
	 * biz_node_plan表列表
	 */
	@RequestMapping(value={"progressCondition",""})
	public String progressCondition(NodePlan nodePlan, Model model, HttpServletRequest request) {
		String orderId = request.getParameter("orderId");
		//已登录的项目经理
		//Manager manager = (Manager) request.getSession().getAttribute("manager");
		logger.info("ID========" + orderId);
		
		NodePlan np = nodePlanService.getByIdLimit(orderId);
		if(null!=np&&np.getProjectMode()==2){
			
			List<NodePlan> list = nodePlanService.justForTraditionNodePlan(Integer.parseInt(orderId));
			
			model.addAttribute("nodeList", list);
			
			
		}
		//logger.info("订单编号(id)："+cso.getId());
		model.addAttribute("nodePlan", np);
		if(null != np ){
			model.addAttribute("projectMode", np.getProjectMode());
		}
		
		return "mobile/modules/Manager/progressMain/progressBulletin/progressCondition";
	}
	
	/**
	 * 上传图片以及页面中的数据
	 * 提交数据到后台
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(value = "submitNodePlanData")
	public String submitNodePlanData(String[] photos, String nodePlanId,HttpServletRequest request,
			String realDoneDate, String delayType, String delayReason) throws IOException {
		logger.info("nodePlanId：" + nodePlanId +"\t "+"实际开工日期:"+
			realDoneDate+"\t 延期类型:"+delayType+"\t 延期说明:"+delayReason);
		
		String result = "0";
		
		//把图片信息写入biz_node_plan_pic
		NodePlanPic nodePlanPic = new NodePlanPic();
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String imgUrl = PicRootName.getConfigValue(ConstantUtils.UPLOAD_PROGRESS);
		if(null!=photos &&photos.length>0){
		for(String pic : photos){
			logger.info("获取到图片值："+pic);
			logger.info("rootPath =" + rootPath);
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			File filePath = new File(rootPath + imgUrl + DateUtils.getDate1());
			//判断该文件是否存在
			if(!filePath.exists()){
				filePath.mkdirs();
			}
			String picUrl = imgUrl + DateUtils.getDate1()+ "/" + uuid + ".jpeg";
			String fullPath = filePath + filePath.separator + uuid + ".jpeg";
			logger.info("完整路径："+fullPath);
			//base64解析成图片并放到指定文件夹
			Base64Util.generateImage(pic, fullPath.toString());
			nodePlanPic.setNodePlanId(Integer.valueOf(nodePlanId));
			nodePlanPic.setPicUrl(picUrl);
			result = nodePlanPicService.insertNodePlanPic(nodePlanPic);
			logger.info("insert node_plan_pic flag(true:success;false:false)：" + result);
		}
		}
		
		return result = nodePlanService.updateByDate(realDoneDate,nodePlanId,delayType,delayReason);
	}
}
