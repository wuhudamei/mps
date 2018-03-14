
package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;
import cn.damei.entity.modules.OrderTaskpackGenVo;
import cn.damei.service.modules.OrderTaskpackService;
import cn.damei.service.modules.OrderTaskpackServiceGenVo;
import cn.damei.entity.modules.OrderTaskpackageProcedure;
import cn.damei.service.modules.OrderTaskpackageProcedureService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping(value = "${adminPath}/ordertaskpackageprocedure/orderTaskpackageProcedure")
public class OrderTaskpackageProcedureController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(OrderTaskpackageProcedureController.class);
	
	@Autowired
	private OrderTaskpackageProcedureService orderTaskpackageProcedureService;
	
	@Autowired
	private OrderTaskpackService orderTaskpackService;
	
	@Autowired
	private OrderService2 orderService2;
	
	@Autowired
	private OrderTaskpackServiceGenVo orderTaskpackServiceGenVo;
	
	@ModelAttribute
	public OrderTaskpackageProcedure get(@RequestParam(required=false) String id) {
		OrderTaskpackageProcedure entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderTaskpackageProcedureService.get(id);
		}
		if (entity == null){
			entity = new OrderTaskpackageProcedure();
		}
		return entity;
	}
	
	@RequiresPermissions("ordertaskpackageprocedure:orderTaskpackageProcedure:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrderTaskpackageProcedure orderTaskpackageProcedure, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrderTaskpackageProcedure> page = orderTaskpackageProcedureService.findPage(new Page<OrderTaskpackageProcedure>(request, response), orderTaskpackageProcedure); 
		model.addAttribute("page", page);
		return "modules/ordertaskpackageprocedure/orderTaskpackageProcedureList";
	}

	@RequiresPermissions("ordertaskpackageprocedure:orderTaskpackageProcedure:view")
	@RequestMapping(value = "form")
	public String form(OrderTaskpackageProcedure orderTaskpackageProcedure, Model model) {
		model.addAttribute("orderTaskpackageProcedure", orderTaskpackageProcedure);
		return "modules/ordertaskpackageprocedure/orderTaskpackageProcedureForm";
	}

	@RequiresPermissions("ordertaskpackageprocedure:orderTaskpackageProcedure:edit")
	@RequestMapping(value = "save")
	public String save(OrderTaskpackageProcedure orderTaskpackageProcedure, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orderTaskpackageProcedure)){
			return form(orderTaskpackageProcedure, model);
		}
		orderTaskpackageProcedureService.save(orderTaskpackageProcedure);
		addMessage(redirectAttributes, "保存订单任务包工序成功");
		return "redirect:"+Global.getAdminPath()+"/ordertaskpackageprocedure/orderTaskpackageProcedure/?repage";
	}
	
	@RequiresPermissions("ordertaskpackageprocedure:orderTaskpackageProcedure:edit")
	@RequestMapping(value = "delete")
	public String delete(OrderTaskpackageProcedure orderTaskpackageProcedure, RedirectAttributes redirectAttributes) {
		orderTaskpackageProcedureService.delete(orderTaskpackageProcedure);
		addMessage(redirectAttributes, "删除订单任务包工序成功");
		return "redirect:"+Global.getAdminPath()+"/ordertaskpackageprocedure/orderTaskpackageProcedure/?repage";
	}
	

	@ResponseBody
	@RequestMapping(value = "insertProcedure")
	public String insertProcedure(String procedureName, String procedureNo,String taskPackageTemplatId,String orderId, 
			String taskPackageTemplatNo,String taskpackageId){
		boolean flag = false;
		String result = "0";
		logger.info("procedureName="+procedureName+"\t\t procedureNo"+procedureNo+"\t"
				+ "\t taskPackageTemplatId="+taskPackageTemplatId);
		Order2 order = null;
		OrderTaskpackGenVo orderTaskpackGenVo = null;
		
		if(orderId != null){
			order = orderService2.get(Integer.valueOf(orderId));
			logger.info("当前订单对象的库户姓名："+order.getCustomerName());
			
			orderTaskpackGenVo = orderTaskpackServiceGenVo.getByOrderAndEffectiveDate(
					order.getStoreId(),order.getStoreId(),order.getContractStartDate(),procedureNo,taskPackageTemplatNo,order.getProjectMode());
			
		}
		
		if(null != orderTaskpackGenVo){
			flag = orderTaskpackageProcedureService.insertProcedure(orderTaskpackGenVo,taskpackageId);
			logger.info("当前订单对象的库户姓名："+orderTaskpackGenVo.getProcedureNo());
		}else{
			return result = "2";
		}
		
		if(!flag){
			return result = "1";
		}
		
		return result;
	}
	

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="updateByAuditProcedure")
	public String updateByAuditProcedure(HttpServletRequest request){
		String jsonVal = request.getParameter("jsonVal");
		String taskpackageID = request.getParameter("taskpackageID");
		String viewFlag = request.getParameter("viewFlag");
		boolean flag = false;
		Map<String,String> map = new HashMap<String,String>();
		List<Double> list = new ArrayList<Double>();
		List<Double> laborList = new ArrayList<Double>();
		List<Double> auxiliaryList = new ArrayList<Double>();
		String result = "0";
		logger.info("获取任务包编号："+taskpackageID);
		if(jsonVal != null){
			JSONObject json = JSONObject.fromObject(jsonVal.toString());
			

			Iterator iter = json.keySet().iterator();  
			while (iter.hasNext()) {    
	            String key = (String) iter.next();    
	            String value = json.getString(key);
	            System.out.println("key:"+key+",value:"+value);  
	            map.put(key, value);
	        }
		}
		
		String root = map.get("json");
		JSONArray jsonArray =JSONArray.fromObject(root);

		Double budgetNumber = Double.valueOf(0);
		Double total = Double.valueOf(0);
		String procedureID = "";
		String remarks="";
		Double laborBudgetAmount = Double.valueOf(0);
		Double auxiliaryMaterialsBudgetAmount = Double.valueOf(0);
		for (int i = 0; i < jsonArray.size(); i++) {

			if(((String) jsonArray.getJSONObject(i).get("budgetNumber")).equals("")){
				budgetNumber = Double.valueOf(0);
			}else{
				budgetNumber = Double.valueOf((String) jsonArray.getJSONObject(i).get("budgetNumber"));
			}
			total = Double.valueOf((String) jsonArray.getJSONObject(i).get("total"));
			procedureID = (String) jsonArray.getJSONObject(i).get("procedureID");
			remarks=(String) jsonArray.getJSONObject(i).get("remarks");

			if (((String) jsonArray.getJSONObject(i).get("laborBudgetAmount")).equals("")) {
				laborBudgetAmount = Double.valueOf(0);
			} else {
				laborBudgetAmount = Double.valueOf((String) jsonArray.getJSONObject(i).get("laborBudgetAmount"));
			}

			if (((String) jsonArray.getJSONObject(i).get("auxiliaryMaterialsBudgetAmount")).equals("")) {
				auxiliaryMaterialsBudgetAmount = Double.valueOf(0);
			} else {
				auxiliaryMaterialsBudgetAmount = Double.valueOf((String) jsonArray.getJSONObject(i).get("auxiliaryMaterialsBudgetAmount"));
			}
			list.add(total);
			laborList.add(laborBudgetAmount);
			auxiliaryList.add(auxiliaryMaterialsBudgetAmount);
			flag = orderTaskpackageProcedureService.updateById(budgetNumber,total,procedureID,remarks,laborBudgetAmount,auxiliaryMaterialsBudgetAmount);
		}
		

		Double sum = 0d;
		for(Double d:list){
			sum += d;
		}
		logger.info("该任务包下面的所有工序的总价："+sum);
		
		Double laborSum = 0d;
		for (Double d : laborList) {
			laborSum += d;
		}
		Double auxiliarySum = 0d;
		for (Double d : auxiliaryList) {
			auxiliarySum += d;
		}
		

		if(!flag){
			result = "1";
		}else{
			boolean updateTotalFlag = orderTaskpackService.updateTotal(sum,taskpackageID, laborSum, auxiliarySum);
			if(!updateTotalFlag){
				return result = "2";
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		jsonObject.put("flag", viewFlag);
		return jsonObject.toString();
	}
}