package cn.damei.web.modules;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.DataAuthorityConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.service.modules.BizProjectChangeBillService;
import cn.damei.service.modules.DataAuthorityService;
import cn.damei.entity.modules.ItemManagerMap;
import cn.damei.entity.modules.SelectOrder;
import cn.damei.service.modules.SelectOrderService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/selectOrder/selectOrder")
public class SelectOrderController extends BaseController {

	@Autowired
	private SelectOrderService selectOrderService;

	@Autowired
	private DataAuthorityService dataAuthorityService;
	
	@Autowired
	private BizProjectChangeBillService bizProjectChangeBillService;
	@ModelAttribute
	public SelectOrder get(@RequestParam(required = false) Integer id) {
		SelectOrder entity = null;
		if (StringUtils.isNotBlank(id+"")) {
			entity = selectOrderService.get(id);
		}
		if (entity == null) {
			entity = new SelectOrder();
		}
		return entity;
	}

	@RequiresPermissions("selectOrder:selectOrder:view")
	@RequestMapping(value = { "list", "" })
	public String list(SelectOrder selectOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();



		if(StringUtils.isBlank(selectOrder.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				selectOrder.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				selectOrder.setProjectMode(user.getProjectMode());
			}
		}
		
		return "modules/selectorder/selectOrder";
	}
	@RequiresPermissions("selectOrder:selectOrder:view")
	@RequestMapping(value = { "selectOrderList", "" })
	public String selectOrderList(SelectOrder selectOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String houseisnow =selectOrder.getHouseIsNew();
		if(null!=houseisnow && houseisnow!="" && houseisnow.equals("2")){
			selectOrder.setHouseIsNew("");
		}
		if(selectOrder.getOrderStatusNumber()!=null){
			String[] status = selectOrder.getOrderStatusNumber().split(",");
			if(null!=status && status.length>0){
				selectOrder.setOrderStatusList(Arrays.asList(status));
			}
		}
		
		User user = UserUtils.getUser();
		List<String> orderdPhones = dataAuthorityService.orderdPhones(DataAuthorityConstantUtils.Biz_Business_Type_DD);
		selectOrder.setPhones(orderdPhones);
		
		String userId = user.getId();
		selectOrder.setUserId(userId);
		
	
		

		if(StringUtils.isBlank(selectOrder.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				selectOrder.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				selectOrder.setProjectMode(user.getProjectMode());
			}
		}
		
		
		Page<SelectOrder> page = selectOrderService.findPage(new Page<SelectOrder>(request, response), selectOrder);
		model.addAttribute("page", page);
		return "modules/selectorder/selectOrder";
	}
	@RequiresPermissions("selectOrder:selectOrder:view")
	@RequestMapping(value = { "maplist", "" })
	public String maplist(SelectOrder selectOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==selectOrder.getStoreId()){
			if(null!=user.getStoreId()){
				selectOrder.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(selectOrder.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				selectOrder.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				selectOrder.setProjectMode(user.getProjectMode());
			}
		}
		
		return "modules/selectorder/mapSelectOrder";
	}
	@RequiresPermissions("selectOrder:selectOrder:view")
	@RequestMapping(value = { "maplist2", "" })
	public String maplist2(SelectOrder selectOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==selectOrder.getStoreId()){
			if(null!=user.getStoreId()){
				selectOrder.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(selectOrder.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				selectOrder.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				selectOrder.setProjectMode(user.getProjectMode());
			}
		}
		
		return "modules/selectorder/mapSelectOrder2";
	}
	@RequiresPermissions("selectOrder:selectOrder:view")
	@RequestMapping(value = { "mapselectOrderList", "" })
	public @ResponseBody List<SelectOrder> mapselectOrderList(SelectOrder selectOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		
		User user = UserUtils.getUser();

		if(null==selectOrder.getStoreId()){
			if(null!=user.getStoreId()){
				selectOrder.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(selectOrder.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				selectOrder.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				selectOrder.setProjectMode(user.getProjectMode());
			}
		}
		
		
		List<SelectOrder> list = selectOrderService.findListMap(selectOrder);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("date", new SimpleDateFormat("yyyy-MM").format(new Date()));
		map.put("list", list);
		List<ItemManagerMap> list2 = selectOrderService.findManagerMoreCount(map);
		List<ItemManagerMap> list3 = selectOrderService.findManagerMoreCount1(list);
		if(list.size()>0&&list2.size()>0){
			for (SelectOrder SelectOrder : list) {
				
				for (ItemManagerMap itemManagerMap2 : list2) {
					
						if(itemManagerMap2.getId().equals(SelectOrder.getItemManagerId())){
							
							SelectOrder.setAlreadyDistributeCount(itemManagerMap2.getAlreadyDistributeCount());
							
							break;
						}
					
					
				}
			}
			
			
			
		}
		
		if(list.size()>0&&list3.size()>0){
			for (SelectOrder SelectOrder : list) {
				
				for (ItemManagerMap itemManagerMap2 : list3) {
					
						if(itemManagerMap2.getId().equals(SelectOrder.getItemManagerId())){
							
							SelectOrder.setDoNow(itemManagerMap2.getDoNow());
							
							break;
						}
					
					
				}
			}
			
			
			
		}
			
		return list;
	}


	@RequestMapping(value="exportExcel")
	public void exportExcel(SelectOrder selectOrder, HttpServletResponse response) throws Exception{
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		HSSFWorkbook excel = selectOrderService.exportExcel(selectOrder);
		ServletOutputStream out= null;
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr =new String(("订单信息-"+sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");
			out = response.getOutputStream();
			excel.write(out);
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if(out!=null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}