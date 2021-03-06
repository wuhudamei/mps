
package cn.damei.web.modules;

import java.io.IOException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizBroadcastBill;
import cn.damei.service.modules.BizBroadcastBillService;


@Controller
@RequestMapping(value = "${adminPath}/bizbroadcastbill/bizBroadcastBill")
public class BizBroadcastBillController extends BaseController {

	@Autowired
	private BizBroadcastBillService bizBroadcastBillService;
	
	@ModelAttribute
	public BizBroadcastBill get(@RequestParam(required=false) String id) {
		BizBroadcastBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizBroadcastBillService.get(id);
		}
		if (entity == null){
			entity = new BizBroadcastBill();
		}
		return entity;
	}
	
	@RequiresPermissions("bizbroadcastbill:bizBroadcastBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizBroadcastBill bizBroadcastBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("packNameList", bizBroadcastBillService.findPackageTempleteList());
		return "modules/bizbroadcastbill/bizBroadcastBillList";
	}
	@RequiresPermissions("bizbroadcastbill:bizBroadcastBill:view")
	@RequestMapping(value = {"list1"})
	public String list1(BizBroadcastBill bizBroadcastBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizBroadcastBill> page = bizBroadcastBillService.findPage(new Page<BizBroadcastBill>(request, response), bizBroadcastBill); 
		model.addAttribute("page", page);
		
		model.addAttribute("packNameList", bizBroadcastBillService.findPackageTempleteList());
		return "modules/bizbroadcastbill/bizBroadcastBillList";
	}

	@RequiresPermissions("bizbroadcastbill:bizBroadcastBill:view")
	@RequestMapping(value = "form")
	public String form(BizBroadcastBill bizBroadcastBill, Model model) throws IOException {
		List<BizBroadcastBill> list = bizBroadcastBillService.findBroadCastInfoAndPic(bizBroadcastBill.getBroadcastId());
		
		model.addAttribute("list", list);
		model.addAttribute("broadcast", bizBroadcastBill);
		model.addAttribute("picPrefix", PicRootName.picPrefixName());
		return "modules/bizbroadcastbill/bizBroadcastBillForm";
	}
	
	
	@RequestMapping(value="save")
	public  String  save(RedirectAttributes redirectAttributes,String broadcastId,HttpServletRequest request,Model model,String[] photos,String [] picIds,String [] isShow){
		
		






	
	if(bizBroadcastBillService.checkBroadcastPicAndStatus(broadcastId, request, model, photos, picIds, isShow)){
		addMessage(redirectAttributes, "展示成功..");
		return "redirect:"+Global.getAdminPath()+"/bizbroadcastbill/bizBroadcastBill/?repage";
		
	}else{
		addMessage(redirectAttributes, "展示失败.. 请联系开发人员");
		return "redirect:"+Global.getAdminPath()+"/bizbroadcastbill/bizBroadcastBill/?repage";
	}
	
	
	
	
}

public static boolean isNum(String str){
	return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
}


	

}