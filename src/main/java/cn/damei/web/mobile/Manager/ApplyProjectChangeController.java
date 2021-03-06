package cn.damei.web.mobile.Manager;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.ApplyProjectChangeEntity;
import cn.damei.entity.mobile.Manager.ProjectItem;
import cn.damei.entity.mobile.Manager.ProjectItemType;
import cn.damei.service.mobile.Manager.ApplyProjectChangeService;
import cn.damei.entity.mobile.Manager.BusinessPic;
import cn.damei.service.mobile.Manager.BusinessPicService;

@Controller
@RequestMapping(value = "${adminPath}/app/manager/changeManagement")
public class ApplyProjectChangeController {
	private static Logger logger = LoggerFactory.getLogger(ApplyProjectChangeController.class);
	@Autowired
	private ApplyProjectChangeService service;
	@Autowired
	private BusinessPicService businessPicService;

	@RequestMapping(value = "index")
	public String index() {

		return "mobile/modules/Manager/projectChangeManagment/change_manage";
	}


	@RequestMapping(value = "projectChangeList")
	public String projectChangeList(HttpServletRequest request, Model model) {
		Integer managerId = SessionUtils.getManagerSession(request).getId();

		if (null != managerId) {
			List<ApplyProjectChangeEntity> orderList = service.findOrderList(managerId);
			
			model.addAttribute("orderList", orderList);

		}

		return "mobile/modules/Manager/projectChangeManagment/change_apply";
	}


	@RequestMapping(value = "applyToChange")
	public String applyToChange(HttpServletRequest request, Model model, String orderId,String storeId) {
		getItemList(model, request);
		model.addAttribute("orderId", orderId);
		model.addAttribute("storeId", storeId);
		return "mobile/modules/Manager/projectChangeManagment/change_details";
	}


	@RequestMapping(value = "changeRecord")
	public String changeRecord(HttpServletRequest request, Model model, String orderId) {

		return "mobile/modules/Manager/projectChangeManagment/change_apply";
	}

	
	
	

	@RequestMapping(value = "findItemByTypeId")
	public @ResponseBody List<ProjectItem> findItemByTypeId(String typeId,String storeId,String addOrMinus ,Model model,HttpServletRequest request) {
		ProjectItem item = new ProjectItem();
		if("add".equals(addOrMinus)){
            item.setItemTypeId(Integer.parseInt(typeId));
			item.setProjectIntemMold("1");
			item.setStoreId(storeId);
			item.setGroupType("2");
			List<ProjectItem> list = service.findMinusInnerItemList(item);
			if(null!=list&&list.size()>0){

				
					Iterator<ProjectItem> iterator = list.iterator();

					while (iterator.hasNext()) {
						ProjectItem projectItem = iterator.next();

					
						if (0==projectItem.getCount()||null==projectItem.getCount()) {

							iterator.remove();

						}
					}

				

				return list;
				
			}else{
				
				return null;
			}
			
		}else if("outer".equals(addOrMinus)){

			item.setGroupType("2");
			item.setProjectIntemMold("2");
			item.setStoreId(storeId);
            item.setItemTypeId(Integer.parseInt(typeId));
			List<ProjectItem> list = service.findMinusInnerItemList(item);

			
			Iterator<ProjectItem> iterator = list.iterator();

			while (iterator.hasNext()) {
				ProjectItem projectItem = iterator.next();

			
				if (0==projectItem.getCount()||null==projectItem.getCount()) {

					iterator.remove();

				}
			}

			return list;
			
			
		}else if("inner".equals(addOrMinus)){

			item.setProjectIntemMold("2");
			item.setStoreId(storeId);
			item.setGroupType("1");
            item.setItemTypeId(Integer.parseInt(typeId));
			List<ProjectItem> list = service.findMinusInnerItemList(item);

			
			Iterator<ProjectItem> iterator = list.iterator();

			while (iterator.hasNext()) {
				ProjectItem projectItem = iterator.next();

			
				if (0==projectItem.getCount()||null==projectItem.getCount()) {

					iterator.remove();

				}
			}

			return list;
			
			
			
		}else{
			
			
			return null;
		}
		
		
		
	}
	
	
	
	
	
	


