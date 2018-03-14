
package cn.damei.web.modules;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.PicturePathContantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.constantUtils.projectProblem.ProjectProblemConstantUtil;
import cn.damei.common.mapper.JsonMapper;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.service.mobile.Inspector.CheckItemService;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.service.mobile.Manager.ApplySwitchPanelService;
import cn.damei.service.mobile.Worker.InstallApplyForCompletionService;
import cn.damei.entity.modules.BizComplaintProblemItem;
import cn.damei.service.modules.BizComplaintProblemItemService;
import cn.damei.entity.modules.BizComplaintProblemType;
import cn.damei.service.modules.BizComplaintProblemTypeService;
import cn.damei.entity.modules.BizCusServiceProblem;
import cn.damei.service.modules.BizCusServiceProblemService;
import cn.damei.common.utils.CusserviceUtils;
import cn.damei.entity.modules.BizOrderComArae;
import cn.damei.entity.modules.BizOrderComplaint;
import cn.damei.entity.modules.BizOrderComplaintProblem;
import cn.damei.entity.modules.BizOrderComplaintProblemDeal;
import cn.damei.entity.modules.BizOrderComplaintProblemItem;
import cn.damei.entity.modules.ComPlUtils;
import cn.damei.service.modules.BizOrderComplaintProblemDealService;
import cn.damei.service.modules.BizOrderComplaintProblemItemService;
import cn.damei.service.modules.BizOrderComplaintProblemService;
import cn.damei.service.modules.BizOrderComplaintService;
import cn.damei.common.utils.ResultData;
import cn.damei.common.utils.SaverPictureUtils;


@Controller
@RequestMapping(value = "${adminPath}/ordercomplan/bizOrderComplaint")
public class BizOrderComplaintController extends BaseController {

	@Autowired
	private BizOrderComplaintService bizOrderComplaintService;
	@Autowired
	private BizCusServiceProblemService bizCusServiceProblemService;
	@Autowired
	private ApplySwitchPanelService applySwitchPanelService;
	@Autowired
	private CheckItemService checkItemService;
	@Autowired
	private BizOrderComplaintProblemDealService bizOrderComplaintProblemDealService;

	@Autowired
	private BizOrderComplaintProblemService bizOrderComplaintProblemService;

	@Autowired
	private BizOrderComplaintProblemItemService bizOrderComplaintProblemItemService;
	@Autowired
	private BizComplaintProblemTypeService bizComplaintProblemTypeService;

	@Autowired
	private BizComplaintProblemItemService bizComplaintProblemItemService;
	@Autowired
	private InstallApplyForCompletionService installApplyForCompletionService;

