package cn.damei.web.mobile.Manager;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.BusinessProblemConstantUtil;
import cn.damei.common.constantUtils.PicturePathContantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.service.mobile.Manager.ProblemService;
import cn.damei.service.mobile.Manager.SiteDesignProblemService;
import cn.damei.service.mobile.Manager.WallAndFloorProblemService;
import cn.damei.entity.modules.BizProjectInstallItemProblemType;
import cn.damei.service.modules.BizProjectInstallItemProblemTypeService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;

/**
 * 工地设计问题上报Controller
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value="${adminPath}/app/manager/problem/siteDesign")
public class SiteDesignController {
	
	@Autowired
	private SiteDesignProblemService siteDesignProblemService;
	@Autowired
	private WallAndFloorProblemService wallAndFloorProblemService;
	@Autowired
	private BizProjectInstallItemProblemTypeService bizProjectInstallItemProblemTypeService;
	@Autowired
	private ProblemService problemService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	
	/**
	 * 问题上报----总页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"menu",""})
	public String menu(HttpServletRequest request, Model model){
		
		return "mobile/modules/Manager/project-build/problem_up";
	}
	
	
	/**
	 * 工地设计问题上报--订单列表 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"list",""})
	public String list(HttpServletRequest request, Model model){
		
		// 获取项目经理sesseion
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		if(null!=manager){
			model.addAttribute("managerId", manager.getId());
		}
		return "mobile/modules/Manager/project-build/siteDesignProblem/problem_list";
	}
	
	/**
	 * 动态加载工地设计问题上报列表
	 * @param managerId
	 * @param text
	 * @return
	 */
	@RequestMapping(value="problem_sign_design_ajax_list")
	public @ResponseBody  List<MaterialManagement> problemSignDesignAjaxList(String managerId,String text){
		
		List<MaterialManagement> list = null;
		if(StringUtils.isNotBlank(managerId)){
			list = siteDesignProblemService.findOrderList(Integer.valueOf(managerId),text);
		}
		
		return list;
	}
	
	

	/**
	 * 工地设计问题上报页
	 * @param text
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"problem_sign_design",""})
	public String problemSignDesign(String orderId,HttpServletRequest request, Model model){
		
		MaterialManagement materialManagement = null;
		List<BizProjectInstallItemProblemType> problemList = null;
		if(StringUtils.isNotBlank(orderId)){
			//订单详情
			materialManagement = wallAndFloorProblemService.findOrder(Integer.valueOf(orderId));
			//问题分类
			BizProjectInstallItemProblemType problemType = new BizProjectInstallItemProblemType();
			problemType.setStoreId(materialManagement.getStoreId());
			problemType.setIsEnabled(BusinessProblemConstantUtil.BUSINESS_PROBLEM_IS_ENABLE_1);
			problemType.setProjectMode(materialManagement.getProjectMode());
			problemType.setDelFlag(BusinessProblemConstantUtil.BUSINESS_PROBLEM_DEL_FLAG_0);
			problemType.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_4);
			problemList = bizProjectInstallItemProblemTypeService.findList(problemType);
		}
		
		
		model.addAttribute("materialManagement", materialManagement);
		model.addAttribute("problemList", problemList);
		model.addAttribute("orderId", orderId);
	
		return "mobile/modules/Manager/project-build/siteDesignProblem/ProblemReport";
		
	}	
	
	/**
	 * 工地设计问题上报提交
	 * @param orderId
	 * @param problemTypeId
	 * @param quality
	 * @param delayDays
	 * @param problemDescribe
	 * @param request
	 * @return
	 */
	@RequestMapping(value="sign_design_problem_submit" ,method=RequestMethod.POST)
	public @ResponseBody String signDesignProblemSubmit(String orderId,String problemTypeId,String txtBeginDate,String inchargeName,String problemDescribe,String[] photo,HttpServletRequest request){
		String result = "0";
		
		//1.订单id为空
		if(StringUtils.isBlank(orderId)){
			result = "1";
			return result;
		}
		//2.问题分类为空
		if(StringUtils.isBlank(problemTypeId)){
			result = "2";
			return result;
		}
		//3.期望完成日期为空
		if(StringUtils.isBlank(txtBeginDate)){
			result = "3";
			return result;
		}
		//4.责任人为空
		if(StringUtils.isBlank(inchargeName)){
			result = "4";
			return result;
		}
		//5.上报问题描述为空
		if(StringUtils.isBlank(problemDescribe)){
			result = "5";
			return result;
		}
		//6.获取项目经理
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		if(null==manager || null==manager.getId()){
			result = "6";
			return result;
		}
		
		//7.查询问题分类内容
		BizProjectInstallItemProblemType bizProjectInstallItemProblemType = bizProjectInstallItemProblemTypeService.get(Integer.valueOf(problemTypeId));
		if(null==bizProjectInstallItemProblemType){
			result = "7";
			return result;
		}
		//8.保存上报问题
		Integer problemId = wallAndFloorProblemService.saveProblem(Integer.valueOf(orderId),Integer.valueOf(problemTypeId),null,null,problemDescribe,BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_10,BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_4,DateUtils.parseDate(txtBeginDate),inchargeName,bizProjectInstallItemProblemType);
		if(null==problemId || problemId<1){
			result = "8";
			return result;
		}
		
		//9.保存上报问题日志
		Integer problemLogId = wallAndFloorProblemService.saveProblemLog(problemId,manager.getId(),BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_1,BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_10,problemDescribe);
		if(null==problemLogId || problemLogId<1){
			wallAndFloorProblemService.deleteProblem(problemId);
			result = "9";
			return result;
		}
		
		//10.保存上报问题图片
		if(null!=photo && photo.length>0){
			wallAndFloorProblemService.saveProblemPic(problemId,PictureTypeContantUtil.PICTURE_TYPE_2081,photo,PicturePathContantUtil.UPLOAD_SITE_DESIGN_PROBLEM_MANAGER_APPLY,request);
		} 
		
		//11.获取订单信息--发送短信
		MaterialManagement materialManagement = wallAndFloorProblemService.findOrder(Integer.valueOf(orderId));
		if(StringUtils.isNotBlank(materialManagement.getDesignerPhone())){
			
			//=====================================短信start========================================================
			/**
			 * 问题上报后==设计师
			 */
			//订单（小区名称-楼号-单元号-门牌号-客户姓名）的项目经理（姓名-手机号），上报了设计问题（问题类型），请您及时登录系统进行处理。
			String content = "订单（"+materialManagement.getCommunityName()+"-"+materialManagement.getBuildNumber()+"-"+materialManagement.getBuildUnit()+"-"+materialManagement.getBuildRoom()+"-"+materialManagement.getCustomerName()+"）的项目经理（"+manager.getRealname()+"-"+manager.getPhone()+"），上报了设计问题（"+bizProjectInstallItemProblemType.getTypeName()+"），请您及时登录系统进行处理。";
			BizPhoneMsg phone = new BizPhoneMsg();
			phone.setReceiveEmployeeId(null);
			phone.setReceivePhone(materialManagement.getDesignerPhone());
			phone.setMsgContent(content);
			phone.setMsgGenerateDatetime(new Date());
			phone.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			phone.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_800901);
			phone.setRelatedBusinessIdInt(problemId);
			bizPhoneMsgService.insert(phone);
			
			//=====================================短信end========================================================
		}
		
		
		return result;
	}
	
		
	/**
	 * 工地设计问题上报详情页
	 * @param text
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value={"signDesignproblemRecord",""})
	public String signDesignproblemRecord(String orderId,HttpServletRequest request, Model model) throws IOException{
		
		MaterialManagement materialManagement = null;
		if(StringUtils.isNotBlank(orderId)){
			//订单详情
			materialManagement = wallAndFloorProblemService.findOrder(Integer.valueOf(orderId));
		}
		
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("baseUrl", baseUrl);
		
		model.addAttribute("materialManagement", materialManagement);
		model.addAttribute("orderId", orderId);
		
		return "mobile/modules/Manager/project-build/siteDesignProblem/problem_xq";
	}
	
	/**
	 * 动态加载墙地砖问题上报记录页面
	 * @param managerId
	 * @param text
	 * @return
	 */
	@RequestMapping(value="sign_design_problem_log_ajax_list")
	public @ResponseBody  List<Map<String,String>> signDesignProblemLogAjaxList(String orderId){
		
		List<Map<String,String>> list = null;
		if(StringUtils.isNotBlank(orderId)){
			list = siteDesignProblemService.findProblemLogList(orderId,BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_4,BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_5,BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_70,PictureTypeContantUtil.PICTURE_TYPE_2082);
		}
		return list;
	}
	
	
	/**
	 * 工地设计问题上报记录--详情--图片
	 * @param id
	 * @param text
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="sign_design_problem_log_pic")
	public @ResponseBody List<ReportCheckDetailsPic> signDesignProblemLogPic(Integer id,String text){
		
		List<ReportCheckDetailsPic> picList = problemService.findPic(id,text);

		return picList;
	}
	

}
