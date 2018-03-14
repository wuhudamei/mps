package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizMaterialsStandard;
import cn.damei.service.modules.BizMaterialsStandardService;
import cn.damei.entity.modules.BizMaterialsStandardReceiveDetail;
import cn.damei.service.modules.BizMaterialsStandardReceiveDetailService;
import cn.damei.entity.modules.BizMaterialsStandardReceiveBillVo;
import cn.damei.service.modules.BizMaterialsStandardReceiveBillVoService;
import cn.damei.service.modules.BizSeiralnumService;
@Controller
@RequestMapping(value = "${adminPath}/standradmaterialsrecords/bizMaterialsStandardReceiveBill")
public class BizMaterialsStandardReceiveBillVoController extends BaseController{
	
	@Autowired
	private BizMaterialsStandardReceiveBillVoService bizMaterialsStandardReceiveBillVoService;
	@Autowired
	private BizMaterialsStandardReceiveDetailService bizMaterialsStandardReceiveDetailService;
	@Autowired
	private BizMaterialsStandardService bizMaterialsStandardService;
	
	@ModelAttribute
	public BizMaterialsStandardReceiveBillVo get(@RequestParam(required=false) Integer id) {
		BizMaterialsStandardReceiveBillVo entity = null;
		if (id != null){
			entity = bizMaterialsStandardReceiveBillVoService.get(id);
			//关联数据
			BizMaterialsStandardReceiveDetail detail = new BizMaterialsStandardReceiveDetail();
			detail.setMaterialsStandardReceiveBillId(id);
			List<BizMaterialsStandardReceiveDetail> details =  bizMaterialsStandardReceiveDetailService.findList(detail);
			entity.setDetails(details);
		}
		if (entity == null){
			entity = new BizMaterialsStandardReceiveBillVo();
		}
		return entity;
	}

	@RequiresPermissions("standradmaterialsrecords:bizMaterialsStandardReceiveBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMaterialsStandardReceiveBillVo bizMaterialsStandardReceiveBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizMaterialsStandardReceiveBillVo> page = bizMaterialsStandardReceiveBillVoService.findPage(new Page<BizMaterialsStandardReceiveBillVo>(request, response), bizMaterialsStandardReceiveBill); 
		model.addAttribute("page", page);
		return "modules/managerSettlement/standradmaterialsrecords/bizMaterialsStandardReceiveBillList";
	}
	
	@RequiresPermissions("standradmaterialsrecords:bizMaterialsStandardReceiveBill:edit")
	@RequestMapping(value = "formVo")
	public String form(BizMaterialsStandardReceiveBillVo bizMaterialsStandardReceiveBill, Model model) {
		if(null == bizMaterialsStandardReceiveBill.getReceiveDatetime()){
			bizMaterialsStandardReceiveBill.setReceiveDatetime(new Date());
		}
		model.addAttribute("bizMaterialsStandardReceiveBill", bizMaterialsStandardReceiveBill);
		return "modules/managerSettlement/standradmaterialsrecords/bizMaterialsStandardReceiveBillForm";
	}

