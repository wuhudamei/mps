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

	
	
	/**
	 * 安装项列表（完工）
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(){
		
		return "mobile/modules/Worker/installer/installApplyForCompletion/applyDoneList";
	}
	
	/**
	 * 动态加载施工单列表（申请完工）
	 * @param type
	 * @return
	 */
	@RequestMapping(value="install_construct_bill_applyForCompletion_ajax_list")
    public @ResponseBody  List<InstallItem> installConstructBillApplyForCompletionAjaxList(String text,HttpServletRequest request){

		//1.已登录的安装工信息
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		
		//2.查询施工单列表(状态为20-施工中）
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
	
	/**
	 * 安装项（完工页面）
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="applyPage")
	public String applyPage(String constructBillId, Model model){
		
		InstallItem installItem = new InstallItem();
		//施工单详情
		if(StringUtils.isNotBlank(constructBillId)){
			installItem = installApplyForCompletionService.findInstallConstructBillMessage(Integer.valueOf(constructBillId));
		}
		model.addAttribute("installItem", installItem);
		
		return "mobile/modules/Worker/installer/installApplyForCompletion/subDone";
	}
	
	/**
	 * 施工单(申请完工)
	 * @param constructBillId
	 * @param photo
	 * @param request
	 * @return
	 */
	@RequestMapping(value="install_construct_bill_ApplyForCompletion_submit")
	public @ResponseBody String installConstructBillApplyForCompletionSubmit(String constructBillId,String[] photo,HttpServletRequest request){
		
		String result = "0";
		
		//1.施工单id为空
		if(StringUtils.isBlank(constructBillId)){
			result = "1";
			return result;
		}
		//2.施工图少于3张
		if(null==photo || photo.length<3){
			result = "2";
			return result;
		}
		
		//3.已登录的安装工信息
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		if(null==worker){
			result = "3";
			return result;
		}
		
		//4.根据施工单id查询相关信息
		InstallItem installItem = installApplyForCompletionService.findInstallConstructBillMessage(Integer.valueOf(constructBillId));
		if(null == installItem){
			result = "4";
			return result;
		}
		
		//5.已经申请完工了
		if(!installItem.getConstructBillStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_20)){
			result = "5";
			return result;
		}
		
		//6.保存完工信息
		result = installApplyForCompletionService.saveInstallConstructBillApplyForCompletion(Integer.valueOf(constructBillId),photo,worker,installItem,request);
		
		return result;
		
	}
	
}
