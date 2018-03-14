
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.OrderReportRelatedContract;
import cn.damei.service.modules.OrderReportRelatedContractService;

import java.util.List;


@Controller
@RequestMapping(value = "${adminPath}/orderreportrelatedcontract/orderReportRelatedContract")
public class OrderReportRelatedContractController extends BaseController {

	@Autowired
	private OrderReportRelatedContractService orderReportRelatedContractService;
	
	@ModelAttribute
	public OrderReportRelatedContract get(@RequestParam(required=false) String id) {
		OrderReportRelatedContract entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderReportRelatedContractService.get(id);
		}
		if (entity == null){
			entity = new OrderReportRelatedContract();
		}
		return entity;
	}
	
	@RequiresPermissions("orderreportrelatedcontract:orderReportRelatedContract:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrderReportRelatedContract orderReportRelatedContract, HttpServletRequest request, HttpServletResponse response, Model model) {

        User user = UserUtils.getUser();
        if(null==orderReportRelatedContract.getStoreId()){
            if(null!=user.getStoreId()){
                orderReportRelatedContract.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
		Page<OrderReportRelatedContract> page = orderReportRelatedContractService.findPage(new Page<OrderReportRelatedContract>(request, response), orderReportRelatedContract); 
		model.addAttribute("page", page);
		return "modules/orderreportrelatedcontract/orderReportRelatedContractList";
	}



	@RequestMapping(value = {"findRelatedOrderInfoByReportId"})
	public @ResponseBody
	 List<OrderReportRelatedContract> findOrderInfoByReportId(OrderReportRelatedContract orderReportRelatedContract, HttpServletRequest request, HttpServletResponse response, Model model) {


		List<OrderReportRelatedContract> list=orderReportRelatedContractService.findOrderInfoByReportId(orderReportRelatedContract.getId());
		return list;
	}


	@RequestMapping(value = {"deleteRelatedInfoByOrderId"})
	public @ResponseBody
	String deleteRelatedInfoByOrderId(OrderReportRelatedContract orderReportRelatedContract, HttpServletRequest request, HttpServletResponse response, Model model) {
	String result="0";

				try{

					orderReportRelatedContractService.deleteRelatedInfoByOrderId2(orderReportRelatedContract);
		}catch(Exception e){

			e.printStackTrace();
			result="1";
		}

		return result;
	}


	@RequestMapping(value ="updateOrderReportStatusById")
	public @ResponseBody
	String updateOrderReportStatusById(OrderReportRelatedContract orderReportRelatedContract,String[]orderIds,String []orderNums, HttpServletRequest request, HttpServletResponse response, Model model) {




		String result="0";

		try{

			orderReportRelatedContractService.updateOrderReportStatusById(orderReportRelatedContract.getId(),orderIds,orderNums);

		}catch(Exception e){

			e.printStackTrace();
			result="1";
		}

		return result;

	}



}