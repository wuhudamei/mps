package cn.damei.web.modules;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizBusinessPic;
import cn.damei.service.modules.BizBusinessPicService;


@Controller
@RequestMapping(value = "${adminPath}/bizbusinesspic/bizBusinessPic")
public class BizBusinessPicController {

	@Autowired
	private BizBusinessPicService bizBusinessPicService;

	@ModelAttribute
	public BizBusinessPic get(@RequestParam(required = false) Integer id) {
		BizBusinessPic entity = null;
		if (StringUtils.isNotBlank(id + "")) {

			entity = bizBusinessPicService.get(id);
		}
		if (entity == null) {
			entity = new BizBusinessPic();
		}
		return entity;
	}




	@RequiresPermissions("bizbusinesspic:bizBusinessPic:view")
	@RequestMapping(value = "/getByBusinessIdOrConAjax")
	@ResponseBody
	public Map<Object, Object> getByBusinessIdOrConAjax(String orderID,HttpServletRequest request,
														HttpServletResponse response, Model model) throws IOException {
		List<BizBusinessPic> pics = null;
		if (!orderID.equals("")) {
			pics = bizBusinessPicService.getByBusinessId(Integer.valueOf(orderID));
		}

		model.addAttribute("businessList", pics);
		model.addAttribute("picPrefixName", PicRootName.picPrefixName());
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", pics);
		return mapObject;
	}




	@RequiresPermissions("bizbusinesspic:bizBusinessPic:view")
	@RequestMapping(value = "/getByBusinessIdOrAuditAjax")
	@ResponseBody
	public  Map<Object, Object> getByBusinessIdOrAuditAjax(String orderID,HttpServletRequest request,
														   HttpServletResponse response, Model model) throws IOException{
		List<BizBusinessPic> pics = null;
		if(!("").equals(orderID)&& null !=orderID){
			pics = bizBusinessPicService.getByBusinessId(Integer.valueOf(orderID));
		}

		model.addAttribute("businessList", pics);
		model.addAttribute("picPrefixName",PicRootName.picPrefixName());
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", pics);
		return mapObject;
	}



	@RequestMapping(value = {"showRecheckPic", ""})
	public String showRecheckPic(BizBusinessPic bizBusinessPic, HttpServletRequest request,
								 HttpServletResponse response, Model model,String recheckID, String type) throws IOException {
		List<BizBusinessPic> listPic = null;
		if(!("").equals(type) && null!=recheckID){
			listPic = bizBusinessPicService.getByRecheckID(Integer.valueOf(recheckID),type);

		}
		model.addAttribute("picPrefixName", PicRootName.picPrefixName());
		model.addAttribute("listPic", listPic);
		return "modules/bizrecheck/recheckPic";
	}


	@RequestMapping(value = "/ajaxShowRecheckPic")
	@ResponseBody
	public Map<Object, Object> ajaxShowRecheckPic(BizBusinessPic bizBusinessPic, HttpServletRequest request, HttpServletResponse response, Model model, String recheckID, String type) throws IOException {
		List<BizBusinessPic> listPic = null;
		if (!type.equals("") && recheckID != null) {
			listPic = bizBusinessPicService.getByRecheckID(Integer.valueOf(recheckID), type);
		}
		model.addAttribute("picPrefixName", PicRootName.picPrefixName());
		model.addAttribute("listPic", listPic);
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", listPic);

		return mapObject;
	}


	@RequestMapping(value = { "showRecheckMonitorPic", "" })
	public String showRecheckMonitorPic(BizBusinessPic bizBusinessPic, HttpServletRequest request, HttpServletResponse response, Model model, String recheckID, String type) throws IOException {

		List<BizBusinessPic> listPic = null;
		if (!type.equals("") && recheckID != null) {
			listPic = bizBusinessPicService.getByRecheckID(Integer.valueOf(recheckID), type);
		}
		model.addAttribute("picPrefixName", PicRootName.picPrefixName());
		model.addAttribute("listPic", listPic);
		return "modules/bizrecheck/recheckMointorPic";
	}
}
