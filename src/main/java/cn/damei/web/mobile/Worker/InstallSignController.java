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

	
	
	/**
	 * 安装项列表（签到）
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(){
		
		return "mobile/modules/Worker/installer/installSign/signList";
	}
	
	/**
	 * 动态加载施工单列表（签到）
	 * @param type
	 * @return
	 */
	@RequestMapping(value="install_construct_bill_sign_ajax_list")
    public @ResponseBody  List<InstallItem> installConstructBillSignAjaxList(String text,HttpServletRequest request){

		//1.已登录的安装工信息
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		
		//2.查询施工单列表(状态为10-已确定工人组）
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
	
	
	/**
	 * 施工单(签到)
	 * @param lat
	 * @param lon
	 * @param constructBillId
	 * @param signDistance
	 * @param signAddress
	 * @param request
	 * @return
	 */
	@RequestMapping(value="install_construct_bill_sign_submit")
	public @ResponseBody String installConstructBillSignSubmit(String lat,String lon,String constructBillId,String signDistance,String signAddress,HttpServletRequest request){
		
		String result = "0";
		
		//1.纬度为空
		if(StringUtils.isBlank(lat)){
			result = "1";
			return result;
		}
		//2.经度为空
		if(StringUtils.isBlank(lon)){
			result = "2";
			return result;
		}
		//3.施工单id为空
		if(StringUtils.isBlank(constructBillId)){
			result = "3";
			return result;
		}
		//4.签到距离为空
		if(StringUtils.isBlank(signDistance)){
			result = "4";
			return result;
		}
		//5.签到地址为空
		if(StringUtils.isBlank(signAddress)){
			result = "5";
			return result;
		}
		//6.已登录的安装工信息
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		if(null==worker){
			result = "6";
			return result;
		}
		
		//7.根据施工单id查询相关信息
		InstallItem installItem = installSignService.findInstallConstructBillMessage(Integer.valueOf(constructBillId));
		if(null == installItem){
			result = "7";
			return result;
		}
		
		//8.已经签到过了
		if(!installItem.getConstructBillStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_10)){
			result = "8";
			return result;
		}
		
		//9.保存签到信息
		result = installSignService.saveInstallConstructBillSign(lat,lon,Integer.valueOf(constructBillId),signDistance,signAddress,worker,installItem);
		
		return result;
		
	}
	
}
