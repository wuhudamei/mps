package cn.damei.web.mobile.Manager;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import cn.damei.entity.modules.BizOrderInstallItemProblem;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.BusinessProblemConstantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.Base64Util;
import cn.damei.service.mobile.Inspector.CheckConfirmService;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.AppOrder;
import cn.damei.service.mobile.Manager.AppOrderService;
import cn.damei.entity.mobile.Manager.InstallProblem;
import cn.damei.service.mobile.Manager.ProblemService;
import cn.damei.service.mobile.Manager.WallAndFloorProblemService;
import cn.damei.entity.modules.BizProjectInstallItemProblemType;
import cn.damei.service.modules.BizProjectInstallItemProblemTypeService;
import cn.damei.entity.modules.Order;



@Controller
@RequestMapping(value="${adminPath}/app/manager/problem")
public class ProblemController {
	
	@Autowired
	private ProblemService problemService;
	@Autowired
	private AppOrderService appOrderService;
	@Autowired
	private BizProjectInstallItemProblemTypeService bizProjectInstallItemProblemTypeService;
	@Autowired
	private CheckConfirmService checkConfirmService;
	@Autowired
	private WallAndFloorProblemService wallAndFloorProblemService;
	
	private Logger  logger =  LoggerFactory.getLogger(AuxiliaryApplyController.class);


	@RequestMapping(value={"install_problem_back",""})
	public String installProblemBack(HttpServletRequest request, Model model){
		
		String installProblem = (String)request.getSession().getAttribute("installProblem");
		request.getSession().removeAttribute("installProblem");
		
		if(StringUtils.isNotBlank(installProblem)){
			if("1".equals(installProblem)){
				return "mobile/modules/Manager/projectInstall/projectBuild";	
			}else if("0".equals(installProblem)){
				return "mobile/modules/Manager/project-build/problem_up";
			}else{
				
				logger.warn("主材安装问题上报访问参数installProblem有误  installProblem:"+installProblem);
				return "mobile/modules/Manager/project-build/problem_up";
			}
			
		}else{
			logger.warn("主材安装问题上报访问参数为空");
			return "mobile/modules/Manager/project-build/problem_up";
		}
		
	}
	
	

	@RequestMapping(value={"list",""})
	public String applyMainIngredient(String installProblem,HttpServletRequest request, Model model){
		
		if("0".equals(installProblem)||"1".equals(installProblem)){
			request.getSession().setAttribute("installProblem", installProblem);
		}else{
			logger.warn("主材问题上报访问参数有误 ,无法识别路径参数: installProblem :"+installProblem);
		}
		

		Manager manager = (Manager)request.getSession().getAttribute("manager");
		
		if(null!=manager){
			model.addAttribute("managerId", manager.getId());
		}
		
		return "mobile/modules/Manager/project-build/questionList";
	}
	

	@RequestMapping(value="problem_order_ajax_list")
	public @ResponseBody  List<Order> problemOrderAjaxList(String managerId,String text){
		
		List<Order> list = null;
		if(null!=managerId && !("").equals(managerId)){
			list = problemService.findOrder(Integer.valueOf(managerId),text);
		}
		
		return list;
	}
	

	

	@RequestMapping(value={"problem_report",""})
	public String problemReport(Integer orderId, HttpServletRequest request, Model model){

		AppOrder order = null; 

		List<InstallProblem> list = null;
		
		if(null!=orderId){
			order = appOrderService.getOrder(orderId);
			list = problemService.findInstall(orderId);
		}
		model.addAttribute("order", order);
		model.addAttribute("list", list);
		return "mobile/modules/Manager/project-build/quesDoneList";
	}
	
	

	@RequestMapping(value={"question-submit",""})
	public String questionSubmit(String installItemName,Integer id,Integer orderId, HttpServletRequest request, Model model){
		

		AppOrder order = null; 
		if(null!=orderId){
			
			order = appOrderService.getOrder(orderId);

			BizProjectInstallItemProblemType problemType = new BizProjectInstallItemProblemType();
			problemType.setStoreId(order.getStoreId());
			problemType.setIsEnabled(BusinessProblemConstantUtil.BUSINESS_PROBLEM_IS_ENABLE_1);
			problemType.setProjectMode(order.getProjectMode());
			problemType.setDelFlag("0");
			problemType.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_1);
			List<BizProjectInstallItemProblemType> problemList = bizProjectInstallItemProblemTypeService.findList(problemType);
			
			model.addAttribute("order", order);
			model.addAttribute("id", id);
			model.addAttribute("installItemName", installItemName);
			model.addAttribute("problemList", problemList);
		}
		
