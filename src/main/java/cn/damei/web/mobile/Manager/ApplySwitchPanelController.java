package cn.damei.web.mobile.Manager;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.Base64Util;
import cn.damei.common.SessionUtils;
import cn.damei.service.mobile.Manager.ApplySandService;
import cn.damei.entity.mobile.Manager.PurchaseVo;
import cn.damei.entity.mobile.Manager.SwitchPanelOrderVo;
import cn.damei.entity.mobile.Manager.SwitchPanelPic;
import cn.damei.entity.mobile.Manager.SwitchPanelVo;
import cn.damei.service.mobile.Manager.ApplySwitchPanelService;
import cn.damei.entity.mobile.Manager.PurchaseDetailsVo;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.web.mobile.home.JobSiteController;
import cn.damei.dao.mobile.home.HomeReportDao;
import cn.damei.entity.mobile.home.ViewLog;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;
import cn.damei.service.modules.BizPurchaseMainPanelService;

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年9月27日 下午3:55:52 订单的主材之开关面板 1:申请
 * 
 */

@Controller
@RequestMapping(value = "${adminPath}/app/manager/applySwitchPanel")
public class ApplySwitchPanelController
{

	@Autowired
	private BizPurchaseMainPanelService bizPurchaseMainPanelService;
	@Autowired
	private ApplySwitchPanelService service;
	@Autowired
	private ApplySandService applySandService;
	@Autowired
	private OrderService2 orderService2;

	@RequestMapping(value = "orderList")
	public String orderList(Model model, HttpServletRequest request)
	{


		// 根据登录的经理 查询所有的订单
		Manager manager = SessionUtils.getManagerSession(request);

		if (manager != null && null != manager.getId())
		{
			List<SwitchPanelOrderVo> list = service.getOrderListForSwitchPanelByManagerId(manager.getId());

			model.addAttribute("orderList", list);
		} else
			model.addAttribute("error", "出现了某些不可预估的错误 ,请联系管理员");

		// 放入视图model中

		// 返回申请开关面板页面
		return "mobile/modules/Manager/switchPanelApply/switch_panel_apply";
	}

