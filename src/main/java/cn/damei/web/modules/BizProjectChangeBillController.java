/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.*;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.service.modules.BizBusinessStatusLogService;
import cn.damei.entity.modules.BizBusinessPic;
import cn.damei.service.modules.BizBusinessPicService;
import cn.damei.entity.modules.BizMsg;
import cn.damei.entity.modules.BizProjectChangeBill;
import cn.damei.entity.modules.BizProjectChangeBillItem;
import cn.damei.service.modules.BizProjectChangeBillService;
import cn.damei.entity.modules.BizSynData;
import cn.damei.service.modules.BizSynDataService;
import cn.damei.service.modules.DataAuthorityService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;
import cn.damei.entity.modules.Office;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 设计师审核Controller
 * 
 * @author wyb
 * @version 2016-11-16
 */
@Controller
@RequestMapping(value = "${adminPath}/bizprojectchangebill/bizProjectChangeBill")
public class BizProjectChangeBillController extends BaseController {

	@Autowired
	private BizProjectChangeBillService bizProjectChangeBillService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private DataAuthorityService dataAuthorityService;
	@Autowired
	private BizBusinessPicService bizBusinessPicService;
	@Autowired
	private BizBusinessStatusLogService bizBusinessStatusLogService;
	@Autowired
	private BizSynDataService bizSynDataService;

