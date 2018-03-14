
package cn.damei.web.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.QcBillConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.CheckDetails;
import cn.damei.service.modules.CheckDetailsService;
import cn.damei.entity.modules.BizOrderQcBill;
import cn.damei.entity.modules.BizQcBill;
import cn.damei.service.modules.BizOrderQcBillService;
import cn.damei.entity.modules.CheckNode;
import cn.damei.service.modules.CheckNodeService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/pqc/checkDetails/checkDetails")
public class CheckDetailsController extends BaseController {

	@Autowired
	private CheckDetailsService checkDetailsService;
	@Autowired
	private BizOrderQcBillService bizOrderQcBillService;
	@Autowired
	private CheckNodeService checkNodeService;

	@ModelAttribute
	public CheckDetails get(@RequestParam(required=false) String id) {
		CheckDetails entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = checkDetailsService.get(id);
		}
		if (entity == null){
			entity = new CheckDetails();
		}
		return entity;
	}
	
	@RequiresPermissions("checkDetails:checkDetails:view")
	@RequestMapping(value = {"list", ""})
	public String list(CheckDetails checkDetails, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==checkDetails.getStoreId()){
			if(null!=user.getStoreId()){
				checkDetails.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(checkDetails.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				checkDetails.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				checkDetails.setProjectMode(user.getProjectMode());
			}
		}
		
		return "modules/PQC/checkDetails/checkDetailsList";
	}
	@RequiresPermissions("CheckDetails:CheckDetails:view")
	@RequestMapping(value = {"checkDetailsList", ""})
	public String checkDetailsList(CheckDetails checkDetails, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==checkDetails.getStoreId()){
			if(null!=user.getStoreId()){
				checkDetails.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(checkDetails.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				checkDetails.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("gongcheng", true);
			}else{
				checkDetails.setProjectMode(user.getProjectMode());
			}
		}
		
		Page<CheckDetails> page = checkDetailsService.findPage(new Page<CheckDetails>(request, response), checkDetails); 
		model.addAttribute("page", page);
		return "modules/PQC/checkDetails/checkDetailsList";
	}
	

	@RequiresPermissions("CheckDetails:CheckDetails:view")
	@RequestMapping(value = {"detailsList", ""})
	public String detailsList(Integer orderId, HttpServletRequest request, HttpServletResponse response, Model model) {
		

		BizOrderQcBill bizOrderQcBill = bizOrderQcBillService.findOrder(orderId);

		List<BizQcBill> bizQcBill = checkDetailsService.detailsList(orderId);
		
		model.addAttribute("bizOrderQcBill", bizOrderQcBill);
		model.addAttribute("bizQcBill", bizQcBill);
		return "modules/PQC/checkDetails/detailsList";
	}
	

	@RequiresPermissions("CheckDetails:CheckDetails:view")
	@RequestMapping(value = {"detailsList2", ""})
	public String detailsList2(Integer orderId, HttpServletRequest request, HttpServletResponse response, Model model) {
		

		BizOrderQcBill bizOrderQcBill = bizOrderQcBillService.findOrder(orderId);

		List<BizQcBill> bizQcBill = checkDetailsService.detailsList(orderId);
		
		model.addAttribute("bizOrderQcBill", bizOrderQcBill);
		model.addAttribute("bizQcBill", bizQcBill);
		return "modules/PQC/checkDetails/detailsList2";
	}
	
	


	@RequestMapping(value = "checkNode")
	public @ResponseBody List<CheckNode> checkNode(String storeId, String projectModeValue) {
		
		CheckNode checkNode = new CheckNode();
		if(!"".equals(storeId) && storeId != null){
			checkNode.setStoreId(Integer.valueOf(storeId));
		}
		checkNode.setProjectMode(projectModeValue);
		checkNode.setStatus(QcBillConstantUtil.QC_BILL_STATUS_YES_1);
		
		List<CheckNode> list = checkNodeService.findList(checkNode);

		if (null != list && list.size() > 0) {

			return list;
		} else {

			return null;
		}

	}
	
	

}