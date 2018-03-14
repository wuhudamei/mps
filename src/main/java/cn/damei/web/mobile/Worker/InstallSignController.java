package cn.damei.web.mobile.Worker;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.InstallPlanConstantUtil;
import cn.damei.entity.mobile.Worker.InstallItem;
import cn.damei.service.mobile.Worker.InstallSignService;
import cn.damei.entity.mobile.Worker.Worker;

@Controller
@RequestMapping(value="${adminPath}/app/worker/install/installSign")
public class InstallSignController {
	
	@Autowired
	private InstallSignService installSignService;

	
	

	@RequestMapping(value="list")
	public String list(){
		
		return "mobile/modules/Worker/installer/installSign/signList";
	}
	

	@RequestMapping(value="install_construct_bill_sign_ajax_list")
    public @ResponseBody  List<InstallItem> installConstructBillSignAjaxList(String text,HttpServletRequest request){


		Worker worker = (Worker)request.getSession().getAttribute("worker");
		

		List<InstallItem> list = null;
		if(worker != null && null!=worker.getEmgrouprelationId()){
			
			InstallItem installItem = new InstallItem();
			installItem.setEmployeeGroupId(worker.getEmgrouprelationId());
			installItem.setConstructBillStatus(InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_10);
			installItem.setText(text);
			
			list = installSignService.findInstallConstructBillSignList(installItem);
		}

       return list;

    }
	
	

	@RequestMapping(value="install_construct_bill_sign_submit")
	public @ResponseBody String installConstructBillSignSubmit(String lat,String lon,String constructBillId,String signDistance,String signAddress,HttpServletRequest request){
		
		String result = "0";
		

		if(StringUtils.isBlank(lat)){
			result = "1";
			return result;
		}

		if(StringUtils.isBlank(lon)){
			result = "2";
			return result;
		}

		if(StringUtils.isBlank(constructBillId)){
			result = "3";
			return result;
		}

		if(StringUtils.isBlank(signDistance)){
			result = "4";
			return result;
		}

		if(StringUtils.isBlank(signAddress)){
			result = "5";
			return result;
		}

		Worker worker = (Worker)request.getSession().getAttribute("worker");
		if(null==worker){
			result = "6";
			return result;
		}
		

		InstallItem installItem = installSignService.findInstallConstructBillMessage(Integer.valueOf(constructBillId));
		if(null == installItem){
			result = "7";
			return result;
		}
		

		if(!installItem.getConstructBillStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_10)){
			result = "8";
			return result;
		}
		

		result = installSignService.saveInstallConstructBillSign(lat,lon,Integer.valueOf(constructBillId),signDistance,signAddress,worker,installItem);
		
		return result;
		
	}
	
}
