
package cn.damei.web.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.BizOrderInstallItemProblem;
import cn.damei.entity.modules.BizOrderInstallItemProblemLog;
import cn.damei.entity.modules.BizOrderInstallItemProblemVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import cn.damei.common.constantUtils.BusinessProblemConstantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.mapper.JsonMapper;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.DataAuthorityConstantUtils;
import cn.damei.common.utils.ExportInstallItemProblem;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.service.mobile.Manager.WallAndFloorProblemService;
import cn.damei.entity.mobile.Manager.BusinessPicture;
import cn.damei.service.mobile.Manager.BusinessPictureService;
import cn.damei.service.modules.BizOrderInstallItemProblemService;
import cn.damei.service.modules.BusinessWallAndFloorProblemService;
import cn.damei.service.modules.BizOrderInstallItemProblemLogService;
import cn.damei.service.modules.DataAuthorityService;
import cn.damei.entity.modules.DropModel;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/bizorderinstallitemproblem/bizOrderInstallItemProblem")
public class BizOrderInstallItemProblemController extends BaseController {

	@Autowired
	private BizOrderInstallItemProblemService bizOrderInstallItemProblemService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizOrderInstallItemProblemLogService bizOrderInstallItemProblemLogService;
	@Autowired
	private BusinessPictureService businessPictureService;
	@Autowired
	private DataAuthorityService dataAuthorityService;
	@Autowired
	private WallAndFloorProblemService wallAndFloorProblemService;
	@Autowired
	private BusinessWallAndFloorProblemService businessWallAndFloorProblemService;

	@ModelAttribute
	public BizOrderInstallItemProblemVo getOrderInstallItemProblemVo(@RequestParam(required = false) Integer id) {
		BizOrderInstallItemProblemVo entity = null;
		if (id != null) {
			entity = bizOrderInstallItemProblemService.getOrderInstallItemProblemVo(id);
		}
		if (entity == null) {
			entity = new BizOrderInstallItemProblemVo();
		}
		return entity;
	}

	@RequiresPermissions("bizorderinstallitemproblem:bizOrderInstallItemProblem:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderInstallItemProblem bizOrderInstallItemProblem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizOrderInstallItemProblem> page = bizOrderInstallItemProblemService.findPage(new Page<BizOrderInstallItemProblem>(request, response), bizOrderInstallItemProblem);
		model.addAttribute("page", page);
		return "modules/bizorderinstallitemproblem/bizOrderInstallItemProblemList";
	}

	@RequiresPermissions("bizorderinstallitemproblem:bizOrderInstallItemProblem:view")
	@RequestMapping(value = "form")
	public String form(BizOrderInstallItemProblem bizOrderInstallItemProblem, Model model) {
		model.addAttribute("bizOrderInstallItemProblem", bizOrderInstallItemProblem);
		return "modules/bizorderinstallitemproblem/bizOrderInstallItemProblemForm";
	}

