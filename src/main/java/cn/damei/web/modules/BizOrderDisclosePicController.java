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
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizOrderDisclosePic;
import cn.damei.service.modules.BizOrderDisclosePicService;

/*
 *	订单交底图片查询
 */
@Controller
@RequestMapping(value = "${adminPath}/bizorderdisclosepic/bizOrderDisclosePic")
public class BizOrderDisclosePicController extends BaseController {

	@Autowired
	private BizOrderDisclosePicService bizOrderDisclosePicService;

	@ModelAttribute
	public BizOrderDisclosePic get(@RequestParam(required = false) Integer id) {
		BizOrderDisclosePic entity = null;
		if (StringUtils.isNotBlank(id + "")) {

			entity = bizOrderDisclosePicService.get(id);
		}
		if (entity == null) {
			entity = new BizOrderDisclosePic();
		}
		return entity;
	}

	@RequiresPermissions("bizorderdisclosepic:bizOrderDisclosePic:view")
	@RequestMapping(value = { "showOrderDisclosePhoto", "" })
	public String showOrderDisclosePhoto(BizOrderDisclosePic bizOrderDisclosePic, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		Integer orderDiscloseId = Integer.valueOf(request.getParameter("orderDiscloseId"));
		logger.info("现场交底主键编号：" + orderDiscloseId);

		List<BizOrderDisclosePic> dpList = bizOrderDisclosePicService.getByOrderDiscloseId(orderDiscloseId);

		if (dpList.size() > 0) {
			model.addAttribute("orderDisclosePic", dpList);
		}
		model.addAttribute("picPrefixName", PicRootName.picPrefixName());
		return "modules/bizOrderDisclose/disclosePicListPhoto";
	}

	@RequestMapping(value = "/ajaxShowOrderDisclosePhoto")
	@ResponseBody
	public Map<Object, Object> ajaxShowOrderDisclosePhoto(BizOrderDisclosePic bizOrderDisclosePic, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		Integer orderDiscloseId = Integer.valueOf(request.getParameter("orderDiscloseId"));
		logger.info("现场交底主键编号：" + orderDiscloseId);

		List<BizOrderDisclosePic> dpList = bizOrderDisclosePicService.getByOrderDiscloseId(orderDiscloseId);

		if (dpList.size() > 0) {
			model.addAttribute("orderDisclosePic", dpList);
		}
		model.addAttribute("picPrefixName", PicRootName.picPrefixName());
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", dpList);

		return mapObject;
	}
}
