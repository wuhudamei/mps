package cn.damei.web.modules;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.common.persistence.Page;
import cn.damei.entity.modules.BizMaterialsWallAndFloorByOrder;
import cn.damei.service.modules.BizMaterialsWallAndFloorByOrderService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 材料类按订单汇总查询墙地砖Controller
 * @author 王硕
 * @version 2017-10-26
 */
@Controller
@RequestMapping(value = "${adminPath}/bizMaterialSortedByOrder/materialWallAndFloorByOrder")
public class BizMaterialWallAndFloorByOrderController extends BaseController {

	@Autowired
	private BizMaterialsWallAndFloorByOrderService bizMaterialsWallAndFloorByOrderService;
	@ModelAttribute
	public BizMaterialsWallAndFloorByOrder get(@RequestParam(required=false) String id) {
		BizMaterialsWallAndFloorByOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			
		}
		if (entity == null){
			entity = new BizMaterialsWallAndFloorByOrder();
		}
		return entity;
	}
	//墙地砖列表页
	@RequestMapping(value = "/WallAndFloorList")
	public String listPage(BizMaterialsWallAndFloorByOrder bizMaterialsWallAndFloorByOrder, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizMaterialsWallAndFloorByOrder.getStoreId()){
			if(null!=user.getStoreId()){
				bizMaterialsWallAndFloorByOrder.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		List<Dict>listStatus=bizMaterialsWallAndFloorByOrderService.findPurchaseStatus();
		model.addAttribute("listStatus", listStatus);
		if(bizMaterialsWallAndFloorByOrder.getStatus()!=null){
			String[] status = bizMaterialsWallAndFloorByOrder.getStatus().toString().split(",");
			if(null!=status && status.length>0){
				bizMaterialsWallAndFloorByOrder.setPurchaseStatusList(Arrays.asList(status));
			}
		}else{
			bizMaterialsWallAndFloorByOrder.setStatus("90");
			bizMaterialsWallAndFloorByOrder.setPurchaseStatusList(Arrays.asList("90"));
		}
		Page<BizMaterialsWallAndFloorByOrder> page = bizMaterialsWallAndFloorByOrderService.findPage(new Page<BizMaterialsWallAndFloorByOrder>(request, response), bizMaterialsWallAndFloorByOrder); 
		model.addAttribute("page", page);
		return "modules/mateialSortedByOrder/bizPurchaseWallAndFloorList";
		
	}
	
	//墙地砖详情页
	@RequestMapping(value = "/detail")
	public String detail(BizMaterialsWallAndFloorByOrder bizMaterialsWallAndFloorByOrder, HttpServletRequest request, HttpServletResponse response, Model model,String key){
		//查询基本信息
		BizMaterialsWallAndFloorByOrder list=bizMaterialsWallAndFloorByOrderService.findByOrderId(bizMaterialsWallAndFloorByOrder.getOrderId());
		model.addAttribute("bizMaterialsWallAndFloorByOrder",list);
		//查询墙地砖合计信息
		List<BizMaterialsWallAndFloorByOrder> findHejiByOrderId = bizMaterialsWallAndFloorByOrderService.findHejiByOrderId(bizMaterialsWallAndFloorByOrder.getOrderId()); 
		model.addAttribute("findHejiByOrderId", findHejiByOrderId); 
		//查询墙地砖明细信息
		List<BizMaterialsWallAndFloorByOrder> findMingxiByOrderId = bizMaterialsWallAndFloorByOrderService.findMingxiByOrderId(bizMaterialsWallAndFloorByOrder.getOrderId()); 
		model.addAttribute("findMingxiByOrderId", findMingxiByOrderId); 
		return "modules/mateialSortedByOrder/bizPurchaseWallAndFloorDetails";
		
	}

}