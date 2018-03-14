
package cn.damei.web.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.BizMaterialsStandardReceiveBillApply;
import cn.damei.service.modules.BizMaterialsStandardReceiveBillApplyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizOrderMaterialsStandard;
import cn.damei.entity.modules.BizOrderMaterialsStandardQuery;
import cn.damei.service.modules.BizOrderMaterialsStandardQueryService;
import cn.damei.service.modules.BizOrderMaterialsStandardService;


@Controller
@RequestMapping(value = "${adminPath}/orderMaterialsStandard/bizOrderMaterialsStandard")
public class BizOrderMaterialsStandardQueryController extends BaseController {

	@Autowired
	private BizOrderMaterialsStandardQueryService bizOrderMaterialsStandardQueryService;
	@Autowired
	private BizOrderMaterialsStandardService bizOrderMaterialsStandardService;
	@Autowired
	BizMaterialsStandardReceiveBillApplyService bizMaterialsStandardReceiveBillApplyService;
	
	@ModelAttribute
	public BizOrderMaterialsStandardQuery get() {
		BizOrderMaterialsStandardQuery entity = null;
		if (entity == null){
			entity = new BizOrderMaterialsStandardQuery();
		}
		return entity;
	}
		
	@RequiresPermissions("managersettlement:bizOrderMaterialsStandard:view")
	@RequestMapping(value = {"list"})
	public String list(BizOrderMaterialsStandardQuery bizOrderMaterialsStandard, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/managerSettlement/bizOrderMaterialsStandard/bizOrderMaterialsStandardList";
	}

    @RequiresPermissions("managersettlement:bizOrderMaterialsStandard:view")
    @RequestMapping(value = {"findlist",""})
    public String findList(BizOrderMaterialsStandardQuery bizOrderMaterialsStandard, HttpServletRequest request, HttpServletResponse response, Model model) {

        bizOrderMaterialsStandard.setMaterialsLargeType("1");
        Page<BizOrderMaterialsStandardQuery> page = bizOrderMaterialsStandardQueryService.findPage(new Page<BizOrderMaterialsStandardQuery>(request, response), bizOrderMaterialsStandard);
        model.addAttribute("page", page);
        return "modules/managerSettlement/bizOrderMaterialsStandard/bizOrderMaterialsStandardList";
    }
	@RequiresPermissions("managersettlement:bizOrderMaterialsStandard:view")
	@RequestMapping(value = "form")
	public String form(String orderId, Model model) {
		BizOrderMaterialsStandardQuery queryListByOrderId = bizOrderMaterialsStandardQueryService.getBizOrderMaterialsStandardQueryListByOrderId(orderId,"1");
		model.addAttribute("bizOrderMaterialsStandardQuery", queryListByOrderId);
		List<BizMaterialsStandardReceiveBillApply> billList = bizMaterialsStandardReceiveBillApplyService.findMaterialsApplyBillByOrderId(orderId, "1");
		model.addAttribute("billList", billList);
		List<BizOrderMaterialsStandard> materialsByOrderId = bizOrderMaterialsStandardService.getMaterialsByOrderId(orderId,"1");
		model.addAttribute("list", materialsByOrderId);
		return "modules/managerSettlement/bizOrderMaterialsStandard/bizOrderMaterialsStandardForm";
	}

	

}