	public void getItemList(Model model, HttpServletRequest request) {

			List<ProjectItemType> list = service.findAllProjectTypes();
			
			model.addAttribute("list", list);

	}


	@RequestMapping(value = "saveItem")
	public @ResponseBody List<ProjectItem> saveItem(String[] itemId,String arr,String storeId,Model model,HttpServletRequest request) {

        LinkedList<ProjectItem> list = new LinkedList<ProjectItem>();

        if (null != itemId && itemId.length > 0) {
            for (int v = 0; v < itemId.length; v++) {

                ProjectItem itemDetail = service.findProjectItemDetailById(itemId[v],storeId);
            	list.add(itemDetail);
            }
        }
        if(arr != null){
			String[] split = arr.split(",");
			if (split.length > 0){
				for(int v = 0; v < list.size(); v++) {
					for (int i = 0; i < split.length; i++) {
						if(String.valueOf(list.get(v).getItemId()).equals(split[i])){
							list.remove(v);
						}
					}
				}
			}
		}


        return list;
	}



	@RequestMapping(value = "saveChangeForm")
	public @ResponseBody String saveChangeForm(String changeReason,String[] addOrMinusNum,String[] itemDetail, String[] itemId,
			String[] itemCount,String price[], String orderId,String[] photo,HttpServletRequest request) {


		if (null != itemId && itemId.length > 0) {

			logger.info("项目经理申请施工变更时,填入的申请原因为:" + changeReason);
			logger.info("项目经理申请施工变更时,选择了" + itemId.length + "个增减项");

			String isOK = service.saveChangeForm(changeReason,addOrMinusNum,itemDetail, itemId, itemCount,price, orderId,null,photo,request);
			return isOK;

		} else {

			return "0";
		}

	}


	@RequestMapping(value = "applyRecord")
	public String changeRecord(String storeId,String orderId, Model model) {

		if (null != orderId) {

			List<ApplyProjectChangeEntity> list = service.changeRecord(orderId);
			if (null != list && list.size() > 0) {
				Iterator<ApplyProjectChangeEntity> iterator = list.iterator();
				while (iterator.hasNext()) {
					ApplyProjectChangeEntity next = iterator.next();


					if (next.getStatus().equals("20") || next.getStatus().equals("30")
							|| next.getStatus().equals("40") ||next.getStatus().equals("100")){

						next.setStatusShiro("0");
						

					} else if (next.getStatus().equals("10")) {

						next.setStatusShiro("1");


					} else if (next.getStatus().equals("15") || next.getStatus().equals("25")
							|| next.getStatus().equals("35")) {

						next.setStatusShiro("2");

					}

				}

			} else {

				logger.warn("根据orderId+" + orderId + "查询的变更单申请记录集合为空, 请注意...时间为:" + new Date());
			}

			model.addAttribute("list", list);
            model.addAttribute("storeId", storeId);
			return "mobile/modules/Manager/projectChangeManagment/projectApplyRecord/change_record";

		} else {

			logger.warn("在查看申请记录时: orderId 为空+  :" + orderId);
			return "mobile/modules/Manager/login";
		}

	}


	@RequestMapping(value = "look")
	public String look(Model model, String projectChangeId) {
		if (null != projectChangeId) {

			ApplyProjectChangeEntity bill = service.findChangeBillDetailById(Integer.parseInt(projectChangeId));
			if(null==bill.getCheckManName()){
				bill.setCheckManName(service.findCustomerNameByprojectChangeId(Integer.parseInt(projectChangeId)));
				
			}
			List<ProjectItem> itemList = service.findChangeItemByChangeBillId(bill.getProjectChangeId());
			
			model.addAttribute("bill", bill);
			model.addAttribute("itemList", itemList);

			return "mobile/modules/Manager/projectChangeManagment/projectApplyRecord/change_detail";
		} else {

			logger.warn("在查看变更单详情时: projectChangeId 不合法,可能有js非法注入  执行返回登录页  projectChangeId 为:" + projectChangeId);
			return "mobile/modules/Manager/login";

		}
	}