	@ModelAttribute
	public BizOrderComplaint get(@RequestParam(required = false) String id) {
		BizOrderComplaint entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = bizOrderComplaintService.get(id);
		}
		if (entity == null) {
			entity = new BizOrderComplaint();
		}
		return entity;
	}

	@RequiresPermissions("ordercomplan:bizOrderComplaint:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderComplaint bizOrderComplaint, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizOrderComplaint> page = bizOrderComplaintService.findPageList(new Page<BizOrderComplaint>(request, response), bizOrderComplaint);
		model.addAttribute("page", page);
		return "modules/ordercomplan/bizOrderComplaintList";
	}

	@RequiresPermissions("ordercomplan:bizOrderComplaint:view")
	@RequestMapping(value = "listall")
	public String listall(BizOrderComplaint bizOrderComplaint, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizOrderComplaint> page = bizOrderComplaintService.findPageListall(new Page<BizOrderComplaint>(request, response), bizOrderComplaint);
		model.addAttribute("page", page);
		return "modules/ordercomplan/bizOrderComplaintList";
	}

	@RequiresPermissions("ordercomplan:bizOrderComplaint:view")
	@RequestMapping(value = "mapList")
	public String mapList(BizOrderComplaint bizOrderComplaint, HttpServletRequest request, HttpServletResponse response, Model model) {

		return "modules/mdn_map/order_complaint";

	}

	@RequiresPermissions("ordercomplan:bizOrderComplaint:view")
	@RequestMapping(value = "mapListInfo")
	@ResponseBody
	public List<BizOrderComplaint> mapListInfo(BizOrderComplaint bizOrderComplaint, Model model) {
		List<BizOrderComplaint> orderComplaints = bizOrderComplaintService.findComplaintListForMap(bizOrderComplaint);
		model.addAttribute("entity", bizOrderComplaint);
		return orderComplaints;
	}

	@RequiresPermissions("ordercomplan:bizOrderComplaint:view")
	@RequestMapping(value = "form")
	public String form(BizOrderComplaint bizOrderComplaint, Model model) {
		model.addAttribute("bizOrderComplaint", bizOrderComplaint);
		return "modules/ordercomplan/bizOrderComplaintForm";
	}


	@RequiresPermissions("ordercomplan:bizOrderComplaint:view")
	@RequestMapping(value = "formDetails")
	public String formDetails(BizOrderComplaint bizOrderComplaint, Model model) {
		System.err.println(bizOrderComplaint.getId());

		Map<String, Object> map = bizOrderComplaintService.formDetails(bizOrderComplaint);

		model.addAttribute("entity", map.get("entity"));
		model.addAttribute("list", map.get("list"));

		return "modules/ordercomplan/compDetails";
	}


	@RequiresPermissions("ordercomplan:bizOrderComplaint:view")
	@RequestMapping(value = "formLogPic")
	public String formLogPic(Integer handleId, String handleType, Integer problemId, Model model, HttpServletRequest request) {
		String businessType = "";
		Integer businessIntId = 0;
		if (null == handleId && null != problemId) {
			businessIntId = problemId;
			businessType = PictureTypeContantUtil.PICTURE_TYPE_200;

		} else {

			businessIntId = handleId;
			businessType = null == handleType ? "0" : handleType.equals("1") ? PictureTypeContantUtil.PICTURE_TYPE_109 : PictureTypeContantUtil.PICTURE_TYPE_112;

		}
		List<String> picList = bizOrderComplaintService.findComplaintLogPicByMap(businessIntId, businessType);

		model.addAttribute("picList", picList);


		return "modules/ordercomplan/photo";
	}

	@RequiresPermissions("ordercomplan:bizOrderComplaint:edit")
	@RequestMapping(value = "save")
	public String save(BizOrderComplaint bizOrderComplaint, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizOrderComplaint)) {
			return form(bizOrderComplaint, model);
		}
		bizOrderComplaintService.save(bizOrderComplaint);
		addMessage(redirectAttributes, "保存订单投诉问题成功");
		return "redirect:" + Global.getAdminPath() + "/ordercomplan/bizOrderComplaint/?repage";
	}

	@RequiresPermissions("ordercomplan:bizOrderComplaint:edit")
	@RequestMapping(value = "saveAll")
	public String saveAll(String[] photo, HttpServletRequest request, String[] ordersarea, String orderId, @RequestParam(value = "position[]", required = false) String[] positions, BizOrderComplaint bizOrderComplaint, Model model, RedirectAttributes redirectAttributes) {

		BizOrderComplaint bizOrderComplaint2 = new BizOrderComplaint();

		bizOrderComplaint2.setStoreId(bizOrderComplaint.getOrder().getStoreId());
		bizOrderComplaint2.setOrderId(orderId);
		bizOrderComplaint2.setComplaintSource(bizOrderComplaint.getComplaintSource());
		bizOrderComplaint2.setComplaintPersonName(bizOrderComplaint.getComplaintPersonName());
		bizOrderComplaint2.setComplaintPersonPhone(bizOrderComplaint.getComplaintPersonPhone());
		bizOrderComplaint2.setDataInputChannel("1");
		bizOrderComplaint2.setStatus("10");
		Date createDate = new Date();
		bizOrderComplaint2.setCreateDate(createDate);
		bizOrderComplaint2.setDelFlag("0");
		bizOrderComplaint2.setId(null);
		bizOrderComplaintService.Insert(bizOrderComplaint2);


		BizOrderComplaint bizOrderComplaint3 = new BizOrderComplaint();
		bizOrderComplaint3.setCreateDate(createDate);
		bizOrderComplaint3.setOrderId(orderId);
		BizOrderComplaint bizOrderComplaint4 = bizOrderComplaintService.get(bizOrderComplaint3);
		BizOrderComplaintProblem bizOrderComplaintProblem2 = new BizOrderComplaintProblem();

		bizOrderComplaintProblem2.setOrderComplaintId(bizOrderComplaint4.getComplaintId());

		BizComplaintProblemType bizComplaintProblemType = new BizComplaintProblemType();
		bizComplaintProblemType.setTypeName(bizOrderComplaint.getTypeName());
		BizComplaintProblemType bizComplaintProblemType2 = bizComplaintProblemTypeService.queryComTypeName(bizComplaintProblemType);

		bizOrderComplaintProblem2.setComplaintProblemTypeId(bizComplaintProblemType2.getId());
		if (bizComplaintProblemType2.getTaskPackageTemplatId() != null) {
			bizOrderComplaintProblem2.setTaskPackageTemplatId(bizComplaintProblemType2.getTaskPackageTemplatId() + "");
		}
		if (bizComplaintProblemType2.getPackageId() != null) {
			bizOrderComplaintProblem2.setOrderTaskpackageId(bizComplaintProblemType2.getPackageId() + "");
		}
		bizOrderComplaintProblem2.setComplaintRoleType(bizComplaintProblemType2.getDealPersonType());
		bizOrderComplaintProblem2.setComplaintProblemDescribe(bizOrderComplaint.getTsnr());
		bizOrderComplaintProblem2.setCreateDate(createDate);
		bizOrderComplaintProblem2.setStatus("10");
		bizOrderComplaintProblem2.setId(null);
		bizOrderComplaintProblemService.Insert(bizOrderComplaintProblem2);

		if (bizComplaintProblemType2.getDealPersonType().equals("2")) {




			BizOrderComplaintProblemDeal complaintProblemDeal = new BizOrderComplaintProblemDeal();
			complaintProblemDeal.setOrderComplaintProblemId(bizOrderComplaintProblem2.getId());
			complaintProblemDeal.setDealStatus("0");
			complaintProblemDeal.setDealPersonType(bizComplaintProblemType2.getDealPersonType());

			complaintProblemDeal.setDealStatusDatetime(createDate);
			complaintProblemDeal.setCreateDate(createDate);
			complaintProblemDeal.setId(null);
			bizOrderComplaintProblemDealService.insert(complaintProblemDeal);
		} else if (bizComplaintProblemType2.getDealPersonType().equals("1")) {
			BizOrderComplaintProblem bizOrderComplaintProblem5 = bizOrderComplaintProblemService.queryDealPersonType1(bizOrderComplaintProblem2);
			BizOrderComplaintProblemDeal complaintProblemDeal = new BizOrderComplaintProblemDeal();
			complaintProblemDeal.setOrderComplaintProblemId(bizOrderComplaintProblem2.getId());
			complaintProblemDeal.setDealStatus("0");
			complaintProblemDeal.setDealPersonType(bizComplaintProblemType2.getDealPersonType());
			complaintProblemDeal.setDealEmployeeId(bizOrderComplaintProblem5.getItemmanagerid());
			complaintProblemDeal.setDealStatusDatetime(createDate);
			complaintProblemDeal.setCreateDate(createDate);
			complaintProblemDeal.setId(null);
			bizOrderComplaintProblemDealService.insert(complaintProblemDeal);
		} else if (bizComplaintProblemType2.getDealPersonType().equals("3")) {
			BizOrderComplaintProblem bizOrderComplaintProblem5 = bizOrderComplaintProblemService.queryDealPersonType1(bizOrderComplaintProblem2);
			BizOrderComplaintProblemDeal complaintProblemDeal = new BizOrderComplaintProblemDeal();
			complaintProblemDeal.setOrderComplaintProblemId(bizOrderComplaintProblem2.getId());
			complaintProblemDeal.setDealStatus("0");
			complaintProblemDeal.setDealPersonType(bizComplaintProblemType2.getDealPersonType());
			complaintProblemDeal.setDealEmployeeId(bizOrderComplaintProblem5.getOrderinspectorid());
			complaintProblemDeal.setDealStatusDatetime(createDate);
			complaintProblemDeal.setCreateDate(createDate);
			complaintProblemDeal.setId(null);
			bizOrderComplaintProblemDealService.insert(complaintProblemDeal);
		}

		BizOrderComplaintProblemItem bizOrderComplaintProblemItem = new BizOrderComplaintProblemItem();

		if (ordersarea != null && ordersarea.length > 0) {
			for (int i = 0; i < ordersarea.length; i++) {
				bizOrderComplaintProblemItem.setOrderComplaintProblemId(bizOrderComplaintProblem2.getId());
				bizOrderComplaintProblemItem.setComplaintProblemItemId(ordersarea[i]);
				bizOrderComplaintProblemItem.setId(null);
				bizOrderComplaintProblemItemService.Insert(bizOrderComplaintProblemItem);

			}
		}

		addMessage(redirectAttributes, "保存订单投诉问题成功");
		return "redirect:" + Global.getAdminPath() + "/ordercomplan/bizOrderComplaint/?repage";
	}

	@RequiresPermissions("ordercomplan:bizOrderComplaint:edit")
	@RequestMapping(value = "saveAllBack")
	public String saveAllBack(ComPlUtils bizOrder, String orderId, BizOrderComplaint bizOrderComplaint, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {

		BizOrderComplaint bizOrderComplaint2 = new BizOrderComplaint();

		bizOrderComplaint2.setStoreId(bizOrderComplaint.getOrder().getStoreId());
		bizOrderComplaint2.setOrderId(orderId);
		if (bizOrderComplaint.getComplaintSource() != null) {

		}
		bizOrderComplaint2.setComplaintSource(bizOrderComplaint.getComplaintSource());
		bizOrderComplaint2.setComplaintPersonName(bizOrderComplaint.getComplaintPersonName());
		bizOrderComplaint2.setComplaintPersonPhone(bizOrderComplaint.getComplaintPersonPhone());
		bizOrderComplaint2.setStatus("10");
		Date createDate = new Date();
		bizOrderComplaint2.setCreateDate(createDate);
		bizOrderComplaint2.setDelFlag("0");
		bizOrderComplaint2.setBusinesstype(ProjectProblemConstantUtil.ORDER_COMPLAINT_RELATED_BUSINESS_TYPE_3);
		bizOrderComplaint2.setId(null);
		bizOrderComplaintService.Insert(bizOrderComplaint2);
		List<BizOrderComplaintProblem> bizOrderCompProblemList = bizOrder.getbOrContPros();
		for (BizOrderComplaintProblem bizOrderComplaintProblem : bizOrderCompProblemList) {

			BizOrderComplaintProblem bizOrderComplaintProblem2 = new BizOrderComplaintProblem();

			bizOrderComplaintProblem2.setOrderComplaintId(bizOrderComplaint2.getComplaintId());

			bizOrderComplaintProblem2.setComplaintProblemTypeId(bizOrderComplaintProblem.getComplaintProblemTypeId());


			BizComplaintProblemType bizComplaintProblemType = new BizComplaintProblemType();
			bizComplaintProblemType.setComplaintProblemTypeId(bizOrderComplaintProblem.getComplaintProblemTypeId());
			bizComplaintProblemType.setOrderId(orderId);
			List<BizComplaintProblemType> bizComplaintProblemType2List = bizComplaintProblemTypeService.queryComTypeid(bizComplaintProblemType);



			if (bizComplaintProblemType2List.size() > 0) {

				if (bizOrderComplaintProblem.getComplaintRoleType().equals("项目经理")) {
					bizOrderComplaintProblem.setComplaintRoleType("1");
				} else if (bizOrderComplaintProblem.getComplaintRoleType().equals("工人+项目经理")) {
					bizOrderComplaintProblem.setComplaintRoleType("2");
				} else if (bizOrderComplaintProblem.getComplaintRoleType().equals("质检员")) {
					bizOrderComplaintProblem.setComplaintRoleType("3");
				}

				if (bizComplaintProblemType2List.get(0).getTaskPackageTemplatId() != null) {
					bizOrderComplaintProblem2.setTaskPackageTemplatId(bizComplaintProblemType2List.get(0).getTaskPackageTemplatId() + "");
				}
				if (bizComplaintProblemType2List.get(0).getPackageId() != null) {
					bizOrderComplaintProblem2.setOrderTaskpackageId(bizComplaintProblemType2List.get(0).getPackageId() + "");
				}
				bizOrderComplaintProblem2.setComplaintRoleType(bizOrderComplaintProblem.getComplaintRoleType());
				bizOrderComplaintProblem2.setComplaintProblemDescribe(bizOrderComplaintProblem.getComplaintProblemnei());
				bizOrderComplaintProblem2.setCreateDate(createDate);
				bizOrderComplaintProblem2.setComplaintProblemDescribe(bizOrderComplaintProblem.getComplaintProblemNr());
				bizOrderComplaintProblem2.setStatus("10");
				bizOrderComplaintProblem2.setId(null);
				bizOrderComplaintProblemService.Insert(bizOrderComplaintProblem2);


				MultipartFile[] photo = bizOrderComplaintProblem.getPhoto();

				if (photo != null && photo.length > 0) {

					for (int i = 0; i < photo.length; i++) {
						MultipartFile file = photo[i];

						List<ReportCheckDetailsPic> saveFile = saveFile(file, null, request, bizOrderComplaintProblem2.getId());
						if (saveFile != null)
							checkItemService.savePic(saveFile);

					}
				}


				if (bizOrderComplaintProblem.getComplaintRoleType().equals("2")) {


					List<BizOrderComplaintProblem> bizOrderComplaintProblemList = bizOrderComplaintProblemService.queryProblemdeal(bizOrderComplaintProblem2);
					BizOrderComplaintProblem bizOrderComplaintProblem3 = bizOrderComplaintProblemList.get(0);
					String empids = bizOrderComplaintProblem3.getEmpids();
					BizOrderComplaintProblemDeal complaintProblemDeal = new BizOrderComplaintProblemDeal();
					complaintProblemDeal.setOrderComplaintProblemId(bizOrderComplaintProblem2.getId());
					complaintProblemDeal.setDealStatus("0");
					complaintProblemDeal.setDealStatusDatetime(createDate);
					complaintProblemDeal.setCreateDate(createDate);
					if (empids != null) {
						String[] split = empids.split(",");
						complaintProblemDeal.setDealPersonType("2");
						for (String string : split) {
							complaintProblemDeal.setDealEmployeeId(string);
							complaintProblemDeal.setId(null);
							bizOrderComplaintProblemDealService.insert(complaintProblemDeal);
							savePhone(bizOrderComplaintProblem2.getId(), bizOrderComplaintProblem3.getCustomername(), bizOrderComplaintProblem3.getCustaddress(), bizOrderComplaintProblem.getCiIds(), Integer.parseInt(string));
						}
					}
					complaintProblemDeal.setDealPersonType("1");
					complaintProblemDeal.setId(null);
					complaintProblemDeal.setDealEmployeeId(bizOrderComplaintProblem3.getItemmanagerid());
					bizOrderComplaintProblemDealService.insert(complaintProblemDeal);
					savePhone(bizOrderComplaintProblem2.getId(), bizOrderComplaintProblem3.getCustomername(), bizOrderComplaintProblem3.getCustaddress(), bizOrderComplaintProblem.getCiIds(), Integer.parseInt(bizOrderComplaintProblem3.getItemmanagerid()));
				} else if (bizOrderComplaintProblem.getComplaintRoleType().equals("1")) {
					BizOrderComplaintProblem bizOrderComplaintProblem5 = bizOrderComplaintProblemService.queryDealPersonType1(bizOrderComplaintProblem2);
					BizOrderComplaintProblemDeal complaintProblemDeal = new BizOrderComplaintProblemDeal();
					complaintProblemDeal.setOrderComplaintProblemId(bizOrderComplaintProblem2.getId());
					complaintProblemDeal.setDealStatus("0");
					complaintProblemDeal.setDealPersonType(bizOrderComplaintProblem.getComplaintRoleType());
					complaintProblemDeal.setDealEmployeeId(bizOrderComplaintProblem5.getItemmanagerid());
					complaintProblemDeal.setDealStatusDatetime(createDate);
					complaintProblemDeal.setCreateDate(createDate);
					complaintProblemDeal.setId(null);
					bizOrderComplaintProblemDealService.insert(complaintProblemDeal);
					savePhone(bizOrderComplaintProblem2.getId(), bizOrderComplaintProblem5.getCustomername(), bizOrderComplaintProblem5.getCustaddress(), bizOrderComplaintProblem.getCiIds(), Integer.parseInt(bizOrderComplaintProblem5.getItemmanagerid()));
				} else if (bizOrderComplaintProblem.getComplaintRoleType().equals("3")) {
					BizOrderComplaintProblem bizOrderComplaintProblem5 = bizOrderComplaintProblemService.queryDealPersonType1(bizOrderComplaintProblem2);
					BizOrderComplaintProblemDeal complaintProblemDeal = new BizOrderComplaintProblemDeal();
					complaintProblemDeal.setOrderComplaintProblemId(bizOrderComplaintProblem2.getId());
					complaintProblemDeal.setDealStatus("0");
					complaintProblemDeal.setDealPersonType(bizOrderComplaintProblem.getComplaintRoleType());
					complaintProblemDeal.setDealEmployeeId(bizOrderComplaintProblem5.getOrderinspectorid());
					complaintProblemDeal.setDealStatusDatetime(createDate);
					complaintProblemDeal.setCreateDate(createDate);
					complaintProblemDeal.setId(null);
					bizOrderComplaintProblemDealService.insert(complaintProblemDeal);
					savePhone(bizOrderComplaintProblem2.getId(), bizOrderComplaintProblem5.getCustomername(), bizOrderComplaintProblem5.getCustaddress(), bizOrderComplaintProblem.getCiIds(), Integer.parseInt(bizOrderComplaintProblem5.getOrderinspectorid()));
				}

				BizOrderComplaintProblemItem bizOrderComplaintProblemItem = new BizOrderComplaintProblemItem();
				if (bizOrderComplaintProblem.getCiIds() != null) {
					String ciIds2 = bizOrderComplaintProblem.getCiIds();
					String[] split = ciIds2.split(",");
					if (split != null && split.length > 0) {
						for (int i = 0; i < split.length; i++) {
							bizOrderComplaintProblemItem.setOrderComplaintProblemId(bizOrderComplaintProblem2.getId());
							bizOrderComplaintProblemItem.setComplaintProblemItemId(split[i]);
							bizOrderComplaintProblemItem.setId(null);
							bizOrderComplaintProblemItemService.Insert(bizOrderComplaintProblemItem);
						}
					}
				}
			}

		}

		addMessage(redirectAttributes, "保存订单投诉问题成功");

		return "redirect:" + Global.getAdminPath() + "/ordercomplan/bizOrderComplaint/listall?repage";
	}


	@RequestMapping(value = "saveAllafte")
	public String saveAllafte(ComPlUtils bizOrder, String workOrderCode, String orderId, String cusServiceId, String beforehandDatehou, String disposefaHou, String executeTimeLimit, BizOrderComplaint bizOrderComplaint, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {

		BizOrderComplaint bizOrderComplaint2 = new BizOrderComplaint();

		bizOrderComplaint2.setStoreId(bizOrderComplaint.getOrder().getStoreId());
		bizOrderComplaint2.setOrderId(orderId);
		bizOrderComplaint2.setComplaintSource("4");

		bizOrderComplaint2.setComplaintPersonName(bizOrderComplaint.getComplaintPersonName());
		bizOrderComplaint2.setComplaintPersonPhone(bizOrderComplaint.getComplaintPersonPhone());
		bizOrderComplaint2.setStatus("10");
		bizOrderComplaint2.setBusinessid(Integer.parseInt(bizOrderComplaint.getCusServiceId()));
		bizOrderComplaint2.setCusServiceProblemId(cusServiceId);
		Date createDate = new Date();
		bizOrderComplaint2.setCreateDate(createDate);
		bizOrderComplaint2.setDelFlag("0");
		bizOrderComplaint2.setBusinesstype(ProjectProblemConstantUtil.ORDER_COMPLAINT_RELATED_BUSINESS_TYPE_2);


		bizOrderComplaint2.setId(null);
		bizOrderComplaintService.Insert(bizOrderComplaint2);
		List<BizOrderComplaintProblem> bizOrderCompProblemList = bizOrder.getbOrContPros();
		for (BizOrderComplaintProblem bizOrderComplaintProblem : bizOrderCompProblemList) {


			BizOrderComplaintProblem bizOrderComplaintProblem2 = new BizOrderComplaintProblem();

			bizOrderComplaintProblem2.setOrderComplaintId(bizOrderComplaint2.getComplaintId());

			bizOrderComplaintProblem2.setComplaintProblemTypeId(bizOrderComplaintProblem.getComplaintProblemTypeId());


			BizComplaintProblemType bizComplaintProblemType = new BizComplaintProblemType();
			bizComplaintProblemType.setComplaintProblemTypeId(bizOrderComplaintProblem.getComplaintProblemTypeId());
			bizComplaintProblemType.setOrderId(orderId);
			List<BizComplaintProblemType> bizComplaintProblemType2List = bizComplaintProblemTypeService.queryComTypeid(bizComplaintProblemType);


			if (bizComplaintProblemType2List.size() > 0) {
				if (bizOrderComplaintProblem.getComplaintRoleType().equals("项目经理")) {
					bizOrderComplaintProblem.setComplaintRoleType("1");
				} else if (bizOrderComplaintProblem.getComplaintRoleType().equals("工人+项目经理")) {
					bizOrderComplaintProblem.setComplaintRoleType("2");
				} else if (bizOrderComplaintProblem.getComplaintRoleType().equals("质检员")) {
					bizOrderComplaintProblem.setComplaintRoleType("3");
				}
				if (bizComplaintProblemType2List.get(0).getTaskPackageTemplatId() != null) {
					bizOrderComplaintProblem2.setTaskPackageTemplatId(bizComplaintProblemType2List.get(0).getTaskPackageTemplatId() + "");
				}
				if (bizComplaintProblemType2List.get(0).getPackageId() != null) {
					bizOrderComplaintProblem2.setOrderTaskpackageId(bizComplaintProblemType2List.get(0).getPackageId() + "");
				}
				bizOrderComplaintProblem2.setComplaintRoleType(bizOrderComplaintProblem.getComplaintRoleType());
				bizOrderComplaintProblem2.setComplaintProblemDescribe(bizOrderComplaintProblem.getComplaintProblemnei());
				bizOrderComplaintProblem2.setCreateDate(createDate);
				bizOrderComplaintProblem2.setComplaintProblemDescribe(bizOrderComplaintProblem.getComplaintProblemNr());
				bizOrderComplaintProblem2.setStatus("10");
				bizOrderComplaintProblem2.setId(null);
				bizOrderComplaintProblemService.Insert(bizOrderComplaintProblem2);


				MultipartFile[] photo = bizOrderComplaintProblem.getPhoto();
				String[] split2 = null;
				if (null != bizOrderComplaintProblem.getPhotos()) {
					split2 = bizOrderComplaintProblem.getPhotos().split(",");
				}

				if ((photo != null && photo.length > 0) || (split2 != null && split2.length > 0)) {

					if (null != split2 && split2.length > 0) {
						for (int i = 0; i < split2.length; i++) {

							List<ReportCheckDetailsPic> saveFile = saveFileSall(split2[i], request, bizOrderComplaintProblem2.getId());
							checkItemService.savePic(saveFile);
						}
					}

					for (int i = 0; i < photo.length; i++) {
						MultipartFile file = photo[i];

						List<ReportCheckDetailsPic> saveFile = saveFile(file, split2, request, bizOrderComplaintProblem2.getId());
						if (saveFile != null)
							checkItemService.savePic(saveFile);
					}

				}


				if (bizOrderComplaintProblem.getComplaintRoleType().equals("2")) {


					List<BizOrderComplaintProblem> bizOrderComplaintProblemList = bizOrderComplaintProblemService.queryProblemdeal(bizOrderComplaintProblem2);
					BizOrderComplaintProblem bizOrderComplaintProblem3 = bizOrderComplaintProblemList.get(0);
					String empids = bizOrderComplaintProblem3.getEmpids();
					BizOrderComplaintProblemDeal complaintProblemDeal = new BizOrderComplaintProblemDeal();
					complaintProblemDeal.setOrderComplaintProblemId(bizOrderComplaintProblem2.getId());
					complaintProblemDeal.setDealStatus("0");
					complaintProblemDeal.setDealStatusDatetime(createDate);
					complaintProblemDeal.setCreateDate(createDate);
					if (empids != null) {
						String[] split = empids.split(",");
						complaintProblemDeal.setDealPersonType("2");
						for (String string : split) {
							complaintProblemDeal.setDealEmployeeId(string);
							complaintProblemDeal.setId(null);
							bizOrderComplaintProblemDealService.insert(complaintProblemDeal);
							savePhone(bizOrderComplaintProblem2.getId(), bizOrderComplaintProblem3.getCustomername(), bizOrderComplaintProblem3.getCustaddress(), bizOrderComplaintProblem.getCiIds(), Integer.parseInt(string));
						}
					}
					complaintProblemDeal.setDealPersonType("1");
					complaintProblemDeal.setId(null);
					complaintProblemDeal.setDealEmployeeId(bizOrderComplaintProblem3.getItemmanagerid());
					bizOrderComplaintProblemDealService.insert(complaintProblemDeal);
					savePhone(bizOrderComplaintProblem2.getId(), bizOrderComplaintProblem3.getCustomername(), bizOrderComplaintProblem3.getCustaddress(), bizOrderComplaintProblem.getCiIds(), Integer.parseInt(bizOrderComplaintProblem3.getItemmanagerid()));
				} else if (bizOrderComplaintProblem.getComplaintRoleType().equals("1")) {
					BizOrderComplaintProblem bizOrderComplaintProblem5 = bizOrderComplaintProblemService.queryDealPersonType1(bizOrderComplaintProblem2);
					BizOrderComplaintProblemDeal complaintProblemDeal = new BizOrderComplaintProblemDeal();
					complaintProblemDeal.setOrderComplaintProblemId(bizOrderComplaintProblem2.getId());
					complaintProblemDeal.setDealStatus("0");
					complaintProblemDeal.setDealPersonType(bizOrderComplaintProblem.getComplaintRoleType());
					complaintProblemDeal.setDealEmployeeId(bizOrderComplaintProblem5.getItemmanagerid());
					complaintProblemDeal.setDealStatusDatetime(createDate);
					complaintProblemDeal.setCreateDate(createDate);
					complaintProblemDeal.setId(null);
					bizOrderComplaintProblemDealService.insert(complaintProblemDeal);
					savePhone(bizOrderComplaintProblem2.getId(), bizOrderComplaintProblem5.getCustomername(), bizOrderComplaintProblem5.getCustaddress(), bizOrderComplaintProblem.getCiIds(), Integer.parseInt(bizOrderComplaintProblem5.getItemmanagerid()));
				} else if (bizOrderComplaintProblem.getComplaintRoleType().equals("3")) {
					BizOrderComplaintProblem bizOrderComplaintProblem5 = bizOrderComplaintProblemService.queryDealPersonType1(bizOrderComplaintProblem2);
					BizOrderComplaintProblemDeal complaintProblemDeal = new BizOrderComplaintProblemDeal();
					complaintProblemDeal.setOrderComplaintProblemId(bizOrderComplaintProblem2.getId());
					complaintProblemDeal.setDealStatus("0");
					complaintProblemDeal.setDealPersonType(bizOrderComplaintProblem.getComplaintRoleType());
					complaintProblemDeal.setDealEmployeeId(bizOrderComplaintProblem5.getOrderinspectorid());
					complaintProblemDeal.setDealStatusDatetime(createDate);
					complaintProblemDeal.setCreateDate(createDate);
					complaintProblemDeal.setId(null);
					bizOrderComplaintProblemDealService.insert(complaintProblemDeal);
					savePhone(bizOrderComplaintProblem2.getId(), bizOrderComplaintProblem5.getCustomername(), bizOrderComplaintProblem5.getCustaddress(), bizOrderComplaintProblem.getCiIds(), Integer.parseInt(bizOrderComplaintProblem5.getOrderinspectorid()));
				}

				BizOrderComplaintProblemItem bizOrderComplaintProblemItem = new BizOrderComplaintProblemItem();
				if (bizOrderComplaintProblem.getCiIds() != null) {
					String ciIds2 = bizOrderComplaintProblem.getCiIds();
					String[] split = ciIds2.split(",");
					if (split != null && split.length > 0) {
						for (int i = 0; i < split.length; i++) {
							bizOrderComplaintProblemItem.setOrderComplaintProblemId(bizOrderComplaintProblem2.getId());
							bizOrderComplaintProblemItem.setComplaintProblemItemId(split[i]);
							bizOrderComplaintProblemItem.setId(null);
							bizOrderComplaintProblemItemService.Insert(bizOrderComplaintProblemItem);
						}
					}
				}
			}
		}

		String sendHttp1 = CusserviceUtils.sendHttp(workOrderCode, "RECEIVED", null);
		String sendHttp2 = "";

		if (sendHttp1.equals("SUCCESS")) {
			BizCusServiceProblem bizCusServiceProblem = new BizCusServiceProblem();
			bizCusServiceProblem.setBeforehandDatehou(beforehandDatehou);
			bizCusServiceProblem.setRemarks(disposefaHou);
			sendHttp2 = CusserviceUtils.sendHttp(workOrderCode, "PROCESSING", bizCusServiceProblem);
			logger.error("cn.damei.web.modules.BizOrderComplaintController.saveAllafte:接收成功");
		} else {
			addMessage(redirectAttributes, "接收售后问题失败!原因:" + sendHttp1);
			logger.error("cn.damei.web.modules.BizOrderComplaintController.saveAllafte:接收失败:" + sendHttp1);
			return "redirect:" + Global.getAdminPath() + "/cusserviceproblem/bizCusServiceProblem/list?repage";
		}
		if (sendHttp2.equals("SUCCESS")) {
			BizCusServiceProblem bizCusServiceProblem = new BizCusServiceProblem();
			bizCusServiceProblem.setId(bizOrderComplaint.getCusServiceId());
			bizCusServiceProblem.setTreamentTime(beforehandDatehou);
			bizCusServiceProblem.setRemarks(disposefaHou);
			bizCusServiceProblem.setStatus(ProjectProblemConstantUtil.PROJECT_PROBLEM_COMPLAINT_STATUS_10);
			bizCusServiceProblem.setStatusdatetime(new Date());
			bizCusServiceProblemService.updateYu(bizCusServiceProblem);
			logger.error("cn.damei.web.modules.BizOrderComplaintController.saveAllafte处理成功");
			addMessage(redirectAttributes, "接收售后问题成功");
		} else {
			addMessage(redirectAttributes, "接收售后问题失败!原因:" + sendHttp2);
			logger.error("cn.damei.web.modules.BizOrderComplaintController.saveAllafte处理失败:" + sendHttp1);
			return "redirect:" + Global.getAdminPath() + "/cusserviceproblem/bizCusServiceProblem/list?repage";
		}

		addMessage(redirectAttributes, "接收售后问题成功");

		return "redirect:" + Global.getAdminPath() + "/cusserviceproblem/bizCusServiceProblem/list?repage";
	}

	private List<ReportCheckDetailsPic> saveFileSall(String split2, HttpServletRequest request, String string) {

		List<ReportCheckDetailsPic> savePhoto = null;

		savePhoto = SaverPictureUtils.savePhoto(split2, request, string);

		return savePhoto;
	}


	@RequestMapping(value = "ajaxupdateScuStats")
	@ResponseBody
	public Map<String, Object> ajaxupdateScuStats(String workOrderCode, String cusServiceid, String status) {



		String sendHttp2 = "";
		String result = "";
		String msg = "";

		sendHttp2 = CusserviceUtils.sendHttp(workOrderCode, "COMPLETED", null);
		result = "1";

		if (null != sendHttp2 && sendHttp2.equals("SUCCESS")) {
			BizCusServiceProblem bizCusServiceProblem = new BizCusServiceProblem();
			bizCusServiceProblem.setId(cusServiceid);
			bizCusServiceProblem.setStatus(status);
			bizCusServiceProblem.setStatus(ProjectProblemConstantUtil.PROJECT_PROBLEM_COMPLAINT_STATUS_10);
			bizCusServiceProblem.setStatusdatetime(new Date());
			bizCusServiceProblemService.update(bizCusServiceProblem);
			result = "1";
		} else {
			result = "0";
			msg = sendHttp2;
			if (null == sendHttp2 || sendHttp2.equals("")) {
				msg = "请求接口失败";
			}
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("code", result);
		resultMap.put("msg", msg);
		return resultMap;
	}

	@RequiresPermissions("ordercomplan:bizOrderComplaint:edit")
	@RequestMapping(value = "delete")
	public String delete(BizOrderComplaint bizOrderComplaint, RedirectAttributes redirectAttributes) {
		bizOrderComplaintService.delete(bizOrderComplaint);
		addMessage(redirectAttributes, "删除订单投诉问题成功");
		return "redirect:" + Global.getAdminPath() + "/ordercomplan/bizOrderComplaint/?repage";
	}

	@RequestMapping(value = "uploadPic")
	public String uploadPic(String[] photo, HttpServletRequest request, BizOrderComplaint bizOrderComplaint) {

		if (null != photo && photo.length > 0) {



		}

		System.err.println();

		return "redirect:" + Global.getAdminPath() + "/ordercomplan/bizOrderComplaint/?repage";
	}


	@RequestMapping("/getModelList")
	@ResponseBody
	public Map<String, Object> getModelList(Integer storeId) {

		List<BizComplaintProblemType> allListmodelList = bizOrderComplaintService.getSrmsModelListByBrandId(storeId);
		Map<String, Object> returnMap = Maps.newHashMap();

		returnMap.put("modelList", allListmodelList);

		return returnMap;
	}


	@RequestMapping("/gettasklList")
	@ResponseBody
	public Map<String, Object> gettasklList(BizComplaintProblemType bizComplaintProblemType) {



		List<BizComplaintProblemType> queryComTypeidList = bizComplaintProblemTypeService.queryComTypeid(bizComplaintProblemType);
		BizComplaintProblemType queryComTypeid = queryComTypeidList.get(0);
		if (queryComTypeid.getDealPersonType().equals("1")) {
			queryComTypeid.setDealPersonType("项目经理");
		} else if (queryComTypeid.getDealPersonType().equals("2")) {
			queryComTypeid.setDealPersonType("工人+项目经理");
		} else if (queryComTypeid.getDealPersonType().equals("3")) {
			queryComTypeid.setDealPersonType("质检员");
		}
		if (queryComTypeid.getPackName() == null) {
			queryComTypeid.setPackName("");
		}
		List<BizComplaintProblemType> bizComplaintProblemTypes = new ArrayList<BizComplaintProblemType>();
		bizComplaintProblemTypes.add(queryComTypeid);
		Map<String, Object> returnMap = Maps.newHashMap();
		String substring = null;

		if (bizComplaintProblemTypes != null) {
			BizComplaintProblemItem bizComplaintProblemItem = new BizComplaintProblemItem();
			bizComplaintProblemItem.setComplaintProblemTypeId(Integer.parseInt(queryComTypeid.getComplaintProblemTypeId()));
			List<BizComplaintProblemItem> bcpiList = bizComplaintProblemItemService.getcomplaintProblemTypeId(bizComplaintProblemItem);
			if (bcpiList.size() > 0) {
				StringBuffer buffer = new StringBuffer();
				for (BizComplaintProblemItem bizComplaintProblemItem2 : bcpiList) {
					buffer.append(bizComplaintProblemItem2.getId() + ",");
				}
				substring = buffer.toString().substring(0, buffer.toString().length() - 1);
			}
		}
		returnMap.put("substring", substring);
		returnMap.put("ComplaintProblemType", bizComplaintProblemTypes);

		return returnMap;
	}


	@RequestMapping(value = "ajaxTypeItem")
	@ResponseBody
	public String ajaxTypeItem(BizComplaintProblemItem bizComplaintProblemItem, HttpServletRequest request, HttpServletResponse response, Model model) {

		List<BizComplaintProblemItem> bcpiList = bizComplaintProblemItemService.getcomplaintProblemTypeId(bizComplaintProblemItem);
		List<BizOrderComArae> araes = new ArrayList<BizOrderComArae>();
		for (BizComplaintProblemItem bizOrderComArae : bcpiList) {
			BizOrderComArae araesbean = new BizOrderComArae();
			araesbean.setLabel(bizOrderComArae.getItemName());
			araesbean.setValue(bizOrderComArae.getId());
			araes.add(araesbean);
		}
		return JsonMapper.getInstance().toJson(araes);
	}



	@RequestMapping(value = "ajaxTypeItemAndEx")
	@ResponseBody
	public Map<String, Object> ajaxTypeItemAndEx(BizComplaintProblemItem bizComplaintProblemItem, HttpServletRequest request, HttpServletResponse response, Model model) {

		List<BizComplaintProblemItem> bcpiList = bizComplaintProblemItemService.getcomplaintProblemId(bizComplaintProblemItem);
		Map<String, Object> returnMap = Maps.newHashMap();
		for (BizComplaintProblemItem bizOrderComArae : bcpiList) {
			BizComplaintProblemItem bizComplaintProblemItem2 = new BizComplaintProblemItem();
			bizComplaintProblemItem2.setExecuteTimeLimit(bizOrderComArae.getExecuteTimeLimit());
			returnMap.put("bizComplaintProblemItem", bizComplaintProblemItem2);

		}
		return returnMap;
	}

	@RequestMapping(value = "/uploadview")
	public String uploadview(BizComplaintProblemItem bizComplaintProblemItem, HttpServletRequest request, HttpServletResponse response, Model model) {




		return "modules/ordercomplan/uploadOrderComplaintForm";

	}

	@RequestMapping(value = "/upload")
	public ResultData<Object> upload(BizComplaintProblemItem bizComplaintProblemItem, @RequestParam(value = "photo") MultipartFile[] photo, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		ResultData<Object> resultData = new ResultData<>();

		if (photo != null && photo.length > 0) {

			for (int i = 0; i < photo.length; i++) {
				MultipartFile file = photo[i];

				saveFile(file, null, request, adminPath);
			}
		}

		return resultData;



	}

	private List<ReportCheckDetailsPic> saveFile(MultipartFile photo, String[] split2, HttpServletRequest request, String compd) {
		List<ReportCheckDetailsPic> savePhoto = null;
		if (photo != null) {
			String type = null;
			String fileName = photo.getOriginalFilename();
			System.out.println("上传的文件原名称:" + fileName);


			type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
			if (type != null) {
				if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {

					String uuid = UUID.randomUUID().toString().replaceAll("-", "");


					String rootPath = request.getSession().getServletContext().getRealPath("");
					File filePath = new File(rootPath + PicturePathContantUtil.UPLOAD_COMPLAINTPROBLEM_PROJECT_DEAL + DateUtils.getDate1());

					if (!filePath.exists() && !filePath.isDirectory()) {
						filePath.mkdirs();
					}
					String filepath = filePath + filePath.separator + uuid + ".jpeg";
					try {
						photo.transferTo(new File(filepath));
					} catch (IllegalStateException | IOException e) {

						e.printStackTrace();
					}

					String picpath = PicturePathContantUtil.UPLOAD_COMPLAINTPROBLEM_PROJECT_DEAL + DateUtils.getDate1() + filePath.separator + uuid + ".jpeg";

					savePhoto = SaverPictureUtils.savePhoto(picpath, request, compd);
				}
			}

		}
		return savePhoto;

	}



































	public void savePhone(String orderComplaintId, String customerName, String customerAddress, String itemIds, Integer... employeeId) {

		bizOrderComplaintService.saveMessageContent(Integer.parseInt(orderComplaintId), customerAddress, customerName, itemIds, employeeId);

	}

}