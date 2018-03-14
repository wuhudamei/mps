package cn.damei.web.mobile.Worker;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.InstallPlanConstantUtil;
import cn.damei.service.mobile.Worker.InstallApplyForCompletionService;
import cn.damei.entity.mobile.Worker.InstallItem;
import cn.damei.entity.mobile.Worker.Worker;

@Controller
@RequestMapping(value="${adminPath}/app/worker/install/installApplyForCompletion")
public class InstallApplyForCompletionController {
	
	@Autowired
	private InstallApplyForCompletionService installApplyForCompletionService;

	
	

	@RequestMapping(value="list")
	public String list(){
		
		return "mobile/modules/Worker/installer/installApplyForCompletion/applyDoneList";
	}
	

	@RequestMapping(value="install_construct_bill_applyForCompletion_ajax_list")
    public @ResponseBody  List<InstallItem> installConstructBillApplyForCompletionAjaxList(String text,HttpServletRequest request){


		Worker worker = (Worker)request.getSession().getAttribute("worker");
		

		List<InstallItem> list = null;
		if(worker != null && null!=worker.getEmgrouprelationId()){
			
			InstallItem installItem = new InstallItem();
			installItem.setEmployeeGroupId(worker.getEmgrouprelationId());
			installItem.setConstructBillStatus(InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_20);
			installItem.setText(text);
			
			list = installApplyForCompletionService.findInstallConstructBillApplyForCompletionList(installItem);
		}

       return list;

    }
	

	@RequestMapping(value="applyPage")
	public String applyPage(String constructBillId, Model model){
		
		InstallItem installItem = new InstallItem();

		if(StringUtils.isNotBlank(constructBillId)){
			installItem = installApplyForCompletionService.findInstallConstructBillMessage(Integer.valueOf(constructBillId));
		}
		model.addAttribute("installItem", installItem);
		
		return "mobile/modules/Worker/installer/installApplyForCompletion/subDone";
	}
	

	@RequestMapping(value="install_construct_bill_ApplyForCompletion_submit")
	public @ResponseBody String installConstructBillApplyForCompletionSubmit(String constructBillId,String[] photo,HttpServletRequest request){
		
		String result = "0";
		

		if(StringUtils.isBlank(constructBillId)){
			result = "1";
			return result;
		}

		if(null==photo || photo.length<3){
			result = "2";
			return result;
		}
		

		Worker worker = (Worker)request.getSession().getAttribute("worker");
		if(null==worker){
			result = "3";
			return result;
		}
		

		InstallItem installItem = installApplyForCompletionService.findInstallConstructBillMessage(Integer.valueOf(constructBillId));
		if(null == installItem){
			result = "4";
			return result;
		}
		

		if(!installItem.getConstructBillStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_20)){
			result = "5";
			return result;
		}
		

		result = installApplyForCompletionService.saveInstallConstructBillApplyForCompletion(Integer.valueOf(constructBillId),photo,worker,installItem,request);
		
		return result;
		
	}
	
}