	@RequestMapping(value = "modify")
	public String modify(String storeId,Model model, String projectChangeId,HttpServletRequest request) {
		if (null != projectChangeId) {
			getItemList(model,request);
			ApplyProjectChangeEntity bill = service.findChangeBillDetailById(Integer.parseInt(projectChangeId));
			if(null==bill.getCheckManName()){
				bill.setCheckManName(service.findCustomerNameByprojectChangeId(Integer.parseInt(projectChangeId)));
				
			}
			List<ProjectItem> itemList = service.findChangeItemByChangeBillId(bill.getProjectChangeId());
			
			model.addAttribute("bill", bill);
			model.addAttribute("itemList", itemList);
			model.addAttribute("orderId", bill.getOrderId());
            model.addAttribute("storeId", storeId);
			return "mobile/modules/Manager/projectChangeManagment/projectApplyRecord/change_modify";
		} else {

			logger.warn("在查看变更单详情时: projectChangeId 不合法,可能有js非法注入  执行返回登录页  projectChangeId 为:" + projectChangeId);
			return "mobile/modules/Manager/login";

		}

	}
	

	@RequestMapping(value="updateChangeForm")
	public @ResponseBody String  updateChangeForm(String changeReason, String[] addOrMinusNum,String[] itemDetail, String[] itemId,
			String[] itemCount, String[]price,String projectChangeId,String orderId,String[] photo,HttpServletRequest request){


		if (null != itemId && itemId.length > 0) {

			logger.info("项目经理更改了变更单: 变更单id:"+projectChangeId+",填入的申请原因为:" + changeReason);
			String isOK = service.saveChangeForm(changeReason,addOrMinusNum,itemDetail, itemId, itemCount, price,orderId,projectChangeId,photo,request);
			return isOK;

		} else {

			return "0";
		}
		
		
		
	
	}
	


	@RequestMapping(value = "reSubmit")
	public String reSubmit(String storeId,Model model, String projectChangeId,HttpServletRequest request) {
		if (null != projectChangeId ) {
			getItemList(model,request);
			ApplyProjectChangeEntity bill = service.findChangeBillDetailByIdAndStatus(Integer.parseInt(projectChangeId));
			if(null==bill.getCheckManName()){
				bill.setCheckManName(service.findCustomerNameByprojectChangeId(Integer.parseInt(projectChangeId)));
				
			}
				
			
			List<ProjectItem> itemList = service.findChangeItemByChangeBillId(bill.getProjectChangeId());
			
			model.addAttribute("bill", bill);
			model.addAttribute("itemList", itemList);
			model.addAttribute("orderId", bill.getOrderId());
            model.addAttribute("storeId", storeId);
			return "mobile/modules/Manager/projectChangeManagment/projectApplyRecord/check_again";
		} else {

			logger.warn("在查看变更单详情时: projectChangeId 不合法,可能有js非法注入  执行返回登录页  projectChangeId 为:" + projectChangeId);
			return "mobile/modules/Manager/login";

		}
	}
	
	


	@RequestMapping(value="reSubmitBill")
	public @ResponseBody String  reSubmitBill(String changeReason, String[] addOrMinusNum,String[] itemDetail, String[] itemId,
			String[] itemCount,String[]price, String projectChangeId,String orderId,String[] photo,HttpServletRequest request){


		if (null != itemId && itemId.length > 0) {

			logger.info("由于审核不通过    ,项目经理更改了变更单: 变更单id:"+projectChangeId+",填入的申请原因为:" + changeReason);
			String isOK = service.saveChangeForm(changeReason,addOrMinusNum,itemDetail, itemId, itemCount,price, orderId,projectChangeId,photo,request);
			return isOK;

		} else {

			return "0";
		}
		
		
		
	
	}
	

	@RequestMapping(value="querySignaturePic")
	public String querySignaturePic(String businessType, Integer businessID,Model model){
		List<BusinessPic> list = businessPicService.getByBusType(businessType,businessID);
		model.addAttribute("list", list);
		return "mobile/modules/Manager/projectChangeManagment/projectApplyRecord/upPicDetails";
	}
	

}