	@RequiresPermissions("bizorderinstallitemproblem:bizOrderInstallItemProblem:edit")
	@RequestMapping(value = "save")
	public String save(BizOrderInstallItemProblem bizOrderInstallItemProblem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizOrderInstallItemProblem)) {
			return form(bizOrderInstallItemProblem, model);
		}
		bizOrderInstallItemProblemService.save(bizOrderInstallItemProblem);
		addMessage(redirectAttributes, "保存订单安装项问题成功");
		return "redirect:" + Global.getAdminPath() + "/bizorderinstallitemproblem/bizOrderInstallItemProblem/?repage";
	}

	@RequiresPermissions("bizorderinstallitemproblem:bizOrderInstallItemProblem:edit")
	@RequestMapping(value = "delete")
	public String delete(BizOrderInstallItemProblem bizOrderInstallItemProblem, RedirectAttributes redirectAttributes) {
		bizOrderInstallItemProblemService.delete(bizOrderInstallItemProblem);
		addMessage(redirectAttributes, "删除订单安装项问题成功");
		return "redirect:" + Global.getAdminPath() + "/bizorderinstallitemproblem/bizOrderInstallItemProblem/?repage";
	}


	@RequiresPermissions("bizorderinstallitemproblem:bizOrderInstallItemProblem:view")
	@RequestMapping(value = "listVo")
	public String listVo(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();

		if (null == bizOrderInstallItemProblemVo.getStoreId()) {
			if (null != user.getStoreId()) {
				bizOrderInstallItemProblemVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		if (StringUtils.isBlank(bizOrderInstallItemProblemVo.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}

		bizOrderInstallItemProblemVo.setStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_10);

		bizOrderInstallItemProblemVo.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_1);

		bizOrderInstallItemProblemVo.setPictureType(PictureTypeContantUtil.PICTURE_TYPE_207);

		bizOrderInstallItemProblemVo.setProblemSolveRole(BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_2);

		bizOrderInstallItemProblemVo.setLogStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_30);

		Page<BizOrderInstallItemProblemVo> page = bizOrderInstallItemProblemService.findVoPage(new Page<BizOrderInstallItemProblemVo>(request, response), bizOrderInstallItemProblemVo);
		model.addAttribute("page", page);

		return "modules/bizorderinstallitemproblem/bizOrderInstallItemProblemList";
	}


	@RequiresPermissions("bizorderinstallitemproblem:bizOrderInstallItemProblem:view")
	@RequestMapping(value = "listVoLast")
	public String listVoLast(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();

		if (null == bizOrderInstallItemProblemVo.getStoreId()) {
			if (null != user.getStoreId()) {
				bizOrderInstallItemProblemVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		if (StringUtils.isBlank(bizOrderInstallItemProblemVo.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}

		bizOrderInstallItemProblemVo.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_1);

		bizOrderInstallItemProblemVo.setPictureType(PictureTypeContantUtil.PICTURE_TYPE_207);

		bizOrderInstallItemProblemVo.setProblemSolveRole(BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_2);

		bizOrderInstallItemProblemVo.setLogStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_30);

		Page<BizOrderInstallItemProblemVo> page = bizOrderInstallItemProblemService.findVoPage(new Page<BizOrderInstallItemProblemVo>(request, response), bizOrderInstallItemProblemVo);
		model.addAttribute("page", page);

		return "modules/bizorderinstallitemproblem/bizOrderInstallItemProblemList";
	}


	@ResponseBody
	@RequestMapping(value = "queryInstallItemProblemTypeList")
	public String queryInstallItemProblemTypeList(String storeId, String projectMode, String businessType, HttpServletRequest request, HttpServletResponse response) {
		Map map = new HashMap();
		map.put("storeId", storeId);
		map.put("projectMode", projectMode);
		map.put("isEnabled", "1");
		map.put("businessType", businessType);
		List<DropModel> list = bizOrderInstallItemProblemService.queryInstallItemProblemTypeList(map);
		return JsonMapper.getInstance().toJson(list);
	}


	@RequestMapping(value = "update_install_problem_ajax")
	public @ResponseBody String updateInstallProblemAjax(String problemId, String problemSolveNotes, String status) {

		String result = "0";


		if (null == problemId || problemId.equals("")) {
			result = "1";
			return result;
		}


		if (null == problemSolveNotes || problemSolveNotes.equals("")) {
			result = "2";
			return result;
		}


		if (null == problemSolveNotes || problemSolveNotes.equals("")) {
			result = "3";
			return result;
		}


		BizOrderInstallItemProblem bizOrderInstallItemProblem = bizOrderInstallItemProblemService.get(Integer.valueOf(problemId));
		if (null != bizOrderInstallItemProblem && !bizOrderInstallItemProblem.getStatus().equals(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_10)) {
			result = "4";
			return result;
		}


		User user = UserUtils.getUser();
		Integer managerId = null;
		if (null == user) {
			result = "5";
			return result;
		} else if (null != user.getEmpId()) {
			managerId = Integer.parseInt(user.getEmpId());
		}


		boolean flag = businessWallAndFloorProblemService.updateProblem(bizOrderInstallItemProblem, Integer.valueOf(problemId), status);
		if (!flag) {
			result = "6";
			return result;
		}


		Integer problemLogId = wallAndFloorProblemService.saveProblemLog(Integer.valueOf(problemId), managerId, BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_2, status, problemSolveNotes);
		if (null == problemLogId || problemLogId < 1) {
			result = "7";
			return result;
		}

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "queryBizOrderInstallItemProblemVoById")
	public String queryBizOrderInstallItemProblemVoById(Integer id) {
		BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo = bizOrderInstallItemProblemService.getOrderInstallItemProblemVo(id);
		return JsonMapper.getInstance().toJson(bizOrderInstallItemProblemVo);
	}

	@ResponseBody
	@RequestMapping(value = "queryById")
	public String queryById(Integer id) {
		BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo = bizOrderInstallItemProblemService.queryById(id);
		return JsonMapper.getInstance().toJson(bizOrderInstallItemProblemVo);
	}


	@RequiresPermissions("bizorderinstallitemproblem:bizOrderInstallItemProblem:view")
	@RequestMapping(value = "list2")
	public String list2(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();

		if (null == bizOrderInstallItemProblemVo.getStoreId()) {
			if (null != user.getStoreId()) {
				bizOrderInstallItemProblemVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		if (StringUtils.isBlank(bizOrderInstallItemProblemVo.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}

		bizOrderInstallItemProblemVo.setStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_30);

		bizOrderInstallItemProblemVo.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_1);

		bizOrderInstallItemProblemVo.setPictureType(PictureTypeContantUtil.PICTURE_TYPE_207);

		bizOrderInstallItemProblemVo.setProblemSolveRole(BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_2);

		bizOrderInstallItemProblemVo.setLogStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_30);


		List<Integer> projectInstallItemIdList = new ArrayList<Integer>();
		if (null != bizOrderInstallItemProblemVo.getProjectInstallItemId()) {
			projectInstallItemIdList.add(bizOrderInstallItemProblemVo.getProjectInstallItemId());
		}
		if (null != bizOrderInstallItemProblemVo.getProjectInstallItemIdStop()) {
			projectInstallItemIdList.add(bizOrderInstallItemProblemVo.getProjectInstallItemIdStop());
		}
		if (CollectionUtils.isNotEmpty(projectInstallItemIdList)) {
			bizOrderInstallItemProblemVo.setProjectInstallItemIdList(projectInstallItemIdList);
		}

		Page<BizOrderInstallItemProblemVo> page = bizOrderInstallItemProblemService.findVoPage(new Page<BizOrderInstallItemProblemVo>(request, response), bizOrderInstallItemProblemVo);
		model.addAttribute("page", page);

		return "modules/bizorderinstallitemproblem/bizOrderInstallItemProblemList2";
	}


	@RequiresPermissions("bizorderinstallitemproblem:bizOrderInstallItemProblem:view")
	@RequestMapping(value = "list2Last")
	public String list2Last(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();

		if (null == bizOrderInstallItemProblemVo.getStoreId()) {
			if (null != user.getStoreId()) {
				bizOrderInstallItemProblemVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		if (StringUtils.isBlank(bizOrderInstallItemProblemVo.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}


		bizOrderInstallItemProblemVo.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_1);

		bizOrderInstallItemProblemVo.setPictureType(PictureTypeContantUtil.PICTURE_TYPE_207);

		bizOrderInstallItemProblemVo.setProblemSolveRole(BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_2);

		bizOrderInstallItemProblemVo.setLogStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_30);


		List<Integer> projectInstallItemIdList = new ArrayList<Integer>();
		if (null != bizOrderInstallItemProblemVo.getProjectInstallItemId()) {
			projectInstallItemIdList.add(bizOrderInstallItemProblemVo.getProjectInstallItemId());
		}
		if (null != bizOrderInstallItemProblemVo.getProjectInstallItemIdStop()) {
			projectInstallItemIdList.add(bizOrderInstallItemProblemVo.getProjectInstallItemIdStop());
		}
		if (CollectionUtils.isNotEmpty(projectInstallItemIdList)) {
			bizOrderInstallItemProblemVo.setProjectInstallItemIdList(projectInstallItemIdList);
		}

		Page<BizOrderInstallItemProblemVo> page = bizOrderInstallItemProblemService.findVoPage(new Page<BizOrderInstallItemProblemVo>(request, response), bizOrderInstallItemProblemVo);
		model.addAttribute("page", page);

		return "modules/bizorderinstallitemproblem/bizOrderInstallItemProblemList2";
	}


	@RequestMapping(value = "list3")
	public String list3(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();

		if (null == bizOrderInstallItemProblemVo.getStoreId()) {
			if (null != user.getStoreId()) {
				bizOrderInstallItemProblemVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		if (StringUtils.isBlank(bizOrderInstallItemProblemVo.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}


		List<String> orderdPhones = dataAuthorityService.orderdPhones(DataAuthorityConstantUtils.Biz_Business_Type_DD);
		bizOrderInstallItemProblemVo.setPhones(orderdPhones);

		String userId = user.getId();
		bizOrderInstallItemProblemVo.setUserId(userId);


		if (bizOrderInstallItemProblemVo.getStatus() != null) {
			String[] split = bizOrderInstallItemProblemVo.getStatus().split(",");
			bizOrderInstallItemProblemVo.setParamStatus(Arrays.asList(split));
		}


		bizOrderInstallItemProblemVo.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_1);

		bizOrderInstallItemProblemVo.setPictureType(PictureTypeContantUtil.PICTURE_TYPE_207);


		List<Integer> projectInstallItemIdList = new ArrayList<Integer>();
		if (null != bizOrderInstallItemProblemVo.getProjectInstallItemId()) {
			projectInstallItemIdList.add(bizOrderInstallItemProblemVo.getProjectInstallItemId());
		}
		if (null != bizOrderInstallItemProblemVo.getProjectInstallItemIdStop()) {
			projectInstallItemIdList.add(bizOrderInstallItemProblemVo.getProjectInstallItemIdStop());
		}
		if (CollectionUtils.isNotEmpty(projectInstallItemIdList)) {
			bizOrderInstallItemProblemVo.setProjectInstallItemIdList(projectInstallItemIdList);
		}

		return "modules/bizorderinstallitemproblem/bizOrderInstallItemProblemList3";
	}


	@RequestMapping(value = "list3Last")
	public String list3Last(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();

		if (null == bizOrderInstallItemProblemVo.getStoreId()) {
			if (null != user.getStoreId()) {
				bizOrderInstallItemProblemVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		if (StringUtils.isBlank(bizOrderInstallItemProblemVo.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}


		List<String> orderdPhones = dataAuthorityService.orderdPhones(DataAuthorityConstantUtils.Biz_Business_Type_DD);
		bizOrderInstallItemProblemVo.setPhones(orderdPhones);

		String userId = user.getId();
		bizOrderInstallItemProblemVo.setUserId(userId);


		if (bizOrderInstallItemProblemVo.getStatus() != null) {
			String[] split = bizOrderInstallItemProblemVo.getStatus().split(",");
			bizOrderInstallItemProblemVo.setParamStatus(Arrays.asList(split));
		}

		bizOrderInstallItemProblemVo.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_1);

		bizOrderInstallItemProblemVo.setPictureType(PictureTypeContantUtil.PICTURE_TYPE_207);


		List<Integer> projectInstallItemIdList = new ArrayList<Integer>();
		if (null != bizOrderInstallItemProblemVo.getProjectInstallItemId()) {
			projectInstallItemIdList.add(bizOrderInstallItemProblemVo.getProjectInstallItemId());
		}
		if (null != bizOrderInstallItemProblemVo.getProjectInstallItemIdStop()) {
			projectInstallItemIdList.add(bizOrderInstallItemProblemVo.getProjectInstallItemIdStop());
		}
		if (CollectionUtils.isNotEmpty(projectInstallItemIdList)) {
			bizOrderInstallItemProblemVo.setProjectInstallItemIdList(projectInstallItemIdList);
		}

		Page<BizOrderInstallItemProblemVo> page = bizOrderInstallItemProblemService.findVoPage3(new Page<BizOrderInstallItemProblemVo>(request, response), bizOrderInstallItemProblemVo);
		model.addAttribute("page", page);

		return "modules/bizorderinstallitemproblem/bizOrderInstallItemProblemList3";
	}

	@RequestMapping(value = "export")
	public void export(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo, HttpServletRequest request, HttpServletResponse response) {

		User user = UserUtils.getUser();

		if (null == bizOrderInstallItemProblemVo.getStoreId()) {
			if (null != user.getStoreId()) {
				bizOrderInstallItemProblemVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}

		if (StringUtils.isBlank(bizOrderInstallItemProblemVo.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
			} else {
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
			} else {
				bizOrderInstallItemProblemVo.setProjectMode(user.getProjectMode());
			}
		}


		List<String> orderdPhones = dataAuthorityService.orderdPhones(DataAuthorityConstantUtils.Biz_Business_Type_DD);
		bizOrderInstallItemProblemVo.setPhones(orderdPhones);

		String userId = user.getId();
		bizOrderInstallItemProblemVo.setUserId(userId);

		if (bizOrderInstallItemProblemVo.getStatus() != null) {
			String[] split = bizOrderInstallItemProblemVo.getStatus().split(",");
			bizOrderInstallItemProblemVo.setParamStatus(Arrays.asList(split));
		}


		bizOrderInstallItemProblemVo.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_1);

		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		ServletOutputStream ouputStream = null;
		List<BizOrderInstallItemProblemVo> list = bizOrderInstallItemProblemService.queryOrderInstallItemProblemVoList3(bizOrderInstallItemProblemVo);
		HSSFWorkbook problemDetail = ExportInstallItemProblem.exportInstallItemProblem(list);
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr = new String(("主材安装问题明细表" + sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");
			response.setHeader("Content-disposition", "attachment; filename=" + headerStr + ".xls");
			ouputStream = response.getOutputStream();
			problemDetail.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}


	@RequestMapping(value = "detail")
	public String detail(Integer id, Model model) {

		BizOrderInstallItemProblemVo bizOrderInstallItemProblem = null;
		if (null != id) {
			bizOrderInstallItemProblem = bizOrderInstallItemProblemService.getOrderInstallItemProblemVo(id);
		}

		BizOrderInstallItemProblemLog bizOrderInstallItemProblemLog = new BizOrderInstallItemProblemLog();
		bizOrderInstallItemProblemLog.setBusinessProblemId(id);
		List<BizOrderInstallItemProblemLog> list = bizOrderInstallItemProblemLogService.findList(bizOrderInstallItemProblemLog);

		model.addAttribute("bizOrderInstallItemProblem", bizOrderInstallItemProblem);
		model.addAttribute("list", list);

		return "modules/bizorderinstallitemproblem/detail";
	}

	@RequestMapping(value = "photo")
	public String photo(Integer id, Model model, HttpServletRequest request) throws IOException {

		List<BusinessPicture> pictures = businessPictureService.queryPictureByBussinessIdAndType(id, "207");
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);
		return "modules/bizorderinstallitemproblem/photo";
	}

	@RequestMapping(value = "ajaxPhoto")
	@ResponseBody
	public Map<Object, Object> ajaxPhoto(Integer id, Model model, HttpServletRequest request) throws IOException {

		List<BusinessPicture> pictures = businessPictureService.queryPictureByBussinessIdAndType(id, "207");
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", pictures);

		return mapObject;
	}
}