
package cn.damei.web.modules;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizProjectInstallItem;
import cn.damei.service.modules.BizProjectInstallItemService;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/installitem/bizProjectInstallItem")
public class BizProjectInstallItemController extends BaseController {

	@Autowired
	private BizProjectInstallItemService bizProjectInstallItemService;

	@ModelAttribute
	public BizProjectInstallItem get(@RequestParam(required = false) String id) {
		BizProjectInstallItem entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = bizProjectInstallItemService.get(id);
		}
		if (entity == null) {
			entity = new BizProjectInstallItem();
		}
		return entity;
	}

	@RequiresPermissions("installitem:bizProjectInstallItem:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizProjectInstallItem bizProjectInstallItem, HttpServletRequest request, HttpServletResponse response, Model model) {

		return "modules/installitem/bizProjectInstallItemList";
	}

	@RequiresPermissions("installitem:bizProjectInstallItem:view")
	@RequestMapping(value = { "list1" })
	public String list1(BizProjectInstallItem bizProjectInstallItem, HttpServletRequest request, HttpServletResponse response, Model model) {

		if (!UserUtils.getUser().getOffice().getId().equals("1")) {

			bizProjectInstallItem.setStoreId(UserUtils.getUser().getStoreId());
		}

		Page<BizProjectInstallItem> page = bizProjectInstallItemService.findPage(new Page<BizProjectInstallItem>(request, response), bizProjectInstallItem);
		model.addAttribute("page", page);
		return "modules/installitem/bizProjectInstallItemList";
	}

	@RequiresPermissions("installitem:bizProjectInstallItem:view")
	@RequestMapping(value = "form")
	public String form(BizProjectInstallItem bizProjectInstallItem, Model model, String id) {

		model.addAttribute("readOnly", UserUtils.getUser().getProjectMode());

		model.addAttribute("bizProjectInstallItem", bizProjectInstallItem);
		return "modules/installitem/bizProjectInstallItemForm";
	}

	@RequiresPermissions("installitem:bizProjectInstallItem:edit")
	@RequestMapping(value = "save")
	public String save(BizProjectInstallItem bizProjectInstallItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizProjectInstallItem)) {
			return form(bizProjectInstallItem, model, null);
		}
		String isToChecksize = bizProjectInstallItem.getIsToChecksize();
		if (isToChecksize.equals("0")) {
			bizProjectInstallItem.setDaysPlanChecksize(null);
		}
		bizProjectInstallItemService.save(bizProjectInstallItem);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:" + Global.getAdminPath() + "/installitem/bizProjectInstallItem/?repage";
	}

	@RequiresPermissions("installitem:bizProjectInstallItem:edit")
	@RequestMapping(value = "delete")
	public String delete(BizProjectInstallItem bizProjectInstallItem, RedirectAttributes redirectAttributes, Model model) {

		if (bizProjectInstallItem.getIsOn().equals("1")) {
			bizProjectInstallItem.setIsOn("0");
		} else {
			bizProjectInstallItem.setIsOn("1");
		}

		bizProjectInstallItemService.delete(bizProjectInstallItem);

		addMessage(redirectAttributes, "操作成功");
		return "redirect:" + Global.getAdminPath() + "/installitem/bizProjectInstallItem/?repage";
	}

	@RequestMapping(value = "ajaxGetInstallItem")
	@ResponseBody
	public Map<String, Object> ajaxGetInstallItem(BizProjectInstallItem bizProjectInstallItem, RedirectAttributes redirectAttributes, Model model) {
		Map<String, Object> mapList = new HashMap<String, Object>();
		List<BizProjectInstallItem> bizProjectInstallItemList = bizProjectInstallItemService.queryInstallItemList(bizProjectInstallItem);
		mapList.put("resultMap", bizProjectInstallItemList);
		return mapList;

	}
}