
package cn.damei.web.modules;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizQcBill;
import cn.damei.service.modules.BizQcBillService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "${adminPath}/bizqcbill/bizQcBill")
public class BizQcBillController extends BaseController {

	@Autowired
	private BizQcBillService bizQcBillService;
	
	@ModelAttribute
	public BizQcBill get(@RequestParam(required=false) String id) {
		BizQcBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizQcBillService.get(id);
		}
		if (entity == null){
			entity = new BizQcBill();
		}
		return entity;
	}
	
	@RequiresPermissions("bizqcbill:bizQcBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizQcBill bizQcBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==bizQcBill.getStoreId()){
			if(null!=user.getStoreId()){
				bizQcBill.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
			model.addAttribute("gongcheng", true);
		}else{
			bizQcBill.setProjectMode(user.getProjectMode());
		}
		return "modules/bizqcbill/bizQcBillList";
	}
	@RequiresPermissions("bizqcbill:bizQcBill:view")
	@RequestMapping(value = {"bizqcbillList", ""})
	public String bizqcbillList(BizQcBill bizQcBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		String qcbillType ="";
		if(bizQcBill!=null&&bizQcBill.getQcBillType()!=null &&bizQcBill.getQcBillType().equals("3")){
			qcbillType = bizQcBill.getQcBillType();
			bizQcBill.setIsRecheck("1");
			bizQcBill.setQcBillType("");
		}
		
		User user = UserUtils.getUser();

		if( null!= bizQcBill && null==bizQcBill.getStoreId()){
			if( user != null && null!=user.getStoreId()){
				bizQcBill.setStoreId(user.getStoreId());
			}
		}
		if( null != user && StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if( null != user ){
			if(  StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3") ){
				model.addAttribute("gongcheng", true);
			}else{
				bizQcBill.setProjectMode(user.getProjectMode());
			}
		}

		Page<BizQcBill> page = bizQcBillService.findPage(new Page<BizQcBill>(request, response), bizQcBill);
		model.addAttribute("page", page);
		if(""!=qcbillType && qcbillType.equals("3")){
			bizQcBill.setQcBillType(qcbillType);
		}
		return "modules/bizqcbill/bizQcBillList";
	}

	


	
	

}