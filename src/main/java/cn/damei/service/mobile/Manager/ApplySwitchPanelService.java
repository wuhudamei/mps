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



@Service
@Transactional(readOnly = true)
public class ApplySwitchPanelService extends CrudService2<ApplySwitchPanelDao, SwitchPanelOrderVo>
{

	private Logger logger = LoggerFactory.getLogger(ApplySwitchPanelService.class);

	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;


	public List<SwitchPanelOrderVo> getOrderListForSwitchPanelByManagerId(Integer managerId)
	{

		return dao.getOrderListForSwitchPanelByManagerId(managerId);
	}


	public List<SwitchPanelVo> selectSwitchPanelByStoreId(Integer storeId)
	{
		return dao.selectSwitchPanelByStoreId(storeId);
	}


	public String purchaseCode()
	{

		String purchaseCode = ConstantUtils.PURCHASE_NUMBER;

		StringBuilder builder = new StringBuilder();


		PurchaseCode purchaseObj = dao.getCode();

		purchaseObj.setPurchaseCode(String.valueOf(Integer.parseInt(purchaseObj.getPurchaseCode()) + 1));

		dao.updateCode(purchaseObj);


		String format = new SimpleDateFormat("yyyyMMdd").format(purchaseObj.getAuxiliaryDate());

		String code = purchaseObj.getPurchaseCode();

		builder.append(purchaseCode).append(format);

		if (code.length() == 1)
		{

			builder.append("000").append(code);

		} else if (code.length() == 2)
		{

			builder.append("00").append(code);
		} else if (code.length() == 3)
		{
			builder.append("0").append(code);
		} else if (code.length() == 4)
		{
			builder.append(code);
		}


		return builder.toString();
	}


	@Transactional(readOnly = false)
	public PurchaseVo savePurchase(HttpServletRequest request, String overCount, String currentcount, String orderId, String hopeForTime, String remarks, String totalMoney) throws ParseException
	{

		Date nowDate = new Date();

		PurchaseVo purcharse = new PurchaseVo();

		if (null != overCount && !overCount.equals(""))
		{
			purcharse.setOverCount(Integer.parseInt(overCount));
		}

		if (null != currentcount && !currentcount.equals(""))
		{
			purcharse.setPurchaseCountTotal(Double.valueOf(currentcount));
		}

		if (null != orderId && !orderId.equals(""))
		{
			purcharse.setOrderId(Integer.valueOf(orderId));
		}

		purcharse.setStatus(PurchaseConstantUtil.PURCHASE_AUXILIARY_STATUS_10);

		purcharse.setApplyPerson(SessionUtils.getManagerSession(request).getId());

		purcharse.setApplyTime(nowDate);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse(hopeForTime);


		purcharse.setRemarks(remarks);

		purcharse.setHopeForTime(date);


		purcharse.setPurchaseCode(purchaseCode());
		purcharse.setCreateDate(nowDate);
		purcharse.setDelFlag(PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);


		if (null != totalMoney && !totalMoney.equals(""))
		{
			purcharse.setAuxiliaryAllMoney(Double.parseDouble(totalMoney));
		}

		purcharse.setPurchaseType(PurchaseConstantUtil.PURCHASE_TYPE_2);

		dao.savePurchase(purcharse);
		return purcharse;

	}


	@Transactional(readOnly = false)
	public void updatePurchaseByid(PurchaseVo purcharse)
	{

		dao.updatePurchaseByid(purcharse);
	}


	public SwitchPanelVo selectAttributeForSwitchPanel(SwitchPanelVo vo)
	{

		return dao.selectAttributeForSwitchPanel(vo);
	}


	public PurchaseCode getCode()
	{

		return dao.getCode();
	}


	@Transactional(readOnly = false)
	public void updateCode(PurchaseCode code)
	{
		dao.updateCode(code);
	}


	@Transactional(readOnly = false)
	public void saveSwitchPanel(HttpServletRequest request, String ids[], String count[], String orderId, Integer storeId, String brand[], Integer purcharseId)
	{
		for (int v = 0; v < ids.length; v++)
		{

			if (count[v] != null && count[v] != "" && count[v] != "," && !count[v].equals("0"))
			{


				SwitchPanelVo panelVo = new SwitchPanelVo();
				panelVo.setOrderId(Integer.parseInt(orderId));
				panelVo.setId(Integer.parseInt(ids[v]));
				panelVo.setManagerId(SessionUtils.getManagerSession(request).getId());
				panelVo.setStoreId(storeId);

				SwitchPanelVo switchPanel = dao.selectAttributeForSwitchPanel(panelVo);



				switchPanel.setCount(Integer.parseInt(count[v]));
				switchPanel.setOwedCount(switchPanel.getCount());
				switchPanel.setReceivedCount(0);
				switchPanel.setBrands(brand[v]);

				switchPanel.setOrderId(Integer.parseInt(orderId));

				switchPanel.setPurchaseId(purcharseId);

				switchPanel.setSwitchPanelType(ConstantUtils.SWITCH_PANEL_NUMBER);
				switchPanel.setCategoryId(dao.selectSwitchPanelCategoryId());
				dao.saveSwitchPanel(switchPanel);
			}
		}

		String managerName = SessionUtils.getManagerSession(request).getRealname();
		String managerPhone = SessionUtils.getManagerSession(request).getPhone();


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


	public List<PurchaseVo> selectPurchaseByOrderId(Integer orderId)
	{
		return dao.selectPurchaseByOrderId(orderId);
	}


	public PurchaseVo selectPurchaseByOrderIdLimitOneOrderByTime(Integer orderId)
	{

		return dao.selectPurchaseByOrderIdLimitOneOrderByTime(orderId);
	}


	public List<PurchaseDetailsVo> selectPurchaseDetailsByPurchaseCode(Integer purchaseId)
	{
		return dao.selectPurchaseDetailsByPurchaseCode(purchaseId);
	}

	public Integer selectSwitchPanelCategoryId()
	{

		return dao.selectSwitchPanelCategoryId();
	}


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