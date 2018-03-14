package cn.damei.web.modules;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.service.mobile.Manager.DelaySheetService;
import cn.damei.entity.mobile.Manager.BusinessPicture;
import cn.damei.service.mobile.Manager.BusinessPictureService;
import cn.damei.entity.modules.DelayBill;
import cn.damei.service.modules.DelayBillService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.service.modules.SiteDesignProblemPCService;
import cn.damei.entity.modules.Dict;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

@RequestMapping(value = "${adminPath}/delayBill")
@Controller
public class DelayBillController extends BaseController {
	@Autowired
	private DelayBillService delayBillService;
	@Autowired
	private BusinessPictureService businessPictureService;
	@Autowired
	private SiteDesignProblemPCService siteDesignProblemService;
	@Autowired
	private DelaySheetService delaySheetService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	@ModelAttribute
	public DelayBill get(@RequestParam(required = false) String id) {
		DelayBill entity = null;
		if (StringUtils.isNotBlank(id)) {
			/* entity = delayBillService.get(id); */
		}
		if (entity == null) {
			entity = new DelayBill();
		}
		return entity;
	}
	/**
	* @Description: 延期单查询列表
	* @Author zhangkangjian
	* @param
	* @return
	* @Date 2017/11/14 18:42
	*/
	@RequestMapping(value = "list")
	public String list(DelayBill delayBill, HttpServletRequest request, HttpServletResponse response, Model model) {

		if (UserUtils.getUser().getStoreId() != null) {
			// 当前登录用户门店
			delayBill.setStoreId(UserUtils.getUser().getStoreId());
		} else {
			// 门店是总部的查询所有部门信息
			if (delayBill.getStoreId() != null && delayBill.getStoreId().equals("1")) {
				// 总部
				delayBill.setStoreId(null);
			}
		}
		// 过滤工程模式
		if (delayBill.getProjectMode() == null || "".equals(delayBill.getProjectMode())) {
			User user = UserUtils.getUser();
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode()) || null == be.getProjectMode()) {
					delayBill.setProjectMode(null);
				} else {
					delayBill.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			} else {
				delayBill.setProjectMode(null);
			}
		}
		//		默认查询待审批的延期单
		String status = delayBill.getStatus();
		if(status == null ||  "".equals(status)){
			delayBill.setStatus("10");
		}
		Page<DelayBill> findPage = delayBillService.findPage(new Page<DelayBill>(request, response), delayBill);
		model.addAttribute("page", findPage);
		List<Dict> list = delaySheetService.findDelayCategory(1);
		model.addAttribute("list", list);
		model.addAttribute("delayBill", delayBill);
		return "/modules/delaybill/DelayBillList";
	}

	@RequestMapping(value = "photo")
	public String findPictureByBusinessProblemId(Integer id, String picType, Model model) throws IOException {
		List<BusinessPicture> pictures = businessPictureService.queryPictureByBussinessIdAndType(id, PictureTypeContantUtil.PICTURE_TYPE_10010);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);
		return "modules/bizorderinstallitemproblem/photo";
	}

	@RequestMapping(value = "ajaxPhoto")
	@ResponseBody
	public Map<Object, Object> ajaxPhoto(Integer id, String picType, Model model) throws IOException {
		List<BusinessPicture> pictures = businessPictureService.queryPictureByBussinessIdAndType(id, PictureTypeContantUtil.PICTURE_TYPE_10010);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", pictures);

		return mapObject;
	}

	/**
	 * 通过
	 * 
	 * @param delayBill
	 * @return
	 */
	@RequestMapping(value = "pass")
	public String pass(DelayBill delayBill, Model model, RedirectAttributes redirectAttributes) {
		delaySheetService.pass(delayBill);
		addMessage(redirectAttributes, "通过延期单成功");
		return "forward:" + Global.getAdminPath() + "/delayBill/list";
	}

	/**
	 * 动态加载延期原因
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "ajaxreson")
	@ResponseBody
	public List<Dict> ajaxreson(String id) {
		// 查询延期分类 根据一级ID查询二级类目
		List<Dict> list = delaySheetService.findDelayCategorytow(id, 2);
		return list;
	}
	/**
	 * @Description: 动态加载延期阶段
	 * @Author zhangkangjian
	 * @param
	 * @return
	 * @Date 2017/11/14 14:50
	 */
	@RequestMapping(value = "/ajaxDelayStage")
	@ResponseBody
	public List<Dict> ajaxDelayStage(String storeId,String projectMode){
		return delaySheetService.findTemplateNodePlan(storeId,projectMode);
	}

	/**
	 * 拒绝
	 * 
	 * @param delayBill
	 * @return
	 */
	@RequestMapping(value = "refuse")
	public String refuse(DelayBill delayBill, RedirectAttributes redirectAttributes) {
		delaySheetService.refuse(delayBill);
		addMessage(redirectAttributes, "拒绝延期单成功");
		return "redirect:" + Global.getAdminPath() + "/delayBill/list";
	}

	/**
	 * @Description: 更新延期单延期时间
	 * @param @param delayBill
	 * @param @param redirectAttributes
	 * @param @return
	 * @author zkj
	 * @date 2017年10月24日 下午3:47:31
	 */
	@RequestMapping(value = "update")
	@ResponseBody
	public void update(DelayBill delayBill, RedirectAttributes redirectAttributes) {
		delayBillService.save(delayBill);
		addMessage(redirectAttributes, "延期单更新成功");
	}

	/**
	 * @Description: 延期单详情
	 * @param @return
	 * @author zkj
	 * @date 2017年10月24日 下午5:46:16
	 */
	@RequestMapping(value = "detail")
	public String detail(HttpServletResponse response, Model model, HttpServletRequest resquest, DelayBill delayBill) {
		List<DelayBill> findList = delayBillService.findList(delayBill);
		if (findList.size() > 0) {
			DelayBill delayBill2 = findList.get(0);
			model.addAttribute("delayBill", delayBill2);
		}
		model.addAttribute("list", findList);
		return "/modules/delaybill/delayBillDetail";
	}

	/**
	 * @Description: 导出延期单
	 * @param @param response
	 * @param @param model
	 * @param @param resquest
	 * @param @param delayBill
	 * @author zkj
	 * @date 2017年10月26日 下午3:33:28
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public void export(HttpServletResponse response, Model model, HttpServletRequest resquest, DelayBill delayBill) {
		delayBillService.exportDetailExcel(delayBill, response);
	}

	/**
	* @Description: 延期单作废
	* @Author zhangkangjian
	* @param
	* @return
	* @Date 2017/11/14 18:48
	*/
	@RequestMapping(value="delayBillInvalid")
	@ResponseBody
	public void invalid(DelayBill delayBill){
		delayBillService.delayBillInvalid(delayBill);
	}

}
