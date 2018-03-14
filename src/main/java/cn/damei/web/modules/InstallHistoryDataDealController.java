package cn.damei.web.modules;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.InstallPlanConstantUtil;
import cn.damei.common.web.BaseController;
import cn.damei.entity.mobile.Inspector.BizQcBill;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.InstallHistoryData;
import cn.damei.service.modules.InstallHistoryDataDealService;


@Controller
@RequestMapping(value = "${adminPath}/installHistoryDateDeal/installHistoryDateDeal")
public class InstallHistoryDataDealController extends BaseController {

    @Autowired
    private InstallHistoryDataDealService installHistoryDataDealService;



    @RequiresPermissions("installHistoryDateDeal:installHistoryDateDeal:view")
    @RequestMapping(value = { "preList", "" })
    public String preList(InstallHistoryData installHistoryData, HttpServletRequest request, HttpServletResponse response, Model model) {

    	return "modules/enginInstallNew/historyData/historyDataList";
    }

    @RequiresPermissions("installHistoryDateDeal:installHistoryDateDeal:view")
    @RequestMapping(value = { "list", "" })
    public String list(InstallHistoryData installHistoryData, HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	List<InstallHistoryData> list = installHistoryDataDealService.findList(installHistoryData);
    	model.addAttribute("list", list);
    	
    	
    	return "modules/enginInstallNew/historyData/historyDataList";
    }
    
  

	@RequestMapping(value = "deleteRepeatedData")
	public String deleteRepeatedData(InstallHistoryData installHistoryData, Model model, RedirectAttributes redirectAttributes) {
		
		installHistoryDataDealService.deleteRepeatedData();
		addMessage(redirectAttributes, "批量删除重复数据 ");
		return "redirect:"+Global.getAdminPath()+"/installHistoryDateDeal/installHistoryDateDeal/list";
	}
	

	@RequestMapping(value = "updateData")
	public String updateData(InstallHistoryData installHistoryData, Model model, RedirectAttributes redirectAttributes) {
		
		installHistoryDataDealService.updateApplyData();
		installHistoryDataDealService.updateSupplierData();
		
		addMessage(redirectAttributes, "批量更新成功 ");
		return "redirect:"+Global.getAdminPath()+"/installHistoryDateDeal/installHistoryDateDeal/list";
	}
	
	

