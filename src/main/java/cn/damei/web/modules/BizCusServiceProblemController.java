/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
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
import cn.damei.common.constantUtils.projectProblem.ProjectProblemConstantUtil;
import cn.damei.common.mapper.JsonMapper;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizComplaintProblemItem;
import cn.damei.service.modules.BizComplaintProblemItemService;
import cn.damei.entity.modules.BizComplaintProblemType;
import cn.damei.service.modules.BizComplaintProblemTypeService;
import cn.damei.entity.modules.BizCusServiceProblem;
import cn.damei.service.modules.BizCusServiceProblemService;
import cn.damei.common.utils.CusserviceUtils;
import cn.damei.entity.modules.Order;
import cn.damei.service.modules.OrderService;
import cn.damei.entity.modules.BizOrderComArae;
import cn.damei.entity.modules.BizOrderComplaint;

/**
 * 售后问题反馈详情Controller
 *
 * @author joseph
 * @version 2017-06-23
 */
@Controller
@RequestMapping(value = "${adminPath}/cusserviceproblem/bizCusServiceProblem")
public class BizCusServiceProblemController extends BaseController {

	@Autowired
	private BizCusServiceProblemService bizCusServiceProblemService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private BizComplaintProblemTypeService bizComplaintProblemTypeService;
	@Autowired
	private BizComplaintProblemItemService bizComplaintProblemItemService;

