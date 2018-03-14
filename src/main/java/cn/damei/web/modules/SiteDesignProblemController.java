package cn.damei.web.modules;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.BizOrderInstallItemProblem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.BusinessProblemConstantUtil;
import cn.damei.common.constantUtils.PicturePathContantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.DataAuthorityConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.SiteDesignProblemExport;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.common.Base64Util;
import cn.damei.service.mobile.Manager.WallAndFloorProblemService;
import cn.damei.service.mobile.Manager.BusinessPictureService;
import cn.damei.service.modules.BizOrderInstallItemProblemService;
import cn.damei.service.modules.BusinessWallAndFloorProblemService;
import cn.damei.service.modules.BizOrderInstallItemProblemLogService;
import cn.damei.service.modules.DataAuthorityService;
import cn.damei.entity.modules.BusinessPic;
import cn.damei.entity.modules.SiteDesignProblem;
import cn.damei.service.modules.SiteDesignProblemPCService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/bizorderinstallitemproblem/siteDesignProblem")
public class SiteDesignProblemController extends BaseController {

	@Autowired
	private BizOrderInstallItemProblemService bizOrderInstallItemProblemService;
	@Autowired
	private SiteDesignProblemPCService siteDesignProblemService;
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
	public SiteDesignProblem getOrderInstallItemProblemVo(@RequestParam(required = false) Integer id, SiteDesignProblem siteDesignProblem) {

		if (siteDesignProblem == null) {
			SiteDesignProblem site = new SiteDesignProblem();
			siteDesignProblem = site;
			siteDesignProblem.setStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_10);
		} else {
			String status = siteDesignProblem.getStatus();
			if (status == null) {
				siteDesignProblem.setStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_10);
			}

		}

		return siteDesignProblem;
	}

	@RequestMapping(value = "preList")
	public String preList(SiteDesignProblem siteDesignProblem, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();

		if (null == siteDesignProblem.getStoreId()) {
			if (null != user.getStoreId()) {
				siteDesignProblem.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}



		String projectMode = user.getProjectMode();
		if (projectMode == null || projectMode.equals("3")) {
			siteDesignProblem.setProjectMode(null);
		} else {
			siteDesignProblem.setProjectMode(projectMode);
			model.addAttribute("gongcheng", true);
		}


		return "modules/bizorderinstallitemproblem/siteDesignProblem/siteDesignProblemList";
	}


	@RequestMapping(value = "list")
	public String list2(SiteDesignProblem siteDesignProblem, String flag, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();
		List<String> orderdPhones = dataAuthorityService.orderdPhones(DataAuthorityConstantUtils.Biz_Business_Type_GDSJ);
		siteDesignProblem.setPhones(orderdPhones);
		String userId = user.getId();
		siteDesignProblem.setUserId(userId);
		if (flag != null) {
			model.addAttribute("message", "处理成功！");
		}


		String projectMode = user.getProjectMode();
		if (projectMode == null || projectMode.equals("3")) {
			siteDesignProblem.setProjectMode(null);
		} else {
			siteDesignProblem.setProjectMode(projectMode);
			model.addAttribute("gongcheng", true);
		}


		siteDesignProblem.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_4);

		siteDesignProblem.setPictureType(PictureTypeContantUtil.PICTURE_TYPE_2081);

		siteDesignProblem.setProblemSolveRole(BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_5);

		siteDesignProblem.setLogStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_70);

		Page<SiteDesignProblem> page = siteDesignProblemService.findPage(new Page<SiteDesignProblem>(request, response), siteDesignProblem);
		model.addAttribute("page", page);
		return "modules/bizorderinstallitemproblem/siteDesignProblem/siteDesignProblemList";
	}


	@RequestMapping(value = "details")
	public String detials(String problemId, Model model) {
		SiteDesignProblem siteDesignProblem = null;
		if (null != problemId && !problemId.equals("")) {
			siteDesignProblem = siteDesignProblemService.findDetails(Integer.valueOf(problemId), BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_5, BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_70, PictureTypeContantUtil.PICTURE_TYPE_2082);
		}
		model.addAttribute("siteDesignProblem", siteDesignProblem);
		return "modules/bizorderinstallitemproblem/siteDesignProblem/siteDesignProblemDetails";
	}


	@RequestMapping(value = "handle")
	public String handle(String problemId, String problemSolveNotes, String photos[], HttpServletRequest request) throws IOException {
		String result = "0";


		if (null == problemId || problemId.equals("")) {
			result = "1";
			return result;
		}


		if (null == problemSolveNotes || problemSolveNotes.equals("")) {
			result = "2";
			return result;
		}


		BizOrderInstallItemProblem bizOrderInstallItemProblem = bizOrderInstallItemProblemService.get(Integer.valueOf(problemId));
		if (null != bizOrderInstallItemProblem && bizOrderInstallItemProblem.getStatus().equals(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_70)) {
			result = "3";
			return result;
		}


		User user = UserUtils.getUser();
		Integer managerId = null;
		if (null == user) {
			result = "4";
			return result;
		} else if (null != user.getId()) {
			managerId = Integer.parseInt(user.getId());
		}


		Integer problemLogId = wallAndFloorProblemService.saveProblemLog(Integer.valueOf(problemId), managerId, BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_5, BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_70, problemSolveNotes);
		if (null == problemLogId || problemLogId.intValue() < 1) {
			result = "6";
			return result;
		}

		if (photos != null && photos.length > 0) {

			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String imgUrl = PicturePathContantUtil.UPLOAD_SITEDESIGNPROBLEM;

			for (String pic : photos) {
				logger.info("pictures URL：" + pic);

				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				File filePath = new File(rootPath + imgUrl + DateUtils.getDate1());

				if (!filePath.exists()) {
					filePath.mkdirs();
				}
				String picUrl = imgUrl + DateUtils.getDate1() + "/" + uuid + ".jpeg";
				String fullPath = filePath + filePath.separator + uuid + ".jpeg";
				logger.info("完整路径：" + fullPath);
				Base64Util.generateImage(pic, fullPath.toString());
				BusinessPic bp = new BusinessPic();
				bp.preInsert();
				bp.setBusinessIdInt(problemId);
				bp.setBusinessType(PictureTypeContantUtil.PICTURE_TYPE_2082);
				bp.setPicDatetime(new Date());
				bp.setPicUrl(picUrl);
				siteDesignProblemService.insertBusinessPic(bp);

			}
		}

		boolean flag = businessWallAndFloorProblemService.updateProblem(bizOrderInstallItemProblem, Integer.valueOf(problemId), BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_70);
		if (!flag) {
			result = "5";
			return result;
		}


		return "forward:" + Global.getAdminPath() + "/bizorderinstallitemproblem/siteDesignProblem/list?flag=1";

	}


	@RequestMapping(value = "photo")
	public String findPictureByBusinessProblemId(Integer id, String picType, Model model) {
		List<String> listPath = siteDesignProblemService.findPictureByBusinessProblemId(id, picType);
		model.addAttribute("listPath", listPath);
		return "modules/bizorderinstallitemproblem/siteDesignProblem/photo";
	}


	@RequestMapping(value = "/ajaxPhoto")
	@ResponseBody
	public Map<Object, Object> ajaxPhoto(Integer id, String picType, Model model) {
		List<String> listPath = siteDesignProblemService.findPictureByBusinessProblemId(id, picType);
		model.addAttribute("listPath", listPath);
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", listPath);

		return mapObject;
	}

	@RequestMapping(value = "query")
	public String query(SiteDesignProblem siteDesignProblem, Model model, HttpServletRequest request, HttpServletResponse response) {


		User user = UserUtils.getUser();
		List<String> orderdPhones = dataAuthorityService.orderdPhones(DataAuthorityConstantUtils.Biz_Business_Type_GDSJ);
		siteDesignProblem.setPhones(orderdPhones);
		String userId = user.getId();
		siteDesignProblem.setUserId(userId);

		if (StringUtils.isBlank(siteDesignProblem.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				siteDesignProblem.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				siteDesignProblem.setProjectMode(user.getProjectMode());
			}
		}


		siteDesignProblem.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_4);

		siteDesignProblem.setPictureType(PictureTypeContantUtil.PICTURE_TYPE_2081);

		siteDesignProblem.setProblemSolveRole(BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_5);

		siteDesignProblem.setLogStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_70);
		Page<SiteDesignProblem> page = siteDesignProblemService.findVoPage(new Page<SiteDesignProblem>(request, response), siteDesignProblem);
		model.addAttribute("page", page);
		return "modules/bizorderinstallitemproblem/siteDesignProblem/siteDesignProblemQuery";
	}


	@RequestMapping(value = "exportExcel")
	public String exportExcel(SiteDesignProblem siteDesignProblem, Model model, HttpServletRequest request, HttpServletResponse response) {

		siteDesignProblem.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_4);

		siteDesignProblem.setPictureType(PictureTypeContantUtil.PICTURE_TYPE_2081);

		siteDesignProblem.setProblemSolveRole(BusinessProblemConstantUtil.BUSINESS_PROBLEM_SOLVE_ROLE_5);

		siteDesignProblem.setLogStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_70);

		User user = UserUtils.getUser();
		List<String> orderdPhones = dataAuthorityService.orderdPhones(DataAuthorityConstantUtils.Biz_Business_Type_GDSJ);
		siteDesignProblem.setPhones(orderdPhones);
		String userId = user.getId();
		siteDesignProblem.setUserId(userId);

		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
		List<SiteDesignProblem> findQueryList = siteDesignProblemService.findQueryList(siteDesignProblem);
		SiteDesignProblemExport exs = new SiteDesignProblemExport();
		HSSFWorkbook exportExcel = exs.exportExcel(findQueryList);

		ServletOutputStream out = null;
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr = new String(("工地设计问题-" + sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");
			response.setHeader("Content-disposition", "attachment; filename=" + headerStr + ".xls");
			out = response.getOutputStream();
			exportExcel.write(out);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;

	}

}