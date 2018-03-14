/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.InstallPlanConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizSupplierInstallBill;
import cn.damei.entity.modules.SupplierInstallWorker;
import cn.damei.service.modules.BizSupplierInstallBillService;
import cn.damei.entity.modules.BizSupplierInstallConstructBill;
import cn.damei.entity.modules.EnginInstallSupplier;
import cn.damei.entity.modules.BizProjectInstallItem;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 供应商安装单表Controller
 * @author wyb
 * @version 2017-07-13
 */
@Controller
@RequestMapping(value = "${adminPath}/bizsupplierinstallbill/bizSupplierInstallBill")
public class BizSupplierInstallBillController extends BaseController {

	@Autowired
	private BizSupplierInstallBillService bizSupplierInstallBillService;
	
	@ModelAttribute
	public BizSupplierInstallBill get(@RequestParam(required=false) Integer id) {
		BizSupplierInstallBill entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizSupplierInstallBillService.get(id);
		}
		if (entity == null){
			entity = new BizSupplierInstallBill();
		}
		return entity;
	}
	
	@RequiresPermissions("bizsupplierinstallbill:bizSupplierInstallBill:view")
	@RequestMapping(value = {"preList", ""})
	public String preList(BizSupplierInstallBill bizSupplierInstallBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizSupplierInstallBill.getStoreId()){
			if(null!=user.getStoreId()){
				bizSupplierInstallBill.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		
		//供应商列表
		List<EnginInstallSupplier> supplierList = bizSupplierInstallBillService.findSupplierList(user.getEmployeePhone());
		
		List<Integer> supplierIdList = new ArrayList<Integer>();
		
		if(null == bizSupplierInstallBill.getSupplierId()){
			if(StringUtils.isNotBlank(user.getEmployeePhone()) && !user.getEmployeePhone().equals("")){
				//只加载自己
				for(EnginInstallSupplier a:supplierList){
					supplierIdList.add(a.getSupplierId());
				}
				bizSupplierInstallBill.setSupplierIdList(supplierIdList);
			}
		}
		
		
		//安装单状态
		bizSupplierInstallBill.setStatus(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_10
				+","+ InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_15);
		
		model.addAttribute("supplierList", supplierList);
		
		return "modules/bizsupplierinstallbill/bizSupplierInstallBillList";
	}
	
	
	@RequiresPermissions("bizsupplierinstallbill:bizSupplierInstallBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizSupplierInstallBill bizSupplierInstallBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		
		//安装项状态集合
		if(bizSupplierInstallBill.getStatus()!=null){
			String[] status = bizSupplierInstallBill.getStatus().split(",");
			if(null!=status && status.length>0){
				bizSupplierInstallBill.setInstallBillStatusList(Arrays.asList(status));
			}
		}		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizSupplierInstallBill.getStoreId()){
			if(null!=user.getStoreId()){
				bizSupplierInstallBill.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		
		
		//供应商列表
		List<EnginInstallSupplier> supplierList = bizSupplierInstallBillService.findSupplierList(user.getEmployeePhone());
		
		List<Integer> supplierIdList = new ArrayList<Integer>();
		
		if(null == bizSupplierInstallBill.getSupplierId()){
			if(StringUtils.isNotBlank(user.getEmployeePhone()) && !user.getEmployeePhone().equals("")){
				//只加载自己
				for(EnginInstallSupplier a:supplierList){
					supplierIdList.add(a.getSupplierId());
				}
				
				if(CollectionUtils.isNotEmpty(supplierIdList)){
					bizSupplierInstallBill.setSupplierIdList(supplierIdList);
				}
			}
		}
				
		Page<BizSupplierInstallBill> page = bizSupplierInstallBillService.findPage(new Page<BizSupplierInstallBill>(request, response), bizSupplierInstallBill); 
		model.addAttribute("page", page);
		model.addAttribute("supplierList", supplierList);
		
		return "modules/bizsupplierinstallbill/bizSupplierInstallBillList";
	}
	
	
	/**
	 * 根据供应商加载安装项列表
	 * @param supplierId
	 * @return
	 */
	@RequestMapping(value="findProjectInstallItemList")
    public @ResponseBody  List<BizProjectInstallItem> findProjectInstallItemList(String supplierId){
		
		List<BizProjectInstallItem> list = null;
		if(StringUtils.isNotBlank(supplierId)){
			
			list = bizSupplierInstallBillService.findProjectInstallItemList(Integer.valueOf(supplierId));
			
		}
		
		return list;
    }
	
	/**
	 * 查询工人组
	 * @param installBillId
	 * @param workerName
	 * @return
	 */
	@RequestMapping(value="findInstallWorkerList")
	public @ResponseBody  List<SupplierInstallWorker> findInstallWorkerList(String installBillId,String workerName){
		
		List<SupplierInstallWorker> list = null;
		if(StringUtils.isNotBlank(installBillId)){
			
			list = bizSupplierInstallBillService.findInstallWorkerList(Integer.valueOf(installBillId),workerName);
			
		}
		
		return list;
	}
	
	/**
	 * 查询工人组施工单信息
	 * @param installConstructBillId
	 * @return
	 */
	@RequestMapping(value="findEmployeeGroupMessage")
	public @ResponseBody  BizSupplierInstallConstructBill findEmployeeGroupMessage(String installConstructBillId){
		
		BizSupplierInstallConstructBill bizSupplierInstallConstructBill = null;
		if(StringUtils.isNotBlank(installConstructBillId)){
			
			bizSupplierInstallConstructBill = bizSupplierInstallBillService.findEmployeeGroupMessage(Integer.valueOf(installConstructBillId));
			
		}
		
		return bizSupplierInstallConstructBill;
	}


	/**
	 * 供应商确认工期
	 * @param supplierConfirmIntoDate
	 * @param supplierConfirmCompleteDate
	 * @param installBillId
	 * @return
	 */
	@RequestMapping(value="update_supplier_confirm_date_ajax")
	public @ResponseBody String updateSupplierConfirmDateAjax(String supplierConfirmIntoDate,String supplierConfirmCompleteDate,String installBillId){
		
		String result = "0";
		
		//1.安装单id为空
		if(StringUtils.isBlank(installBillId)){
			result= "1";
			return result;
		}
		//2.供应商确认进场日期为空
		if(StringUtils.isBlank(supplierConfirmIntoDate)){
			result= "2";
			return result;
		}
		//3.供应商确认完工日期为空
		if(StringUtils.isBlank(supplierConfirmCompleteDate)){
			result= "3";
			return result;
		}
		//4.当前登录人
		User user = UserUtils.getUser();
		Integer empId = null;
		if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
			empId = Integer.valueOf(user.getEmpId());
		}
		//5.查询安装单详情 只有安装单为10-已转给供应商 才可确认工期
		BizSupplierInstallBill bizSupplierInstallBill = bizSupplierInstallBillService.get(Integer.valueOf(installBillId));
		if(!(bizSupplierInstallBill.getStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_10))){
			result= "5";
			return result;
		}
		//6.更新安装单--确认工期
		boolean flag = bizSupplierInstallBillService.updateSupplierConfirmDate(bizSupplierInstallBill,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_15,supplierConfirmIntoDate,supplierConfirmCompleteDate);
		if(!flag){
			result = "6";
			return result;
		}
		//7.保存安装单日志
		Integer BillLogId = bizSupplierInstallBillService.saveBusinessStatusLog(empId,Integer.valueOf(installBillId),BusinessLogConstantUtil.BUSINESS_TYPE_9011,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_15,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_NAME_15,null);
		if(null==BillLogId || BillLogId<1){
			result = "7";
			return result;  
		}
		
		return result;
	}

	/**
	 * 供应商分配工人组
	 * @param supplierConfirmIntoDate
	 * @param supplierConfirmCompleteDate
	 * @param installBillId
	 * @param employeegroupId
	 * @return
	 */
	@RequestMapping(value="update_supplier_distribution_workers_ajax")
	public @ResponseBody String updateSupplierDistributionWorkersAjax(String supplierConfirmIntoDate,String supplierConfirmCompleteDate,String installBillId,String employeegroupId){
		
		String result = "0";
		
		//1.安装单id为空
		if(StringUtils.isBlank(installBillId)){
			result= "1";
			return result;
		}
		//2.查询安装单详情 只有安装单为10-已转给供应商 15-已确定工期 20 -已确定工人组  30-施工中  40-工人已申请完工    才可确认工期
		BizSupplierInstallBill bizSupplierInstallBill = bizSupplierInstallBillService.findInstallBillDetails(Integer.valueOf(installBillId));
		if(!((bizSupplierInstallBill.getStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_10))||(bizSupplierInstallBill.getStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_15))||(bizSupplierInstallBill.getStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_20))||(bizSupplierInstallBill.getStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_30))||(bizSupplierInstallBill.getStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_40)))){
			result= "2";
			return result;
		}
		
		if(bizSupplierInstallBill.getStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_10)){
			//3.供应商确认进场日期为空
			if(StringUtils.isBlank(supplierConfirmIntoDate)){
				result= "3";
				return result;
			}
			//4.供应商确认完工日期为空
			if(StringUtils.isBlank(supplierConfirmCompleteDate)){
				result= "4";
				return result;
			}
		}
		
		//5.工人组id
		if(StringUtils.isBlank(employeegroupId)){
			result= "5";
			return result;
		}
		
		//6.工人选择的工人组与现在的工人组一样
		if(Integer.valueOf(employeegroupId).equals(bizSupplierInstallBill.getEmployeeGroupId())){
			result= "6";
			return result;
		}
		
		//7.当前登录人
		User user = UserUtils.getUser();
		Integer empId = null;
		if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
			empId = Integer.valueOf(user.getEmpId());
		}
		
		//8.分配工人组
		result = bizSupplierInstallBillService.updateSupplierDistributionWorkers(bizSupplierInstallBill,Integer.valueOf(installBillId),supplierConfirmIntoDate,supplierConfirmCompleteDate,Integer.valueOf(employeegroupId),empId);
		
		
		
		return result;
	}
	
	
	


}