	@ModelAttribute
	public BizCusServiceProblem get(@RequestParam(required = false) String id) {
		BizCusServiceProblem entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = bizCusServiceProblemService.get(id);
		}
		if (entity == null) {
			entity = new BizCusServiceProblem();
		}
		return entity;
	}

	/**
	 * 查询售后投诉单
	 *
	 * @Title: list
	 * @Description:
	 * @param @param bizCusServiceProblem
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequiresPermissions("cusserviceproblem:bizCusServiceProblem:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizCusServiceProblem bizCusServiceProblem, HttpServletRequest request, HttpServletResponse response, Model model) {
        //过滤门店
        User user = UserUtils.getUser();
        if(null==bizCusServiceProblem.getStoreId()){
            if(null!=user.getStoreId()){
                bizCusServiceProblem.setStoreId(user.getStoreId());
            }
        }
		Page<BizCusServiceProblem> page = bizCusServiceProblemService.findPage(new Page<BizCusServiceProblem>(request, response), bizCusServiceProblem);
		model.addAttribute("page", page);
		model.addAttribute("entity", bizCusServiceProblem);
		return "modules/cusserviceproblem/bizCusServiceProblemList";
	}

	/**
	 *
	 * @Title: update 接收来自售后的投诉订单信息然后拆分
	 * @Description:
	 * @param @param bizCusServiceProblem
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequiresPermissions("cusserviceproblem:bizCusServiceProblem:edit")
	@RequestMapping(value = "update")
	public String update(BizCusServiceProblem bizCusServiceProblem,String customerNameNot, HttpServletRequest request, HttpServletResponse response, Model model) {
		BizOrderComplaint bizOrderComplaint = new BizOrderComplaint();
		Page<BizCusServiceProblem> page = bizCusServiceProblemService.findPage(new Page<BizCusServiceProblem>(request, response), bizCusServiceProblem);
		if(null!=page.getList()&&page.getList().size ()>0){
		BizCusServiceProblem bizCusServiceProblem2 = page.getList().get(0);
		Order order = orderService.getProjectbyId(bizCusServiceProblem2.getOrderId());

            /*List< Order> orderList = orderService.getProjectName(bizCusServiceProblem2.getCustomerName ());
            if (null!=orderList&&orderList.size ()==1){
                order=orderList.get (0);
            }
            else if(null!=orderList&&orderList.size ()>1){
                for (Order order1 : orderList) {
                    if (page.getList ().get (0).getCustomerAddress ().contains (order1.getCustomerAddress () )){
                        order=order1;
                    }
                }
            }*/
		model.addAttribute("page", page);
		model.addAttribute("entity", bizCusServiceProblem);
		model.addAttribute("entity", order);
		bizOrderComplaint.setCusServiceId(bizCusServiceProblem.getId());
		bizOrderComplaint.setWorkOrderCode(bizCusServiceProblem.getWorkOrderCode());
		bizOrderComplaint.setComplaintSource("4");
		bizOrderComplaint.setOrder(order);
		bizOrderComplaint.setAfterId(bizCusServiceProblem2.getId()); // 售后ID
		bizOrderComplaint.setComplaintProblemNr(bizCusServiceProblem2.getProblemDescribe()); // 售后需要显示的字段
		model.addAttribute("bizOrderComplaint", bizOrderComplaint);
		bizCusServiceProblem.setStatus("10");
		bizCusServiceProblem.setStatusdatetime(new Date());
		// bizCusServiceProblemService.update(bizCusServiceProblem);
		}
		String photo = bizCusServiceProblemService.findPicsById(Integer.parseInt(bizCusServiceProblem.getId()));
		List<BizCusServiceProblem> list = new ArrayList<BizCusServiceProblem>();

		if (null != photo && !"".equals(photo.trim())) {
			String[] photos = photo.split(",");

			if (photos.length > 0) {

				for (int v = 0; v < photos.length; v++) {
					BizCusServiceProblem bizCusServiceProblem3 = new BizCusServiceProblem();
					bizCusServiceProblem3.setXuhaourl(photos[v]);
					bizCusServiceProblem3.setXuhao((v + 1) + "");
					bizCusServiceProblem3.setCusServicefla("1");
					list.add(bizCusServiceProblem3);
				}
				model.addAttribute("picPrefixName", request.getContextPath());
			}
			for (int i = 0; i <= (8 - list.size()); i++) {
				BizCusServiceProblem bizCusServiceProblem3 = new BizCusServiceProblem();
				bizCusServiceProblem3.setXuhaourl("/static/bizOrderComplaint/images/upPic.png");
				bizCusServiceProblem3.setXuhao((list.size() + 1) + "");
				bizCusServiceProblem3.setCusServicefla("0");
				list.add(bizCusServiceProblem3);
			}

		} else {
			for (int i = 0; i < 6; i++) {
				BizCusServiceProblem bizCusServiceProblem3 = new BizCusServiceProblem();
				bizCusServiceProblem3.setXuhaourl("/static/bizOrderComplaint/images/upPic.png");
				bizCusServiceProblem3.setXuhao((i + 1) + "");
				bizCusServiceProblem3.setCusServicefla("0");
				list.add(bizCusServiceProblem3);
			}
		}
		model.addAttribute("picList", list);
		return "modules/ordercomplan/OrderComplaintFormCusService";
	}

	/**
	 * 处理来自售后的投诉订单信息
	 *
	 * @Title: handle
	 * @Description:
	 * @param @param bizCusServiceProblem
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequiresPermissions("cusserviceproblem:bizCusServiceProblem:edit")
	@RequestMapping(value = { "handle", "" })
	public String handle(BizCusServiceProblem bizCusServiceProblem, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {

		String sendHttp1 = CusserviceUtils.sendHttp(bizCusServiceProblem.getWorkOrderCode(), "RECEIVED", null);
		String sendHttp2 = "";
		String sendHttp3 = "";
		if (sendHttp1.equals("SUCCESS")) {
			bizCusServiceProblem.setBeforehandDatehou("0");
			bizCusServiceProblem.setRemarks(bizCusServiceProblem.getStatusdescribe());
			sendHttp2 = CusserviceUtils.sendHttp(bizCusServiceProblem.getWorkOrderCode(), "PROCESSING", bizCusServiceProblem);
			logger.error("cn.damei.web.modules.BizCusServiceProblemController.handle:接收成功");
		} else {
			addMessage(redirectAttributes, "处理售后问题失败!原因:" + sendHttp1);
			logger.error("cn.damei.web.modules.BizCusServiceProblemController.handle:接收失败,失败原因:" + sendHttp1);
			return "redirect:" + Global.getAdminPath() + "/cusserviceproblem/bizCusServiceProblem/list?repage";
		}
		if (sendHttp2.equals("SUCCESS")) {
			sendHttp3 = CusserviceUtils.sendHttp(bizCusServiceProblem.getWorkOrderCode(), "COMPLETED", null);
			logger.error("cn.damei.web.modules.BizCusServiceProblemController.handle:处理中成功");
		} else {
			addMessage(redirectAttributes, "处理售后问题失败!原因:" + sendHttp2);
			logger.error("cn.damei.web.modules.BizCusServiceProblemController.handle:处理中失败,失败原因:" + sendHttp2);
			return "redirect:" + Global.getAdminPath() + "/cusserviceproblem/bizCusServiceProblem/list?repage";
		}
		if (sendHttp3.equals("SUCCESS")) {
			bizCusServiceProblem.setStatus(ProjectProblemConstantUtil.PROJECT_PROBLEM_COMPLAINT_STATUS_20); // 已处理
			bizCusServiceProblem.setStatusdatetime(new Date());
			bizCusServiceProblemService.update(bizCusServiceProblem);
			logger.error("cn.damei.web.modules.BizCusServiceProblemController.handle:处理完成成功");
			addMessage(redirectAttributes, "处理售后问题成功");
		} else {
			addMessage(redirectAttributes, "处理售后问题失败!原因:" + sendHttp3);
			logger.error("cn.damei.web.modules.BizCusServiceProblemController.handle:处理中失败,失败原因:" + sendHttp3);
			return "redirect:" + Global.getAdminPath() + "/cusserviceproblem/bizCusServiceProblem/list?repage";
		}

		return "redirect:" + Global.getAdminPath() + "/cusserviceproblem/bizCusServiceProblem/list?repage";
	}

	/**
	 * 驳回售后投诉订单的信息
	 *
	 * @Title: reject
	 * @Description:
	 * @param @param bizCusServiceProblem
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequiresPermissions("cusserviceproblem:bizCusServiceProblem:edit")
	@RequestMapping(value = { "reject", "" })
	public String reject(BizCusServiceProblem bizCusServiceProblem, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		String sendHttp1 = CusserviceUtils.sendHttp(bizCusServiceProblem.getWorkOrderCode(), "RECEIVED", null);
		String sendHttp2 = "";
		if (sendHttp1.equals("SUCCESS")) {
			bizCusServiceProblem.setBeforehandDatehou("0");
			bizCusServiceProblem.setRemarks(bizCusServiceProblem.getStatusdescribe());
			sendHttp2 = CusserviceUtils.sendHttp(bizCusServiceProblem.getWorkOrderCode(), "ASSIGN", bizCusServiceProblem);
			logger.error("cn.damei.web.modules.BizCusServiceProblemController.reject:驳回接收成功");
		} else {
			addMessage(redirectAttributes, "驳回售后问题失败!原因:" + sendHttp1);
			logger.error("cn.damei.web.modules.BizCusServiceProblemController.reject:驳回失败" + sendHttp1);
			return "redirect:" + Global.getAdminPath() + "/cusserviceproblem/bizCusServiceProblem/list?repage";
		}
		if (sendHttp2.equals("SUCCESS")) {
			bizCusServiceProblem.setStatus(ProjectProblemConstantUtil.PROJECT_PROBLEM_COMPLAINT_STATUS_30);
			logger.error("cn.damei.web.modules.BizCusServiceProblemController.reject:驳回申述成功");
			bizCusServiceProblem.setStatusdatetime(new Date());
			bizCusServiceProblemService.update(bizCusServiceProblem);
			addMessage(redirectAttributes, "驳回售后问题成功");
		} else {
			addMessage(redirectAttributes, "驳回售后问题失败!原因:" + sendHttp2);
			logger.error("cn.damei.web.modules.BizCusServiceProblemController.reject:驳回申述失败" + sendHttp2);
			return "redirect:" + Global.getAdminPath() + "/cusserviceproblem/bizCusServiceProblem/list?repage";
		}

		return "redirect:" + Global.getAdminPath() + "/cusserviceproblem/bizCusServiceProblem/list?repage";
	}

	/**
	 * 显示处理售后页面
	 *
	 * @Title: handleView
	 * @Description:
	 * @param @param bizCusServiceProblem
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequiresPermissions("cusserviceproblem:bizCusServiceProblem:edit")
	@RequestMapping(value = { "handleView", "" })
	public String handleView(BizCusServiceProblem bizCusServiceProblem, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<BizCusServiceProblem> findList = bizCusServiceProblemService.findList(bizCusServiceProblem);
		BizCusServiceProblem bizCusServiceProblem2 = findList.get(0);
		bizCusServiceProblem2.setStatusdescribe("");
		model.addAttribute("bizCusServiceProblem", bizCusServiceProblem2);

		return "modules/cusserviceproblem/bizCusServiceProblemHandle";
	}

	/**
	 * 显示驳回售后页面
	 *
	 * @Title: rejectView
	 * @Description:
	 * @param @param bizCusServiceProblem
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequiresPermissions("cusserviceproblem:bizCusServiceProblem:edit")
	@RequestMapping(value = { "rejectView", "" })
	public String rejectView(BizCusServiceProblem bizCusServiceProblem, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<BizCusServiceProblem> findList = bizCusServiceProblemService.findList(bizCusServiceProblem);
		BizCusServiceProblem bizCusServiceProblem2 = findList.get(0);
		bizCusServiceProblem2.setStatusdescribe("");
		model.addAttribute("bizCusServiceProblem", bizCusServiceProblem2);

		return "modules/cusserviceproblem/bizCusServiceProblemReject";
	}

	/**
	 * 显示项目售后页面
	 *
	 * @Title: rejectView
	 * @Description: TODO
	 * @param @param bizCusServiceProblem
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequiresPermissions("cusserviceproblem:bizCusServiceProblem:edit")
	@RequestMapping(value = "ProjectView")
	public String projectView(BizCusServiceProblem bizCusServiceProblem, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("bizCusServiceProblem", bizCusServiceProblem);
		return "modules/cusserviceproblem/bizCusServiceProblemProjectV";
	}

	@RequiresPermissions("cusserviceproblem:bizCusServiceProblem:edit")
	@RequestMapping(value = "ProjectViewafte")
	public String projectViewafte(BizCusServiceProblem bizCusServiceProblem, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("bizCusServiceProblem", bizCusServiceProblem);
		return "modules/cusserviceproblem/CusServicecalft";
	}

	/**
	 * 订单ID查询所有的有关这个订单的信息
	 *
	 * @Title: ProjectbyId
	 * @Description: TODO
	 * @param @param bizCusServiceProblem
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequestMapping(value = { "ProjectbyId", "" })
	public String projectbyId(BizCusServiceProblem bizCusServiceProblem, Order projectbyId, BizOrderComplaint bizOrderComplaint, HttpServletRequest request, HttpServletResponse response, Model model) {
		Order order = null;
		if (bizCusServiceProblem.getId() != null) {
			order = orderService.getProjectbyId(bizCusServiceProblem.getId());
		} else {
			// if (projectbyId.getOrderId() == null)
			// {
			// model.addAttribute("message", "请先选择项目");
			// return "modules/ordercomplan/bizOrderComplaintForm";
			// }

			order = orderService.getProjectbyId(projectbyId.getOrderId() + "");
			// 查询分类信息的数据
			BizComplaintProblemType bizComplaintProblemType = bizOrderComplaint.getBizComplaintProblemType(); // 前台通过另一个类传过来的值setBizComplaintProblemTyp中查询
			bizComplaintProblemType.setTypeName(bizOrderComplaint.getTypeName());
			// 根据分类任务包名称查询这条分类是否有任务包
			BizComplaintProblemType bizComplaintProblemType3 = bizComplaintProblemTypeService.queryIsordertaskpackag(bizComplaintProblemType);
			if (bizComplaintProblemType3.getPackName() != null) {
				bizComplaintProblemType3 = bizComplaintProblemTypeService.queryComTypeName(bizComplaintProblemType);
			}

			bizOrderComplaint.setBizComplaintProblemType(bizComplaintProblemType3);
			// 查询事项信息
			if (bizComplaintProblemType3 != null) {
				BizComplaintProblemItem bizComplaintProblemItem = new BizComplaintProblemItem();
				bizComplaintProblemItem.setComplaintProblemTypeId(Integer.parseInt(bizComplaintProblemType3.getId()));
				List<BizComplaintProblemItem> bcpiList = bizComplaintProblemItemService.getcomplaintProblemTypeId(bizComplaintProblemItem);
				if (bcpiList.size() > 0) {
					StringBuffer buffer = new StringBuffer();
					for (BizComplaintProblemItem bizComplaintProblemItem2 : bcpiList) {
						buffer.append(bizComplaintProblemItem2.getId() + ",");
					}
					bizOrderComplaint.setBiItembeans(buffer.toString().substring(0, buffer.toString().length() - 1));
					bizOrderComplaint.setBiItemList(bcpiList);
				}
			}

		}
		bizOrderComplaint.setOrder(order);
		model.addAttribute("bizOrderComplaint", bizOrderComplaint);

		return "modules/ordercomplan/bizOrderComplaintForm";
	}

	@RequestMapping(value = "projectbyIdaflt")
	public String projectbyIdaflt(BizCusServiceProblem bizCusServiceProblem, Order projectbyId, BizOrderComplaint bizOrderComplaint, HttpServletRequest request, HttpServletResponse response, Model model) {
		Order order = null;
		if (bizCusServiceProblem.getId() != null) {
			order = orderService.getProjectbyId(bizCusServiceProblem.getId());
		} else {
			// if (projectbyId.getOrderId() == null)
			// {
			// model.addAttribute("message", "请先选择项目");
			// return "modules/ordercomplan/bizOrderComplaintForm";
			// }

			order = orderService.getProjectbyId(projectbyId.getOrderId() + "");
			// 查询分类信息的数据
			BizComplaintProblemType bizComplaintProblemType = bizOrderComplaint.getBizComplaintProblemType(); // 前台通过另一个类传过来的值setBizComplaintProblemTyp中查询
			bizComplaintProblemType.setTypeName(bizOrderComplaint.getTypeName());
			// 根据分类任务包名称查询这条分类是否有任务包
			BizComplaintProblemType bizComplaintProblemType3 = bizComplaintProblemTypeService.queryIsordertaskpackag(bizComplaintProblemType);
			if (bizComplaintProblemType3.getPackName() != null) {
				bizComplaintProblemType3 = bizComplaintProblemTypeService.queryComTypeName(bizComplaintProblemType);
			}

			bizOrderComplaint.setBizComplaintProblemType(bizComplaintProblemType3);
			// 查询事项信息
			if (bizComplaintProblemType3 != null) {
				BizComplaintProblemItem bizComplaintProblemItem = new BizComplaintProblemItem();
				bizComplaintProblemItem.setComplaintProblemTypeId(Integer.parseInt(bizComplaintProblemType3.getId()));
				List<BizComplaintProblemItem> bcpiList = bizComplaintProblemItemService.getcomplaintProblemTypeId(bizComplaintProblemItem);
				if (bcpiList.size() > 0) {
					StringBuffer buffer = new StringBuffer();
					for (BizComplaintProblemItem bizComplaintProblemItem2 : bcpiList) {
						buffer.append(bizComplaintProblemItem2.getId() + ",");
					}
					bizOrderComplaint.setBiItembeans(buffer.toString().substring(0, buffer.toString().length() - 1));
					bizOrderComplaint.setBiItemList(bcpiList);
				}
			}

		}
		bizOrderComplaint.setComplaintSource("4");
		bizOrderComplaint.setOrder(order);
		model.addAttribute("bizOrderComplaint", bizOrderComplaint);

		return "modules/ordercomplan/OrderComplaintFormCusService";
	}

	/**
	 * 售后订单ID查询所有的有关这个订单的信息
	 *
	 * @Title: ProjectbyId
	 * @Description: TODO
	 * @param @param bizCusServiceProblem
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequestMapping(value = { "ProjectbyIdsall", "" })
	public String ProjectbyIdsall(BizCusServiceProblem bizCusServiceProblem, Order projectbyId, BizOrderComplaint bizOrderComplaint, HttpServletRequest request, HttpServletResponse response, Model model) {
		Order order = null;
		if (bizCusServiceProblem.getId() != null) {
			order = orderService.getProjectbyId(bizCusServiceProblem.getId());
		} else {
			// if (projectbyId.getOrderId() == null)
			// {
			// model.addAttribute("message", "请先选择项目");
			// return "modules/ordercomplan/bizOrderComplaintForm";
			// }

			order = orderService.getProjectbyId(projectbyId.getOrderId() + "");
			// 查询分类信息的数据
			BizComplaintProblemType bizComplaintProblemType = bizOrderComplaint.getBizComplaintProblemType(); // 前台通过另一个类传过来的值setBizComplaintProblemTyp中查询
			bizComplaintProblemType.setTypeName(bizOrderComplaint.getTypeName());
			// 根据分类任务包名称查询这条分类是否有任务包
			BizComplaintProblemType bizComplaintProblemType3 = bizComplaintProblemTypeService.queryIsordertaskpackag(bizComplaintProblemType);
			if (bizComplaintProblemType3.getPackName() != null) {
				bizComplaintProblemType3 = bizComplaintProblemTypeService.queryComTypeName(bizComplaintProblemType);
			}

			bizOrderComplaint.setBizComplaintProblemType(bizComplaintProblemType3);
			// 查询事项信息
			if (bizComplaintProblemType3 != null) {
				BizComplaintProblemItem bizComplaintProblemItem = new BizComplaintProblemItem();
				bizComplaintProblemItem.setComplaintProblemTypeId(Integer.parseInt(bizComplaintProblemType3.getId()));
				List<BizComplaintProblemItem> bcpiList = bizComplaintProblemItemService.getcomplaintProblemTypeId(bizComplaintProblemItem);
				if (bcpiList.size() > 0) {
					StringBuffer buffer = new StringBuffer();
					for (BizComplaintProblemItem bizComplaintProblemItem2 : bcpiList) {
						buffer.append(bizComplaintProblemItem2.getId() + ",");
					}
					bizOrderComplaint.setBiItembeans(buffer.toString().substring(0, buffer.toString().length() - 1));
					bizOrderComplaint.setBiItemList(bcpiList);
				}
			}

		}
		bizOrderComplaint.setOrder(order);
		model.addAttribute("bizOrderComplaint", bizOrderComplaint);

		return "modules/ordercomplan/bizOrderComplaintForm";
	}

	@RequestMapping(value = { "ajaxgetItem", "" })
	@ResponseBody
	public String ajaxgetItem(BizOrderComplaint bizOrderComplaint, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 查询分类信息
		BizComplaintProblemType bizComplaintProblemType = bizOrderComplaint.getBizComplaintProblemType(); // 前台通过另一个类传过来的值setBizComplaintProblemTyp中查询
		bizComplaintProblemType.setTypeName(bizOrderComplaint.getTypeName());
		BizComplaintProblemType bizComplaintProblemType2 = bizComplaintProblemTypeService.queryComTypeName(bizComplaintProblemType);

		bizOrderComplaint.setBizComplaintProblemType(bizComplaintProblemType2);

		BizComplaintProblemItem bizComplaintProblemItem = new BizComplaintProblemItem();
		bizComplaintProblemItem.setComplaintProblemTypeId(Integer.parseInt(bizComplaintProblemType2.getId()));
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

	/**
	 * 查看来自订单信息的详情
	 *
	 * @Title: details
	 * @Description:
	 * @param @param bizCusServiceProblem
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequiresPermissions("cusserviceproblem:bizCusServiceProblem:view")
	@RequestMapping(value = { "details", "" })
	public String details(BizCusServiceProblem bizCusServiceProblem, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<BizCusServiceProblem> findList = bizCusServiceProblemService.findList(bizCusServiceProblem);
		BizCusServiceProblem bizCusServiceProblem2 = findList.get(0);
		model.addAttribute("bizCusServiceProblem", bizCusServiceProblem2);

		return "modules/cusserviceproblem/bizCusServiceProblemForm";
	}

	@RequiresPermissions("cusserviceproblem:bizCusServiceProblem:view")
	@RequestMapping(value = { "viewPicsById" })
	public String viewPicsById(Integer id, HttpServletRequest request, HttpServletResponse response, Model model) {
		String photo = bizCusServiceProblemService.findPicsById(id);

		if (null != photo && !"".equals(photo.trim())) {
			String[] photos = photo.split(",");
			if (photos.length > 0) {
				List<String> list = new ArrayList<>();
				for (int v = 0; v < photos.length; v++) {
					list.add(photos[v]);
				}
				model.addAttribute("picPrefixName", request.getContextPath());
				model.addAttribute("picList", list);
			}
		}
		return "modules/cusserviceproblem/photo";
	}

	/*
	 * 查询图
	 */
	@RequestMapping(value = { "ajaxViewPicsById" })
	@ResponseBody
	public Map<Object, Object> ajaxViewPicsById(Integer id, HttpServletRequest request, HttpServletResponse response, Model model) {
		String photo = bizCusServiceProblemService.findPicsById(id);
		List<String> list = new ArrayList<>();

		if (null != photo && !"".equals(photo.trim())) {
			String[] photos = photo.split(",");
			if (photos.length > 0) {
				for (int v = 0; v < photos.length; v++) {
					list.add(photos[v]);
				}
				model.addAttribute("picPrefixName", request.getContextPath());
				model.addAttribute("picList", list);
			}
		}
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", list);

		return mapObject;
	}
}