package cn.damei.service.mobile.Manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.service.modules.BizPhoneMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.PurchaseConstantUtil;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.SessionUtils;
import cn.damei.dao.mobile.Manager.ApplySwitchPanelDao;
import cn.damei.entity.mobile.Manager.PurchaseCode;
import cn.damei.entity.mobile.Manager.PurchaseVo;
import cn.damei.entity.mobile.Manager.SwitchPanelOrderVo;
import cn.damei.entity.mobile.Manager.SwitchPanelPic;
import cn.damei.entity.mobile.Manager.SwitchPanelVo;
import cn.damei.entity.mobile.Manager.PurchaseDetailsVo;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年9月28日 上午10:14:21 类说明
 */

@Service
@Transactional(readOnly = true)
public class ApplySwitchPanelService extends CrudService2<ApplySwitchPanelDao, SwitchPanelOrderVo>
{

	private Logger logger = LoggerFactory.getLogger(ApplySwitchPanelService.class);// 日志

	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;

	/**
	 * 根据项目经理查询所有订单 订单有状态设置
	 * 
	 * @param managerId
	 * @return
	 */
	public List<SwitchPanelOrderVo> getOrderListForSwitchPanelByManagerId(Integer managerId)
	{

		return dao.getOrderListForSwitchPanelByManagerId(managerId);
	}

	/**
	 * 查询该门店下的开关面板
	 * 
	 * @param storeId
	 * @return
	 */
	public List<SwitchPanelVo> selectSwitchPanelByStoreId(Integer storeId)
	{
		return dao.selectSwitchPanelByStoreId(storeId);
	}

	/**
	 * 采购单编号生成方法
	 * 
	 *
	 *
	 *
	 */
	public String purchaseCode()
	{
		// 以PO开头
		String purchaseCode = ConstantUtils.PURCHASE_NUMBER;

		StringBuilder builder = new StringBuilder();

		// num和date
		PurchaseCode purchaseObj = dao.getCode();
		// 流水号+1
		purchaseObj.setPurchaseCode(String.valueOf(Integer.parseInt(purchaseObj.getPurchaseCode()) + 1));
		// 更新数据库
		dao.updateCode(purchaseObj);

		// 格式后的时间戳
		String format = new SimpleDateFormat("yyyyMMdd").format(purchaseObj.getAuxiliaryDate());
		// 得到的流水号
		String code = purchaseObj.getPurchaseCode();

		builder.append(purchaseCode).append(format);
		// 判断长度
		if (code.length() == 1)
		{

			builder.append("000").append(code);

		} else if (code.length() == 2)
		{
			// 拼接采购单编号
			builder.append("00").append(code);
		} else if (code.length() == 3)
		{
			builder.append("0").append(code);
		} else if (code.length() == 4)
		{
			builder.append(code);
		}

		// 返回采购单编号
		return builder.toString();
	}

	/**
	 * 保存采购单
	 * 
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public PurchaseVo savePurchase(HttpServletRequest request, String overCount, String currentcount, String orderId, String hopeForTime, String remarks, String totalMoney) throws ParseException
	{

		Date nowDate = new Date();

		PurchaseVo purcharse = new PurchaseVo();
		// 超出数量
		if (null != overCount && !overCount.equals(""))
		{
			purcharse.setOverCount(Integer.parseInt(overCount));
		}
		// 采购单商品总数
		if (null != currentcount && !currentcount.equals(""))
		{
			purcharse.setPurchaseCountTotal(Double.valueOf(currentcount));
		}
		// 订单id
		if (null != orderId && !orderId.equals(""))
		{
			purcharse.setOrderId(Integer.valueOf(orderId));
		}
		// 状态 10为项目经理已提交
		purcharse.setStatus(PurchaseConstantUtil.PURCHASE_AUXILIARY_STATUS_10);
		// 申请人为当前登录的项目经理
		purcharse.setApplyPerson(SessionUtils.getManagerSession(request).getId());
		// 申请时间为当前时间
		purcharse.setApplyTime(nowDate);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
		java.util.Date date = sdf.parse(hopeForTime);

		// 备注为期望送货人 默认登陆者+手机(项目经理)
		purcharse.setRemarks(remarks);
		// 期望送货时间
		purcharse.setHopeForTime(date);

		// 采购单编号
		purcharse.setPurchaseCode(purchaseCode());
		purcharse.setCreateDate(nowDate);
		purcharse.setDelFlag(PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);

		// 总价
		if (null != totalMoney && !totalMoney.equals(""))
		{
			purcharse.setAuxiliaryAllMoney(Double.parseDouble(totalMoney));
		}
		// 采购单类型
		purcharse.setPurchaseType(PurchaseConstantUtil.PURCHASE_TYPE_2);

		dao.savePurchase(purcharse);
		return purcharse;

	}

	// 更新采购单
	@Transactional(readOnly = false)
	public void updatePurchaseByid(PurchaseVo purcharse)
	{

		dao.updatePurchaseByid(purcharse);
	}

	/**
	 * 根据开关面板的门店id 和编号 查询 开关面板
	 * 
	 * @param vo
	 * @return
	 */
	public SwitchPanelVo selectAttributeForSwitchPanel(SwitchPanelVo vo)
	{

		return dao.selectAttributeForSwitchPanel(vo);
	}

	/**
	 * 采购单编码
	 * 
	 */
	public PurchaseCode getCode()
	{

		return dao.getCode();
	}

