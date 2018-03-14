package cn.damei.web.mobile.Worker;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.InstallPlanConstantUtil;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.mobile.Worker.InstallItem;
import cn.damei.service.mobile.Worker.InstallItemListService;
import cn.damei.entity.mobile.Worker.Worker;

@Controller
@RequestMapping(value="${adminPath}/app/worker/install/installItemList")
public class InstallItemListController {
	
	@Autowired
	private InstallItemListService installItemListService;

	
	
	/**
	 * 安装项列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(){
		
		return "mobile/modules/Worker/installer/installItemList/installList";
	}
	
	/**
	 * 动态加载施工单列表
	 * @param type
	 * @return
	 */
	@RequestMapping(value="install_construct_bill_ajax_list")
    public @ResponseBody  List<InstallItem> installConstructBillAjaxList(String type,HttpServletRequest request){

		//1.已登录的安装工信息
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		
		//2.判断施工单状态
		List<String> statusList = new ArrayList<String>();
		if(StringUtils.isNotBlank(type)){
			if(type.equals("1")){
				//未完工
				statusList.add(InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_10);
				statusList.add(InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_20);
			}else{
				//已完工
				statusList.add(InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_30);
				statusList.add(InstallPlanConstantUtil.SUPPLIER_INSTALL_CONSTRUCT_BILL_STATUS_50);
			}
		}
		
		//3.查询施工单列表
		List<InstallItem> list = null;
		if(worker != null && null!=worker.getEmgrouprelationId()){
			
			InstallItem installItem = new InstallItem();
			installItem.setConstructBillStatusList(statusList);
			installItem.setEmployeeGroupId(worker.getEmgrouprelationId());
			
			list = installItemListService.findInstallConstructBillList(installItem);
		}

       return list;

    }
	
}