	@Autowired
	private BizSeiralnumService bizSeiralnumService;
	@RequiresPermissions("standradmaterialsrecords:bizMaterialsStandardReceiveBill:edit")
	@RequestMapping(value = "saveVo")
	public String save(BizMaterialsStandardReceiveBillVo bizMaterialsStandardReceiveBill,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		/*if (!beanValidator(model, bizMaterialsStandardReceiveBill)){
			return form(bizMaterialsStandardReceiveBill, model);
		}*/
		List<BizMaterialsStandardReceiveDetail> list = new ArrayList<BizMaterialsStandardReceiveDetail>();
		String[] ids = request.getParameterValues("ids");
		/*if(ids.length == 0 || ids == null){
			addMessage(redirectAttributes, "请先选择标化辅材！");
			return "redirect:"+Global.getAdminPath()+"/standradmaterialsrecords/bizMaterialsStandardReceiveBill/list";
		}*/
		String[] amounts = request.getParameterValues("amounts");
		double a = 0d;
		for (int i=0; i<ids.length;i++) {
			BizMaterialsStandardReceiveDetail detail = new BizMaterialsStandardReceiveDetail();
			BizMaterialsStandard bizMaterialsStandard = bizMaterialsStandardService.get(Integer.parseInt(ids[i]));
			//detail.setMaterialsStandardReceiveBillId(bill.getId());
			detail.setMaterialsId(Integer.parseInt(ids[i]));
			detail.setMaterialsName(bizMaterialsStandard.getMaterialsName());
			detail.setMaterialsType(bizMaterialsStandard.getMaterialsType());
			detail.setMaterialsUnit(bizMaterialsStandard.getMaterialsUnit());
			detail.setMaterialsPrice(bizMaterialsStandard.getMaterialsPrice());
			detail.setReceiveNumber(Double.valueOf(amounts[i]));
			detail.setMaterialsAmount(Double.valueOf(amounts[i])*bizMaterialsStandard.getMaterialsPrice());
			a += Double.valueOf(amounts[i])*bizMaterialsStandard.getMaterialsPrice();
			list.add(detail);
			//bizMaterialsStandardReceiveDetailService.save(detail);
		}
		bizMaterialsStandardReceiveBill.setReceiveBillAmount(a);
		bizMaterialsStandardReceiveBill.setIsSettled(ConstantUtils.IS_SETTLED_0);
		if(null == bizMaterialsStandardReceiveBill.getMaterialsStandardReceiveBillCode()){
			String code = bizSeiralnumService.getDateSequence("LQ");
			bizMaterialsStandardReceiveBill.setMaterialsStandardReceiveBillCode(code);
		}
		if(null == bizMaterialsStandardReceiveBill.getReceiveEmployeeId()){
			bizMaterialsStandardReceiveBill.setReceiveEmployeeId(Integer.parseInt(request.getParameter("hidemployeeId")));
		}
		bizMaterialsStandardReceiveBillVoService.save(bizMaterialsStandardReceiveBill);
		BizMaterialsStandardReceiveBillVo bill = bizMaterialsStandardReceiveBillVoService.findByCode(bizMaterialsStandardReceiveBill.getMaterialsStandardReceiveBillCode());
		//List<BizMaterialsStandardReceiveDetail> details = bizMaterialsStandardReceiveDetailService.findDetailsByBillId(bill.getId());
		for (BizMaterialsStandardReceiveDetail detail : list) {
			detail.setMaterialsStandardReceiveBillId(bill.getId());
			//bizMaterialsStandardReceiveDetailService.add(detail);
		}
		//根据billId查询details
		bizMaterialsStandardReceiveDetailService.add1(list,bill.getId());
		//判断是否存在
		
		
		addMessage(redirectAttributes, "保存标化辅材记录成功");
		return "redirect:"+Global.getAdminPath()+"/standradmaterialsrecords/bizMaterialsStandardReceiveBill/list";
	}
	
	@RequiresPermissions("standradmaterialsrecords:bizMaterialsStandardReceiveBill:edit")
	@RequestMapping(value = "detail")
	public String detail(Integer id,HttpServletRequest request, Model model){
		BizMaterialsStandardReceiveBillVo bizMaterialsStandardReceiveBillVo = bizMaterialsStandardReceiveBillVoService.get(id);
		List<BizMaterialsStandardReceiveDetail>  list = bizMaterialsStandardReceiveDetailService.findDetailsByBillId(id);
		model.addAttribute("bizMaterialsStandardReceiveBillVo", bizMaterialsStandardReceiveBillVo);
		model.addAttribute("list", list);
		return "modules/managerSettlement/standradmaterialsrecords/bizMaterialsStandardReceiveBillDetail";
	}
	
	@ResponseBody
	@RequestMapping(value = "isExited")
	public String isExited(Integer orderId,HttpServletRequest request){
		
		BizMaterialsStandardReceiveBillVo  vo = bizMaterialsStandardReceiveBillVoService.findByOrderId(orderId);
		if(vo == null){
			return "error";
		}else{
			return "success";
		}
	}
	
}
