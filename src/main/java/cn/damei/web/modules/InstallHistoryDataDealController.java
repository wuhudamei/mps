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

/**
 * 主材安装历史数据处理Controller
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/installHistoryDateDeal/installHistoryDateDeal")
public class InstallHistoryDataDealController extends BaseController {

    @Autowired
    private InstallHistoryDataDealService installHistoryDataDealService;


    /**
     * 主材安装申请--历史数据处理
     * @param enginInstallNew
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("installHistoryDateDeal:installHistoryDateDeal:view")
    @RequestMapping(value = { "preList", "" })
    public String preList(InstallHistoryData installHistoryData, HttpServletRequest request, HttpServletResponse response, Model model) {

    	return "modules/enginInstallNew/historyData/historyDataList";
    }
    /**
     * 主材安装申请--历史数据处理
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("installHistoryDateDeal:installHistoryDateDeal:view")
    @RequestMapping(value = { "list", "" })
    public String list(InstallHistoryData installHistoryData, HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	List<InstallHistoryData> list = installHistoryDataDealService.findList(installHistoryData);
    	model.addAttribute("list", list);
    	
    	
    	return "modules/enginInstallNew/historyData/historyDataList";
    }
    
  
    /**
     * 批量删除重复数据 
     * @param installHistoryData
     * @param model
     * @param redirectAttributes
     * @return
     */
	@RequestMapping(value = "deleteRepeatedData")
	public String deleteRepeatedData(InstallHistoryData installHistoryData, Model model, RedirectAttributes redirectAttributes) {
		
		installHistoryDataDealService.deleteRepeatedData();
		addMessage(redirectAttributes, "批量删除重复数据 ");
		return "redirect:"+Global.getAdminPath()+"/installHistoryDateDeal/installHistoryDateDeal/list";
	}
	
	/**
	 * 批量更新数据 
	 * @param installHistoryData
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "updateData")
	public String updateData(InstallHistoryData installHistoryData, Model model, RedirectAttributes redirectAttributes) {
		
		installHistoryDataDealService.updateApplyData();
		installHistoryDataDealService.updateSupplierData();
		
		addMessage(redirectAttributes, "批量更新成功 ");
		return "redirect:"+Global.getAdminPath()+"/installHistoryDateDeal/installHistoryDateDeal/list";
	}
	
	
	/**
	 * 批量插入申请日志数据 
	 * @param installHistoryData
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "insertApplyData")
	public String insertApplyData(InstallHistoryData installHistoryData, Model model, RedirectAttributes redirectAttributes) {
		
		//申请
		List<InstallHistoryData> applyList = installHistoryDataDealService.findApplyList();
		
		List<BizBusinessStatusLog> list = new ArrayList<BizBusinessStatusLog>();
		
		if(CollectionUtils.isNotEmpty(applyList)){
			for(InstallHistoryData apply:applyList){
				
				BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
				//1.唯一标识
				bizBusinessStatusLog.setBusinessOnlyMarkInt(apply.getId());
				//2.业务类型
				bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_901);
				//3.业务状态
				bizBusinessStatusLog.setBusinessStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_2);
				//4.业务备注
				bizBusinessStatusLog.setBusinessRemarks(apply.getRemarks());
				//5.状态时间
				bizBusinessStatusLog.setStatusDatetime(apply.getApplyIntoCreateDatetime());
				//6.业务人员员工id
				bizBusinessStatusLog.setBusinessEmployeeId(apply.getItemManagerId());
				
				bizBusinessStatusLog.setRemarks(apply.getApplyIntoDateString());
				
				bizBusinessStatusLog.setCreateDate(apply.getApplyIntoCreateDatetime());
				
				bizBusinessStatusLog.setUpdateDate(apply.getApplyIntoCreateDatetime());
				
				bizBusinessStatusLog.setDelFlag("0");
				
				list.add(bizBusinessStatusLog);
				
			}
		}
		
		
		//批量插入
		if(CollectionUtils.isNotEmpty(list)){
        	for(int i=0; i<list.size();i+=100){
        		//从集合中取出100条数据
        		List<BizBusinessStatusLog> mixInsertList = null;
        		if((i+100)<list.size()){
        			mixInsertList = list.subList(i, i+100);
        		}else{
        			mixInsertList = list.subList(i, list.size());
        		}
        	
        		//批量插入100条数据
        		boolean flag = false;
        		if(CollectionUtils.isNotEmpty(mixInsertList)){
        			flag = installHistoryDataDealService.batchInsertList(mixInsertList);
        		}
        		
        		//插入是否成功
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

	/**
	 * 批量插入下达供应商日志数据 
	 * @param installHistoryData
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "insertSupplierData")
	public String insertSupplierData(InstallHistoryData installHistoryData, Model model, RedirectAttributes redirectAttributes) {
		
		//下达供应商
		List<InstallHistoryData> supplierList = installHistoryDataDealService.findSupplierList();
				
		List<BizBusinessStatusLog> list = new ArrayList<BizBusinessStatusLog>();
		
		if(CollectionUtils.isNotEmpty(supplierList)){
			for(InstallHistoryData apply:supplierList){
				
				BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
				//1.唯一标识
				bizBusinessStatusLog.setBusinessOnlyMarkInt(apply.getId());
				//2.业务类型
				bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_901);
				//3.业务状态
				bizBusinessStatusLog.setBusinessStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3);
				//4.业务备注
				bizBusinessStatusLog.setBusinessRemarks(apply.getSupplierConfirmRemarks());
				
				bizBusinessStatusLog.setRemarks(apply.getSupplierConfirmIntoDateString());
				
				
				bizBusinessStatusLog.setDelFlag("0");
				
				list.add(bizBusinessStatusLog);
				
			}
		}
		
		
		//批量插入
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0; i<list.size();i+=100){
				//从集合中取出100条数据
				List<BizBusinessStatusLog> mixInsertList = null;
				if((i+100)<list.size()){
					mixInsertList = list.subList(i, i+100);
				}else{
					mixInsertList = list.subList(i, list.size());
				}
				
				//批量插入100条数据
				boolean flag = false;
				if(CollectionUtils.isNotEmpty(mixInsertList)){
					flag = installHistoryDataDealService.batchInsertList(mixInsertList);
				}
				
				//插入是否成功
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
	
	/**
	 * 批量插入验收日志数据 
	 * @param installHistoryData
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "insertAcceptData")
	public String insertAcceptData(InstallHistoryData installHistoryData, Model model, RedirectAttributes redirectAttributes) {
		
		
		//验收
		List<InstallHistoryData> acceptList = installHistoryDataDealService.findAcceptList();
		
		List<BizBusinessStatusLog> list = new ArrayList<BizBusinessStatusLog>();
		
		if(CollectionUtils.isNotEmpty(acceptList)){
			for(InstallHistoryData apply:acceptList){
				
				BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
				//1.唯一标识
				bizBusinessStatusLog.setBusinessOnlyMarkInt(apply.getId());
				//2.业务类型
				bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_901);
				//3.业务状态
				bizBusinessStatusLog.setBusinessStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_4);
				//4.业务备注
				bizBusinessStatusLog.setBusinessRemarks("安装项已验收");
				//5.状态时间
				bizBusinessStatusLog.setStatusDatetime(apply.getRealAcceptDate());
				//6.业务人员员工id
				bizBusinessStatusLog.setBusinessEmployeeId(apply.getItemManagerId());
				
				bizBusinessStatusLog.setCreateDate(apply.getRealAcceptDate());
				
				bizBusinessStatusLog.setUpdateDate(apply.getRealAcceptDate());
				
				bizBusinessStatusLog.setDelFlag("0");
				
				list.add(bizBusinessStatusLog);
				
			}
		}
		
		
		//批量插入
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0; i<list.size();i+=100){
				//从集合中取出100条数据
				List<BizBusinessStatusLog> mixInsertList = null;
				if((i+100)<list.size()){
					mixInsertList = list.subList(i, i+100);
				}else{
					mixInsertList = list.subList(i, list.size());
				}
				
				//批量插入100条数据
				boolean flag = false;
				if(CollectionUtils.isNotEmpty(mixInsertList)){
					flag = installHistoryDataDealService.batchInsertList(mixInsertList);
				}
				
				//插入是否成功
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
	

	/**
     * 
     * 基装验收和竣工验收的接口推送历史数据
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = { "acceptance_interface_history_data", "" })
    public String acceptanceInterfaceHistoryData(InstallHistoryData installHistoryData,HttpServletRequest request, HttpServletResponse response, Model model) {
    	//查询约检单（基装验收和竣工验收）
    	List<BizQcBill> list = installHistoryDataDealService.findQcBillList();
    	if(CollectionUtils.isNotEmpty(list)){
    		for(BizQcBill bizQcBill:list){
    			installHistoryDataDealService.saveAnotherInfo(bizQcBill.getQcBillId(),request);
    		}
    	}
    	return "modules/enginInstallNew/historyData/historyDataList";
    }
    

    
    
}