	@RequestMapping(value = "insertApplyData")
	public String insertApplyData(InstallHistoryData installHistoryData, Model model, RedirectAttributes redirectAttributes) {
		

		List<InstallHistoryData> applyList = installHistoryDataDealService.findApplyList();
		
		List<BizBusinessStatusLog> list = new ArrayList<BizBusinessStatusLog>();
		
		if(CollectionUtils.isNotEmpty(applyList)){
			for(InstallHistoryData apply:applyList){
				
				BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();

				bizBusinessStatusLog.setBusinessOnlyMarkInt(apply.getId());

				bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_901);

				bizBusinessStatusLog.setBusinessStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_2);

				bizBusinessStatusLog.setBusinessRemarks(apply.getRemarks());

				bizBusinessStatusLog.setStatusDatetime(apply.getApplyIntoCreateDatetime());

				bizBusinessStatusLog.setBusinessEmployeeId(apply.getItemManagerId());
				
				bizBusinessStatusLog.setRemarks(apply.getApplyIntoDateString());
				
				bizBusinessStatusLog.setCreateDate(apply.getApplyIntoCreateDatetime());
				
				bizBusinessStatusLog.setUpdateDate(apply.getApplyIntoCreateDatetime());
				
				bizBusinessStatusLog.setDelFlag("0");
				
				list.add(bizBusinessStatusLog);
				
			}
		}
		
		

		if(CollectionUtils.isNotEmpty(list)){
        	for(int i=0; i<list.size();i+=100){

        		List<BizBusinessStatusLog> mixInsertList = null;
        		if((i+100)<list.size()){
        			mixInsertList = list.subList(i, i+100);
        		}else{
        			mixInsertList = list.subList(i, list.size());
        		}
        	

        		boolean flag = false;
        		if(CollectionUtils.isNotEmpty(mixInsertList)){
        			flag = installHistoryDataDealService.batchInsertList(mixInsertList);
        		}
        		

        		if(flag){
        			logger.debug("第"+i+"到"+(i+99)+"插入成功");
        		}else{
        			logger.error("第"+i+"到"+(i+99)+"插入失败");
        		}
        	}
		}
		
		addMessage(redirectAttributes, "批量插入申请日志成功 ");
		return "redirect:"+Global.getAdminPath()+"/installHistoryDateDeal/installHistoryDateDeal/list";
	}


	@RequestMapping(value = "insertSupplierData")
	public String insertSupplierData(InstallHistoryData installHistoryData, Model model, RedirectAttributes redirectAttributes) {
		

		List<InstallHistoryData> supplierList = installHistoryDataDealService.findSupplierList();
				
		List<BizBusinessStatusLog> list = new ArrayList<BizBusinessStatusLog>();
		
		if(CollectionUtils.isNotEmpty(supplierList)){
			for(InstallHistoryData apply:supplierList){
				
				BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();

				bizBusinessStatusLog.setBusinessOnlyMarkInt(apply.getId());

				bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_901);

				bizBusinessStatusLog.setBusinessStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3);

				bizBusinessStatusLog.setBusinessRemarks(apply.getSupplierConfirmRemarks());
				
				bizBusinessStatusLog.setRemarks(apply.getSupplierConfirmIntoDateString());
				
				
				bizBusinessStatusLog.setDelFlag("0");
				
				list.add(bizBusinessStatusLog);
				
			}
		}
		
		

		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0; i<list.size();i+=100){

				List<BizBusinessStatusLog> mixInsertList = null;
				if((i+100)<list.size()){
					mixInsertList = list.subList(i, i+100);
				}else{
					mixInsertList = list.subList(i, list.size());
				}
				

				boolean flag = false;
				if(CollectionUtils.isNotEmpty(mixInsertList)){
					flag = installHistoryDataDealService.batchInsertList(mixInsertList);
				}
				

				if(flag){
					logger.debug("第"+i+"到"+(i+99)+"插入成功");
				}else{
					logger.error("第"+i+"到"+(i+99)+"插入失败");
				}
			}
		}
		
		addMessage(redirectAttributes, "批量插入下达供应商日志成功 ");
		return "redirect:"+Global.getAdminPath()+"/installHistoryDateDeal/installHistoryDateDeal/list";
	}
	

	@RequestMapping(value = "insertAcceptData")
	public String insertAcceptData(InstallHistoryData installHistoryData, Model model, RedirectAttributes redirectAttributes) {
		
		

		List<InstallHistoryData> acceptList = installHistoryDataDealService.findAcceptList();
		
		List<BizBusinessStatusLog> list = new ArrayList<BizBusinessStatusLog>();
		
		if(CollectionUtils.isNotEmpty(acceptList)){
			for(InstallHistoryData apply:acceptList){
				
				BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();

				bizBusinessStatusLog.setBusinessOnlyMarkInt(apply.getId());

				bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_901);

				bizBusinessStatusLog.setBusinessStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_4);

				bizBusinessStatusLog.setBusinessRemarks("安装项已验收");

				bizBusinessStatusLog.setStatusDatetime(apply.getRealAcceptDate());

				bizBusinessStatusLog.setBusinessEmployeeId(apply.getItemManagerId());
				
				bizBusinessStatusLog.setCreateDate(apply.getRealAcceptDate());
				
				bizBusinessStatusLog.setUpdateDate(apply.getRealAcceptDate());
				
				bizBusinessStatusLog.setDelFlag("0");
				
				list.add(bizBusinessStatusLog);
				
			}
		}
		
		

		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0; i<list.size();i+=100){

				List<BizBusinessStatusLog> mixInsertList = null;
				if((i+100)<list.size()){
					mixInsertList = list.subList(i, i+100);
				}else{
					mixInsertList = list.subList(i, list.size());
				}
				

				boolean flag = false;
				if(CollectionUtils.isNotEmpty(mixInsertList)){
					flag = installHistoryDataDealService.batchInsertList(mixInsertList);
				}
				

				if(flag){
					logger.debug("第"+i+"到"+(i+99)+"插入成功");
				}else{
					logger.error("第"+i+"到"+(i+99)+"插入失败");
				}
			}
		}
		
		addMessage(redirectAttributes, "批量插入验收日志成功 ");
		return "redirect:"+Global.getAdminPath()+"/installHistoryDateDeal/installHistoryDateDeal/list";
	}
	


    @RequestMapping(value = { "acceptance_interface_history_data", "" })
    public String acceptanceInterfaceHistoryData(InstallHistoryData installHistoryData,HttpServletRequest request, HttpServletResponse response, Model model) {

    	List<BizQcBill> list = installHistoryDataDealService.findQcBillList();
    	if(CollectionUtils.isNotEmpty(list)){
    		for(BizQcBill bizQcBill:list){
    			installHistoryDataDealService.saveAnotherInfo(bizQcBill.getQcBillId(),request);
    		}
    	}
    	return "modules/enginInstallNew/historyData/historyDataList";
    }
    

    
    
}