	/**
	 * 申请开关面板数据校验
	 * @param orderId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "applySwitchPanel_data_check_ajax")
	public @ResponseBody String applySwitchPanelDataCheckAjax(String orderId,HttpServletRequest request){
		
		String result = "0";
		//1.判断订单ID是否为空
		if(StringUtils.isBlank(orderId)){
			//订单ID为空
			result = "1";
			return result;
		}
		//2.登录的项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		if(null==manager){
			//项目经理是否登录
			result = "2";
			return result;
		}
		
		Order2 order = orderService2.findOrderById(Integer.valueOf(orderId));
		if(null!=order && order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_4)){
			//3.判断【基装】约检节点的状态是否大于等于10-质检员已提交约检验收。
			//如果是，则不允许再申请开关面板
			Integer count = applySandService.findQcBillAcceptStatus(Integer.valueOf(orderId));
			if(null==count || count>0){
				result = "3";
				return result;
			}
		}
		
		
		// 查询已经申请的采购单
		PurchaseVo purchaseVo = service.selectPurchaseByOrderIdLimitOneOrderByTime(Integer.parseInt(orderId));
		// 如果有采购单
		if (null != purchaseVo){
			// 4.根据播报单id 查询是否已读
			ViewLog log = new ViewLog();
			log.setBusinessIdInt(purchaseVo.getId());
			log.setBusinessType("301");// 开关面板
			log.setBusinessViewerOnlyMark(manager.getPhone());
			Integer integer = logDao.findView(log);
			if (null == integer || integer == 0){
				result = "4";
				return result;
			}
			// 5.根据提交时间判断是否可以再次申请

			if (purchaseVo.getApplyTime().getTime() + 300 * 1000 > new Date().getTime()){
				result = "5";
				return result;
			}

		}
				
		return result;
		
	}
	
	/**
	 * 修改开关面板
	 * 
	 * @Title: switchPanelApply
	 * @Description: TODO
	 * @param @param orderId
	 * @param @param model
	 * @param @param request
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequestMapping(value = "switchPanelApply")
	public String switchPanelApply(String orderId, Model model, HttpServletRequest request)
	{

		// 得到登录的项目经理
		// 查询是哪个门店的, 得到门店id
		// 查询门店下下的开关面板

		// 更新为根据订单id
		// 2017-3-9加入是否计算数量限制(部分开关面板不计算到与合同面积的数量限制中)
		List<SwitchPanelVo> switchPanelList = service.selectSwitchPanelByStoreId(Integer.parseInt(orderId));
		String managerInfo = SessionUtils.getManagerSession(request).getRealname() + "-" + SessionUtils.getManagerSession(request).getPhone();

		// 12-26 新增需求, 开关面板超定额
		// 1: 该订单历史申请开关面板数量
		// 2:取该订单的合同面积*048 历史数量+这次页面的数量不能大于订单合同面积*0.48 如果 -->弹出超出原因 +照片

		PurchaseDetailsVo vo = service.selectOrderContractAreaAndTotalCount(Integer.parseInt(orderId));

		model.addAttribute("purchaseVo", vo);

		model.addAttribute("list", switchPanelList);
		model.addAttribute("managerInfo", managerInfo);
		model.addAttribute("orderId", orderId);
		return "mobile/modules/Manager/switchPanelApply/switch_submit";
	}

	/**
	 * 提交开关面板
	 * 
	 * @param totalMoney
	 * @param request
	 * @param hopeForTime
	 * @param orderId
	 * @param remarks
	 * @param ids
	 * @param count
	 * @param photo
	 * @param overApplyWords
	 * @return
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "submit")
	public @ResponseBody String submit(String overCount, String currentcount, String overApplyWords, String[] photo, String totalMoney, HttpServletRequest request, String hopeForTime, String orderId, String remarks, String[] ids, String[] count, String[] brand) throws ParseException, UnsupportedEncodingException
	{
		
		Order2 order = orderService2.findOrderById(Integer.valueOf(orderId));
		if(null!=order && order.getProjectMode().equals(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_4)){
			
			//判断【基装】约检节点的状态是否大于等于10-质检员已提交约检验收。
			//如果是，则不允许再申请开关面板
			Integer countApply = applySandService.findQcBillAcceptStatus(Integer.valueOf(orderId));
			if(null==countApply || countApply>0){
				return "noApply";
			}
			
		}
				
				
		// 查询已经申请的采购单
		PurchaseVo purchaseVo = service.selectPurchaseByOrderIdLimitOneOrderByTime(Integer.parseInt(orderId));

		if (null != purchaseVo)
		{
			// 根据时间判断是否可以再次申请

			if (purchaseVo.getApplyTime().getTime() + 300 * 1000 > new Date().getTime())
			{
				// 五分钟之内不可以
				return "NO";
			}
		}

		Integer storeId = service.selectstoreIdByManagerId(SessionUtils.getManagerSession(request).getId());

		// 返回采购单对象有id
		PurchaseVo purcharse = service.savePurchase(request, overCount, currentcount, orderId, hopeForTime, remarks, totalMoney);

		if (null != photo && photo.length > 0)
		{
			// 保存采购单表
			if (null == overApplyWords || "".equals(overApplyWords))
			{
				// 变更原因
				purcharse.setOverReasonType("1");

			} else
			{
				// 其他原因
				purcharse.setOverReasonType("2");
				// 备注
				purcharse.setOverWords(overApplyWords);
			}
			service.updatePurchaseByid(purcharse);
			for (String p : photo)
			{

				String uuid = UUID.randomUUID().toString().replaceAll("-", "");

				// String rootPath = PicRootName.SystemEnvironment(request);
				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + ConstantUtils.UPLOAD_PURCHASE_APPLY_OVER_SWITCHPANEL + DateUtils.getDate1());
				// 判断该文件是否存在
				if (!filePath.exists() && !filePath.isDirectory())
				{
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);

				String picpath = ConstantUtils.UPLOAD_PURCHASE_APPLY_OVER_SWITCHPANEL + DateUtils.getDate1() + filePath.separator + uuid + ".jpeg";

				SwitchPanelPic switchPanelPic = new SwitchPanelPic();
				switchPanelPic.setBussinessType("104");
				switchPanelPic.setPicUpdateTime(new Date());
				switchPanelPic.setPicUrl(picpath);
				switchPanelPic.setRelatedBussinessId(purcharse.getId());

				service.saveSwitchPanelPic(switchPanelPic);
			}
		}
		try
		{
			// 保存对应的商品
			service.saveSwitchPanel(request, ids, count, orderId, storeId, brand, purcharse.getId());
		} catch (Exception e)
		{

			service.deletePurchaseById(purcharse.getId());
			return "fail";
		}

		return String.valueOf(purcharse.getId());
	}

	@RequestMapping(value = "record")
	public String record(String orderId, Model model)
	{

		// 根据orderId 查询采购单

		List<PurchaseVo> purchaseList = new ArrayList<PurchaseVo>();
		if(StringUtils.isNotBlank(orderId)){
			purchaseList = service.selectPurchaseByOrderId(Integer.parseInt(orderId));
		}
		// 采购单包括: 1:编号 2:状态 3:第几次申请 4:期望进场日期 5:申请时间
		if (null != purchaseList && purchaseList.size() > 0)
		{
			String customerInfo = purchaseList.get(0).getCommunityName() + "-" + purchaseList.get(0).getBuildNumber() + "-" + purchaseList.get(0).getBuildUnit() + "-" + purchaseList.get(0).getBuildRoom() + "-" + purchaseList.get(0).getCustomerName();

			model.addAttribute("purchaseList", purchaseList);
			model.addAttribute("customerInfo", customerInfo);
		} else
		{
			model.addAttribute("error", "该订单暂无开关面板申请记录");
		}

		return "mobile/modules/Manager/switchPanelApply/switch_record";
	}

	@Autowired
	private HomeReportDao logDao;

	@RequestMapping(value = "details")
	public String details(String id, Model model, HttpServletRequest request)
	{

		// 插入已阅读log表

		ViewLog log = new ViewLog();

		log.setBusinessIdInt(Integer.parseInt(id));
		log.setBusinessType("301");// 开关面板
		log.setBusinessViewerOnlyMark(SessionUtils.getManagerSession(request).getPhone());
		Integer integer = logDao.findView(log);

		if (null == integer || integer == 0)
		{

			log.setBusinessType("301");
			Date date = new Date();
			log.setBusinessViewDatetime(date);
			log.setBusinessViewDatetime(date);
			log.setBusinessViewerOnlyMark(SessionUtils.getManagerSession(request).getPhone());
			log.setCreateDate(date);
			log.setUpdateDate(date);
			log.setDelFlag("0");
			log.setBusinessIdInt(Integer.parseInt(JobSiteController.isNum(id) ? id : "0"));
			logDao.insertView(log);

		}

		// 根据采购单编号 查询详情
		Double totalMoney = (double) 0;
		Integer totalCount = 0;
		String customerInfo = "";
		List<PurchaseDetailsVo> purchaseDetail = service.selectPurchaseDetailsByPurchaseCode(Integer.parseInt(id));

		// 一个是采购单详情 多个是辅料详情
		PurchaseVo purchaseVo = new PurchaseVo();
		// 如果有详情
		if (null != purchaseDetail && purchaseDetail.size() > 0)
		{

			// 设置辅料小计总金额
			for (PurchaseDetailsVo detail : purchaseDetail)
			{

				// 一个采购单
				// 编号
				purchaseVo.setPurchaseCode(detail.getPurchaseCode());
				// 状态 : 去字典表的内容
				purchaseVo.setStatus(detail.getStatus());
				// 期望送货日期
				purchaseVo.setHopeForTime(detail.getHopeForTime());
				// 废弃原因
				purchaseVo.setStatusDescribe(detail.getStatusDescribe());
				// 状态
				purchaseVo.setStatusId(detail.getStatusId());

				customerInfo = detail.getCommunityName() + "-" + detail.getBuildNumber() + "-" + detail.getBuildUnit() + "-" + detail.getBuildRoom() + "-" + detail.getCustomerName();
				purchaseVo.setOrderId(detail.getOrderId());

				// 如果价格和数量不为0 不位空
				if (detail.getPrice() != 0 && detail.getCount() != 0 || null != detail.getPrice() && null != detail.getCount())
				{
					detail.setAuxiliaryMoney(detail.getPrice() * detail.getCount());
					// 总价
					totalMoney += detail.getAuxiliaryMoney();
					totalCount += detail.getCount();

				}
			}
			DecimalFormat format = new DecimalFormat("0.00");

			String string = format.format(totalMoney);
			// 所有辅料的总价
			purchaseVo.setAuxiliaryAllMoney(Double.parseDouble(string));

			// 所有辅料的数量
			purchaseVo.setTotalCount(totalCount);
			model.addAttribute("purchaseDetail", purchaseDetail);
			model.addAttribute("purchaseVo", purchaseVo);

		} else
		{
			model.addAttribute("error", "没有采购单编号");

		}

		model.addAttribute("customerInfo", customerInfo);
		// 到采购单详情页
		return "mobile/modules/Manager/switchPanelApply/switch_apply_details";
	}

}