		return "mobile/modules/Manager/project-build/quesDone";
	}
	

	@RequestMapping(value="problem-submit" ,method=RequestMethod.POST)
	public @ResponseBody String applyWallAndFloor(String orderInstallItemId,String problemTypeId,String quality,String delayDays,String problemDescribe,String[] photo, HttpServletRequest request) throws UnsupportedEncodingException{
		
		String result = "0";
		

		if(null==orderInstallItemId || orderInstallItemId.equals("")){
			result = "1";
			return result;
		}

		if(null==problemTypeId || problemTypeId.equals("")){
			result = "2";
			return result;
		}

		if(null==quality || quality.equals("")){
			result = "3";
			return result;
		}

		String isDelay = null;
		if(quality.equals("yes1")){
			isDelay = BusinessProblemConstantUtil.BUSINESS_PROBLEM_IS_DELAY_1;
			if(null==delayDays || delayDays.equals("")){
				result = "4";
				return result;
			}
		}else{
			isDelay = BusinessProblemConstantUtil.BUSINESS_PROBLEM_IS_DELAY_0;
		}

		if(null==problemDescribe || problemDescribe.equals("")){
			result = "5";
			return result;
		}

		Manager manager = (Manager)request.getSession().getAttribute("manager");
		if(null==manager || null==manager.getId()){
			result = "6";
			return result;
		}

		BizProjectInstallItemProblemType bizProjectInstallItemProblemType = bizProjectInstallItemProblemTypeService.get(Integer.valueOf(problemTypeId));
		if(null==bizProjectInstallItemProblemType){
			result = "7";
			return result;
		}

		Integer problemId = wallAndFloorProblemService.saveProblem(Integer.valueOf(orderInstallItemId),Integer.valueOf(problemTypeId),isDelay,Double.valueOf(delayDays),problemDescribe,BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_30,BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_1,null,null,bizProjectInstallItemProblemType);
		if(null==problemId || problemId<1){
			result = "8";
			return result;
		}
		

		Integer problemLogId = wallAndFloorProblemService.saveProblemLog(problemId,manager.getId(),BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_2,BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_30,problemDescribe);
		if(null==problemLogId || problemLogId<1){
			wallAndFloorProblemService.deleteProblem(problemId);
			result = "9";
			return result;
		}
		
		Date date = new Date();

		List<ReportCheckDetailsPic> pList = new ArrayList<ReportCheckDetailsPic>();
		if (null!=photo && photo.length>0) {
			
			for(String p : photo){
				
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				
				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + ConstantUtils.UPLOAD_PROBLEM_MANAGER + DateUtils.getDate1());

				if(!filePath.exists() && !filePath.isDirectory()){
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);
				
				String picpath = ConstantUtils.UPLOAD_PROBLEM_MANAGER + DateUtils.getDate1()+filePath.separator + uuid + ".jpeg";
				

				ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
				reportCheckDetailsPic.setBusinessIdInt(problemId);
				reportCheckDetailsPic.setBusinessType("207");
				reportCheckDetailsPic.setPicUrl(picpath);
				reportCheckDetailsPic.setPicDatetime(date);
				reportCheckDetailsPic.setCreateDate(date);
				reportCheckDetailsPic.setUpdateDate(date);
				reportCheckDetailsPic.setDelFlag("0");
				pList.add(reportCheckDetailsPic);
			}

			checkConfirmService.savePicAll(pList);
		}

		if(quality.equals("yes1")){
			result = "11";
		}else{
			result = "12";
		}
		return result;
	}
		

	@RequestMapping(value={"reported_record",""})
	public String reportedRecord(Integer orderId, HttpServletRequest request, Model model){

		AppOrder order = null; 

		List<InstallProblem> list = null;
		
		if(null!=orderId){
			order = appOrderService.getOrder(orderId);
			list = problemService.findInstallAndProblem(orderId);
		}
		model.addAttribute("order", order);
		model.addAttribute("list", list);
		return "mobile/modules/Manager/project-build/quesDetailsList";
	}
	

	@RequestMapping(value={"reported_record_details",""})
	public String reportedRecordDetails(String installItemName,Integer id,Integer orderId, HttpServletRequest request, Model model){
		

		AppOrder order = null; 
		if(null!=orderId){
			order = appOrderService.getOrder(orderId);
		}
		

		List<BizOrderInstallItemProblem> list = problemService.findProblemDetails(id);
		
		model.addAttribute("order", order);
		model.addAttribute("installItemName", installItemName);
		model.addAttribute("id", id);
		model.addAttribute("list", list);
		
		return "mobile/modules/Manager/project-build/quesDoneDetails";
	}

	@RequestMapping(value={"reported_record_details_picture",""})
	public String reportedRecordDetailsPicture(Integer id, HttpServletRequest request, Model model) throws IOException{
		
		String text = "207";
		List<ReportCheckDetailsPic> picList = problemService.findPic(id,text);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", baseUrl);
		return "mobile/modules/Manager/project-build/photo";
	}
	

}