	/**
	 * 更新采购单编码
	 */
	@Transactional(readOnly = false)
	public void updateCode(PurchaseCode code)
	{
		dao.updateCode(code);
	}

	/**
	 * 保存开关面板申请记录
	 *
	 */
	@Transactional(readOnly = false)
	public void saveSwitchPanel(HttpServletRequest request, String ids[], String count[], String orderId, Integer storeId, String brand[], Integer purcharseId)
	{
		for (int v = 0; v < ids.length; v++)
		{

			if (count[v] != null && count[v] != "" && count[v] != "," && !count[v].equals("0"))
			{

				// 已有: 开关面板编号, 数量, 门店id 根据 编号和门店id 查询 开关面板表, 查到价格 ,名字 ,等
				SwitchPanelVo panelVo = new SwitchPanelVo();
				panelVo.setOrderId(Integer.parseInt(orderId));
				panelVo.setId(Integer.parseInt(ids[v]));
				panelVo.setManagerId(SessionUtils.getManagerSession(request).getId());
				panelVo.setStoreId(storeId);
				// 保存到biz_purchase_main_mate表中
				SwitchPanelVo switchPanel = dao.selectAttributeForSwitchPanel(panelVo);
				// 需要的数据有 采购单id,订单id, 编号,数量,名字,规格,单位,图片

				// 数量是count
				switchPanel.setCount(Integer.parseInt(count[v]));
				switchPanel.setOwedCount(switchPanel.getCount());
				switchPanel.setReceivedCount(0);
				switchPanel.setBrands(brand[v]);
				// 订单
				switchPanel.setOrderId(Integer.parseInt(orderId));
				// 采购单
				switchPanel.setPurchaseId(purcharseId);
				// 开关面板
				switchPanel.setSwitchPanelType(ConstantUtils.SWITCH_PANEL_NUMBER);
				switchPanel.setCategoryId(dao.selectSwitchPanelCategoryId());
				dao.saveSwitchPanel(switchPanel);
			}
		}

		String managerName = SessionUtils.getManagerSession(request).getRealname();
		String managerPhone = SessionUtils.getManagerSession(request).getPhone();

		// 根据门店和短信组类型查找 messageGroupType : '8';
		BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(storeId + "", "8");
		List<Integer> list = new ArrayList<Integer>();
		List<BizEmployee2> employeelist = null;

		if (null != bizMessagegroup)
		{
			Order2 order = orderService2.findOrderById(Integer.parseInt(orderId));
			String[] str = bizMessagegroup.getEmployees().split(",");
			for (String id : str)
			{
				list.add(Integer.valueOf(id));
			}
			employeelist = bizEmployeeService2.getById(list);
			// 订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），项目经理（姓名-手机号），项目经理已申请开关面板，请尽快登录系统查看详情
			String content = "订单（" + order.getOrderNumber() + "," + order.getCommunityName() + "-" + order.getBuildNumber() + "-" + order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName() + "-" + order.getCustomerPhone() + ",项目经理（" + managerName + "-" + managerPhone + "），项目经理已申请开关面板，请尽快登录系统查看详情。";
			if (null != employeelist && employeelist.size() > 0){
				for (BizEmployee2 bizEmployee2 : employeelist){
					bizPhoneMsgService.sendMessage(bizEmployee2.getId(), bizEmployee2.getPhone(),
							content, SendMsgBusinessType.RELATED_BUSINESS_TYPE_900202,Integer.parseInt(orderId));
				}
			}
		}

	}

	@Autowired
	private OrderService2 orderService2;
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	public Integer selectstoreIdByManagerId(Integer managerId)
	{

		return dao.selectstoreIdByManagerId(managerId);
	}

	@Transactional(readOnly = false)
	public void deletePurchaseById(Integer purchaseId)
	{

		dao.deletePurchaseById(purchaseId);
	}

	/**
	 * 根据orderId 查询辅料采购单
	 * 
	 * @param orderId
	 * @return
	 */
	public List<PurchaseVo> selectPurchaseByOrderId(Integer orderId)
	{
		return dao.selectPurchaseByOrderId(orderId);
	}

	// 查询最近的采购单 根据orderId
	public PurchaseVo selectPurchaseByOrderIdLimitOneOrderByTime(Integer orderId)
	{

		return dao.selectPurchaseByOrderIdLimitOneOrderByTime(orderId);
	}

	/**
	 * 根据采购单编号查询采购单详情
	 * 
	 * @param purchaseId
	 * @return
	 */
	public List<PurchaseDetailsVo> selectPurchaseDetailsByPurchaseCode(Integer purchaseId)
	{
		return dao.selectPurchaseDetailsByPurchaseCode(purchaseId);
	}

	public Integer selectSwitchPanelCategoryId()
	{

		return dao.selectSwitchPanelCategoryId();
	}

	/**
	 * 查询该订单历史申请记录和 合同的面积
	 * 
	 * @param orderId
	 * @return
	 */
	public PurchaseDetailsVo selectOrderContractAreaAndTotalCount(Integer orderId)
	{

		return dao.selectOrderContractAreaAndTotalCount(orderId);
	}

	@Transactional(readOnly = false)
	public void saveSwitchPanelPic(SwitchPanelPic switchPanelPic)
	{
		dao.saveSwitchPanelPic(switchPanelPic);

	}
}