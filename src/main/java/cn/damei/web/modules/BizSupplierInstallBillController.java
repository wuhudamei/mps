
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

		if(null==bizSupplierInstallBill.getStoreId()){
			if(null!=user.getStoreId()){
				bizSupplierInstallBill.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		

		List<EnginInstallSupplier> supplierList = bizSupplierInstallBillService.findSupplierList(user.getEmployeePhone());
		
		List<Integer> supplierIdList = new ArrayList<Integer>();
		
		if(null == bizSupplierInstallBill.getSupplierId()){
			if(StringUtils.isNotBlank(user.getEmployeePhone()) && !user.getEmployeePhone().equals("")){

				for(EnginInstallSupplier a:supplierList){
					supplierIdList.add(a.getSupplierId());
				}
				bizSupplierInstallBill.setSupplierIdList(supplierIdList);
			}
		}
		
		

		bizSupplierInstallBill.setStatus(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_10
				+","+ InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_15);
		
		model.addAttribute("supplierList", supplierList);
		
		return "modules/bizsupplierinstallbill/bizSupplierInstallBillList";
	}
	
	
	@RequiresPermissions("bizsupplierinstallbill:bizSupplierInstallBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizSupplierInstallBill bizSupplierInstallBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		

		if(bizSupplierInstallBill.getStatus()!=null){
			String[] status = bizSupplierInstallBill.getStatus().split(",");
			if(null!=status && status.length>0){
				bizSupplierInstallBill.setInstallBillStatusList(Arrays.asList(status));
			}
		}		
		User user = UserUtils.getUser();

		if(null==bizSupplierInstallBill.getStoreId()){
			if(null!=user.getStoreId()){
				bizSupplierInstallBill.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		
		

		List<EnginInstallSupplier> supplierList = bizSupplierInstallBillService.findSupplierList(user.getEmployeePhone());
		
		List<Integer> supplierIdList = new ArrayList<Integer>();
		
		if(null == bizSupplierInstallBill.getSupplierId()){
			if(StringUtils.isNotBlank(user.getEmployeePhone()) && !user.getEmployeePhone().equals("")){

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
	
	

	@RequestMapping(value="findProjectInstallItemList")
    public @ResponseBody  List<BizProjectInstallItem> findProjectInstallItemList(String supplierId){
		
		List<BizProjectInstallItem> list = null;
		if(StringUtils.isNotBlank(supplierId)){
			
			list = bizSupplierInstallBillService.findProjectInstallItemList(Integer.valueOf(supplierId));
			
		}
		
		return list;
    }
	

	@RequestMapping(value="findInstallWorkerList")
	public @ResponseBody  List<SupplierInstallWorker> findInstallWorkerList(String installBillId,String workerName){
		
		List<SupplierInstallWorker> list = null;
		if(StringUtils.isNotBlank(installBillId)){
			
			list = bizSupplierInstallBillService.findInstallWorkerList(Integer.valueOf(installBillId),workerName);
			
		}
		
		return list;
	}
	

	@RequestMapping(value="findEmployeeGroupMessage")
	public @ResponseBody  BizSupplierInstallConstructBill findEmployeeGroupMessage(String installConstructBillId){
		
		BizSupplierInstallConstructBill bizSupplierInstallConstructBill = null;
		if(StringUtils.isNotBlank(installConstructBillId)){
			
			bizSupplierInstallConstructBill = bizSupplierInstallBillService.findEmployeeGroupMessage(Integer.valueOf(installConstructBillId));
			
		}
		
		return bizSupplierInstallConstructBill;
	}



	@RequestMapping(value="update_supplier_confirm_date_ajax")
	public @ResponseBody String updateSupplierConfirmDateAjax(String supplierConfirmIntoDate,String supplierConfirmCompleteDate,String installBillId){
		
		String result = "0";
		

		if(StringUtils.isBlank(installBillId)){
			result= "1";
			return result;
		}

		if(StringUtils.isBlank(supplierConfirmIntoDate)){
			result= "2";
			return result;
		}

		if(StringUtils.isBlank(supplierConfirmCompleteDate)){
			result= "3";
			return result;
		}

		User user = UserUtils.getUser();
		Integer empId = null;
		if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
			empId = Integer.valueOf(user.getEmpId());
		}

		BizSupplierInstallBill bizSupplierInstallBill = bizSupplierInstallBillService.get(Integer.valueOf(installBillId));
		if(!(bizSupplierInstallBill.getStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_10))){
			result= "5";
			return result;
		}

		boolean flag = bizSupplierInstallBillService.updateSupplierConfirmDate(bizSupplierInstallBill,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_15,supplierConfirmIntoDate,supplierConfirmCompleteDate);
		if(!flag){
			result = "6";
			return result;
		}

		Integer BillLogId = bizSupplierInstallBillService.saveBusinessStatusLog(empId,Integer.valueOf(installBillId),BusinessLogConstantUtil.BUSINESS_TYPE_9011,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_15,InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_NAME_15,null);
		if(null==BillLogId || BillLogId<1){
			result = "7";
			return result;  
		}
		
		return result;
	}


	@RequestMapping(value="update_supplier_distribution_workers_ajax")
	public @ResponseBody String updateSupplierDistributionWorkersAjax(String supplierConfirmIntoDate,String supplierConfirmCompleteDate,String installBillId,String employeegroupId){
		
		String result = "0";
		

		if(StringUtils.isBlank(installBillId)){
			result= "1";
			return result;
		}

		BizSupplierInstallBill bizSupplierInstallBill = bizSupplierInstallBillService.findInstallBillDetails(Integer.valueOf(installBillId));
		if(!((bizSupplierInstallBill.getStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_10))||(bizSupplierInstallBill.getStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_15))||(bizSupplierInstallBill.getStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_20))||(bizSupplierInstallBill.getStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_30))||(bizSupplierInstallBill.getStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_40)))){
			result= "2";
			return result;
		}
		
		if(bizSupplierInstallBill.getStatus().equals(InstallPlanConstantUtil.SUPPLIER_INSTALL_BILL_STATUS_10)){

			if(StringUtils.isBlank(supplierConfirmIntoDate)){
				result= "3";
				return result;
			}

			if(StringUtils.isBlank(supplierConfirmCompleteDate)){
				result= "4";
				return result;
			}
		}
		

		if(StringUtils.isBlank(employeegroupId)){
			result= "5";
			return result;
		}
		

		if(Integer.valueOf(employeegroupId).equals(bizSupplierInstallBill.getEmployeeGroupId())){
			result= "6";
			return result;
		}
		

		User user = UserUtils.getUser();
		Integer empId = null;
		if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
			empId = Integer.valueOf(user.getEmpId());
		}
		

		result = bizSupplierInstallBillService.updateSupplierDistributionWorkers(bizSupplierInstallBill,Integer.valueOf(installBillId),supplierConfirmIntoDate,supplierConfirmCompleteDate,Integer.valueOf(employeegroupId),empId);
		
		
		
		return result;
	}
	
	
	


}