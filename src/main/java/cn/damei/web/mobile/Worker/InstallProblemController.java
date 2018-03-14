package cn.damei.web.mobile.Worker;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.damei.entity.modules.BizOrderInstallItemProblem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.BusinessProblemConstantUtil;
import cn.damei.common.constantUtils.InstallPlanConstantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Worker.InstallItem;
import cn.damei.service.mobile.Worker.InstallProblemService;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.entity.modules.BizProjectInstallItemProblemType;
import cn.damei.service.modules.BizProjectInstallItemProblemTypeService;

@Controller
@RequestMapping(value="${adminPath}/app/worker/install/installProblem")
public class InstallProblemController {
	
	@Autowired
	private InstallProblemService installProblemService;
	@Autowired
	private BizProjectInstallItemProblemTypeService bizProjectInstallItemProblemTypeService;

	

	@RequestMapping(value="list")
	public String list(){
		
		return "mobile/modules/Worker/installer/installProblem/installQuesList";
	}
	

	@RequestMapping(value="install_construct_bill_problem_ajax_list")
    public @ResponseBody  List<InstallItem> installConstructBillProblemAjaxList(String text,HttpServletRequest request){


		Worker worker = (Worker)request.getSession().getAttribute("worker");
		

		List<String> statusList = new ArrayList<String>();
		statusList.add(InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_10);
		statusList.add(InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_20);
		statusList.add(InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_30);
		

		List<InstallItem> list = null;
		if(worker != null && null!=worker.getEmgrouprelationId()){
			
			InstallItem installItem = new InstallItem();
			installItem.setConstructBillStatusList(statusList);
			installItem.setEmployeeGroupId(worker.getEmgrouprelationId());
			installItem.setText(text);
			
			list = installProblemService.findInstallConstructBillProblemList(installItem);
		}

       return list;

    }
	

	@RequestMapping(value="problem_report_ajax_time")
	public @ResponseBody String problemReportAjaxTime(String constructBillId,String businessType){
		
		String result = "0";
		

		if(StringUtils.isBlank(constructBillId)){
			result = "1";
			return result;
		}
		

		if(StringUtils.isBlank(businessType)){
			result = "2";
			return result;
		}
		

		Integer count = installProblemService.findProblemCount(Integer.valueOf(constructBillId),businessType);
		
		if(null!=count && count>0){
			result = "3";
			return result;
		}
		return result;
	}
	
	
	

	@RequestMapping(value={"problem_list",""})
	public String problemList(String constructBillId, Model model){
		
		InstallItem installItem = null;
		List<BizProjectInstallItemProblemType> problemList = null;
		
		if(StringUtils.isNotBlank(constructBillId)){
			

			installItem = installProblemService.findInstallConstructBillMessage(Integer.valueOf(constructBillId));
			

			BizProjectInstallItemProblemType problemType = new BizProjectInstallItemProblemType();
			problemType.setStoreId(installItem.getStoreId());
			problemType.setIsEnabled(BusinessProblemConstantUtil.BUSINESS_PROBLEM_IS_ENABLE_1);
			problemType.setProjectMode(installItem.getProjectMode());
			problemType.setDelFlag(BusinessProblemConstantUtil.BUSINESS_PROBLEM_DEL_FLAG_0);
			problemType.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_5);
			problemList = bizProjectInstallItemProblemTypeService.findList(problemType);
		}
		
		model.addAttribute("installItem", installItem);
		model.addAttribute("problemList", problemList);
	
		return "mobile/modules/Worker/installer/installProblem/installQues";
		
	}	
	
	
	@RequestMapping(value="problem_submit")
	public @ResponseBody String problemSubmit(String constructBillId,String problemTypeId,String problemDescribe,String[] photo,HttpServletRequest request){
		
		String result = "0";
		

		if(StringUtils.isBlank(constructBillId)){
			result = "1";
			return result;
		}
		

		if(StringUtils.isBlank(problemTypeId)){
			result = "2";
			return result;
		}
		

		if(null==photo || photo.length<1){
			result = "3";
			return result;
		}
		

		Worker worker = (Worker)request.getSession().getAttribute("worker");
		if(null==worker){
			result = "4";
			return result;
		}
		

		InstallItem installItem = installProblemService.findInstallConstructBillMessage(Integer.valueOf(constructBillId));
		if(null == installItem){
			result = "5";
			return result;
		}
		

		if(!((installItem.getConstructBillStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_10)) || (installItem.getConstructBillStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_20)) || (installItem.getConstructBillStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_30)))){
			result = "6";
			return result;
		}
		

		BizProjectInstallItemProblemType bizProjectInstallItemProblemType = bizProjectInstallItemProblemTypeService.get(Integer.valueOf(problemTypeId));
		if(null==bizProjectInstallItemProblemType){
			result = "7";
			return result;
		}
				

		result = installProblemService.saveConstructBillProblem(Integer.valueOf(constructBillId),problemTypeId,problemDescribe,photo,worker,installItem,bizProjectInstallItemProblemType,request);
		
		return result;
	}
	
	

	@RequestMapping(value={"problem_record_list",""})
	public String problemRecordList(String constructBillId, Model model){
		
		InstallItem installItem = null;
		
		if(StringUtils.isNotBlank(constructBillId)){
			

			installItem = installProblemService.findInstallConstructBillMessage(Integer.valueOf(constructBillId));
			
		}
		
		model.addAttribute("installItem", installItem);
		model.addAttribute("constructBillId", constructBillId);
		
		return "mobile/modules/Worker/installer/installProblem/installQuesDetails";
		
	}	
	

	@RequestMapping(value="problem_log_ajax_list")
	public @ResponseBody  List<BizOrderInstallItemProblem> problemLogAjaxList(String constructBillId){
		
		List<BizOrderInstallItemProblem> list = null;
		if(StringUtils.isNotBlank(constructBillId)){
			list = installProblemService.findProblemLogList(Integer.valueOf(constructBillId),BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_5);
		}
		
		return list;
	}
	
	

	@RequestMapping(value={"picture",""})
	public String picture(String id, HttpServletRequest request, Model model) throws IOException{
		
		List<ReportCheckDetailsPic> picList = null;
		
		if(StringUtils.isNotBlank(id)){
			picList = installProblemService.findPic(Integer.valueOf(id),PictureTypeContantUtil.PICTURE_TYPE_2072);
		}
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", baseUrl);
		return "mobile/modules/Worker/installer/installProblem/photo";
	}
}