	@ModelAttribute
	public BizProjectChangeBill get(@RequestParam(required = false) String id) {
		BizProjectChangeBill entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = bizProjectChangeBillService.get(id);
		}
		if (entity == null) {
			entity = new BizProjectChangeBill();
		}
		return entity;
	}

	/**
	 * 设计师审核
	 * 
	 * @param bizProjectChangeBill
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizprojectchangebill:bizProjectChangeBill:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizProjectChangeBill bizProjectChangeBill, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();
		// 过滤门店
		/*
		 * List<Role> roleList = user.getRoleList(); Role role2 =
		 * roleList.get(0); String string = role2.getId();
		 * List<BizProjectChangeBill> storeIds =
		 * bizProjectChangeBillService.findAllStore(string);
		 * model.addAttribute("storeIds", storeIds);
		 */
		if (null == bizProjectChangeBill.getStoreId()) {
			if (null != user.getStoreId()) {
				bizProjectChangeBill.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
			model.addAttribute("gongcheng", true);
		} else {
			bizProjectChangeBill.setProjectMode(user.getProjectMode());
		}

		return "modules/bizprojectchangebill/bizProjectChangeBillList";
	}

	/**
	 * 设计师审核
	 * 
	 * @param bizProjectChangeBill
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizprojectchangebill:bizProjectChangeBill:view")
	@RequestMapping(value = { "designerList", "" })
	public String designerList(BizProjectChangeBill bizProjectChangeBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		bizProjectChangeBill.setType("1");

		User user = UserUtils.getUser();
		// 过滤门店
		if (null == bizProjectChangeBill.getStoreId()) {
			if (null != user.getStoreId()) {
				bizProjectChangeBill.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
			model.addAttribute("gongcheng", true);
		} else {
			bizProjectChangeBill.setProjectMode(user.getProjectMode());
		}

		Page<BizProjectChangeBill> page = bizProjectChangeBillService.findPage(new Page<BizProjectChangeBill>(request, response), bizProjectChangeBill);
		model.addAttribute("page", page);
		return "modules/bizprojectchangebill/bizProjectChangeBillList";
	}

	/**
	 * 设计师审核2
	 * 
	 * @param bizProjectChangeBill
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizprojectchangebill:bizProjectChangeBill:view")
	@RequestMapping(value = { "designerList2", "" })
	public String designerList2(BizProjectChangeBill bizProjectChangeBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		bizProjectChangeBill.setType("1");
		User user = UserUtils.getUser();
		/*
		 * if(null==bizProjectChangeBill.getStoreId()){
		 * if(null!=user.getStoreId()){ Office office = user.getOffice(); String
		 * parentIds = office.getParentIds(); String substring =
		 * parentIds.substring(4, 5);
		 * bizProjectChangeBill.setStoreId(substring); } }
		 * if(StringUtils.isBlank(user.getStoreId())){
		 * model.addAttribute("storeDropEnable", true); }
		 */

		// 过滤工程模式
		if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
			model.addAttribute("gongcheng", true);
		} else {
			bizProjectChangeBill.setProjectMode(user.getProjectMode());
		}

		List<String> orderdPhones = dataAuthorityService.orderdPhones(DataAuthorityConstantUtils.Biz_Business_Type_SGBGD);
		bizProjectChangeBill.setPhones(orderdPhones);

		String userId = user.getId();
		bizProjectChangeBill.setUserId(userId);

		Page<BizProjectChangeBill> page = bizProjectChangeBillService.findPage(new Page<BizProjectChangeBill>(request, response), bizProjectChangeBill);
		model.addAttribute("page", page);
		/*
		 * model.addAttribute("storeIds", storeIds); String storeId =
		 * bizProjectChangeBill.getStoreId();
		 * model.addAttribute("storeIdhh",storeId );
		 */
		return "modules/bizprojectchangebill/bizProjectChangeBillList";
	}

	@RequestMapping(value = { "validatPhone", "validatPhone" })
	@ResponseBody
	public String validatPhone(String phone, String id) {
		String state = "false";
		User user = bizProjectChangeBillService.getCountByPhone(phone);
		if (user != null) {
			if (user.getId().equals(id)) {

				state = "true";
			} else {

				state = "false";
			}
		} else {
			state = "true";
		}

		// 如果是修改操作，相同的编号和登录名不做验证，如果不是相同的则验证。
		/*
		 * if (entity.getId() != null) { if (entity.getPhoneValid() != null &&
		 * entity.getPhoneValid().equalsIgnoreCase(entity.getPhone())) {
		 * state="true"; } }
		 */
		return state;
	}

	/**
	 * 审计员审核
	 * 
	 * @param bizProjectChangeBill
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizprojectchangebill:bizProjectChangeBill:view")
	@RequestMapping(value = { "list2", "" })
	public String list2(BizProjectChangeBill bizProjectChangeBill, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser1();

		// 过滤门店
		if (null == bizProjectChangeBill.getStoreId()) {
			if (null != user.getStoreId()) {
				Office office = user.getOffice();
				String parentId = office.getParentId();
				bizProjectChangeBill.setStoreId(parentId);

			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
			model.addAttribute("gongcheng", true);
		} else {
			bizProjectChangeBill.setProjectMode(user.getProjectMode());
		}

		return "modules/bizprojectchangebill/bizProjectChangeBillList2";
	}

	/**
	 * 审计员审核
	 * 
	 * @param bizProjectChangeBill
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizprojectchangebill:bizProjectChangeBill:view")
	@RequestMapping(value = { "budgeterList2", "" })
	public String budgeterList2(BizProjectChangeBill bizProjectChangeBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		bizProjectChangeBill.setType("2");

		User user = UserUtils.getUser();
		// 过滤门店
		if (null == bizProjectChangeBill.getStoreId()) {
			if (null != user.getStoreId()) {
				Office office = user.getOffice();
				String parentId = office.getParentId();
				bizProjectChangeBill.setStoreId(parentId);
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
			model.addAttribute("gongcheng", true);
		} else {
			bizProjectChangeBill.setProjectMode(user.getProjectMode());
		}
		// 权限控制
		List<String> id = dataAuthorityService.findAuthorityCode(DataAuthorityConstantUtils.BIZ_BUSINESS_TYPE_SJB);
		bizProjectChangeBill.setIds(id);
		Page<BizProjectChangeBill> page = bizProjectChangeBillService.findPage(new Page<BizProjectChangeBill>(request, response), bizProjectChangeBill);
		model.addAttribute("page", page);
		return "modules/bizprojectchangebill/bizProjectChangeBillList2";
	}

	/**
	 * 财务存档
	 * 
	 * @param bizProjectChangeBill
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizprojectchangebill:bizProjectChangeBill:view")
	@RequestMapping(value = { "list3", "" })
	public String list3(BizProjectChangeBill bizProjectChangeBill, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();
		// 过滤门店
		if (null == bizProjectChangeBill.getStoreId()) {
			if (null != user.getStoreId()) {
				bizProjectChangeBill.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
			model.addAttribute("gongcheng", true);
		} else {
			bizProjectChangeBill.setProjectMode(user.getProjectMode());
		}

		return "modules/bizprojectchangebill/bizProjectChangeBillList3";
	}

	/**
	 * 财务存档
	 * 
	 * @param bizProjectChangeBill
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizprojectchangebill:bizProjectChangeBill:view")
	@RequestMapping(value = { "financeList3", "" })
	public String financeList3(BizProjectChangeBill bizProjectChangeBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		bizProjectChangeBill.setType("3");

		User user = UserUtils.getUser();
		// 过滤门店
		if (null == bizProjectChangeBill.getStoreId()) {
			if (null != user.getStoreId()) {
				bizProjectChangeBill.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
			model.addAttribute("gongcheng", true);
		} else {
			bizProjectChangeBill.setProjectMode(user.getProjectMode());
		}

		Page<BizProjectChangeBill> page = bizProjectChangeBillService.findPage(new Page<BizProjectChangeBill>(request, response), bizProjectChangeBill);
		model.addAttribute("page", page);
		return "modules/bizprojectchangebill/bizProjectChangeBillList3";
	}

	/**
	 * 变更单审核--通过
	 * 
	 * @param bizProjectChangeBill
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("bizprojectchangebill:bizProjectChangeBill:edit")
	@RequestMapping(value = "approvePass")
	public String approvePass(Integer projectChangeId, String status, Model model, RedirectAttributes redirectAttributes) throws Exception {

		Date date = new Date();
		BizProjectChangeBill bizProjectChangeBill = new BizProjectChangeBill();

		bizProjectChangeBill.setProjectChangeId(projectChangeId);
		if (status.equals("10")) {
			// 设计师审核通过--变更单状态改为20
			bizProjectChangeBill.setStatus("20");
		}
		if (status.equals("20")) {
                    // 审计员审核通过--变更单状态改为30
			bizProjectChangeBill.setStatus("30");

			DecimalFormat df = new DecimalFormat("#.00");

			// 减项清单
			List<BizProjectChangeBillItem> subItem = bizProjectChangeBillService.findSubItem(projectChangeId);
			if (null != subItem && subItem.size() > 0) {
				for (BizProjectChangeBillItem sub : subItem) {
					// 总价
					Double allPrice = sub.getEveryPrice() * sub.getProjectIntemAmount();
					allPrice = Double.parseDouble(df.format(allPrice));
					sub.setAllPrice(allPrice);
				}
			}

			// 查询变更单详情
			BizProjectChangeBill billDetails = bizProjectChangeBillService.findDetails(projectChangeId);
			// 增项清单
			List<BizProjectChangeBillItem> addItem = bizProjectChangeBillService.findAddItem(projectChangeId);
			if (null != addItem && addItem.size() > 0) {
				for (BizProjectChangeBillItem add : addItem) {
					// 总价
					Double allPrice = add.getEveryPrice() * add.getProjectIntemAmount();
					allPrice = Double.parseDouble(df.format(allPrice));
					add.setAllPrice(allPrice);
				}
			}
			// 向同步表插入数据 以便发送 ------------------------
			BizSynData bizSynData = new BizSynData();

			JSONObject jsonObject = new JSONObject();

			jsonObject.put("orderNo", billDetails.getOrderNumber());
			jsonObject.put("constructionChangeNo", billDetails.getProjectChangeBillCode());
			jsonObject.put("changeApplyDate", DateUtils.formatDate(billDetails.getApplyDate()));
			jsonObject.put("changeReason", billDetails.getChangeReason());

			JSONArray jsonArray = new JSONArray();
			if (addItem != null && addItem.size() > 0) {
				for (BizProjectChangeBillItem bizProjectChangeBillItem : addItem) {
					// 增项是个数组,其实就是嵌套json
					JSONObject jsonObject1 = new JSONObject();
					jsonObject1.put("changeType", bizProjectChangeBillItem.getProjectIntemMold());
					jsonObject1.put("changeProjectName", bizProjectChangeBillItem.getProjectIntemName());

					jsonObject1.put("unit", bizProjectChangeBillItem.getProjectIntemUnit());
					jsonObject1.put("amount", bizProjectChangeBillItem.getProjectIntemAmount());
					jsonObject1.put("loss", 0);

					jsonObject1.put("laborCosts", 0);
					jsonObject1.put("totalUnitPrice", bizProjectChangeBillItem.getEveryPrice());
					jsonObject1.put("unitProjectTotalPrice", bizProjectChangeBillItem.getAllPrice());
					jsonObject1.put("explain", bizProjectChangeBillItem.getExplainWords());

					jsonArray.add(jsonObject1);
				}
			}
			if (subItem != null && subItem.size() > 0) {
				for (BizProjectChangeBillItem bizProjectChangeBillItem : subItem) {
					// 增项是个数组,其实就是嵌套json
					JSONObject jsonObject2 = new JSONObject();
					jsonObject2.put("changeType", bizProjectChangeBillItem.getProjectIntemMold());
					jsonObject2.put("changeProjectName", bizProjectChangeBillItem.getProjectIntemName());

					jsonObject2.put("unit", bizProjectChangeBillItem.getProjectIntemUnit());
					jsonObject2.put("amount", bizProjectChangeBillItem.getProjectIntemAmount());
					jsonObject2.put("loss", 0);

					jsonObject2.put("laborCosts", 0);
					jsonObject2.put("totalUnitPrice", bizProjectChangeBillItem.getEveryPrice());
					jsonObject2.put("unitProjectTotalPrice", bizProjectChangeBillItem.getAllPrice());
					jsonObject2.put("explain", bizProjectChangeBillItem.getExplainWords());

					jsonArray.add(jsonObject2);
				}
			}

			jsonObject.put("constructionChangeInfo", jsonArray);
			jsonObject.put("addProjectTotalPrice", billDetails.getAddItemTotalPrice() + "");
			jsonObject.put("cutProjectTotalPrice", billDetails.getSubItemTotalPrice() + "");
			double d = Double.parseDouble(df.format(billDetails.getAddItemTotalPrice() - billDetails.getSubItemTotalPrice()));
			jsonObject.put("changeListTotalPrice", d + "");

			String[] paramArr = new String[] { "orderNo=" + billDetails.getOrderNumber(), "constructionChangeNo=" + billDetails.getProjectChangeBillCode(), "changeApplyDate=" + DateUtils.formatDate(billDetails.getApplyDate()), "changeReason=" + billDetails.getChangeReason(), "addProjectTotalPrice=" + billDetails.getAddItemTotalPrice(), "cutProjectTotalPrice=" + billDetails.getSubItemTotalPrice(), "changeListTotalPrice=" + d };

			String key = KeyAuthenticateUtils2.getKey(paramArr, BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
			jsonObject.put("key", key);

			String jsonData = jsonObject.toString();

			bizSynData.setBusinessData(jsonData);
			bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
			bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_503);
			bizSynData.setBusinessOnlyMarkInt(projectChangeId);
			bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
			bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
			bizSynData.preInsert();

			bizSynDataService.save(bizSynData);
		}
		if (status.equals("40")) {
			// 财务存档--变更单状态改为100
			bizProjectChangeBill.setStatus("100");
		}
		bizProjectChangeBill.setCheckWords("");
		User user = UserUtils.getUser();
		bizProjectChangeBill.setCheckEmployeeId(Integer.valueOf(user.getId()));
		bizProjectChangeBill.setCheckDate(date);
		bizProjectChangeBillService.updateStatus(bizProjectChangeBill);

		// 通过变更单id查询客户信息
		BizProjectChangeBill change = bizProjectChangeBillService.findDetails(projectChangeId);

		if (status.equals("10")) {

			// =====================================短信start========================================================
			/**
			 * 设计师审核通过==项目经理
			 */
			// 项目经理【美得你】订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），设计师（姓名-手机号），施工变更单设计师已审核通过，请登录APP查看详情。
			String content = "订单（" + change.getCommunityName() + "-" + change.getBuildNumber() + "-" + change.getBuildUnit() + "-" + change.getBuildRoom() + "-" + change.getCustomerName() + "-" + change.getCustomerPhone() + "），设计师（" + user.getName() + "-" + user.getPhone() + "），施工变更单设计师已审核通过，请登录APP查看详情。";
			BizPhoneMsg phone = new BizPhoneMsg();
			phone.setReceiveEmployeeId(change.getItemManagerId());
			phone.setReceivePhone(change.getItemManagerPhone());
			phone.setMsgContent(content);
			phone.setMsgGenerateDatetime(date);
			phone.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			phone.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_400201);
			phone.setRelatedBusinessIdInt(projectChangeId);
			bizPhoneMsgService.insert(phone);

			/**
			 * 设计师审核通过==审计员
			 */
			// 审计员【美得你】订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），设计师（姓名-手机号），施工变更单设计师已审核通过，请及时登录系统审核。
			String content2 = "订单（" + change.getCommunityName() + "-" + change.getBuildNumber() + "-" + change.getBuildUnit() + "-" + change.getBuildRoom() + "-" + change.getCustomerName() + "-" + change.getCustomerPhone() + "），设计师（" + user.getName() + "-" + user.getPhone() + "），施工变更单设计师已审核通过，请及时登录系统审核。";
			BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(change.getStoreId(), "11");
			List<Integer> list = new ArrayList<Integer>();
			List<BizEmployee2> employeelist = null;
			if (null != bizMessagegroup) {
				String[] str = bizMessagegroup.getEmployees().split(",");
				for (String id1 : str) {
					list.add(Integer.valueOf(id1));
				}
				employeelist = bizEmployeeService2.getById(list);
				if (null != employeelist && employeelist.size() > 0) {
					for (BizEmployee2 bizEmployee2 : employeelist) {

						BizPhoneMsg phone2 = new BizPhoneMsg();
						phone2.setReceiveEmployeeId(bizEmployee2.getId());
						phone2.setReceivePhone(bizEmployee2.getPhone());
						phone2.setMsgContent(content2);
						phone2.setMsgGenerateDatetime(date);
						phone2.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
						phone2.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_400203);
						phone2.setRelatedBusinessIdInt(projectChangeId);
						bizPhoneMsgService.insert(phone2);
					}
				}
			}
			// =====================================短信end========================================================

			// =====================================消息推送start========================================================

			/**
			 * 消息推送 消息推送类型 101001001-通知项目经理-设计师审核通过
			 */
			BizMsg bizMsg = new BizMsg();
			bizMsg.setMsgTitle("施工变更单设计师审核通过");
			bizMsg.setMsgTime(date);
			bizMsg.setMsgContent(content);
			bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
			bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_101001001);
			bizMsg.setBusiIdInt(projectChangeId);
			bizMsg.setEmployeeId(change.getItemManagerId());
			bizProjectChangeBillService.saveBizMsg(bizMsg);
			// =====================================消息推送end========================================================
			// 日志
			BizBusinessStatusLog log = new BizBusinessStatusLog();
			log.setStatusDatetime(date);
			log.setBusinessEmployeeId(Integer.parseInt(UserUtils.getUser().getId()));
			log.setBusinessOnlyMarkInt(projectChangeId);
			// 设计师审核通过
			log.setBusinessType(MessagePushType.MESSAGE_PUSH_TYPE_101001001);
			bizBusinessStatusLogService.save(log);

			addMessage(redirectAttributes, "设计师审核通过");
			return "redirect:" + Global.getAdminPath() + "/bizprojectchangebill/bizProjectChangeBill/designerList?repage";
		}
		if (status.equals("20")) {

			// =====================================短信start========================================================
			/**
			 * 审计员审核通过==项目经理
			 */
			// 项目经理【美得你】订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），审计员（姓名-手机号），施工变更单审计员已审核通过，请登录APP查看详情。
			String content3 = "订单（" + change.getCommunityName() + "-" + change.getBuildNumber() + "-" + change.getBuildUnit() + "-" + change.getBuildRoom() + "-" + change.getCustomerName() + "-" + change.getCustomerPhone() + "），审计员（" + user.getName() + "-" + user.getPhone() + "），施工变更单审计员已审核通过，请登录APP查看详情。";
			BizPhoneMsg phone3 = new BizPhoneMsg();
			phone3.setReceiveEmployeeId(change.getItemManagerId());
			phone3.setReceivePhone(change.getItemManagerPhone());
			phone3.setMsgContent(content3);
			phone3.setMsgGenerateDatetime(date);
			phone3.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			phone3.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_400301);
			phone3.setRelatedBusinessIdInt(projectChangeId);
			bizPhoneMsgService.insert(phone3);

			/**
			 * 审计员审核通过==客户
			 */
			// 客户【美得你】尊敬的用户，我是您的家装管家（姓名-手机号）请确认施工变更的内容，点这里登录（加密链接）
			/*
			 * String content4 =
			 * "尊敬的用户，我是您的家装管家（"+change.getItemManager()+change
			 * .getItemManagerPhone()+"），请进入客户端APP确认施工变更的内容。"; BizPhoneMsg
			 * phone4 = new BizPhoneMsg();
			 * phone4.setReceivePhone(change.getCustomerPhone());
			 * phone4.setMsgContent(content4);
			 * phone4.setMsgGenerateDatetime(date);
			 * phone4.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			 * phone4.setRelatedBusinessType
			 * (SendMsgBusinessType.RELATED_BUSINESS_TYPE_400302);
			 * phone4.setRelatedBusinessIdInt(projectChangeId);
			 * bizPhoneMsgService.insert(phone4);
			 */
			// =====================================短信end========================================================

			// //【美得你】尊敬的用户，我是您的家装管家（孙伟-13411112222），请确认施工变更的内容，点这里登录（http://XXXXXXXXX）
			// String content =
			// "尊敬的用户，我是您的家装管家（"+change.getItemManager()+change.getItemManagerPhone()+"），请确认施工变更的内容，点这里登录（"+PicRootName.picPrefixName()+"/mDJlSsBc?micro="+change.getOrderId()+"）";
			//
			// //--------------------------短信start--------------------------------------------------------------
			// //变更单-预算员审核通过--给客户发短信
			// Map<String, String> param = new HashMap<String, String>();
			// param.put("source", "1");
			// param.put("content",content);
			// param.put("sendtime", "");
			// param.put("mobile", change.getCustomerPhone());
			// HttpConnection.post(ConstantUtils.SEND_MESSAGE_INTERFACE, param);
			// //--------------------------短信end--------------------------------------------------------------

			// =====================================消息推送start========================================================

			/**
			 * 消息推送 消息推送类型 101001003-通知项目经理-预算员审核通过
			 */
			BizMsg bizMsg = new BizMsg();
			bizMsg.setMsgTitle("施工变更单预算员审核通过");
			bizMsg.setMsgTime(date);
			bizMsg.setMsgContent(content3);
			bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
			bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_101001003);
			bizMsg.setBusiIdInt(projectChangeId);
			bizMsg.setEmployeeId(change.getItemManagerId());
			bizProjectChangeBillService.saveBizMsg(bizMsg);
			// =====================================消息推送end========================================================

			// 日志
			BizBusinessStatusLog log = new BizBusinessStatusLog();
			log.setStatusDatetime(date);
			log.setBusinessEmployeeId(Integer.parseInt(UserUtils.getUser().getId()));
			log.setBusinessOnlyMarkInt(projectChangeId);
			// 审计审核通过
			log.setBusinessType(MessagePushType.MESSAGE_PUSH_TYPE_101001003);
			bizBusinessStatusLogService.save(log);
			System.out.println(UserUtils.getUser().getName() + "--------");
			System.out.println(UserUtils.getUser().getId() + "------------");
			addMessage(redirectAttributes, "预算员审核通过");
			return "redirect:" + Global.getAdminPath() + "/bizprojectchangebill/bizProjectChangeBill/budgeterList2?repage";
		}

		if (status.equals("40")) {

			// =====================================短信start========================================================
			/**
			 * 财务存档==项目经理
			 */
			// 项目经理【美得你】订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），财务人员（姓名-手机号），施工变更单财务已存档，请登录APP查看详情。
			String content5 = "订单（" + change.getCommunityName() + "-" + change.getBuildNumber() + "-" + change.getBuildUnit() + "-" + change.getBuildRoom() + "-" + change.getCustomerName() + "-" + change.getCustomerPhone() + "），财务人员（" + user.getName() + "-" + user.getPhone() + "），施工变更单财务已存档，请登录APP查看详情。";
			BizPhoneMsg phone5 = new BizPhoneMsg();
			phone5.setReceiveEmployeeId(change.getItemManagerId());
			phone5.setReceivePhone(change.getItemManagerPhone());
			phone5.setMsgContent(content5);
			phone5.setMsgGenerateDatetime(date);
			phone5.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			phone5.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_400501);
			phone5.setRelatedBusinessIdInt(projectChangeId);
			bizPhoneMsgService.insert(phone5);
			// =====================================短信end========================================================

			// =====================================消息推送start========================================================

			/**
			 * 消息推送 消息推送类型 101001007-通知项目经理-财务已存档
			 */
			BizMsg bizMsg = new BizMsg();
			bizMsg.setMsgTitle("施工变更单财务存档");
			bizMsg.setMsgTime(date);
			bizMsg.setMsgContent(content5);
			bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
			bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_101001007);
			bizMsg.setBusiIdInt(projectChangeId);
			bizMsg.setEmployeeId(change.getItemManagerId());
			bizProjectChangeBillService.saveBizMsg(bizMsg);
			// =====================================消息推送end========================================================

		}

		addMessage(redirectAttributes, "财务已存档");
		return "redirect:" + Global.getAdminPath() + "/bizprojectchangebill/bizProjectChangeBill/financeList3?repage";
	}

	/**
	 * 变更单审核--驳回
	 * 
	 * @param bizProjectChangeBill
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bizprojectchangebill:bizProjectChangeBill:edit")
	@RequestMapping(value = "refusePass")
	public String refusePass(Integer projectChangeId, String reason, String status, RedirectAttributes redirectAttributes) {

		Date date = new Date();
		// 设计师审核驳回--变更单状态改为15
		BizProjectChangeBill bizProjectChangeBill = new BizProjectChangeBill();
		bizProjectChangeBill.setProjectChangeId(projectChangeId);
		if (status.equals("10")) {
			// 设计师审核不通过--变更单状态改为15
			bizProjectChangeBill.setStatus("15");
		}
		if (status.equals("20")) {
			// 审计员审核不通过--变更单状态改为25
			bizProjectChangeBill.setStatus("25");
		}
		bizProjectChangeBill.setCheckWords(reason);
		User user = UserUtils.getUser();
		bizProjectChangeBill.setCheckEmployeeId(Integer.valueOf(user.getId()));
		bizProjectChangeBill.setCheckDate(date);
		bizProjectChangeBillService.updateStatus(bizProjectChangeBill);

		// 通过变更单id查询客户信息
		BizProjectChangeBill change = bizProjectChangeBillService.findDetails(projectChangeId);
		if (status.equals("10")) {

			// =====================================短信start========================================================
			/**
			 * 设计师审核==驳回
			 */
			// 项目经理
			// 【美得你】订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），设计师（姓名-手机号），施工变更单设计师审核不通过，驳回原因（原因），请登录APP重新提交。
			String content = "订单（" + change.getCommunityName() + "-" + change.getBuildNumber() + "-" + change.getBuildUnit() + "-" + change.getBuildRoom() + "-" + change.getCustomerName() + "-" + change.getCustomerPhone() + "），设计师（" + user.getName() + "-" + user.getPhone() + "），施工变更单设计师审核不通过，驳回原因（" + reason + "），请登录APP重新提交。";
			BizPhoneMsg phone = new BizPhoneMsg();
			phone.setReceiveEmployeeId(change.getItemManagerId());
			phone.setReceivePhone(change.getItemManagerPhone());
			phone.setMsgContent(content);
			phone.setMsgGenerateDatetime(date);
			phone.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			phone.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_400202);
			phone.setRelatedBusinessIdInt(projectChangeId);
			bizPhoneMsgService.insert(phone);
			// =====================================短信end========================================================

			// =====================================消息推送start========================================================

			/**
			 * 消息推送 消息推送类型 101001002-通知项目经理-设计师审核不通过
			 */
			BizMsg bizMsg = new BizMsg();
			bizMsg.setMsgTitle("施工变更单设计师审核不通过");
			bizMsg.setMsgTime(date);
			bizMsg.setMsgContent(content);
			bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
			bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_101001002);
			bizMsg.setBusiIdInt(projectChangeId);
			bizMsg.setEmployeeId(change.getItemManagerId());
			bizProjectChangeBillService.saveBizMsg(bizMsg);
			// =====================================消息推送end========================================================

			addMessage(redirectAttributes, "设计师审核驳回");
			return "redirect:" + Global.getAdminPath() + "/bizprojectchangebill/bizProjectChangeBill/designerList?repage";
		}

		if (status.equals("20")) {

			// =====================================短信start========================================================
			/**
			 * 预算员审核==驳回
			 */
			// 项目经理【美得你】订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），审计员（姓名-手机号），施工变更单审计员审核不通过，驳回原因（原因），请登录APP重新提交。
			String content2 = "订单（" + change.getCommunityName() + "-" + change.getBuildNumber() + "-" + change.getBuildUnit() + "-" + change.getBuildRoom() + "-" + change.getCustomerName() + "-" + change.getCustomerPhone() + "），审计员（" + user.getName() + "-" + user.getPhone() + "），施工变更单审计员审核不通过，驳回原因（" + reason + "），请登录APP重新提交。";
			BizPhoneMsg phone = new BizPhoneMsg();
			phone.setReceiveEmployeeId(change.getItemManagerId());
			phone.setReceivePhone(change.getItemManagerPhone());
			phone.setMsgContent(content2);
			phone.setMsgGenerateDatetime(date);
			phone.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			phone.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_400303);
			phone.setRelatedBusinessIdInt(projectChangeId);
			bizPhoneMsgService.insert(phone);
			// =====================================短信end========================================================

			// =====================================消息推送start========================================================

			/**
			 * 消息推送 消息推送类型 101001004-通知项目经理-预算员审核不通过
			 */
			BizMsg bizMsg = new BizMsg();
			bizMsg.setMsgTitle("施工变更单预算员审核不通过");
			bizMsg.setMsgTime(date);
			bizMsg.setMsgContent(content2);
			bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
			bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_101001004);
			bizMsg.setBusiIdInt(projectChangeId);
			bizMsg.setEmployeeId(change.getItemManagerId());
			bizProjectChangeBillService.saveBizMsg(bizMsg);
			// =====================================消息推送end========================================================

		}

		addMessage(redirectAttributes, "预算员审核驳回");
		return "redirect:" + Global.getAdminPath() + "/bizprojectchangebill/bizProjectChangeBill/budgeterList2?repage";
	}

	/**
	 * 变更单详情
	 * 
	 * @param bizProjectChangeBill
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("bizprojectchangebill:bizProjectChangeBill:edit")
	@RequestMapping(value = "details")
	public String details(Integer projectChangeId, Model model) {
		DecimalFormat df = new DecimalFormat("#.00");

		// 查询变更单详情
		BizProjectChangeBill bizProjectChangeBill = bizProjectChangeBillService.findDetails(projectChangeId);

		// 增项清单
		List<BizProjectChangeBillItem> addItem = bizProjectChangeBillService.findAddItem(projectChangeId);
		if (null != addItem && addItem.size() > 0) {
			for (BizProjectChangeBillItem add : addItem) {
				// 总价
				Double allPrice = add.getEveryPrice() * add.getProjectIntemAmount();
				allPrice = Double.parseDouble(df.format(allPrice));
				add.setAllPrice(allPrice);
			}
		}
		// 减项清单
		List<BizProjectChangeBillItem> subItem = bizProjectChangeBillService.findSubItem(projectChangeId);
		if (null != subItem && subItem.size() > 0) {
			for (BizProjectChangeBillItem sub : subItem) {
				// 总价
				Double allPrice = sub.getEveryPrice() * sub.getProjectIntemAmount();
				allPrice = Double.parseDouble(df.format(allPrice));
				sub.setAllPrice(allPrice);
			}
		}

		// 增项合计
		double addMoney = bizProjectChangeBill.getAddItemTotalPrice();
		// 减项合计
		double reducePrice = bizProjectChangeBill.getSubItemTotalPrice();
		// 基装变更合计
		double totalMoney = addMoney - reducePrice;
		totalMoney = Double.parseDouble(df.format(totalMoney));

		model.addAttribute("bizProjectChangeBill", bizProjectChangeBill);
		model.addAttribute("addItem", addItem);
		model.addAttribute("subItem", subItem);
		model.addAttribute("addMoney", addMoney);
		model.addAttribute("reducePrice", reducePrice);
		model.addAttribute("totalMoney", totalMoney);
		return "modules/bizprojectchangebill/details";
	}

	// 导出变更单excel格式文件
	@RequestMapping(value = "exportExcel")
	public void exportExcel(Integer projectChangeId, HttpServletResponse response) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		HSSFWorkbook excel = bizProjectChangeBillService.projectChangeBillForExcel(projectChangeId);
		ServletOutputStream out = null;// 创建一个输出流对象
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr = new String(("导出变更单excel格式文件" + sf.format(new Date())).getBytes("utf-8"), "ISO8859-1");// headerString为中文时转码
			response.setHeader("Content-disposition", "attachment; filename=" + headerStr + ".xls");// filename是下载的xls的名
			out = response.getOutputStream();
			excel.write(out);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (null != out) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "querySignaturePic")
	public String querySignaturePic(Integer recheckID, String type, Model model) throws IOException {
		List<BizBusinessPic> list = bizBusinessPicService.getByRecheckID(recheckID, type);
		model.addAttribute("list", list);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("baseUrl", baseUrl);
		return "modules/bizprojectchangebill/queryBillPic";
	}

	@RequestMapping(value = "/ajaxQuerySignaturePic")
	@ResponseBody
	public Map<Object, Object> ajaxQuerySignaturePic(Integer recheckID, String type, Model model) throws IOException {
		List<BizBusinessPic> list = bizBusinessPicService.getByRecheckID(recheckID, type);
		model.addAttribute("list", list);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("baseUrl", baseUrl);
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", list);

		return mapObject;
	}

	/**
	 * 基装变更单查询页面
	 */
	@RequestMapping(value = "queryAllChangeBill")
	public String queryAllChangeBill(BizProjectChangeBill bizProjectChangeBill, Model mode, HttpServletRequest request, HttpServletResponse response) {
		if (bizProjectChangeBill.getStatus() == null) {
			// 默认查询 10项目经理已提报 15设计师审核不通过 20设计师审核通过
			bizProjectChangeBill.setStatus("10,15,20");
		}
		// 权限控制
		User user = UserUtils.getUser();
		List<String> orderdPhones = dataAuthorityService.orderdPhones(DataAuthorityConstantUtils.Biz_Business_Type_DD);
		bizProjectChangeBill.setPhones(orderdPhones);
		String userId = user.getId();
		bizProjectChangeBill.setUserId(userId);
		Page<BizProjectChangeBill> findPageQuery = bizProjectChangeBillService.findPageQuery(new Page<BizProjectChangeBill>(request, response), bizProjectChangeBill);
		mode.addAttribute("page", findPageQuery);
		return "/modules/bizprojectchangebill/projectChangeBillQuery";
	}

	/**
	 * 查看设计师和审核员
	 */
	@RequestMapping(value = "findDetail")
	@ResponseBody
	public List<BizProjectChangeBill> findDetail(BizProjectChangeBill bizProjectChangeBill, Model mode, HttpServletRequest request, HttpServletResponse response) {
		List<BizProjectChangeBill> list = bizProjectChangeBillService.findDetail(bizProjectChangeBill);
		return list;
	}

	/**
	 * 基装变更设置页面
	 */
	@RequestMapping(value = "budgeterList4")
	public String budgeterList4(HttpServletResponse response, HttpServletRequest request, Model model, BizProjectChangeBill bizProjectChangeBill) {
		User user = UserUtils.getUser();
		// 过滤门店
		if (null == bizProjectChangeBill.getStoreId()) {
			if (null != user.getStoreId()) {
				Office office = user.getOffice();
				String parentId = office.getParentId();
				bizProjectChangeBill.setStoreId(parentId);
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
			model.addAttribute("gongcheng", true);
		} else {
			bizProjectChangeBill.setProjectMode(user.getProjectMode());
		}
		// 只查 审计员审核通过 30
		bizProjectChangeBill.setStatus("30");
		bizProjectChangeBill.setType("2");
		Page<BizProjectChangeBill> page = bizProjectChangeBillService.findPage(new Page<BizProjectChangeBill>(request, response), bizProjectChangeBill);
		model.addAttribute("page", page);
		return "modules/bizprojectchangebill/bizProjectChangeBillList4";
	}

	/**
	 * 处理
	 */
	@RequestMapping(value = "handle")
	public String handle(HttpServletResponse response, HttpServletRequest request, Model model, BizProjectChangeBill bizProjectChangeBill, RedirectAttributes redirectAttributes) {
		String empId = UserUtils.getUser().getEmpId();
		bizProjectChangeBill.setDealEmplyeeName(empId);
		bizProjectChangeBill.setIsDealed("1");
		bizProjectChangeBillService.updataIsDealed(bizProjectChangeBill);
		addMessage(redirectAttributes, "工程部处理通过");
		return "redirect:" + Global.getAdminPath() + "/bizprojectchangebill/bizProjectChangeBill/budgeterList4";
	}

}