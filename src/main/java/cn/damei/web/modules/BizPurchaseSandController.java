
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

import cn.damei.common.constantUtils.PurchaseConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.service.mobile.Manager.ApplySandService;
import cn.damei.entity.mobile.Manager.AuxiliaryVo;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizPurchaseVo;
import cn.damei.service.modules.BizPurchaseVoService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/purchase/bizPurchase/sand")
public class BizPurchaseSandController extends BaseController {

	@Autowired
	private BizPurchaseVoService bizPurchaseVoService;
	@Autowired
	private ApplySandService applySandService;
	
	@ModelAttribute
	public BizPurchaseVo get(@RequestParam(required=false) Integer id) {
		BizPurchaseVo entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizPurchaseVoService.get(id);
		}
		if (entity == null){
			entity = new BizPurchaseVo();
		}
		return entity;
	}
	

	@RequiresPermissions("sand:sand:view")
	@RequestMapping(value = "listPage")
	public String listPage(BizPurchaseVo bizPurchaseVo, HttpServletRequest request, HttpServletResponse response, Model model){
		
		User user = UserUtils.getUser();

		if(null==bizPurchaseVo.getStoreId()){
			if(null!=user.getStoreId()){
				bizPurchaseVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}

		if(StringUtils.isBlank(bizPurchaseVo.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("projectModeEnable", true);
			}else{
				bizPurchaseVo.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("projectModeEnable", true);
			}else{
				bizPurchaseVo.setProjectMode(user.getProjectMode());
			}
		}
		

		List<BizPurchaseVo> bizSupplierList = null;
		if(null == bizPurchaseVo.getSupplierId()){
			if(StringUtils.isBlank(user.getEmployeePhone()) || user.getEmployeePhone().equals("")){

				bizSupplierList = bizPurchaseVoService.findSupplierList(user.getEmployeePhone(),PurchaseConstantUtil.PURCHASE_TYPE_6,PurchaseConstantUtil.PURCHASE_STATUS_YES_1,PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);
			}else{

				bizSupplierList = bizPurchaseVoService.findSupplierList(user.getEmployeePhone(),PurchaseConstantUtil.PURCHASE_TYPE_6,PurchaseConstantUtil.PURCHASE_STATUS_YES_1,PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);
				for(BizPurchaseVo a:bizSupplierList){
					bizPurchaseVo.setSupplierId(a.getSupplierId());
					break;
				}
			}
		}else{
			if(StringUtils.isBlank(user.getEmployeePhone()) || user.getEmployeePhone().equals("")){

				bizSupplierList = bizPurchaseVoService.findSupplierList(user.getEmployeePhone(),PurchaseConstantUtil.PURCHASE_TYPE_6,PurchaseConstantUtil.PURCHASE_STATUS_YES_1,PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);
			}else{

				bizSupplierList = bizPurchaseVoService.findSupplierList(user.getEmployeePhone(),PurchaseConstantUtil.PURCHASE_TYPE_6,PurchaseConstantUtil.PURCHASE_STATUS_YES_1,PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);
				
			}
		}
		

		if(StringUtils.isBlank(bizPurchaseVo.getStatus())){
			bizPurchaseVo.setStatus(PurchaseConstantUtil.PURCHASE_AUXILIARY_STATUS_10);
		}

		bizPurchaseVo.setPurchaseType(PurchaseConstantUtil.PURCHASE_TYPE_6);
		Page<BizPurchaseVo> page = bizPurchaseVoService.findPage(new Page<BizPurchaseVo>(request, response), bizPurchaseVo);
		model.addAttribute("page", page);
		model.addAttribute("bizSupplierList", bizSupplierList);
		
		return "modules/purchase/bizPurchaseSandList";
		
	}
	

	@RequiresPermissions("sand:sand:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPurchaseVo bizPurchaseVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==bizPurchaseVo.getStoreId()){
			if(null!=user.getStoreId()){
				bizPurchaseVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}

		if(StringUtils.isBlank(bizPurchaseVo.getProjectMode())){
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("projectModeEnable", true);
			}else{
				bizPurchaseVo.setProjectMode(user.getProjectMode());
			}
		}else{
			if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
				model.addAttribute("projectModeEnable", true);
			}else{
				bizPurchaseVo.setProjectMode(user.getProjectMode());
			}
		}

		List<BizPurchaseVo> bizSupplierList = null;
		if(null == bizPurchaseVo.getSupplierId()){
			if(StringUtils.isBlank(user.getEmployeePhone()) || user.getEmployeePhone().equals("")){

				bizSupplierList = bizPurchaseVoService.findSupplierList(user.getEmployeePhone(),PurchaseConstantUtil.PURCHASE_TYPE_6,PurchaseConstantUtil.PURCHASE_STATUS_YES_1,PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);
			}else{

				bizSupplierList = bizPurchaseVoService.findSupplierList(user.getEmployeePhone(),PurchaseConstantUtil.PURCHASE_TYPE_6,PurchaseConstantUtil.PURCHASE_STATUS_YES_1,PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);
				for(BizPurchaseVo a:bizSupplierList){
					bizPurchaseVo.setSupplierId(a.getSupplierId());
					break;
				}
			}
		}else{
			if(StringUtils.isBlank(user.getEmployeePhone()) || user.getEmployeePhone().equals("")){

				bizSupplierList = bizPurchaseVoService.findSupplierList(user.getEmployeePhone(),PurchaseConstantUtil.PURCHASE_TYPE_6,PurchaseConstantUtil.PURCHASE_STATUS_YES_1,PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);
			}else{

				bizSupplierList = bizPurchaseVoService.findSupplierList(user.getEmployeePhone(),PurchaseConstantUtil.PURCHASE_TYPE_6,PurchaseConstantUtil.PURCHASE_STATUS_YES_1,PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);
				
			}
		}
				

		bizPurchaseVo.setPurchaseType(PurchaseConstantUtil.PURCHASE_TYPE_6);
		Page<BizPurchaseVo> page = bizPurchaseVoService.findPage(new Page<BizPurchaseVo>(request, response), bizPurchaseVo);
		model.addAttribute("page", page);
		model.addAttribute("bizSupplierList", bizSupplierList);
		return "modules/purchase/bizPurchaseSandList";
	}
	

	@RequiresPermissions("sand:sand:view")
	@RequestMapping(value = "details")
	public String details(Integer id , HttpServletRequest request, HttpServletResponse response, Model model){

		BizPurchaseVo bizPurchaseVo =  bizPurchaseVoService.findById(id);
		
		List<AuxiliaryVo> goodsList = applySandService.findPurchaseGoods(id);
		
		model.addAttribute("bizPurchaseVo", bizPurchaseVo);
		model.addAttribute("goodsList", goodsList);
		
		return "modules/purchase/sandDetails";
	}
	
	
	
	
}