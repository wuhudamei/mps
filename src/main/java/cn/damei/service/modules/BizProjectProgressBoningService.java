/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.BizPrePmSettleFinDao;
import cn.damei.dao.modules.BizProjectProgressBoningDao;
import cn.damei.entity.modules.BizProjectProgressBoning;
import cn.damei.common.utils.BizDictUtils;

/**
 * 工程进度大看板Service
 * 
 * @author qww
 * @version 2016-10-26
 */
@Service
@Transactional(readOnly = true)
public class BizProjectProgressBoningService
		extends CrudService2<BizProjectProgressBoningDao, BizProjectProgressBoning> {

	@Autowired
	private BizPrePmSettleFinDao bizPrePmSettleFinDao;

	public BizProjectProgressBoning get(Integer id) {
		return super.get(id);
	}

	public List<BizProjectProgressBoning> findList(BizProjectProgressBoning bizProjectProgressBoning) {
		return super.findList(bizProjectProgressBoning);
	}

	public Page<BizProjectProgressBoning> findPage(Page<BizProjectProgressBoning> page,
			BizProjectProgressBoning bizProjectProgressBoning) {
		return super.findPage(page, bizProjectProgressBoning);
	}

	public Page<BizProjectProgressBoning> findPage1(Page<BizProjectProgressBoning> page,
			BizProjectProgressBoning bizProjectProgressBoning) throws Exception {
		Page<BizProjectProgressBoning> pageList = super.findPage(page, bizProjectProgressBoning);
		List<BizProjectProgressBoning> list = pageList.getList();
		if (CollectionUtils.isNotEmpty(list)) {
			for (BizProjectProgressBoning boning : list) {
				int index = 0;
				Date oldDate = null;
				for (int i = 1; i <= 22; i++) {
					Class ownerClass = boning.getClass();
					Method method = ownerClass.getMethod("getNode" + i + "ActualDate");
					Object obj = method.invoke(boning);
					if (obj != null) {
						Date date = (Date) obj;
						// 判断日期大小
						Map<String, Object> map = this.compare(index, oldDate, i, date);
						index = Integer.parseInt(map.get("index").toString());
						oldDate = (Date) map.get("oldDate");
					}
				}
				// 判断是哪个节点
				String noteName = this.getNoteName(index);
				boning.setNodeName(noteName);
				boning.setNodeLastActualDate(oldDate);
			}
		}
		return pageList;
	}

	/**
	 * 比较两个日期的大小
	 * 
	 * @param index
	 * @param oldDate
	 * @param i
	 * @param newDate
	 */
	private Map<String, Object> compare(int index, Date oldDate, int i, Date newDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (oldDate == null || oldDate.getTime() < newDate.getTime()) {
			map.put("index", i);
			map.put("oldDate", newDate);
		} else {
			map.put("index", index);
			map.put("oldDate", oldDate);
		}
		return map;
	}

	private String getNoteName(int index) {
		String noteName = "";
		switch (index) {
		case 1:
			noteName = "主材第一次核尺";
			break;
		case 2:
			noteName = "辅材进场";
			break;
		case 3:
			noteName = "瓷砖";
			break;
		case 4:
			noteName = "水电隐蔽验收";
			break;
		case 5:
			noteName = "防水验收";
			break;
		case 6:
			noteName = "橱柜核尺";
			break;
		case 7:
			noteName = "瓦工验收";
			break;
		case 8:
			noteName = "二期款（同瓦工验收日期）";
			break;
		case 9:
			noteName = "基础施工验收";
			break;
		case 10:
			noteName = "厨卫吊顶";
			break;
		case 11:
			noteName = "洁具";
			break;
		case 12:
			noteName = "五金，灯具，开关面板";
			break;
		case 13:
			noteName = "橱柜";
			break;
		case 14:
			noteName = "定制衣柜";
			break;
		case 15:
			noteName = "壁纸";
			break;
		case 16:
			noteName = "木门，铝镁门，门窗套";
			break;
		case 17:
			noteName = "木地板";
			break;
		case 18:
			noteName = "窗帘";
			break;
		case 19:
			noteName = "竣工验收";
			break;
		case 20:
			noteName = "尾款";
			break;
		case 21:
			noteName = "家电";
			break;
		case 22:
			noteName = "家具";
			break;
		default:
			noteName = "";
			break;
		}
		return noteName;
	}

	@Transactional(readOnly = false)
	public void insert(BizProjectProgressBoning bizProjectProgressBoning) {
		dao.insert(bizProjectProgressBoning);
		if(bizProjectProgressBoning.getOrderStatus().equals("400") || bizProjectProgressBoning.getIsScrap() == 1){//订单状态是已竣工或者订单已废除
			//更新订单表的is_to_refresh_process_data字段为1
			dao.updateOrder(bizProjectProgressBoning.getOrderId());
		}
	}

	@Transactional(readOnly = false)
	public void save(BizProjectProgressBoning bizProjectProgressBoning) {
		super.save(bizProjectProgressBoning);
	}

	@Transactional(readOnly = false)
	public void delete(BizProjectProgressBoning bizProjectProgressBoning) {
		super.delete(bizProjectProgressBoning);
	}

	/**
	 * 查询订单
	 * 
	 * @return
	 */
	public List<BizProjectProgressBoning> queryOrderByCondition() {
		return dao.queryOrderByCondition();
	}

	/**
	 * 根据订单查询
	 * 
	 * @param orderId
	 * @return
	 */
	public BizProjectProgressBoning queryByOrderId(Integer orderId) {
		return dao.queryByOrderId(orderId);
	}

	/**
	 * 根据订单id更新
	 * 
	 * @param boning
	 */
	@Transactional(readOnly = false)
	public void updateByOrderId(BizProjectProgressBoning boning) {
		dao.updateByOrderId(boning);
		if(boning.getOrderStatus().equals("400") || boning.getIsScrap() == 1){//订单状态是已竣工或者订单已废除
			//更新订单表的is_to_refresh_process_data字段为1
			dao.updateOrder(boning.getOrderId());
		}
	}

	/**
	 * 更新订单节点
	 * 
	 * @param orderId
	 */
	@Transactional(readOnly = false)
	public void editOrderNode(Integer orderId) {
		BizProjectProgressBoning boning = dao.queryOrderByOrderId(orderId);
		if (boning != null) {
			this.queryOrderAllNode(boning);
			boning.setUpdateDate(new Date());
			dao.updateByOrderId(boning);
		}
	}

	public void queryOrderAllNode(BizProjectProgressBoning boning) {
		BizProjectProgressBoning delayDaysBoing = dao.queryDelayDaysByOrderId(boning.getOrderId());
		if(delayDaysBoing != null){
			boning.setDelayDays(delayDaysBoing.getDelayDays());
		}
		List<BizProjectProgressBoning> nodeBoningList = dao.queryNodePlanByOrderId(boning.getOrderId());
		if (CollectionUtils.isNotEmpty(nodeBoningList)) {
			for (BizProjectProgressBoning nodeBoning : nodeBoningList) {
				if (nodeBoning.getNodeIndex() == 1) {
					boning.setNode1PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node1Boning = dao.queryNode1ByOrderId(boning.getOrderId());
					if (node1Boning != null) {
						boning.setNode1ActualDate(node1Boning.getNode1ActualDate());
						boning.setNode1SubmDate(node1Boning.getNode1SubmDate());
						boning.setNode1PlanSubDate(node1Boning.getNode1PlanSubDate());
						boning.setNode1ApplyEntryDate(node1Boning.getNode1ApplyEntryDate());
						boning.setNode1ActualEntryDate(node1Boning.getNode1ActualEntryDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node1Boning != null
							&& node1Boning.getNode1ActualDate() != null) {
						int node1DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node1Boning.getNode1ActualDate());
						boning.setNode1DiffDay(node1DiffDay);
					}
					if(node1Boning != null && node1Boning.getNode1PlanSubDate() != null && node1Boning.getNode1SubmDate() != null){
						int node1SubDiffDay = this.daysBetween(node1Boning.getNode1PlanSubDate(), node1Boning.getNode1SubmDate());
						boning.setNode1SubDiffDay(node1SubDiffDay);
					}
					
					if(node1Boning != null && node1Boning.getNode1ApplyEntryDate() != null && node1Boning.getNode1ActualEntryDate() != null){
						int node1EntryDiffDay = this.daysBetween(node1Boning.getNode1ApplyEntryDate(),node1Boning.getNode1ActualEntryDate());
						boning.setNode1EntryDiffDay(node1EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 2) {
					boning.setNode2PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node2Boning = dao.queryNode2ByOrderId(boning.getOrderId());
					if (node2Boning != null) {
						boning.setNode2ExpectDate(node2Boning.getNode2ExpectDate());
						boning.setNode2ActualDate(node2Boning.getNode2ActualDate());
						boning.setNode2SubmDate(node2Boning.getNode2SubmDate());
						boning.setNode2PlanSubDate(node2Boning.getNode2PlanSubDate());
						boning.setNode2ActualExpectDate(node2Boning.getNode2ActualExpectDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node2Boning != null
							&& node2Boning.getNode2ActualDate() != null) {
						int node2DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node2Boning.getNode2ActualDate());
						boning.setNode2DiffDay(node2DiffDay);
					}
					if(node2Boning != null && node2Boning.getNode2PlanSubDate() != null && node2Boning.getNode2SubmDate() != null){
						int node2SubDiffDay = this.daysBetween(node2Boning.getNode2PlanSubDate(), node2Boning.getNode2SubmDate());
						boning.setNode2SubDiffDay(node2SubDiffDay);
					}
					
					if(node2Boning != null && node2Boning.getNode2ExpectDate() != null && node2Boning.getNode2ActualExpectDate() != null){
						int node2EntryDiffDay = this.daysBetween(node2Boning.getNode2ExpectDate(),node2Boning.getNode2ActualExpectDate());
						boning.setNode2EntryDiffDay(node2EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 3) {
					boning.setNode3PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node3Boning = dao.queryNode3ByOrderId(boning.getOrderId());
					if (node3Boning != null) {
						boning.setNode3ExpectDate(node3Boning.getNode3ExpectDate());
						boning.setNode3ActualDate(node3Boning.getNode3ActualDate());
						boning.setNode3SubmDate(node3Boning.getNode3SubmDate());
						boning.setNode3PlanSubDate(node3Boning.getNode3PlanSubDate());
						boning.setNode3ActualExpectDate(node3Boning.getNode3ActualExpectDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node3Boning != null
							&& node3Boning.getNode3ActualDate() != null) {
						int node3DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node3Boning.getNode3ActualDate());
						boning.setNode3DiffDay(node3DiffDay);
					}
					if(node3Boning != null && node3Boning.getNode3PlanSubDate() != null && node3Boning.getNode3SubmDate() != null){
						int node3SubDiffDay = this.daysBetween(node3Boning.getNode3PlanSubDate(), node3Boning.getNode3SubmDate());
						boning.setNode3SubDiffDay(node3SubDiffDay);
					}
					
					if(node3Boning != null && node3Boning.getNode3ExpectDate() != null && node3Boning.getNode3ActualExpectDate() != null){
						int node3EntryDiffDay = this.daysBetween(node3Boning.getNode3ExpectDate(),node3Boning.getNode3ActualExpectDate());
						boning.setNode3EntryDiffDay(node3EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 4) {
					boning.setNode4PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node4Boning = dao.queryNode4ByOrderId(boning.getOrderId());
					if (node4Boning != null) {
						boning.setNode4ActualDate(node4Boning.getNode4ActualDate());
						boning.setNode4SubmDate(node4Boning.getNode4SubmDate());
						boning.setNode4ExpectDate(node4Boning.getNode4ExpectDate());
						boning.setNode4PlanSubDate(node4Boning.getNode4PlanSubDate());
						boning.setNode4ActualExpectDate(node4Boning.getNode4ActualExpectDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node4Boning != null
							&& node4Boning.getNode4ActualDate() != null) {
						int node4DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node4Boning.getNode4ActualDate());
						boning.setNode4DiffDay(node4DiffDay);
					}
					if(node4Boning != null && node4Boning.getNode4PlanSubDate() != null && node4Boning.getNode4SubmDate() != null){
						int node4SubDiffDay = this.daysBetween(node4Boning.getNode4PlanSubDate(), node4Boning.getNode4SubmDate());
						boning.setNode4SubDiffDay(node4SubDiffDay);
					}
					
					if(node4Boning != null && node4Boning.getNode4ExpectDate() != null && node4Boning.getNode4ActualExpectDate() != null){
						int node4EntryDiffDay = this.daysBetween(node4Boning.getNode4ExpectDate(),node4Boning.getNode4ActualExpectDate());
						boning.setNode4EntryDiffDay(node4EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 5) {
					boning.setNode5PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node5Boning = dao.queryNode5ByOrderId(boning.getOrderId());
					if (node5Boning != null) {
						boning.setNode5ActualDate(node5Boning.getNode5ActualDate());
						boning.setNode5SubmDate(node5Boning.getNode5SubmDate());
						boning.setNode5ExpectDate(node5Boning.getNode5ExpectDate());
						boning.setNode5PlanSubDate(node5Boning.getNode5PlanSubDate());
						boning.setNode5ActualExpectDate(node5Boning.getNode5ActualExpectDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node5Boning != null
							&& node5Boning.getNode5ActualDate() != null) {
						int node5DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node5Boning.getNode5ActualDate());
						boning.setNode5DiffDay(node5DiffDay);
					}
					if(node5Boning != null && node5Boning.getNode5PlanSubDate() != null && node5Boning.getNode5SubmDate() != null){
						int node5SubDiffDay = this.daysBetween(node5Boning.getNode5PlanSubDate(), node5Boning.getNode5SubmDate());
						boning.setNode5SubDiffDay(node5SubDiffDay);
					}
					
					if(node5Boning != null && node5Boning.getNode5ExpectDate() != null && node5Boning.getNode5ActualExpectDate() != null){
						int node5EntryDiffDay = this.daysBetween(node5Boning.getNode5ExpectDate(),node5Boning.getNode5ActualExpectDate());
						boning.setNode5EntryDiffDay(node5EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 6) {
					boning.setNode6PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node6Boning = dao.queryNode6ByOrderId(boning.getOrderId());
					if (node6Boning != null) {
						boning.setNode6ActualDate(node6Boning.getNode6ActualDate());
						boning.setNode6SubmDate(node6Boning.getNode6SubmDate());
						boning.setNode6PlanSubDate(node6Boning.getNode6PlanSubDate());
						boning.setNode6ExpectDate(node6Boning.getNode6ExpectDate());
						boning.setNode6ActualExpectDate(node6Boning.getNode6ActualExpectDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node6Boning != null
							&& node6Boning.getNode6ActualDate() != null) {
						int node6DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node6Boning.getNode6ActualDate());
						boning.setNode6DiffDay(node6DiffDay);
					}
					if(node6Boning != null && node6Boning.getNode6PlanSubDate() != null && node6Boning.getNode6SubmDate() != null){
						int node6SubDiffDay = this.daysBetween(node6Boning.getNode6PlanSubDate(), node6Boning.getNode6SubmDate());
						boning.setNode6SubDiffDay(node6SubDiffDay);
					}
					
					if(node6Boning != null && node6Boning.getNode6ExpectDate() != null && node6Boning.getNode6ActualExpectDate() != null){
						int node6EntryDiffDay = this.daysBetween(node6Boning.getNode6ExpectDate(),node6Boning.getNode6ActualExpectDate());
						boning.setNode6EntryDiffDay(node6EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 7) {
					boning.setNode7PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node7Boning = dao.queryNode7ByOrderId(boning.getOrderId());
					if (node7Boning != null) {
						boning.setNode7ActualDate(node7Boning.getNode7ActualDate());
						boning.setNode7SubmDate(node7Boning.getNode7SubmDate());
						boning.setNode7ExpectDate(node7Boning.getNode7ExpectDate());
						boning.setNode7PlanSubDate(node7Boning.getNode7PlanSubDate());
						boning.setNode7ActualExpectDate(node7Boning.getNode7ActualExpectDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node7Boning != null
							&& node7Boning.getNode7ActualDate() != null) {
						int node7DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node7Boning.getNode7ActualDate());
						boning.setNode7DiffDay(node7DiffDay);
					}
					
					if(node7Boning != null && node7Boning.getNode7PlanSubDate() != null && node7Boning.getNode7SubmDate() != null){
						int node7SubDiffDay = this.daysBetween(node7Boning.getNode7PlanSubDate(), node7Boning.getNode7SubmDate());
						boning.setNode7SubDiffDay(node7SubDiffDay);
					}
					
					if(node7Boning != null && node7Boning.getNode7ExpectDate() != null && node7Boning.getNode7ActualExpectDate() != null){
						int node7EntryDiffDay = this.daysBetween(node7Boning.getNode7ExpectDate(),node7Boning.getNode7ActualExpectDate());
						boning.setNode7EntryDiffDay(node7EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 8) {
					boning.setNode8PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node8Boning = dao.queryNode8ByOrderId(boning.getOrderId());
					if (node8Boning != null) {
						boning.setNode8AmountDate(node8Boning.getNode8AmountDate());
					}
					BizProjectProgressBoning node8Boing2 = dao.queryNode8ActualDateByOrderId(boning.getOrderId());
					if (node8Boing2 != null) {
						boning.setNode8ActualDate(node8Boing2.getNode8ActualDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node8Boing2 != null
							&& node8Boing2.getNode8ActualDate() != null) {
						int node8DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node8Boing2.getNode8ActualDate());
						boning.setNode8DiffDay(node8DiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 9) {
					boning.setNode9PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node9Boning = dao.queryNode9ByOrderId(boning.getOrderId());
					if (node9Boning != null) {
						boning.setNode9ActualDate(node9Boning.getNode9ActualDate());
						boning.setNode9SubmDate(node9Boning.getNode9SubmDate());
						boning.setNode9ExpectDate(node9Boning.getNode9ExpectDate());
						boning.setNode9PlanSubDate(node9Boning.getNode9PlanSubDate());
						boning.setNode9ActualExpectDate(node9Boning.getNode9ActualExpectDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node9Boning != null
							&& node9Boning.getNode9ActualDate() != null) {
						int node9DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node9Boning.getNode9ActualDate());
						boning.setNode9DiffDay(node9DiffDay);
					}
					
					if(node9Boning != null && node9Boning.getNode9PlanSubDate() != null && node9Boning.getNode9SubmDate() != null){
						int node9SubDiffDay = this.daysBetween(node9Boning.getNode9PlanSubDate(), node9Boning.getNode9SubmDate());
						boning.setNode9SubDiffDay(node9SubDiffDay);
					}
					
					if(node9Boning != null && node9Boning.getNode9ExpectDate() != null && node9Boning.getNode9ActualExpectDate() != null){
						int node9EntryDiffDay = this.daysBetween(node9Boning.getNode9ExpectDate(),node9Boning.getNode9ActualExpectDate());
						boning.setNode9EntryDiffDay(node9EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 10) {
					boning.setNode10PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node10Boning = dao.queryNode10ByOrderId(boning.getOrderId());
					if (node10Boning != null) {
						boning.setNode10ActualDate(node10Boning.getNode10ActualDate());
						boning.setNode10SubmDate(node10Boning.getNode10SubmDate());
						boning.setNode10ApplyEntryDate(node10Boning.getNode10ApplyEntryDate());
						boning.setNode10ActualEntryDate(node10Boning.getNode10ActualEntryDate());
						boning.setNode10ActualCheckDate(node10Boning.getNode10ActualCheckDate());
						boning.setNode10PlanSubDate(node10Boning.getNode10PlanSubDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node10Boning != null
							&& node10Boning.getNode10ActualDate() != null) {
						int node10DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node10Boning.getNode10ActualDate());
						boning.setNode10DiffDay(node10DiffDay);
					}
					if(node10Boning != null && node10Boning.getNode10PlanSubDate() != null && node10Boning.getNode10SubmDate() != null){
						int node10SubDiffDay = this.daysBetween(node10Boning.getNode10PlanSubDate(), node10Boning.getNode10SubmDate());
						boning.setNode10SubDiffDay(node10SubDiffDay);
					}
					
					if(node10Boning != null && node10Boning.getNode10ApplyEntryDate() != null && node10Boning.getNode10ActualEntryDate() != null){
						int node10EntryDiffDay = this.daysBetween(node10Boning.getNode10ApplyEntryDate(),node10Boning.getNode10ActualEntryDate());
						boning.setNode10EntryDiffDay(node10EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 11) {
					boning.setNode11PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node11Boning = dao.queryNode11ByOrderId(boning.getOrderId());
					if (node11Boning != null) {
						boning.setNode11InstallDate(node11Boning.getNode11InstallDate());
						boning.setNode11ActualDate(node11Boning.getNode11ActualDate());
						boning.setNode11SubmDate(node11Boning.getNode11SubmDate());
						boning.setNode11ApplyEntryDate(node11Boning.getNode11ApplyEntryDate());
						boning.setNode11ActualEntryDate(node11Boning.getNode11ActualEntryDate());
						boning.setNode11PlanSubDate(node11Boning.getNode11PlanSubDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node11Boning != null
							&& node11Boning.getNode11ActualDate() != null) {
						int node11DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node11Boning.getNode11ActualDate());
						boning.setNode11DiffDay(node11DiffDay);
					}
					
					if(node11Boning != null && node11Boning.getNode11PlanSubDate() != null && node11Boning.getNode11SubmDate() != null){
						int node11SubDiffDay = this.daysBetween(node11Boning.getNode11PlanSubDate(), node11Boning.getNode11SubmDate());
						boning.setNode11SubDiffDay(node11SubDiffDay);
					}
					
					if(node11Boning != null && node11Boning.getNode11ApplyEntryDate() != null && node11Boning.getNode11ActualEntryDate() != null){
						int node11EntryDiffDay = this.daysBetween(node11Boning.getNode11ApplyEntryDate(),node11Boning.getNode11ActualEntryDate());
						boning.setNode11EntryDiffDay(node11EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 12) {
					boning.setNode12PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node12Boning = dao.queryNode12ByOrderId(boning.getOrderId());
					if (node12Boning != null) {
						boning.setNode12InstallDate(node12Boning.getNode12InstallDate());
						boning.setNode12ActualDate(node12Boning.getNode12ActualDate());
						boning.setNode12SubmDate(node12Boning.getNode12SubmDate());
						boning.setNode12ApplyEntryDate(node12Boning.getNode12ApplyEntryDate());
						boning.setNode12ActualEntryDate(node12Boning.getNode12ActualEntryDate());
						boning.setNode12PlanSubDate(node12Boning.getNode12PlanSubDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node12Boning != null
							&& node12Boning.getNode12ActualDate() != null) {
						int node12DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node12Boning.getNode12ActualDate());
						boning.setNode12DiffDay(node12DiffDay);
					}
					if(node12Boning != null && node12Boning.getNode12PlanSubDate() != null && node12Boning.getNode12SubmDate() != null){
						int node12SubDiffDay = this.daysBetween(node12Boning.getNode12PlanSubDate(), node12Boning.getNode12SubmDate());
						boning.setNode12SubDiffDay(node12SubDiffDay);
					}
					
					if(node12Boning != null && node12Boning.getNode12ApplyEntryDate() != null && node12Boning.getNode12ActualEntryDate() != null){
						int node12EntryDiffDay = this.daysBetween(node12Boning.getNode12ApplyEntryDate(),node12Boning.getNode12ActualEntryDate());
						boning.setNode12EntryDiffDay(node12EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 13) {
					boning.setNode13PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node13Boning = dao.queryNode13ByOrderId(boning.getOrderId());
					if (node13Boning != null) {
						boning.setNode13InstallDate(node13Boning.getNode13InstallDate());
						boning.setNode13ActualDate(node13Boning.getNode13ActualDate());
						boning.setNode13SubmDate(node13Boning.getNode13SubmDate());
						boning.setNode13ApplyEntryDate(node13Boning.getNode13ApplyEntryDate());
						boning.setNode13ActualEntryDate(node13Boning.getNode13ActualEntryDate());
						boning.setNode13PlanSubDate(node13Boning.getNode13PlanSubDate());
						boning.setNode13RuleDate(node13Boning.getNode13RuleDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node13Boning != null
							&& node13Boning.getNode13ActualDate() != null) {
						int node13DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node13Boning.getNode13ActualDate());
						boning.setNode13DiffDay(node13DiffDay);
					}
					if(node13Boning != null && node13Boning.getNode13PlanSubDate() != null && node13Boning.getNode13SubmDate() != null){
						int node13SubDiffDay = this.daysBetween(node13Boning.getNode13PlanSubDate(), node13Boning.getNode13SubmDate());
						boning.setNode13SubDiffDay(node13SubDiffDay);
					}
					
					if(node13Boning != null && node13Boning.getNode13ApplyEntryDate() != null && node13Boning.getNode13ActualEntryDate() != null){
						int node13EntryDiffDay = this.daysBetween(node13Boning.getNode13ApplyEntryDate(),node13Boning.getNode13ActualEntryDate());
						boning.setNode13EntryDiffDay(node13EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 14) {
					boning.setNode14PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node14Boning = dao.queryNode14ByOrderId(boning.getOrderId());
					if (node14Boning != null) {
						boning.setNode14InstallDate(node14Boning.getNode14InstallDate());
						boning.setNode14ActualDate(node14Boning.getNode14ActualDate());
						boning.setNode14SubmDate(node14Boning.getNode14SubmDate());
						boning.setNode14ApplyEntryDate(node14Boning.getNode14ApplyEntryDate());
						boning.setNode14ActualEntryDate(node14Boning.getNode14ActualEntryDate());
						boning.setNode14PlanSubDate(node14Boning.getNode14PlanSubDate());
						boning.setNode14RuleDate(node14Boning.getNode14RuleDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node14Boning != null
							&& node14Boning.getNode14ActualDate() != null) {
						int node14DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node14Boning.getNode14ActualDate());
						boning.setNode14DiffDay(node14DiffDay);
					}
					if(node14Boning != null && node14Boning.getNode14PlanSubDate() != null && node14Boning.getNode14SubmDate() != null){
						int node14SubDiffDay = this.daysBetween(node14Boning.getNode14PlanSubDate(), node14Boning.getNode14SubmDate());
						boning.setNode14SubDiffDay(node14SubDiffDay);
					}
					
					if(node14Boning != null && node14Boning.getNode14ApplyEntryDate() != null && node14Boning.getNode14ActualEntryDate() != null){
						int node14EntryDiffDay = this.daysBetween(node14Boning.getNode14ApplyEntryDate(),node14Boning.getNode14ActualEntryDate());
						boning.setNode14EntryDiffDay(node14EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 15) {
					boning.setNode15PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node15Boning = dao.queryNode15ByOrderId(boning.getOrderId());
					if (node15Boning != null) {
						boning.setNode15InstallDate(node15Boning.getNode15InstallDate());
						boning.setNode15ActualDate(node15Boning.getNode15ActualDate());
						boning.setNode15SubmDate(node15Boning.getNode15SubmDate());
						boning.setNode15ApplyEntryDate(node15Boning.getNode15ApplyEntryDate());
						boning.setNode15ActualEntryDate(node15Boning.getNode15ActualEntryDate());
						boning.setNode15PlanSubDate(node15Boning.getNode15PlanSubDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node15Boning != null
							&& node15Boning.getNode15ActualDate() != null) {
						int node15DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node15Boning.getNode15ActualDate());
						boning.setNode15DiffDay(node15DiffDay);
					}
					if(node15Boning != null && node15Boning.getNode15PlanSubDate() != null && node15Boning.getNode15SubmDate() != null){
						int node15SubDiffDay = this.daysBetween(node15Boning.getNode15PlanSubDate(), node15Boning.getNode15SubmDate());
						boning.setNode15SubDiffDay(node15SubDiffDay);
					}
					
					if(node15Boning != null && node15Boning.getNode15ApplyEntryDate() != null && node15Boning.getNode15ActualEntryDate() != null){
						int node15EntryDiffDay = this.daysBetween(node15Boning.getNode15ApplyEntryDate(),node15Boning.getNode15ActualEntryDate());
						boning.setNode15EntryDiffDay(node15EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 16) {
					boning.setNode16PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node16Boning = dao.queryNode16ByOrderId(boning.getOrderId());
					if (node16Boning != null) {
						boning.setNode16InstallDate(node16Boning.getNode16InstallDate());
						boning.setNode16ActualDate(node16Boning.getNode16ActualDate());
						boning.setNode16SubmDate(node16Boning.getNode16SubmDate());
						boning.setNode16ApplyEntryDate(node16Boning.getNode16ApplyEntryDate());
						boning.setNode16ActualEntryDate(node16Boning.getNode16ActualEntryDate());
						boning.setNode16PlanSubDate(node16Boning.getNode16PlanSubDate());
						boning.setNode16RuleDate(node16Boning.getNode16RuleDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node16Boning != null
							&& node16Boning.getNode16ActualDate() != null) {
						int node16DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node16Boning.getNode16ActualDate());
						boning.setNode16DiffDay(node16DiffDay);
					}
					
					if(node16Boning != null && node16Boning.getNode16PlanSubDate() != null && node16Boning.getNode16SubmDate() != null){
						int node16SubDiffDay = this.daysBetween(node16Boning.getNode16PlanSubDate(), node16Boning.getNode16SubmDate());
						boning.setNode16SubDiffDay(node16SubDiffDay);
					}
					
					if(node16Boning != null && node16Boning.getNode16ApplyEntryDate() != null && node16Boning.getNode16ActualEntryDate() != null){
						int node16EntryDiffDay = this.daysBetween(node16Boning.getNode16ApplyEntryDate(),node16Boning.getNode16ActualEntryDate());
						boning.setNode16EntryDiffDay(node16EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 17) {
					boning.setNode17PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node17Boning = dao.queryNode17ByOrderId(boning.getOrderId());
					if (node17Boning != null) {
						boning.setNode17InstallDate(node17Boning.getNode17InstallDate());
						boning.setNode17ActualDate(node17Boning.getNode17ActualDate());
						boning.setNode17SubmDate(node17Boning.getNode17SubmDate());
						boning.setNode17ApplyEntryDate(node17Boning.getNode17ApplyEntryDate());
						boning.setNode17ActualEntryDate(node17Boning.getNode17ActualEntryDate());
						boning.setNode17PlanSubDate(node17Boning.getNode17PlanSubDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node17Boning != null
							&& node17Boning.getNode17ActualDate() != null) {
						int node17DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node17Boning.getNode17ActualDate());
						boning.setNode17DiffDay(node17DiffDay);
					}
					
					if(node17Boning != null && node17Boning.getNode17PlanSubDate() != null && node17Boning.getNode17SubmDate() != null){
						int node17SubDiffDay = this.daysBetween(node17Boning.getNode17PlanSubDate(), node17Boning.getNode17SubmDate());
						boning.setNode17SubDiffDay(node17SubDiffDay);
					}
					
					if(node17Boning != null && node17Boning.getNode17ApplyEntryDate() != null && node17Boning.getNode17ActualEntryDate() != null){
						int node17EntryDiffDay = this.daysBetween(node17Boning.getNode17ApplyEntryDate(),node17Boning.getNode17ActualEntryDate());
						boning.setNode17EntryDiffDay(node17EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 18) {
					boning.setNode18PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node18Boning = dao.queryNode18ByOrderId(boning.getOrderId());
					if (node18Boning != null) {
						boning.setNode18InstallDate(node18Boning.getNode18InstallDate());
						boning.setNode18ActualDate(node18Boning.getNode18ActualDate());
						boning.setNode18SubmDate(node18Boning.getNode18SubmDate());
						boning.setNode18ApplyEntryDate(node18Boning.getNode18ApplyEntryDate());
						boning.setNode18ActualEntryDate(node18Boning.getNode18ActualEntryDate());
						boning.setNode18PlanSubDate(node18Boning.getNode18PlanSubDate());
						boning.setNode18RuleDate(node18Boning.getNode18RuleDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node18Boning != null
							&& node18Boning.getNode18ActualDate() != null) {
						int node18DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node18Boning.getNode18ActualDate());
						boning.setNode18DiffDay(node18DiffDay);
					}
					
					if(node18Boning != null && node18Boning.getNode18PlanSubDate() != null && node18Boning.getNode18SubmDate() != null){
						int node18SubDiffDay = this.daysBetween(node18Boning.getNode18PlanSubDate(), node18Boning.getNode18SubmDate());
						boning.setNode18SubDiffDay(node18SubDiffDay);
					}
					
					if(node18Boning != null && node18Boning.getNode18ApplyEntryDate() != null && node18Boning.getNode18ActualEntryDate() != null){
						int node18EntryDiffDay = this.daysBetween(node18Boning.getNode18ApplyEntryDate(),node18Boning.getNode18ActualEntryDate());
						boning.setNode18EntryDiffDay(node18EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 19) {
					boning.setNode19PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node19Boning = dao.queryNode19ByOrderId(boning.getOrderId());
					if (node19Boning != null) {
						boning.setNode19ActualDate(node19Boning.getNode19ActualDate());
						boning.setNode19SubmDate(node19Boning.getNode19SubmDate());
						boning.setNode19ExpectDate(node19Boning.getNode19ExpectDate());
						boning.setNode19PlanSubDate(node19Boning.getNode19PlanSubDate());
						boning.setNode19ActualEntryDate(node19Boning.getNode19ActualEntryDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node19Boning != null
							&& node19Boning.getNode19ActualDate() != null) {
						int node19DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node19Boning.getNode19ActualDate());
						boning.setNode19DiffDay(node19DiffDay);
					}
					if(node19Boning != null && node19Boning.getNode19PlanSubDate() != null && node19Boning.getNode19SubmDate() != null){
						int node19SubDiffDay = this.daysBetween(node19Boning.getNode19PlanSubDate(), node19Boning.getNode19SubmDate());
						boning.setNode19SubDiffDay(node19SubDiffDay);
					}
					
					if(node19Boning != null && node19Boning.getNode19ExpectDate() != null && node19Boning.getNode19ActualEntryDate() != null){
						int node19EntryDiffDay = this.daysBetween(node19Boning.getNode19ExpectDate(),node19Boning.getNode19ActualEntryDate());
						boning.setNode19EntryDiffDay(node19EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 20) {
					boning.setNode20PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node20Boning = dao.queryNode20ActualDateByOrderId(boning.getOrderId());
					if (node20Boning != null) {
						boning.setNode20ActualDate(node20Boning.getNode20ActualDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node20Boning != null
							&& node20Boning.getNode20ActualDate() != null) {
						int node20DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node20Boning.getNode20ActualDate());
						boning.setNode20DiffDay(node20DiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 21) {
					boning.setNode21PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node21Boning = dao.queryNode21ByOrderId(boning.getOrderId());
					if (node21Boning != null) {
						boning.setNode21InstallDate(node21Boning.getNode21InstallDate());
						boning.setNode21ActualDate(node21Boning.getNode21ActualDate());
						boning.setNode21SubmDate(node21Boning.getNode21SubmDate());
						boning.setNode21ApplyEntryDate(node21Boning.getNode21ApplyEntryDate());
						boning.setNode21ActualEntryDate(node21Boning.getNode21ActualEntryDate());
						boning.setNode21PlanSubDate(node21Boning.getNode21PlanSubDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node21Boning != null
							&& node21Boning.getNode21ActualDate() != null) {
						int node21DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node21Boning.getNode21ActualDate());
						boning.setNode21DiffDay(node21DiffDay);
					}
					
					if(node21Boning != null && node21Boning.getNode21PlanSubDate() != null && node21Boning.getNode21SubmDate() != null){
						int node21SubDiffDay = this.daysBetween(node21Boning.getNode21PlanSubDate(), node21Boning.getNode21SubmDate());
						boning.setNode21SubDiffDay(node21SubDiffDay);
					}
					
					if(node21Boning != null && node21Boning.getNode21ApplyEntryDate() != null && node21Boning.getNode21ActualEntryDate() != null){
						int node21EntryDiffDay = this.daysBetween(node21Boning.getNode21ApplyEntryDate(),node21Boning.getNode21ActualEntryDate());
						boning.setNode21EntryDiffDay(node21EntryDiffDay);
					}
				} else if (nodeBoning.getNodeIndex() == 22) {
					boning.setNode22PlanDate(nodeBoning.getPlanDoneDate());
					BizProjectProgressBoning node22Boning = dao.queryNode22ByOrderId(boning.getOrderId());
					if (node22Boning != null) {
						boning.setNode22InstallDate(node22Boning.getNode22InstallDate());
						boning.setNode22ActualDate(node22Boning.getNode22ActualDate());
						boning.setNode22SubmDate(node22Boning.getNode22SubmDate());
						boning.setNode22ApplyEntryDate(node22Boning.getNode22ApplyEntryDate());
						boning.setNode22ActualEntryDate(node22Boning.getNode22ActualEntryDate());
						boning.setNode22PlanSubDate(node22Boning.getNode22PlanSubDate());
					}
					if (nodeBoning.getPlanDoneDate() != null && node22Boning != null
							&& node22Boning.getNode22ActualDate() != null) {
						int node22DiffDay = this.daysBetween(nodeBoning.getPlanDoneDate(),
								node22Boning.getNode22ActualDate());
						boning.setNode22DiffDay(node22DiffDay);
					}
					
					if(node22Boning != null && node22Boning.getNode22PlanSubDate() != null && node22Boning.getNode22SubmDate() != null){
						int node22SubDiffDay = this.daysBetween(node22Boning.getNode22PlanSubDate(), node22Boning.getNode22SubmDate());
						boning.setNode22SubDiffDay(node22SubDiffDay);
					}
					
					if(node22Boning != null && node22Boning.getNode22ApplyEntryDate() != null && node22Boning.getNode22ActualEntryDate() != null){
						int node22EntryDiffDay = this.daysBetween(node22Boning.getNode22ApplyEntryDate(),node22Boning.getNode22ActualEntryDate());
						boning.setNode22EntryDiffDay(node22EntryDiffDay);
					}
				}
			}
		}
	}

	private int daysBetween(Date smdate, Date bdate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 中国银行导出数据
	 * 
	 * @param bizProjectProgressBoning
	 * @return
	 */
	public HSSFWorkbook exportExcel(BizProjectProgressBoning bizProjectProgressBoning) {
		HSSFWorkbook wb = new HSSFWorkbook();// 创建一个Excel文件
		HSSFSheet sheet = wb.createSheet("工程进度看板");// 创建一个Excel的Sheet

		// 单元格宽度
		/*
		 * sheet.setColumnWidth(0, 2000); sheet.setColumnWidth(1, 2000);
		 * sheet.setColumnWidth(2, 2000); sheet.setColumnWidth(3, 3000);
		 * sheet.setColumnWidth(4, 5000); sheet.setColumnWidth(5, 3000);
		 * sheet.setColumnWidth(6, 3000); sheet.setColumnWidth(7, 3000);
		 * sheet.setColumnWidth(8, 3000); sheet.setColumnWidth(9, 3000);
		 * sheet.setColumnWidth(10, 3000); sheet.setColumnWidth(11, 3000);
		 * sheet.setColumnWidth(12, 3000); sheet.setColumnWidth(13, 3000);
		 */

		HSSFCellStyle columnStyle = wb.createCellStyle();
		columnStyle.setLeftBorderColor(HSSFColor.BLACK.index); // 左边框线的颜色
		columnStyle.setBorderLeft((short) 1);// 左边框线的大小
		columnStyle.setRightBorderColor(HSSFColor.BLACK.index); // 右边框线的颜色
		columnStyle.setBorderRight((short) 1);// 右边框线的大小
		columnStyle.setTopBorderColor(HSSFColor.BLACK.index); // 上边框线的颜色
		columnStyle.setBorderTop((short) 1);// 上边框线的大小
		columnStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 下边框线的颜色
		columnStyle.setBorderBottom((short) 1);// 下边框线的大小
		columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle headStyle = wb.createCellStyle();
		headStyle.setLeftBorderColor(HSSFColor.BLACK.index); // 左边框线的颜色
		headStyle.setBorderLeft((short) 1);// 左边框线的大小
		headStyle.setRightBorderColor(HSSFColor.BLACK.index); // 右边框线的颜色
		headStyle.setBorderRight((short) 1);// 右边框线的大小
		headStyle.setTopBorderColor(HSSFColor.BLACK.index); // 上边框线的颜色
		headStyle.setBorderTop((short) 1);// 上边框线的大小
		headStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 下边框线的颜色
		headStyle.setBorderBottom((short) 1);// 下边框线的大小
		headStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置背景色
		headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 标题
		HSSFRow rowTitle1 = sheet.createRow(0);
		HSSFCell headCell10 = rowTitle1.createCell(0);
		headCell10.setCellStyle(headStyle);
		headCell10.setCellValue("门店");

		HSSFCell headCell11 = rowTitle1.createCell(1);
		headCell11.setCellStyle(headStyle);
		headCell11.setCellValue("区域");
		
		HSSFCell headCell1111 = rowTitle1.createCell(2);
		headCell1111.setCellStyle(headStyle);
		headCell1111.setCellValue("订单编号");
		
		HSSFCell headCell1112 = rowTitle1.createCell(3);
		headCell1112.setCellStyle(headStyle);
		headCell1112.setCellValue("片区");

		HSSFCell headCell12 = rowTitle1.createCell(4);
		headCell12.setCellStyle(headStyle);
		headCell12.setCellValue("接单日期");

		HSSFCell headCell13 = rowTitle1.createCell(5);
		headCell13.setCellStyle(headStyle);
		headCell13.setCellValue("客户姓名");

		HSSFCell headCell14 = rowTitle1.createCell(6);
		headCell14.setCellStyle(headStyle);
		headCell14.setCellValue("客户电话");

		HSSFCell headCell15 = rowTitle1.createCell(7);
		headCell15.setCellStyle(headStyle);
		headCell15.setCellValue("工程地址");
		
		HSSFCell headCell16 = rowTitle1.createCell(8);
		headCell16.setCellStyle(headStyle);
		headCell16.setCellValue("合同面积");
		
		HSSFCell headCell17 = rowTitle1.createCell(9);
		headCell17.setCellStyle(headStyle);
		headCell17.setCellValue("房屋类型");
		

		HSSFCell headCell18 = rowTitle1.createCell(10);
		headCell18.setCellStyle(headStyle);
		headCell18.setCellValue("项目经理");

		HSSFCell headCell19 = rowTitle1.createCell(11);
		headCell19.setCellStyle(headStyle);

		HSSFCell headCell16888 = rowTitle1.createCell(12);
		headCell16888.setCellStyle(headStyle);
		headCell16888.setCellValue("设计师");

		HSSFCell headCell17999 = rowTitle1.createCell(13);
		headCell17999.setCellStyle(headStyle);

		HSSFCell headCell161010 = rowTitle1.createCell(14);
		headCell161010.setCellStyle(headStyle);
		headCell161010.setCellValue("质检员");

		HSSFCell headCell17111 = rowTitle1.createCell(15);
		headCell17111.setCellStyle(headStyle);

		HSSFCell headCell18g1 = rowTitle1.createCell(16);
		headCell18g1.setCellStyle(headStyle);
		headCell18g1.setCellValue("合同工期时间");

		HSSFCell headCell19g1 = rowTitle1.createCell(17);
		headCell19g1.setCellStyle(headStyle);

		HSSFCell headCell110 = rowTitle1.createCell(18);
		headCell110.setCellStyle(headStyle);
		headCell110.setCellValue("实际工期时间");

		HSSFCell headCell111 = rowTitle1.createCell(19);
		headCell111.setCellStyle(headStyle);

		HSSFCell headCell112 = rowTitle1.createCell(20);
		headCell112.setCellStyle(headStyle);
		headCell112.setCellValue("开工延期天数");

		HSSFCell headCell113 = rowTitle1.createCell(21);
		headCell113.setCellStyle(headStyle);
		headCell113.setCellValue("实际开工客\n" + "户是否签字");

		HSSFCell headCell114 = rowTitle1.createCell(22);
		headCell114.setCellStyle(headStyle);
		headCell114.setCellValue("变更/停电/停\n" + "水/客户自装项\n" + "目延期天数");

		HSSFCell headCell115 = rowTitle1.createCell(23);
		headCell115.setCellStyle(headStyle);
		headCell115.setCellValue("客户是\n否签字");
		
//		客户认可延期天数
		HSSFCell headCell1151 = rowTitle1.createCell(24);
		headCell1151.setCellStyle(headStyle);
		headCell1151.setCellValue("客户认可延期天数");

		HSSFCell headCell116 = rowTitle1.createCell(25);
		headCell116.setCellStyle(headStyle);
		headCell116.setCellValue("防盗门核尺");

		HSSFCell headCell117 = rowTitle1.createCell(27);
		headCell117.setCellStyle(headStyle);
		
		/*HSSFCell headCell118 = rowTitle1.createCell(24);
		headCell118.setCellStyle(headStyle);

		HSSFCell headCell119 = rowTitle1.createCell(25);
		headCell119.setCellStyle(headStyle);*/

		HSSFCell headCell120 = rowTitle1.createCell(34);
		headCell120.setCellStyle(headStyle);
		headCell120.setCellValue("辅材进场");

//		44, 53));//瓷砖   

		HSSFCell headCell130 = rowTitle1.createCell(43);
		headCell130.setCellStyle(headStyle);
		headCell130.setCellValue("瓷砖");

		
//		54, 63));//水电隐蔽验收            
		HSSFCell headCell135 = rowTitle1.createCell(52);
		headCell135.setCellStyle(headStyle);
		headCell135.setCellValue("水电隐蔽验收");

		
//		64, 73));//防水验收  
		HSSFCell headCell140 = rowTitle1.createCell(61);
		headCell140.setCellStyle(headStyle);
		headCell140.setCellValue("防水验收 ");

//		74, 83));//橱柜核尺 

		HSSFCell headCell144 = rowTitle1.createCell(70);
		headCell144.setCellStyle(headStyle);
		headCell144.setCellValue("橱柜核尺");

//		84, 93));//瓦工验收

		HSSFCell headCell149 = rowTitle1.createCell(79);
		headCell149.setCellStyle(headStyle);
		headCell149.setCellValue("瓦工验收");

//		94, 97));//二期款  

		HSSFCell headCell153 = rowTitle1.createCell(88);
		headCell153.setCellStyle(headStyle);
		headCell153.setCellValue("二期款");
		
//		98, 107));//基础施工验收     
		HSSFCell headCell153c = rowTitle1.createCell(92);
		headCell153c.setCellStyle(headStyle);
		headCell153c.setCellValue("基础施工验收");
//		108, 117));//厨卫吊顶    
		HSSFCell headCell158 = rowTitle1.createCell(101);
		headCell158.setCellStyle(headStyle);
		headCell158.setCellValue("厨卫吊顶");

//		118, 127));//洁具  
		HSSFCell headCell165 = rowTitle1.createCell(111);
		headCell165.setCellStyle(headStyle);
		headCell165.setCellValue("洁具");

//		128, 137));//五金，灯具，开关面板   

		HSSFCell headCell173 = rowTitle1.createCell(121);
		headCell173.setCellStyle(headStyle);
		headCell173.setCellValue("五金，灯具，开关面板");

//		138, 147));//橱柜  
		HSSFCell headCell180 = rowTitle1.createCell(131);
		headCell180.setCellStyle(headStyle);
		headCell180.setCellValue("橱柜");

//		148, 157));//定制衣柜  

		HSSFCell headCell187 = rowTitle1.createCell(142);
		headCell187.setCellStyle(headStyle);
		headCell187.setCellValue("定制衣柜");

//		158, 167));//壁纸  

		HSSFCell headCell194 = rowTitle1.createCell(153);
		headCell194.setCellStyle(headStyle);
		headCell194.setCellValue("壁纸");

//		168, 177));//木门，铝镁门，门窗套      

		HSSFCell headCell201 = rowTitle1.createCell(155+8);
		headCell201.setCellStyle(headStyle);
		headCell201.setCellValue("木门，铝镁门，门窗套");

//		178, 187));//木地板   

		HSSFCell headCell208 = rowTitle1.createCell(164+10);
		headCell208.setCellStyle(headStyle);
		headCell208.setCellValue("木地板");

//		188, 197));//窗帘 

		HSSFCell headCell215 = rowTitle1.createCell(173+11);
		headCell215.setCellStyle(headStyle);
		headCell215.setCellValue("窗帘");

//		198, 207));//竣工验收   


		HSSFCell headCell222 = rowTitle1.createCell(183+12);
		headCell222.setCellStyle(headStyle);
		headCell222.setCellValue("竣工验收");

//		208, 210));//尾款 

		HSSFCell headCell230 = rowTitle1.createCell(191+13);
		headCell230.setCellStyle(headStyle);
		headCell230.setCellValue("尾款");

//		211, 221));//家电  

		HSSFCell headCell237 = rowTitle1.createCell(193+14);
		headCell237.setCellStyle(headStyle);
		headCell237.setCellValue("家电");

//		222, 232));//家具  
		
		HSSFCell headCell244 = rowTitle1.createCell(202+15);
		headCell244.setCellStyle(headStyle);
		headCell244.setCellValue("家具");
		
		HSSFRow rowTitle2 = sheet.createRow(1);
		HSSFCell headCell20 = rowTitle2.createCell(0);
		headCell20.setCellStyle(headStyle);
		
		HSSFCell headCell2220 = rowTitle2.createCell(1);
		headCell2220.setCellStyle(headStyle);
		
		HSSFCell headCell2221 = rowTitle2.createCell(2);
		headCell2221.setCellStyle(headStyle);
		

		HSSFCell headCell21 = rowTitle2.createCell(3);
		headCell21.setCellStyle(headStyle);

		HSSFCell headCell22 = rowTitle2.createCell(4);
		headCell22.setCellStyle(headStyle);

		HSSFCell headCell23 = rowTitle2.createCell(5);
		headCell23.setCellStyle(headStyle);

		HSSFCell headCell24 = rowTitle2.createCell(6);
		headCell24.setCellStyle(headStyle);

		HSSFCell headCell25 = rowTitle2.createCell(7);
		headCell25.setCellStyle(headStyle);
		
		HSSFCell headCell25g1 = rowTitle2.createCell(8);
		headCell25g1.setCellStyle(headStyle);
		
		HSSFCell headCell25g12 = rowTitle2.createCell(9);
		headCell25g12.setCellStyle(headStyle);

		HSSFCell headCell26 = rowTitle2.createCell(10);
		headCell26.setCellStyle(headStyle);
		headCell26.setCellValue("姓名");

		HSSFCell headCell27 = rowTitle2.createCell(11);
		headCell27.setCellStyle(headStyle);
		headCell27.setCellValue("电话");

		HSSFCell headCell268888 = rowTitle2.createCell(12);
		headCell268888.setCellStyle(headStyle);
		headCell268888.setCellValue("姓名");

		HSSFCell headCell279999 = rowTitle2.createCell(13);
		headCell279999.setCellStyle(headStyle);
		headCell279999.setCellValue("电话");

		HSSFCell headCell261010 = rowTitle2.createCell(14);
		headCell261010.setCellStyle(headStyle);
		headCell261010.setCellValue("姓名");

		HSSFCell headCell271111 = rowTitle2.createCell(15);
		headCell271111.setCellStyle(headStyle);
		headCell271111.setCellValue("电话");

		HSSFCell headCell28 = rowTitle2.createCell(16);
		headCell28.setCellStyle(headStyle);
		headCell28.setCellValue("合同签订开工日期");

		HSSFCell headCell29 = rowTitle2.createCell(17);
		headCell29.setCellStyle(headStyle);
		headCell29.setCellValue("合同签订竣工日期");

		HSSFCell headCell2210 = rowTitle2.createCell(18);
		headCell2210.setCellStyle(headStyle);
		headCell2210.setCellValue("实际开工日期");

		HSSFCell headCell2211 = rowTitle2.createCell(19);
		headCell2211.setCellStyle(headStyle);
		headCell2211.setCellValue("实际竣工日期");

		HSSFCell headCell2212 = rowTitle2.createCell(20);
		headCell2212.setCellStyle(headStyle);

		HSSFCell headCell2213 = rowTitle2.createCell(21);
		headCell2213.setCellStyle(headStyle);

		HSSFCell headCell2214 = rowTitle2.createCell(22);
		headCell2214.setCellStyle(headStyle);

		HSSFCell headCell2215 = rowTitle2.createCell(23);
		headCell2215.setCellStyle(headStyle);

		
//		25, 33));//防盗门复尺     
		HSSFCell headCell22161 = rowTitle2.createCell(25);
		headCell22161.setCellStyle(headStyle);
		headCell22161.setCellValue("计划提报日期");

		HSSFCell headCell2217 = rowTitle2.createCell(26);
		headCell2217.setCellStyle(headStyle);
		headCell2217.setCellValue("实际提报时间");

		HSSFCell headCell2218 = rowTitle2.createCell(27);
		headCell2218.setCellStyle(headStyle);
		headCell2218.setCellValue("提报延期天数");

		HSSFCell headCell2219 = rowTitle2.createCell(28);
		headCell2219.setCellStyle(headStyle);
		headCell2219.setCellValue("期望进场日期");
		
		HSSFCell headCell22162 = rowTitle2.createCell(29);
		headCell22162.setCellStyle(headStyle);
		headCell22162.setCellValue("实际进场时间");

		HSSFCell headCell2222 = rowTitle2.createCell(30);
		headCell2222.setCellStyle(headStyle);
		headCell2222.setCellValue("进场延期天数");

		HSSFCell headCell2223 = rowTitle2.createCell(31);
		headCell2223.setCellStyle(headStyle);
		headCell2223.setCellValue("计划完成日期");

		HSSFCell headCell2224 = rowTitle2.createCell(32);
		headCell2224.setCellStyle(headStyle);
		headCell2224.setCellValue("实际完成时间");

		HSSFCell headCell2225 = rowTitle2.createCell(33);
		headCell2225.setCellStyle(headStyle);
		headCell2225.setCellValue("正常/延期/提前天数");
		
//		34, 43));//辅材进场(3天内)    
		
		HSSFCell headCellfc161 = rowTitle2.createCell(34);
		headCellfc161.setCellStyle(headStyle);
		headCellfc161.setCellValue("计划提报日期");

		HSSFCell headCellfc17 = rowTitle2.createCell(35);
		headCellfc17.setCellStyle(headStyle);
		headCellfc17.setCellValue("实际提报时间");

		HSSFCell headCellfc18 = rowTitle2.createCell(36);
		headCellfc18.setCellStyle(headStyle);
		headCellfc18.setCellValue("提报延期天数");

		HSSFCell headCellfc19 = rowTitle2.createCell(37);
		headCellfc19.setCellStyle(headStyle);
		headCellfc19.setCellValue("期望进场日期");
		
		HSSFCell headCellfc162 = rowTitle2.createCell(38);
		headCellfc162.setCellStyle(headStyle);
		headCellfc162.setCellValue("实际进场时间");

		HSSFCell headCellfcfc = rowTitle2.createCell(39);
		headCellfcfc.setCellStyle(headStyle);
		headCellfcfc.setCellValue("进场延期天数");

		HSSFCell headCellfc23 = rowTitle2.createCell(40);
		headCellfc23.setCellStyle(headStyle);
		headCellfc23.setCellValue("计划完成日期");

		HSSFCell headCellfc24 = rowTitle2.createCell(41);
		headCellfc24.setCellStyle(headStyle);
		headCellfc24.setCellValue("实际完成时间");

		HSSFCell headCellfc25 = rowTitle2.createCell(42);
		headCellfc25.setCellStyle(headStyle);
		headCellfc25.setCellValue("正常/延期/提前天数");
		
//		44-1, 53-1-1));//瓷砖
		
		HSSFCell headCellcz161 = rowTitle2.createCell(43);
		headCellcz161.setCellStyle(headStyle);
		headCellcz161.setCellValue("计划提报日期");

		HSSFCell headCellcz17 = rowTitle2.createCell(44);
		headCellcz17.setCellStyle(headStyle);
		headCellcz17.setCellValue("实际提报时间");

		HSSFCell headCellcz18 = rowTitle2.createCell(45);
		headCellcz18.setCellStyle(headStyle);
		headCellcz18.setCellValue("提报延期天数");

		HSSFCell headCellcz19 = rowTitle2.createCell(46);
		headCellcz19.setCellStyle(headStyle);
		headCellcz19.setCellValue("期望进场日期");
		
		HSSFCell headCellcz162 = rowTitle2.createCell(47);
		headCellcz162.setCellStyle(headStyle);
		headCellcz162.setCellValue("实际进场时间");

		HSSFCell headCellczcz = rowTitle2.createCell(48);
		headCellczcz.setCellStyle(headStyle);
		headCellczcz.setCellValue("进场延期天数");

		HSSFCell headCellcz23 = rowTitle2.createCell(49);
		headCellcz23.setCellStyle(headStyle);
		headCellcz23.setCellValue("计划完成日期");

		HSSFCell headCellcz24 = rowTitle2.createCell(50);
		headCellcz24.setCellStyle(headStyle);
		headCellcz24.setCellValue("实际完成时间");

		HSSFCell headCellcz25 = rowTitle2.createCell(51);
		headCellcz25.setCellStyle(headStyle);
		headCellcz25.setCellValue("正常/延期/提前天数");
//		54-1-1, 63-3));//水电隐蔽验收 
		
		HSSFCell headCellsd161 = rowTitle2.createCell(52);
		headCellsd161.setCellStyle(headStyle);
		headCellsd161.setCellValue("计划提报日期");

		HSSFCell headCellsd17 = rowTitle2.createCell(53);
		headCellsd17.setCellStyle(headStyle);
		headCellsd17.setCellValue("实际提报时间");

		HSSFCell headCellsd18 = rowTitle2.createCell(54);
		headCellsd18.setCellStyle(headStyle);
		headCellsd18.setCellValue("提报延期天数");

		HSSFCell headCellsd19 = rowTitle2.createCell(55);
		headCellsd19.setCellStyle(headStyle);
		headCellsd19.setCellValue("期望进场日期");
		
		HSSFCell headCellsd162 = rowTitle2.createCell(56);
		headCellsd162.setCellStyle(headStyle);
		headCellsd162.setCellValue("实际进场时间");

		HSSFCell headCellsdsd = rowTitle2.createCell(57);
		headCellsdsd.setCellStyle(headStyle);
		headCellsdsd.setCellValue("进场延期天数");

		HSSFCell headCellsd23 = rowTitle2.createCell(58);
		headCellsd23.setCellStyle(headStyle);
		headCellsd23.setCellValue("计划完成日期");

		HSSFCell headCellsd24 = rowTitle2.createCell(59);
		headCellsd24.setCellStyle(headStyle);
		headCellsd24.setCellValue("实际完成时间");

		HSSFCell headCellsd25 = rowTitle2.createCell(60);
		headCellsd25.setCellStyle(headStyle);
		headCellsd25.setCellValue("正常/延期/提前天数");
//		 0, 64-3, 73-4));//防水验收  
		HSSFCell headCellfs161 = rowTitle2.createCell(61);
		headCellfs161.setCellStyle(headStyle);
		headCellfs161.setCellValue("计划提报日期");

		HSSFCell headCellfs17 = rowTitle2.createCell(62);
		headCellfs17.setCellStyle(headStyle);
		headCellfs17.setCellValue("实际提报时间");

		HSSFCell headCellfs18 = rowTitle2.createCell(63);
		headCellfs18.setCellStyle(headStyle);
		headCellfs18.setCellValue("提报延期天数");

		HSSFCell headCellfs19 = rowTitle2.createCell(64);
		headCellfs19.setCellStyle(headStyle);
		headCellfs19.setCellValue("期望进场日期");
		
		HSSFCell headCellfs162 = rowTitle2.createCell(65);
		headCellfs162.setCellStyle(headStyle);
		headCellfs162.setCellValue("实际进场时间");

		HSSFCell headCellfsfs = rowTitle2.createCell(66);
		headCellfsfs.setCellStyle(headStyle);
		headCellfsfs.setCellValue("进场延期天数");

		HSSFCell headCellfs23 = rowTitle2.createCell(67);
		headCellfs23.setCellStyle(headStyle);
		headCellfs23.setCellValue("计划完成日期");

		HSSFCell headCellfs24 = rowTitle2.createCell(68);
		headCellfs24.setCellStyle(headStyle);
		headCellfs24.setCellValue("实际完成时间");

		HSSFCell headCellfs25 = rowTitle2.createCell(69);
		headCellfs25.setCellStyle(headStyle);
		headCellfs25.setCellValue("正常/延期/提前天数");
		
		
//		 0, 74-4, 83-5));//橱柜核尺    
		HSSFCell headCellcg161 = rowTitle2.createCell(70);
		headCellcg161.setCellStyle(headStyle);
		headCellcg161.setCellValue("计划提报日期");

		HSSFCell headCellcg17 = rowTitle2.createCell(71);
		headCellcg17.setCellStyle(headStyle);
		headCellcg17.setCellValue("实际提报时间");

		HSSFCell headCellcg18 = rowTitle2.createCell(72);
		headCellcg18.setCellStyle(headStyle);
		headCellcg18.setCellValue("提报延期天数");

		HSSFCell headCellcg19 = rowTitle2.createCell(73);
		headCellcg19.setCellStyle(headStyle);
		headCellcg19.setCellValue("期望进场日期");
		
		HSSFCell headCellcg162 = rowTitle2.createCell(74);
		headCellcg162.setCellStyle(headStyle);
		headCellcg162.setCellValue("实际进场时间");

		HSSFCell headCellcgcg = rowTitle2.createCell(75);
		headCellcgcg.setCellStyle(headStyle);
		headCellcgcg.setCellValue("进场延期天数");

		HSSFCell headCellcg23 = rowTitle2.createCell(76);
		headCellcg23.setCellStyle(headStyle);
		headCellcg23.setCellValue("计划完成日期");

		HSSFCell headCellcg24 = rowTitle2.createCell(77);
		headCellcg24.setCellStyle(headStyle);
		headCellcg24.setCellValue("实际完成时间");

		HSSFCell headCellcg25 = rowTitle2.createCell(78);
		headCellcg25.setCellStyle(headStyle);
		headCellcg25.setCellValue("正常/延期/提前天数");
		
		
//		 0, 84-5, 93-6));//瓦工验收   
		HSSFCell headCellwg161 = rowTitle2.createCell(79);
		headCellwg161.setCellStyle(headStyle);
		headCellwg161.setCellValue("计划提报日期");

		HSSFCell headCellwg17 = rowTitle2.createCell(80);
		headCellwg17.setCellStyle(headStyle);
		headCellwg17.setCellValue("实际提报时间");

		HSSFCell headCellwg18 = rowTitle2.createCell(81);
		headCellwg18.setCellStyle(headStyle);
		headCellwg18.setCellValue("提报延期天数");

		HSSFCell headCellwg19 = rowTitle2.createCell(82);
		headCellwg19.setCellStyle(headStyle);
		headCellwg19.setCellValue("期望进场日期");
		
		HSSFCell headCellwg162 = rowTitle2.createCell(83);
		headCellwg162.setCellStyle(headStyle);
		headCellwg162.setCellValue("实际进场时间");

		HSSFCell headCellwgwg = rowTitle2.createCell(84);
		headCellwgwg.setCellStyle(headStyle);
		headCellwgwg.setCellValue("进场延期天数");

		HSSFCell headCellwg23 = rowTitle2.createCell(85);
		headCellwg23.setCellStyle(headStyle);
		headCellwg23.setCellValue("计划完成日期");

		HSSFCell headCellwg24 = rowTitle2.createCell(86);
		headCellwg24.setCellStyle(headStyle);
		headCellwg24.setCellValue("实际完成时间");

		HSSFCell headCellwg25 = rowTitle2.createCell(87);
		headCellwg25.setCellStyle(headStyle);
		headCellwg25.setCellValue("正常/延期/提前天数");
		
//		 0, 94-6, 97-6));//二期款  
		HSSFCell headCelleqk161 = rowTitle2.createCell(88);
		headCelleqk161.setCellStyle(headStyle);
		headCelleqk161.setCellValue("计划");

		HSSFCell headCelleqk17 = rowTitle2.createCell(89);
		headCelleqk17.setCellStyle(headStyle);
		headCelleqk17.setCellValue("催款");

		HSSFCell headCelleqk18 = rowTitle2.createCell(90);
		headCelleqk18.setCellStyle(headStyle);
		headCelleqk18.setCellValue("财务实际收款日期");

		HSSFCell headCelleqk19 = rowTitle2.createCell(91);
		headCelleqk19.setCellStyle(headStyle);
		headCelleqk19.setCellValue("正常/延期/提前天数");
//		 0, 92, 107-7));//基础施工验收  
		HSSFCell headCelljc161 = rowTitle2.createCell(92);
		headCelljc161.setCellStyle(headStyle);
		headCelljc161.setCellValue("计划提报日期");

		HSSFCell headCelljc17 = rowTitle2.createCell(93);
		headCelljc17.setCellStyle(headStyle);
		headCelljc17.setCellValue("实际提报时间");

		HSSFCell headCelljc18 = rowTitle2.createCell(94);
		headCelljc18.setCellStyle(headStyle);
		headCelljc18.setCellValue("提报延期天数");

		HSSFCell headCelljc19 = rowTitle2.createCell(95);
		headCelljc19.setCellStyle(headStyle);
		headCelljc19.setCellValue("期望进场日期");
		
		HSSFCell headCelljc162 = rowTitle2.createCell(96);
		headCelljc162.setCellStyle(headStyle);
		headCelljc162.setCellValue("实际进场时间");

		HSSFCell headCelljcjc = rowTitle2.createCell(97);
		headCelljcjc.setCellStyle(headStyle);
		headCelljcjc.setCellValue("进场延期天数");

		HSSFCell headCelljc23 = rowTitle2.createCell(98);
		headCelljc23.setCellStyle(headStyle);
		headCelljc23.setCellValue("计划完成日期");

		HSSFCell headCelljc24 = rowTitle2.createCell(99);
		headCelljc24.setCellStyle(headStyle);
		headCelljc24.setCellValue("实际完成时间");

		HSSFCell headCelljc25 = rowTitle2.createCell(100);
		headCelljc25.setCellStyle(headStyle);
		headCelljc25.setCellValue("正常/延期/提前天数");
//		 0, 101, 110));//厨卫吊顶  
		HSSFCell headCellcw161 = rowTitle2.createCell(101);
		headCellcw161.setCellStyle(headStyle);
		headCellcw161.setCellValue("计划提报日期");

		HSSFCell headCellcw17 = rowTitle2.createCell(102);
		headCellcw17.setCellStyle(headStyle);
		headCellcw17.setCellValue("实际提报时间");

		HSSFCell headCellcw18 = rowTitle2.createCell(103);
		headCellcw18.setCellStyle(headStyle);
		headCellcw18.setCellValue("提报延期天数");

		HSSFCell headCellcw19 = rowTitle2.createCell(104);
		headCellcw19.setCellStyle(headStyle);
		headCellcw19.setCellValue("期望进场日期");

		HSSFCell headCellcw19s = rowTitle2.createCell(105);
		headCellcw19s.setCellStyle(headStyle);
		headCellcw19s.setCellValue("实际进场时间");
		

		HSSFCell headCellcwcw = rowTitle2.createCell(106);
		headCellcwcw.setCellStyle(headStyle);
		headCellcwcw.setCellValue("进场延期天数");

		HSSFCell headCellcw23 = rowTitle2.createCell(107);
		headCellcw23.setCellStyle(headStyle);
		headCellcw23.setCellValue("计划完成日期");

		HSSFCell headCellcw24 = rowTitle2.createCell(108);
		headCellcw24.setCellStyle(headStyle);
		headCellcw24.setCellValue("实际完工日期");
		
		HSSFCell headCellcw24s = rowTitle2.createCell(109);
		headCellcw24s.setCellStyle(headStyle);
		headCellcw24s.setCellValue("实际验收合格日期");
		
		HSSFCell headCellcw25 = rowTitle2.createCell(110);
		headCellcw25.setCellStyle(headStyle);
		headCellcw25.setCellValue("正常/延期/提前天数");
//		 0, 109+2, 118+2));//洁具 10   
		HSSFCell headCellju161 = rowTitle2.createCell(111);
		headCellju161.setCellStyle(headStyle);
		headCellju161.setCellValue("计划提报日期");

		HSSFCell headCellju17 = rowTitle2.createCell(112);
		headCellju17.setCellStyle(headStyle);
		headCellju17.setCellValue("实际提报时间");

		HSSFCell headCellju18 = rowTitle2.createCell(113);
		headCellju18.setCellStyle(headStyle);
		headCellju18.setCellValue("提报延期天数");

		HSSFCell headCellju19 = rowTitle2.createCell(114);
		headCellju19.setCellStyle(headStyle);
		headCellju19.setCellValue("期望进场日期");

		HSSFCell headCellju19s = rowTitle2.createCell(115);
		headCellju19s.setCellStyle(headStyle);
		headCellju19s.setCellValue("实际进场时间");
		

		HSSFCell headCelljuju = rowTitle2.createCell(116);
		headCelljuju.setCellStyle(headStyle);
		headCelljuju.setCellValue("进场延期天数");

		HSSFCell headCellju23 = rowTitle2.createCell(117);
		headCellju23.setCellStyle(headStyle);
		headCellju23.setCellValue("计划完成日期");

		HSSFCell headCellju24 = rowTitle2.createCell(118);
		headCellju24.setCellStyle(headStyle);
		headCellju24.setCellValue("实际完工日期");
		
		HSSFCell headCellju24s = rowTitle2.createCell(119);
		headCellju24s.setCellStyle(headStyle);
		headCellju24s.setCellValue("实际验收合格日期");
		
		HSSFCell headCellju25 = rowTitle2.createCell(120);
		headCellju25.setCellStyle(headStyle);
		headCellju25.setCellValue("正常/延期/提前天数");
//		 0, 117+4, 126+4));//五金，灯具，开关面板    
		HSSFCell headCellwj161 = rowTitle2.createCell(121);
		headCellwj161.setCellStyle(headStyle);
		headCellwj161.setCellValue("计划提报日期");

		HSSFCell headCellwj17 = rowTitle2.createCell(122);
		headCellwj17.setCellStyle(headStyle);
		headCellwj17.setCellValue("实际提报时间");

		HSSFCell headCellwj18 = rowTitle2.createCell(123);
		headCellwj18.setCellStyle(headStyle);
		headCellwj18.setCellValue("提报延期天数");

		HSSFCell headCellwj19 = rowTitle2.createCell(124);
		headCellwj19.setCellStyle(headStyle);
		headCellwj19.setCellValue("期望进场日期");

		HSSFCell headCellwj19s = rowTitle2.createCell(125);
		headCellwj19s.setCellStyle(headStyle);
		headCellwj19s.setCellValue("实际进场时间");
		

		HSSFCell headCellwjwj = rowTitle2.createCell(126);
		headCellwjwj.setCellStyle(headStyle);
		headCellwjwj.setCellValue("进场延期天数");

		HSSFCell headCellwj23 = rowTitle2.createCell(127);
		headCellwj23.setCellStyle(headStyle);
		headCellwj23.setCellValue("计划完成日期");

		HSSFCell headCellwj24 = rowTitle2.createCell(128);
		headCellwj24.setCellStyle(headStyle);
		headCellwj24.setCellValue("实际完工日期");
		
		HSSFCell headCellwj24s = rowTitle2.createCell(129);
		headCellwj24s.setCellStyle(headStyle);
		headCellwj24s.setCellValue("实际验收合格日期");
		
		HSSFCell headCellwj25 = rowTitle2.createCell(130);
		headCellwj25.setCellStyle(headStyle);
		headCellwj25.setCellValue("正常/延期/提前天数");
//		 0, 126+5, 136+5));//橱柜  
		HSSFCell headCellcgs171 = rowTitle2.createCell(131);
		headCellcgs171.setCellStyle(headStyle);
		headCellcgs171.setCellValue("计划提报日期");

		HSSFCell headCellcgs17 = rowTitle2.createCell(132);
		headCellcgs17.setCellStyle(headStyle);
		headCellcgs17.setCellValue("实际提报时间");

		HSSFCell headCellcgs18 = rowTitle2.createCell(133);
		headCellcgs18.setCellStyle(headStyle);
		headCellcgs18.setCellValue("提报延期天数");
		//提报核尺时间
		HSSFCell headCellcgs18s = rowTitle2.createCell(134);
		headCellcgs18s.setCellStyle(headStyle);
		headCellcgs18s.setCellValue("提报核尺时间");
		
		HSSFCell headCellcgs19 = rowTitle2.createCell(135);
		headCellcgs19.setCellStyle(headStyle);
		headCellcgs19.setCellValue("期望进场日期");

		HSSFCell headCellcgs19s = rowTitle2.createCell(136);
		headCellcgs19s.setCellStyle(headStyle);
		headCellcgs19s.setCellValue("实际进场时间");
		
		HSSFCell headCellcgscgs = rowTitle2.createCell(137);
		headCellcgscgs.setCellStyle(headStyle);
		headCellcgscgs.setCellValue("进场延期天数");

		HSSFCell headCellcgs23 = rowTitle2.createCell(138);
		headCellcgs23.setCellStyle(headStyle);
		headCellcgs23.setCellValue("计划完成日期");

		HSSFCell headCellcgs24 = rowTitle2.createCell(139);
		headCellcgs24.setCellStyle(headStyle);
		headCellcgs24.setCellValue("实际完工日期");
		
		HSSFCell headCellcgs24s = rowTitle2.createCell(140);
		headCellcgs24s.setCellStyle(headStyle);
		headCellcgs24s.setCellValue("实际验收合格日期");
		
		HSSFCell headCellcgs25 = rowTitle2.createCell(141);
		headCellcgs25.setCellStyle(headStyle);
		headCellcgs25.setCellValue("正常/延期/提前天数");
//		 0, 136+6, 146+6));//定制衣柜     
		HSSFCell headCelldz161 = rowTitle2.createCell(142);
		headCelldz161.setCellStyle(headStyle);
		headCelldz161.setCellValue("计划提报日期");

		HSSFCell headCelldz17 = rowTitle2.createCell(143);
		headCelldz17.setCellStyle(headStyle);
		headCelldz17.setCellValue("实际提报时间");

		HSSFCell headCelldz18 = rowTitle2.createCell(144);
		headCelldz18.setCellStyle(headStyle);
		headCelldz18.setCellValue("提报延期天数");
		//提报核尺时间
		HSSFCell headCelldz18s = rowTitle2.createCell(145);
		headCelldz18s.setCellStyle(headStyle);
		headCelldz18s.setCellValue("提报核尺时间");
		
		HSSFCell headCelldz19 = rowTitle2.createCell(146);
		headCelldz19.setCellStyle(headStyle);
		headCelldz19.setCellValue("期望进场日期");

		HSSFCell headCelldz19s = rowTitle2.createCell(147);
		headCelldz19s.setCellStyle(headStyle);
		headCelldz19s.setCellValue("实际进场时间");
		
		HSSFCell headCelldzdz = rowTitle2.createCell(148);
		headCelldzdz.setCellStyle(headStyle);
		headCelldzdz.setCellValue("进场延期天数");

		HSSFCell headCelldz23 = rowTitle2.createCell(149);
		headCelldz23.setCellStyle(headStyle);
		headCelldz23.setCellValue("计划完成日期");

		HSSFCell headCelldz24 = rowTitle2.createCell(150);
		headCelldz24.setCellStyle(headStyle);
		headCelldz24.setCellValue("实际完工日期");
		
		HSSFCell headCelldz24s = rowTitle2.createCell(151);
		headCelldz24s.setCellStyle(headStyle);
		headCelldz24s.setCellValue("实际验收合格日期");
		
		HSSFCell headCelldz25 = rowTitle2.createCell(152);
		headCelldz25.setCellStyle(headStyle);
		headCelldz25.setCellValue("正常/延期/提前天数");
//		 0, 146+7, 155+7));//壁纸  
		
		HSSFCell headCellbzs161 = rowTitle2.createCell(153);
		headCellbzs161.setCellStyle(headStyle);
		headCellbzs161.setCellValue("计划提报日期");

		HSSFCell headCellbzs17 = rowTitle2.createCell(154);
		headCellbzs17.setCellStyle(headStyle);
		headCellbzs17.setCellValue("实际提报时间");

		HSSFCell headCellbzs18 = rowTitle2.createCell(155);
		headCellbzs18.setCellStyle(headStyle);
		headCellbzs18.setCellValue("提报延期天数");
		
		
		HSSFCell headCellbzs19 = rowTitle2.createCell(156);
		headCellbzs19.setCellStyle(headStyle);
		headCellbzs19.setCellValue("期望进场日期");

		HSSFCell headCellbzs19s = rowTitle2.createCell(157);
		headCellbzs19s.setCellStyle(headStyle);
		headCellbzs19s.setCellValue("实际进场时间");
		
		HSSFCell headCellbzsbzs = rowTitle2.createCell(158);
		headCellbzsbzs.setCellStyle(headStyle);
		headCellbzsbzs.setCellValue("进场延期天数");

		HSSFCell headCellbzs23 = rowTitle2.createCell(159);
		headCellbzs23.setCellStyle(headStyle);
		headCellbzs23.setCellValue("计划完成日期");

		HSSFCell headCellbzs24 = rowTitle2.createCell(160);
		headCellbzs24.setCellStyle(headStyle);
		headCellbzs24.setCellValue("实际完工日期");
		
		HSSFCell headCellbzs24s = rowTitle2.createCell(161);
		headCellbzs24s.setCellStyle(headStyle);
		headCellbzs24s.setCellValue("实际验收合格日期");
		
		HSSFCell headCellbzs25 = rowTitle2.createCell(162);
		headCellbzs25.setCellStyle(headStyle);
		headCellbzs25.setCellValue("正常/延期/提前天数");
//		 0, 155+8, 164+9));//木门，铝镁门，门窗套        
		HSSFCell headCellmm171 = rowTitle2.createCell(163);
		headCellmm171.setCellStyle(headStyle);
		headCellmm171.setCellValue("计划提报日期");

		HSSFCell headCellmm17 = rowTitle2.createCell(164);
		headCellmm17.setCellStyle(headStyle);
		headCellmm17.setCellValue("实际提报时间");

		HSSFCell headCellmm18 = rowTitle2.createCell(165);
		headCellmm18.setCellStyle(headStyle);
		headCellmm18.setCellValue("提报延期天数");
		//提报核尺时间
		HSSFCell headCellmm18s = rowTitle2.createCell(166);
		headCellmm18s.setCellStyle(headStyle);
		headCellmm18s.setCellValue("提报核尺时间");
		
		HSSFCell headCellmm19 = rowTitle2.createCell(167);
		headCellmm19.setCellStyle(headStyle);
		headCellmm19.setCellValue("期望进场日期");

		HSSFCell headCellmm19s = rowTitle2.createCell(168);
		headCellmm19s.setCellStyle(headStyle);
		headCellmm19s.setCellValue("实际进场时间");
		
		HSSFCell headCellmmmm = rowTitle2.createCell(169);
		headCellmmmm.setCellStyle(headStyle);
		headCellmmmm.setCellValue("进场延期天数");

		HSSFCell headCellmm23 = rowTitle2.createCell(170);
		headCellmm23.setCellStyle(headStyle);
		headCellmm23.setCellValue("计划完成日期");

		HSSFCell headCellmm24 = rowTitle2.createCell(171);
		headCellmm24.setCellStyle(headStyle);
		headCellmm24.setCellValue("实际完工日期");
		
		HSSFCell headCellmm24s = rowTitle2.createCell(172);
		headCellmm24s.setCellStyle(headStyle);
		headCellmm24s.setCellValue("实际验收合格日期");
		
		HSSFCell headCellmm25 = rowTitle2.createCell(173);
		headCellmm25.setCellStyle(headStyle);
		headCellmm25.setCellValue("正常/延期/提前天数");
//		 0, 164+10, 173+10));//木地板 
		HSSFCell headCellmd171 = rowTitle2.createCell(174);
		headCellmd171.setCellStyle(headStyle);
		headCellmd171.setCellValue("计划提报日期");

		HSSFCell headCellmd17 = rowTitle2.createCell(175);
		headCellmd17.setCellStyle(headStyle);
		headCellmd17.setCellValue("实际提报时间");

		HSSFCell headCellmd18 = rowTitle2.createCell(176);
		headCellmd18.setCellStyle(headStyle);
		headCellmd18.setCellValue("提报延期天数");
		
		HSSFCell headCellmd19 = rowTitle2.createCell(177);
		headCellmd19.setCellStyle(headStyle);
		headCellmd19.setCellValue("期望进场日期");

		HSSFCell headCellmd19s = rowTitle2.createCell(178);
		headCellmd19s.setCellStyle(headStyle);
		headCellmd19s.setCellValue("实际进场时间");
		
		HSSFCell headCellmdmd = rowTitle2.createCell(179);
		headCellmdmd.setCellStyle(headStyle);
		headCellmdmd.setCellValue("进场延期天数");

		HSSFCell headCellmd23 = rowTitle2.createCell(180);
		headCellmd23.setCellStyle(headStyle);
		headCellmd23.setCellValue("计划完成日期");

		HSSFCell headCellmd24 = rowTitle2.createCell(181);
		headCellmd24.setCellStyle(headStyle);
		headCellmd24.setCellValue("实际完工日期");
		
		HSSFCell headCellmd24s = rowTitle2.createCell(182);
		headCellmd24s.setCellStyle(headStyle);
		headCellmd24s.setCellValue("实际验收合格日期");
		
		HSSFCell headCellmd25 = rowTitle2.createCell(183);
		headCellmd25.setCellStyle(headStyle);
		headCellmd25.setCellValue("正常/延期/提前天数");
//		0, 173+11, 183+11));//窗帘 
		HSSFCell headCellcl171 = rowTitle2.createCell(184);
		headCellcl171.setCellStyle(headStyle);
		headCellcl171.setCellValue("计划提报日期");

		HSSFCell headCellcl17 = rowTitle2.createCell(185);
		headCellcl17.setCellStyle(headStyle);
		headCellcl17.setCellValue("实际提报时间");

		HSSFCell headCellcl18 = rowTitle2.createCell(186);
		headCellcl18.setCellStyle(headStyle);
		headCellcl18.setCellValue("提报延期天数");
		//提报核尺时间
		HSSFCell headCellcl18s = rowTitle2.createCell(187);
		headCellcl18s.setCellStyle(headStyle);
		headCellcl18s.setCellValue("提报核尺时间");
		
		HSSFCell headCellcl19 = rowTitle2.createCell(188);
		headCellcl19.setCellStyle(headStyle);
		headCellcl19.setCellValue("期望进场日期");

		HSSFCell headCellcl19s = rowTitle2.createCell(189);
		headCellcl19s.setCellStyle(headStyle);
		headCellcl19s.setCellValue("实际进场时间");
		
		HSSFCell headCellclcl = rowTitle2.createCell(190);
		headCellclcl.setCellStyle(headStyle);
		headCellclcl.setCellValue("进场延期天数");

		HSSFCell headCellcl23 = rowTitle2.createCell(191);
		headCellcl23.setCellStyle(headStyle);
		headCellcl23.setCellValue("计划完成日期");

		HSSFCell headCellcl24 = rowTitle2.createCell(192);
		headCellcl24.setCellStyle(headStyle);
		headCellcl24.setCellValue("实际完工日期");
		
		HSSFCell headCellcl24s = rowTitle2.createCell(193);
		headCellcl24s.setCellStyle(headStyle);
		headCellcl24s.setCellValue("实际验收合格日期");
		
		HSSFCell headCellcl25 = rowTitle2.createCell(194);
		headCellcl25.setCellStyle(headStyle);
		headCellcl25.setCellValue("正常/延期/提前天数");
//		 0, 183+12, 191+12));//竣工验收    
		HSSFCell headCelljg171 = rowTitle2.createCell(195);
		headCelljg171.setCellStyle(headStyle);
		headCelljg171.setCellValue("计划提报日期");

		HSSFCell headCelljg17 = rowTitle2.createCell(196);
		headCelljg17.setCellStyle(headStyle);
		headCelljg17.setCellValue("实际提报时间");

		HSSFCell headCelljg18 = rowTitle2.createCell(197);
		headCelljg18.setCellStyle(headStyle);
		headCelljg18.setCellValue("提报延期天数");
		
		
		HSSFCell headCelljg19 = rowTitle2.createCell(198);
		headCelljg19.setCellStyle(headStyle);
		headCelljg19.setCellValue("期望进场日期");

		HSSFCell headCelljg19s = rowTitle2.createCell(199);
		headCelljg19s.setCellStyle(headStyle);
		headCelljg19s.setCellValue("实际进场时间");
		
		HSSFCell headCelljgjg = rowTitle2.createCell(200);
		headCelljgjg.setCellStyle(headStyle);
		headCelljgjg.setCellValue("进场延期天数");

		HSSFCell headCelljg23 = rowTitle2.createCell(201);
		headCelljg23.setCellStyle(headStyle);
		headCelljg23.setCellValue("计划完成日期");

		HSSFCell headCelljg24s = rowTitle2.createCell(202);
		headCelljg24s.setCellStyle(headStyle);
		headCelljg24s.setCellValue("实际验收合格日期");
		
		HSSFCell headCelljg25 = rowTitle2.createCell(203);
		headCelljg25.setCellStyle(headStyle);
		headCelljg25.setCellValue("正常/延期/提前天数");
		
//		 0, 191+13, 193+13));//尾款 
		HSSFCell headCellwk23 = rowTitle2.createCell(204);
		headCellwk23.setCellStyle(headStyle);
		headCellwk23.setCellValue("计划");

		HSSFCell headCellwk24s = rowTitle2.createCell(205);
		headCellwk24s.setCellStyle(headStyle);
		headCellwk24s.setCellValue("实际交款");
		
		HSSFCell headCellwk25 = rowTitle2.createCell(206);
		headCellwk25.setCellStyle(headStyle);
		headCellwk25.setCellValue("正常/延期/提前天数");
		
//		 0, 193+14, 202+14));//家电   
		HSSFCell headCelljd171 = rowTitle2.createCell(207);
		headCelljd171.setCellStyle(headStyle);
		headCelljd171.setCellValue("计划提报日期");

		HSSFCell headCelljd17 = rowTitle2.createCell(208);
		headCelljd17.setCellStyle(headStyle);
		headCelljd17.setCellValue("实际提报时间");

		HSSFCell headCelljd18 = rowTitle2.createCell(209);
		headCelljd18.setCellStyle(headStyle);
		headCelljd18.setCellValue("提报延期天数");
		
		
		HSSFCell headCelljd19 = rowTitle2.createCell(210);
		headCelljd19.setCellStyle(headStyle);
		headCelljd19.setCellValue("期望进场日期");

		HSSFCell headCelljd19s = rowTitle2.createCell(211);
		headCelljd19s.setCellStyle(headStyle);
		headCelljd19s.setCellValue("实际进场时间");
		
		HSSFCell headCelljdjd = rowTitle2.createCell(212);
		headCelljdjd.setCellStyle(headStyle);
		headCelljdjd.setCellValue("进场延期天数");

		HSSFCell headCelljd23 = rowTitle2.createCell(213);
		headCelljd23.setCellStyle(headStyle);
		headCelljd23.setCellValue("计划完成日期");

		HSSFCell headCelljd24 = rowTitle2.createCell(214);
		headCelljd24.setCellStyle(headStyle);
		headCelljd24.setCellValue("实际完工日期");
		
		HSSFCell headCelljd24s = rowTitle2.createCell(215);
		headCelljd24s.setCellStyle(headStyle);
		headCelljd24s.setCellValue("实际验收合格日期");
		
		HSSFCell headCelljd25 = rowTitle2.createCell(216);
		headCelljd25.setCellStyle(headStyle);
		headCelljd25.setCellValue("正常/延期/提前天数");
		
//		 0, 202+15, 211+15));//家具  
		
		HSSFCell headCellju171 = rowTitle2.createCell(217);
		headCellju171.setCellStyle(headStyle);
		headCellju171.setCellValue("计划提报日期");

		HSSFCell headCellju17s = rowTitle2.createCell(218);
		headCellju17s.setCellStyle(headStyle);
		headCellju17s.setCellValue("实际提报时间");

		HSSFCell headCelljus18 = rowTitle2.createCell(219);
		headCelljus18.setCellStyle(headStyle);
		headCelljus18.setCellValue("提报延期天数");
		
		
		HSSFCell headCelljus19 = rowTitle2.createCell(220);
		headCelljus19.setCellStyle(headStyle);
		headCelljus19.setCellValue("期望进场日期");

		HSSFCell headCelljus19s = rowTitle2.createCell(221);
		headCelljus19s.setCellStyle(headStyle);
		headCelljus19s.setCellValue("实际进场时间");
		
		HSSFCell headCelljusjus = rowTitle2.createCell(222);
		headCelljusjus.setCellStyle(headStyle);
		headCelljusjus.setCellValue("进场延期天数");

		HSSFCell headCelljus23 = rowTitle2.createCell(223);
		headCelljus23.setCellStyle(headStyle);
		headCelljus23.setCellValue("计划完成日期");

		HSSFCell headCelljus24 = rowTitle2.createCell(224);
		headCelljus24.setCellStyle(headStyle);
		headCelljus24.setCellValue("实际完工日期");
		
		HSSFCell headCelljus24s = rowTitle2.createCell(225);
		headCelljus24s.setCellStyle(headStyle);
		headCelljus24s.setCellValue("实际验收合格日期");
		
		HSSFCell headCelljus25 = rowTitle2.createCell(226);
		headCelljus25.setCellStyle(headStyle);
		headCelljus25.setCellValue("正常/延期/提前天数");

		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));// 门店
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));//区域
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 2));//订单编号
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 3, 3));//片区
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 4, 4));//接单日期
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 5, 5));//客户姓名
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 6, 6));//客户电话
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 7, 7));//工程地址
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 8, 8));//合同面积
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 9, 9));//房屋类型
		
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 10,11));//项目经理
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 12, 13));//设计师
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 14, 15));//质检员
		
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 16, 17)); //合同工期时间
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 18, 19));//实际工期时间
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 20, 20));//开工延期天数
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 21, 21));//实际开工客户是否签字
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 22, 22));//变更/停电/停水/客户自装项目延期天数
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 23, 23));//客户是否签字
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 24, 24));//客户认可天数
		
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 25, 33));//防盗门复尺
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 34, 43-1));//辅材进场(3天内)
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 44-1, 53-1-1));//瓷砖
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 54-1-1, 63-3));//水电隐蔽验收
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 64-3, 73-4));//防水验收
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 74-4, 83-5));//橱柜核尺
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 84-5, 93-6));//瓦工验收
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 94-6, 97-6));//二期款
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 92, 107-7));//基础施工验收
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 101, 110));//厨卫吊顶
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 109+2, 118+2));//洁具 10
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 117+4, 126+4));//五金，灯具，开关面板
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 126+5, 136+5));//橱柜
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 136+6, 146+6));//定制衣柜
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 146+7, 155+7));//壁纸
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 155+8, 164+9));//木门，铝镁门，门窗套
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 164+10, 173+10));//木地板
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 173+11, 183+11));//窗帘
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 183+12, 191+12));//竣工验收
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 191+13, 193+13));//尾款
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 193+14, 202+14));//家电
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 202+15, 211+15));//家具
		/*sheet.addMergedRegion(new CellRangeAddress(0, 1, 149, 149));//客户认可延期天数
*/
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 数据
		List<BizProjectProgressBoning> list = dao.findList(bizProjectProgressBoning);
		if (CollectionUtils.isNotEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				BizProjectProgressBoning boning = list.get(i);
				HSSFRow row = sheet.createRow(i + 2);

				HSSFCell cell0 = row.createCell(0);
				if (boning.getStoreId() != null) {//门店
					cell0.setCellValue(BizDictUtils.getStoreLabel(boning.getStoreId() + "", ""));
				}

				HSSFCell cell1 = row.createCell(1);//区域
				if (StringUtils.isNoneBlank(boning.getEnginDepartName())) {
					cell1.setCellValue(boning.getEnginDepartName());
				}

				HSSFCell cell2 = row.createCell(2);//订单编号
				if (boning.getOrderNumber() != null) {
					cell2.setCellValue(boning.getOrderNumber());
				}

				HSSFCell cell3 = row.createCell(3);//片区
				if (StringUtils.isNoneBlank(boning.getArea())) {
					cell3.setCellValue(boning.getArea());
				}

				HSSFCell cell4 = row.createCell(4);//接单日期
				if (boning.getOrderCreateDate() != null) {
					cell4.setCellValue(format.format(boning.getOrderCreateDate()));
				}
				
				HSSFCell cell5 = row.createCell(5);//客户姓名
				if (StringUtils.isNoneBlank(boning.getCustomerName())) {
					cell5.setCellValue(boning.getCustomerName());
				}
				
				HSSFCell cell6 = row.createCell(6);//客户电话
				if (StringUtils.isNoneBlank(boning.getCustomerPhone())) {
					cell6.setCellValue(boning.getCustomerPhone());
				}

				HSSFCell cell7 = row.createCell(7);//工程地址
				if (StringUtils.isNoneBlank(boning.getDetailAddress())) {
					cell7.setCellValue(boning.getDetailAddress());
				}
				
				HSSFCell cell6g1 = row.createCell(8);//合同面积
				if (StringUtils.isNoneBlank(boning.getContractArea())) {
					cell6g1.setCellValue(boning.getContractArea());
				}

				HSSFCell cell7g1 = row.createCell(9);//房屋类型
				if (StringUtils.isNoneBlank(boning.getBuildType())) {
					if("0".equals(boning.getBuildType())){
						cell7g1.setCellValue("平层楼房");
					}else if("1".equals(boning.getBuildType())){
						cell7g1.setCellValue("别墅");
					}else if("2".equals(boning.getBuildType())){
						cell7g1.setCellValue("复式");
					}
				}

				//项目经理
				HSSFCell cell8 = row.createCell(10);//姓名
				if (StringUtils.isNoneBlank(boning.getItemManager())) {
					cell8.setCellValue(boning.getItemManager());
				}

				HSSFCell cell9 = row.createCell(11);//电话
				if (StringUtils.isNoneBlank(boning.getItemManagerPhone())) {
					cell9.setCellValue(boning.getItemManagerPhone());
				}
				//设计师
				HSSFCell cell6888 = row.createCell(12);//姓名
				if (StringUtils.isNoneBlank(boning.getDesignerName())) {
					cell6888.setCellValue(boning.getDesignerName());
				}

				HSSFCell cell7999 = row.createCell(13);//电话
				if (StringUtils.isNoneBlank(boning.getDesignerPhone())) {
					cell7999.setCellValue(boning.getDesignerPhone());
				}
                //质检员
				HSSFCell cell1010 = row.createCell(14);//姓名
				if (StringUtils.isNoneBlank(boning.getOrderInspector())) {
					cell1010.setCellValue(boning.getOrderInspector());
				}

				HSSFCell cell71111 = row.createCell(15);//电话
				if (StringUtils.isNoneBlank(boning.getInspectorPhone())) {
					cell71111.setCellValue(boning.getInspectorPhone());
				}
                //合同工期时间
				HSSFCell cell14 = row.createCell(16);//合同签订开工日期
				if (boning.getContractStartDate() != null) {
					cell14.setCellValue(format.format(boning.getContractStartDate()));
				}

				HSSFCell cell15 = row.createCell(17);
				if (boning.getContractEndDate() != null) {//合同签订竣工日期
					cell15.setCellValue(format.format(boning.getContractEndDate()));
				}

				//实际工期时间
				HSSFCell cell10 = row.createCell(18);
				if (boning.getActualStartDate() != null) {//实际开工日期
					cell10.setCellValue(format.format(boning.getActualStartDate()));
				}

				HSSFCell cell11 = row.createCell(19);//实际竣工日期
				if (boning.getActualEndDate() != null) {
					cell11.setCellValue(format.format(boning.getActualEndDate()));
				}
				
				//开工延期天数
				HSSFCell cell12 = row.createCell(20);
				if (boning.getStartDiffDay() != null) {
					cell12.setCellValue(boning.getStartDiffDay());
				}
                //开工是否需要客户签
				HSSFCell cell13 = row.createCell(21);
				if ("0".equals(boning.getIsNeedSign())) {
					cell13.setCellValue("否");
				} else if ("1".equals(boning.getIsNeedSign())) {
					cell13.setCellValue("是");
				}
                //变更/停电/停水/客户自装项目延期天数
				HSSFCell cell20 = row.createCell(22);
				if (boning.getSelfDecorateDelayDays() != null) {
					cell20.setCellValue(boning.getSelfDecorateDelayDays());
				}
                //客户是否签字
				HSSFCell cell21 = row.createCell(23);
				if ("0".equals(boning.getIsSelfDecorateNeedSign())) {
					cell21.setCellValue("否");
				} else if ("1".equals(boning.getIsSelfDecorateNeedSign())) {
					cell21.setCellValue("是");
				}
				
				//客户认可延期天数	
				
				HSSFCell cell16 = row.createCell(24);
				if (boning.getDelayDays() != null) {
					cell16.setCellValue(boning.getDelayDays());
				}
				//防盗门复尺
				HSSFCell cell23 = row.createCell(25);
				
				if (boning.getNode1PlanSubDate() != null) {//计划提报日期
					cell23.setCellValue(format.format(boning.getNode1PlanSubDate()));
				}
				
				HSSFCell cell17 = row.createCell(26);
				if (boning.getNode1SubmDate() != null) {//实际提报日期
					cell17.setCellValue(format1.format(boning.getNode1SubmDate()));
				}

				HSSFCell cell18 = row.createCell(27);
				if (boning.getNode1SubDiffDay() != null) {//提报延期天数
					cell18.setCellValue(boning.getNode1SubDiffDay());
				}
                
				HSSFCell cell19 = row.createCell(28);
				if (boning.getNode1ApplyEntryDate() != null) {//期望进场日期
					cell19.setCellValue(format.format(boning.getNode1ApplyEntryDate()));
				}
				
				HSSFCell cell27 = row.createCell(29);
				if (boning.getNode1ActualEntryDate()!= null) {//实际进场日期
					cell27.setCellValue(format1.format(boning.getNode1ActualEntryDate()));
				}

				HSSFCell cell28 = row.createCell(30);
				if (boning.getNode1EntryDiffDay() != null) {//进场延期天数
					cell28.setCellValue(boning.getNode1EntryDiffDay());
				}

				HSSFCell cell29 = row.createCell(31);
				if (boning.getNode1PlanDate() != null) {//计划完成日期
					cell29.setCellValue(format.format(boning.getNode1PlanDate()));
				}

				HSSFCell cell22 = row.createCell(32);
				if (boning.getNode1ActualDate() != null) {//实际完成日期
					cell22.setCellValue(format1.format(boning.getNode1ActualDate()));
				}
				
				HSSFCell cell22g1 = row.createCell(33);
				if (boning.getNode1DiffDay() != null) {//常/延期/提前天数
					cell22g1.setCellValue(boning.getNode1DiffDay());
				}
				//辅材进场
				HSSFCell cell31 = row.createCell(34);
				if (boning.getNode2PlanSubDate() != null) {//计划
					cell31.setCellValue(format.format(boning.getNode2PlanSubDate()));
				}
				
				HSSFCell cell32 = row.createCell(35);
				if (boning.getNode2SubmDate() != null) {//提报时间
					cell32.setCellValue(format1.format(boning.getNode2SubmDate()));
				}

				HSSFCell cell33 = row.createCell(36);
				if (boning.getNode2SubDiffDay() != null) {//申请期望
					cell33.setCellValue(boning.getNode2SubDiffDay());
				}

				HSSFCell cell25 = row.createCell(37);
				if (boning.getNode2ExpectDate() != null) {//实际
					cell25.setCellValue(format.format(boning.getNode2ExpectDate()));
				}

				HSSFCell cell26 = row.createCell(38);
				if (boning.getNode2ActualExpectDate() != null) {//正常/延期/提前天数
					cell26.setCellValue(format1.format(boning.getNode2ActualExpectDate()));
				}
				HSSFCell cell36 = row.createCell(39);
				if (boning.getNode2EntryDiffDay() != null) {//计划
					cell36.setCellValue(boning.getNode2EntryDiffDay());
				}
				
				HSSFCell cell37 = row.createCell(40);
				if (boning.getNode2PlanDate() != null) {//提报时间
					cell37.setCellValue(format.format(boning.getNode2PlanDate()));
				}
				
				HSSFCell cell38 = row.createCell(41);
				if (boning.getNode2ActualDate() != null) {//期望
					cell38.setCellValue(format1.format(boning.getNode2ActualDate()));
				}
				
				HSSFCell cell39 = row.createCell(42);
				if (boning.getNode2DiffDay() != null) {//实际
					cell39.setCellValue(boning.getNode2DiffDay());
				}

				//瓷砖
				HSSFCell cell40 = row.createCell(43);
				if (boning.getNode3PlanSubDate() != null) {//正常/延期/提前天数
					cell40.setCellValue(format.format(boning.getNode3PlanSubDate()));
				}

				HSSFCell cell30 = row.createCell(44);
				if (boning.getNode3SubmDate() != null) {//计划
					cell30.setCellValue(format1.format(boning.getNode3SubmDate()));
				}
				
				HSSFCell cell42 = row.createCell(45);
				if (boning.getNode3SubDiffDay() != null) {//提报时间`
					cell42.setCellValue(boning.getNode3SubDiffDay());
				}
				
				HSSFCell cell43 = row.createCell(46);
				if (boning.getNode3ExpectDate() != null) {//期望
					cell43.setCellValue(format.format(boning.getNode3ExpectDate()));
				}

				HSSFCell cell44 = row.createCell(47);
				if (boning.getNode3ActualExpectDate() != null) {//实际
					cell44.setCellValue(format1.format(boning.getNode3ActualExpectDate()));
				}

				HSSFCell cell45 = row.createCell(48);
				if (boning.getNode3EntryDiffDay() != null) {//正常/延期/提前天数
					cell45.setCellValue(boning.getNode3EntryDiffDay());
				}

				HSSFCell cell46 = row.createCell(49);
				if (boning.getNode3PlanDate() != null) {//计划
					cell46.setCellValue(format.format(boning.getNode3PlanDate()));
				}
				
				HSSFCell cell47 = row.createCell(50);
				if (boning.getNode3ActualDate() != null) {//提报时间
					cell47.setCellValue(format1.format(boning.getNode3ActualDate()));
				}

				HSSFCell cell48 = row.createCell(51);
				if (boning.getNode3DiffDay() != null) {//实际
					cell48.setCellValue(boning.getNode3DiffDay());
				}
				//水电隐蔽验收
				HSSFCell cell35 = row.createCell(52);
				if (boning.getNode4PlanSubDate() != null) {//正常/延期/提前天数
					cell35.setCellValue(format.format(boning.getNode4PlanSubDate()));
				}

				//瓦工验收
				HSSFCell cell50 = row.createCell(53);
				if (boning.getNode4SubmDate() != null) {//计划
					cell50.setCellValue(format1.format(boning.getNode4SubmDate()));
				}
				
				HSSFCell cell51 = row.createCell(54);
				if (boning.getNode4SubDiffDay() != null) {//提报时间
					cell51.setCellValue(boning.getNode4SubDiffDay());
				}
				
				HSSFCell cell52 = row.createCell(55);
				if (boning.getNode4ExpectDate() != null) {//期望
					cell52.setCellValue(format.format(boning.getNode4ExpectDate()));
				}
				
				HSSFCell cell53 = row.createCell(56);
				if (boning.getNode4ActualExpectDate() != null) {//实际
					cell53.setCellValue(format1.format(boning.getNode4ActualExpectDate()));
				}

				HSSFCell cell54 = row.createCell(57);
				if (boning.getNode4EntryDiffDay() != null) {//正常/延期/提前天数
					cell54.setCellValue(boning.getNode4EntryDiffDay());
				}

				HSSFCell cell55 = row.createCell(58);
				if (boning.getNode4PlanDate() != null) {//计划
					cell55.setCellValue(format.format(boning.getNode4PlanDate()));
				}

				HSSFCell cell56 = row.createCell(59);
				if (boning.getNode4ActualDate() != null) {//催款
					cell56.setCellValue(format1.format(boning.getNode4ActualDate()));
				}

				HSSFCell cell41 = row.createCell(60);
				if (boning.getNode4DiffDay() != null) {//实际
					cell41.setCellValue(boning.getNode4DiffDay());
				}
				//防水验收
				HSSFCell cell58= row.createCell(61);
				if (boning.getNode5PlanSubDate() != null) {//正常/延期/提前天数
					cell58.setCellValue(format.format(boning.getNode5PlanSubDate()));
				}
				
				HSSFCell cell59 = row.createCell(62);
				if (boning.getNode5SubmDate() != null) {//计划
					cell59.setCellValue(format1.format(boning.getNode5SubmDate()));
				}
				
				HSSFCell cell60 = row.createCell(63);
				if (boning.getNode5SubDiffDay() != null) {//提报时间
					cell60.setCellValue(boning.getNode5SubDiffDay());
				}

				HSSFCell cell61 = row.createCell(64);
				if (boning.getNode5ExpectDate() != null) {//期望
					cell61.setCellValue(format.format(boning.getNode5ExpectDate()));
				}

				HSSFCell cell62 = row.createCell(65);
				if (boning.getNode5ActualExpectDate() != null) {//实际
					cell62.setCellValue(format1.format(boning.getNode5ActualExpectDate()));
				}

				HSSFCell cell63 = row.createCell(66);
				if (boning.getNode5EntryDiffDay() != null) {//正常/延期/提前天数
					cell63.setCellValue(boning.getNode5EntryDiffDay());
				}
				
				HSSFCell cell64 = row.createCell(67);
				if (boning.getNode5PlanDate() != null) {//计划
					cell64.setCellValue(format.format(boning.getNode5PlanDate()));
				}
				
				HSSFCell cell65 = row.createCell(68);
				if (boning.getNode5ActualDate() != null) {//提报时间
					cell65.setCellValue(format1.format(boning.getNode5ActualDate()));
				}
				
				HSSFCell cell66 = row.createCell(69);
				if (boning.getNode5DiffDay() != null) {//申请进场
					cell66.setCellValue(boning.getNode5DiffDay());
				}
				//橱柜核尺
				HSSFCell cell67 = row.createCell(70);
				if (boning.getNode6PlanSubDate() != null) {//实际进场
					cell67.setCellValue(format.format(boning.getNode6PlanSubDate()));
				}

				HSSFCell cell68 = row.createCell(71);
				if (boning.getNode6SubmDate() != null) {//实际完工
					cell68.setCellValue(format1.format(boning.getNode6SubmDate()));
				}
				
				HSSFCell cell69 = row.createCell(72);
				if (boning.getNode6SubDiffDay() != null) {//实际验收
					cell69.setCellValue(boning.getNode6SubDiffDay());
				}

				HSSFCell cell70 = row.createCell(73);
				if (boning.getNode6ExpectDate() != null) {//正常/延期/提前天数
					cell70.setCellValue(format.format(boning.getNode6ExpectDate()));
				}

				//洁具
				HSSFCell cell49 = row.createCell(74);
				if (boning.getNode6ActualExpectDate() != null) {//计划时间
					cell49.setCellValue(format1.format(boning.getNode6ActualExpectDate()));
				}

				HSSFCell cell72 = row.createCell(75);
				if (boning.getNode6EntryDiffDay() != null) {//提报时间
					cell72.setCellValue(boning.getNode6EntryDiffDay());
				}
				
				HSSFCell cell73 = row.createCell(76);
				if (boning.getNode6PlanDate() != null) {//申请进场
					cell73.setCellValue(format.format(boning.getNode6PlanDate()));
				}
				
				HSSFCell cell74 = row.createCell(77);
				if (boning.getNode6ActualDate() != null) {//实际进场
					cell74.setCellValue(format1.format(boning.getNode6ActualDate()));
				}
				
				HSSFCell cell75 = row.createCell(78);
				if (boning.getNode6DiffDay() != null) {//实际完工
					cell75.setCellValue(boning.getNode6DiffDay());
				}
				//瓦工验收
				HSSFCell cell76 = row.createCell(79);
				if (boning.getNode7PlanSubDate() != null) {//实际验收
					cell76.setCellValue(format.format(boning.getNode7PlanSubDate()));
				}

				HSSFCell cell77 = row.createCell(80);
				if (boning.getNode7SubmDate() != null) {//正常/延期/提前天数
					cell77.setCellValue(format1.format(boning.getNode7SubmDate()));
				}

				
				HSSFCell cell78 = row.createCell(81);
				if (boning.getNode7SubDiffDay() != null) {//计划
					cell78.setCellValue(boning.getNode7SubDiffDay());
				}

				HSSFCell cell79 = row.createCell(82);
				if (boning.getNode7ExpectDate() != null) {//提报时间
					cell79.setCellValue(format.format(boning.getNode7ExpectDate()));
				}
				
				HSSFCell cell80 = row.createCell(83);
				if (boning.getNode7ActualExpectDate() != null) {//申请进场
					cell80.setCellValue(format1.format(boning.getNode7ActualExpectDate()));
				}
				
				HSSFCell cell81 = row.createCell(84);
				if (boning.getNode7EntryDiffDay() != null) {//实际进场
					cell81.setCellValue(boning.getNode7EntryDiffDay());
				}
				
				HSSFCell cell82= row.createCell(85);
				if (boning.getNode7PlanDate() != null) {//实际完工
					cell82.setCellValue(format.format(boning.getNode7PlanDate()));
				}

				HSSFCell cell83= row.createCell(86);
				if (boning.getNode7ActualDate() != null) {//实际验收
					cell83.setCellValue(format1.format(boning.getNode7ActualDate()));
				}

				HSSFCell cell84 = row.createCell(87);
				if (boning.getNode7DiffDay() != null) {//正常/延期/提前天数
					cell84.setCellValue(boning.getNode7DiffDay());
				}
				
				
				//二期款（同瓦工验收日期）
				HSSFCell cell85 = row.createCell(88);
				if (boning.getNode8PlanDate() != null) {//计划
					cell85.setCellValue(format.format(boning.getNode8PlanDate()));
				}
				
				HSSFCell cell86 = row.createCell(89);
				if (boning.getNode8AmountDate() != null) {//提报时间
					cell86.setCellValue(format1.format(boning.getNode8AmountDate()));
				}
				
				HSSFCell cell87 = row.createCell(90);
				if (boning.getNode8ActualDate() != null) {//申请进场
					cell87.setCellValue(format1.format(boning.getNode8ActualDate()));
				}
				
				HSSFCell cell88 = row.createCell(91);
				if (boning.getNode8DiffDay() != null) {//实际进场
					cell88.setCellValue(boning.getNode8DiffDay());
				}
				//基础施工验收
				HSSFCell cell89 = row.createCell(92);
				if (boning.getNode9PlanSubDate() != null) {//实际完工
					cell89.setCellValue(format.format(boning.getNode9PlanSubDate()));
				}

				HSSFCell cell90 = row.createCell(93);
				if (boning.getNode9SubmDate() != null) {//实际验收
					cell90.setCellValue(format1.format(boning.getNode9SubmDate()));
				}

				HSSFCell cell91 = row.createCell(94);
				if (boning.getNode9SubDiffDay() != null) {//正常/延期/提前天数
					cell91.setCellValue(boning.getNode9SubDiffDay());
				}

				HSSFCell cell92 = row.createCell(95);
				if (boning.getNode9ExpectDate() != null) {//计划
					cell92.setCellValue(format.format(boning.getNode9ExpectDate()));
				}
				
				HSSFCell cell93 = row.createCell(96);
				if (boning.getNode9ActualExpectDate() != null) {//提报时间
					cell93.setCellValue(format1.format(boning.getNode9ActualExpectDate()));
				}
				
				HSSFCell cell94 = row.createCell(97);
				if (boning.getNode9EntryDiffDay() != null) {//申请进场
					cell94.setCellValue(boning.getNode9EntryDiffDay());
				}
				
				HSSFCell cell95 = row.createCell(98);
				if (boning.getNode9PlanDate() != null) {//实际进场
					cell95.setCellValue(format.format(boning.getNode9PlanDate()));
				}

				HSSFCell cell96 = row.createCell(99);
				if (boning.getNode9ActualDate() != null) {//实际完工
					cell96.setCellValue(format1.format(boning.getNode9ActualDate()));
				}

				HSSFCell cell97 = row.createCell(100);
				if (boning.getNode9DiffDay() != null) {//实际验收
					cell97.setCellValue(boning.getNode9DiffDay());
				}
				//厨卫吊顶
				HSSFCell cell98 = row.createCell(101);
				if (boning.getNode10PlanSubDate() != null) {//正常/延期/提前天数
					cell98.setCellValue(format.format(boning.getNode10PlanSubDate()));
				}

				
				//壁纸
				HSSFCell cell99 = row.createCell(102);
				if (boning.getNode10SubmDate() != null) {//计划
					cell99.setCellValue(format1.format(boning.getNode10SubmDate()));
				}
				
				HSSFCell cell100 = row.createCell(103);
				if (boning.getNode10SubDiffDay() != null) {//提报时间
					cell100.setCellValue(boning.getNode10SubDiffDay());
				}
				
				HSSFCell cell101 = row.createCell(104);
				if (boning.getNode10ApplyEntryDate() != null) {//申请进场
					cell101.setCellValue(format.format(boning.getNode10ApplyEntryDate()));
				}
				
				HSSFCell cell102 = row.createCell(105);
				if (boning.getNode10ActualEntryDate() != null) {//实际进场
					cell102.setCellValue(format1.format(boning.getNode10ActualEntryDate()));
				}

				HSSFCell cell103 = row.createCell(106);
				if (boning.getNode10EntryDiffDay() != null) {//实际完工
					cell103.setCellValue(boning.getNode10EntryDiffDay());
				}

				HSSFCell cell104 = row.createCell(107);
				if (boning.getNode10PlanDate() != null) {//实际验收
					cell104.setCellValue(format.format(boning.getNode10PlanDate()));
				}

				HSSFCell cell105 = row.createCell(108);
				if (boning.getNode10ActualDate() != null) {//正常/延期/提前天数
					cell105.setCellValue(format1.format(boning.getNode10ActualDate()));
				}

				HSSFCell cell106 = row.createCell(109);
				if (boning.getNode10ActualCheckDate() != null) {//计划
					cell106.setCellValue(format1.format(boning.getNode10ActualCheckDate()));
				}
				
				HSSFCell cell107 = row.createCell(110);
				if (boning.getNode10DiffDay() != null) {//提报时间
					cell107.setCellValue(boning.getNode10DiffDay());
				}
				
				//洁具
				HSSFCell cell108 = row.createCell(111);
				if (boning.getNode11PlanSubDate() != null) {//申请进场
					cell108.setCellValue(format.format(boning.getNode11PlanSubDate()));
				}
				
				HSSFCell cell109 = row.createCell(112);
				if (boning.getNode11SubmDate() != null) {//实际进场
					cell109.setCellValue(format1.format(boning.getNode11SubmDate()));
				}

				HSSFCell cell110 = row.createCell(113);
				if (boning.getNode11SubDiffDay() != null) {//实际完成
					cell110.setCellValue(boning.getNode11SubDiffDay());
				}

				HSSFCell cell111 = row.createCell(114);
				if (boning.getNode11ApplyEntryDate() != null) {//实际验收
					cell111.setCellValue(format.format(boning.getNode11ApplyEntryDate()));
				}

				HSSFCell cell112 = row.createCell(115);
				if (boning.getNode11ActualEntryDate() != null) {//正常/延期/提前天数
					cell112.setCellValue(format1.format(boning.getNode11ActualEntryDate()));
				}

				
		
				HSSFCell cell113 = row.createCell(116);
				if (boning.getNode11EntryDiffDay() != null) {//计划
					cell113.setCellValue(boning.getNode11EntryDiffDay());
				}

				HSSFCell cell114 = row.createCell(117);
				if (boning.getNode11PlanDate() != null) {//提报时间
					cell114.setCellValue(format.format(boning.getNode11PlanDate()));
				}
				
				HSSFCell cell115 = row.createCell(118);
				if (boning.getNode11InstallDate() != null) {//申请进场
					cell115.setCellValue(format1.format(boning.getNode11InstallDate()));
				}
				
				HSSFCell cell116 = row.createCell(119);
				if (boning.getNode11ActualDate() != null) {//实际进场
					cell116.setCellValue(format1.format(boning.getNode11ActualDate()));
				}
				
				HSSFCell cell117 = row.createCell(120);
				if (boning.getNode11DiffDay() != null) {//实际完成
					cell117.setCellValue(boning.getNode11DiffDay());
				}
				//五金，灯具，开关面板
				HSSFCell cell118 = row.createCell(121);
				if (boning.getNode12PlanSubDate() != null) {//实际验收
					cell118.setCellValue(format.format(boning.getNode12PlanSubDate()));
				}

				HSSFCell cell119 = row.createCell(122);
				if (boning.getNode12SubmDate() != null) {//正常/延期/提前天数
					cell119.setCellValue(format1.format(boning.getNode12SubmDate()));
				}
				
				HSSFCell cell120 = row.createCell(123);
				if (boning.getNode12SubDiffDay() != null) {//计划
					cell120.setCellValue(boning.getNode12SubDiffDay());
				}

				HSSFCell cell121 = row.createCell(124);
				if (boning.getNode12ApplyEntryDate() != null) {//提报时间
					cell121.setCellValue(format.format(boning.getNode12ApplyEntryDate()));
				}
				
				HSSFCell cell122 = row.createCell(125);
				if (boning.getNode12ActualEntryDate() != null) {//申请进场
					cell122.setCellValue(format1.format(boning.getNode12ActualEntryDate()));
				}
				
				HSSFCell cell123 = row.createCell(126);
				if (boning.getNode12EntryDiffDay() != null) {//实际进场
					cell123.setCellValue(boning.getNode12EntryDiffDay());
				}
				
				HSSFCell cell124 = row.createCell(127);
				if (boning.getNode12PlanDate() != null) {//实际完成
					cell124.setCellValue(format.format(boning.getNode12PlanDate()));
				}

				HSSFCell cell125 = row.createCell(128);
				if (boning.getNode12InstallDate() != null) {//实际验收
					cell125.setCellValue(format1.format(boning.getNode12InstallDate()));
				}

				HSSFCell cell126 = row.createCell(129);
				if (boning.getNode12ActualDate() != null) {//正常/延期/提前天数
					cell126.setCellValue(format1.format(boning.getNode12ActualDate()));
				}

				HSSFCell cel81 = row.createCell(130);
				if (boning.getNode12DiffDay() != null) {//计划
					cel81.setCellValue(boning.getNode12DiffDay());
				}
				//橱柜
				HSSFCell cell128 = row.createCell(131);
				if (boning.getNode13PlanSubDate() != null) {//提报时间
					cell128.setCellValue(format.format(boning.getNode13PlanSubDate()));
				}
				
				HSSFCell cell129 = row.createCell(132);
				if (boning.getNode13SubmDate() != null) {//期望
					cell129.setCellValue(format1.format(boning.getNode13SubmDate()));
				}
				
				HSSFCell cell130 = row.createCell(133);
				if (boning.getNode13SubDiffDay() != null) {//实际
					cell130.setCellValue(boning.getNode13SubDiffDay());
				}

				HSSFCell cell131 = row.createCell(134);
				if (boning.getNode13RuleDate() != null) {//正常/延期/提前天数
					cell131.setCellValue(format1.format(boning.getNode13RuleDate()));
				}

				HSSFCell cell132 = row.createCell(135);
				if (boning.getNode13ApplyEntryDate() != null) {//计划
					cell132.setCellValue(format.format(boning.getNode13ApplyEntryDate()));
				}

				HSSFCell cell133 = row.createCell(136);
				if (boning.getNode13ActualEntryDate() != null) {//实际
					cell133.setCellValue(format1.format(boning.getNode13ActualEntryDate()));
				}

				HSSFCell cell134 = row.createCell(137);
				if (boning.getNode13EntryDiffDay() != null) {//正常/延期/提前天数
					cell134.setCellValue(boning.getNode13EntryDiffDay());
				}

				HSSFCell cell135 = row.createCell(138);
				if (boning.getNode13PlanDate() != null) {//计划
					cell135.setCellValue(format.format(boning.getNode13PlanDate()));
				}

				HSSFCell cell136 = row.createCell(139);
				if (boning.getNode13InstallDate() != null) {//提报时间
					cell136.setCellValue(format1.format(boning.getNode13InstallDate()));
				}
				
				HSSFCell cell137 = row.createCell(140);
				if (boning.getNode13ActualDate() != null) {//申请进场
					cell137.setCellValue(format1.format(boning.getNode13ActualDate()));
				}
				
				HSSFCell cell138 = row.createCell(141);
				if (boning.getNode13DiffDay() != null) {//实际进场
					cell138.setCellValue(boning.getNode13DiffDay());
				}
				//定制衣柜
				HSSFCell cell139 = row.createCell(142);
				if (boning.getNode14PlanSubDate() != null) {//实际完成
					cell139.setCellValue(format.format(boning.getNode14PlanSubDate()));
				}

				HSSFCell cell140 = row.createCell(143);
				if (boning.getNode14SubmDate() != null) {//实际验收
					cell140.setCellValue(format1.format(boning.getNode14SubmDate()));
				}

				HSSFCell cell141 = row.createCell(144);
				if (boning.getNode14SubDiffDay() != null) {//正常/延期/提前天数
					cell141.setCellValue(boning.getNode14SubDiffDay());
				}

				//家具
				HSSFCell cell142 = row.createCell(145);
				if (boning.getNode14RuleDate() != null) {//计划
					cell142.setCellValue(format1.format(boning.getNode14RuleDate()));
				}
				
				HSSFCell cell143 = row.createCell(146);
				if (boning.getNode14ApplyEntryDate() != null) {//提报时间
					cell143.setCellValue(format.format(boning.getNode14ApplyEntryDate()));
				}
				
				HSSFCell cell144 = row.createCell(147);
				if (boning.getNode14ActualEntryDate() != null) {//申请进场
					cell144.setCellValue(format1.format(boning.getNode14ActualEntryDate()));
				}
				
				HSSFCell cell145 = row.createCell(148);
				if (boning.getNode14EntryDiffDay() != null) {//实际进场
					 cell145.setCellValue(boning.getNode14EntryDiffDay());
				}

				HSSFCell cell146 = row.createCell(149);
				if (boning.getNode14PlanDate() != null) {//实际完成
					cell146.setCellValue(format.format(boning.getNode14PlanDate()));
				}

				HSSFCell cell147 = row.createCell(150);
				if (boning.getNode14InstallDate() != null) {//实际验收
					cell147.setCellValue(format1.format(boning.getNode14InstallDate()));
				}

				HSSFCell cell148 = row.createCell(151);
				if (boning.getNode14ActualDate() != null) {//正常/延期/提前天数
					cell148.setCellValue(format1.format(boning.getNode14ActualDate()));
				}
				
				HSSFCell cell149 = row.createCell(152);
				if (boning.getNode14DiffDay() != null) {//正常/延期/提前天数
					cell149.setCellValue(boning.getNode14DiffDay());
				}
				
//				--------------------------------------------
				//壁纸
				HSSFCell cell123s = row.createCell(153);
				if (boning.getNode15PlanSubDate() != null) {//实际进场
					cell123s.setCellValue(format.format(boning.getNode15PlanSubDate()));
				}
				
				HSSFCell cell124s = row.createCell(154);
				if (boning.getNode15SubmDate() != null) {//实际完成
					cell124s.setCellValue(format1.format(boning.getNode15SubmDate()));
				}

				HSSFCell cell125s = row.createCell(155);
				if (boning.getNode15SubDiffDay() != null) {//实际验收
					cell125s.setCellValue(boning.getNode15SubDiffDay());
				}

				HSSFCell cell126s = row.createCell(156);
				if (boning.getNode15ApplyEntryDate() != null) {//正常/延期/提前天数
					cell126s.setCellValue(format.format(boning.getNode15ApplyEntryDate()));
				}

				HSSFCell cel81s = row.createCell(157);
				if (boning.getNode15ActualEntryDate() != null) {//计划
					cel81s.setCellValue(format1.format(boning.getNode15ActualEntryDate()));
				}
				HSSFCell cell128s = row.createCell(158);
				if (boning.getNode15EntryDiffDay() != null) {//提报时间
					cell128s.setCellValue(boning.getNode15EntryDiffDay());
				}
				
				HSSFCell cell129s = row.createCell(159);
				if (boning.getNode15PlanDate() != null) {//期望
					cell129s.setCellValue(format.format(boning.getNode15PlanDate()));
				}
				
				HSSFCell cell130s = row.createCell(160);
				if (boning.getNode15InstallDate() != null) {//实际
					cell130s.setCellValue(format1.format(boning.getNode15InstallDate()));
				}

				HSSFCell cell131s = row.createCell(161);
				if (boning.getNode15ActualDate() != null) {//正常/延期/提前天数
					cell131s.setCellValue(format1.format(boning.getNode15ActualDate()));
				}

				HSSFCell cell132s = row.createCell(162);
				if (boning.getNode15DiffDay() != null) {//计划
					cell132s.setCellValue(boning.getNode15DiffDay());
				}
				//木门，铝镁门，门窗套
				HSSFCell cell133s = row.createCell(163);
				if (boning.getNode16PlanSubDate() != null) {//实际
					cell133s.setCellValue(format.format(boning.getNode16PlanSubDate()));
				}

				HSSFCell cell134s = row.createCell(164);
				if (boning.getNode16SubmDate() != null) {//正常/延期/提前天数
					cell134s.setCellValue(format1.format(boning.getNode16SubmDate()));
				}

				HSSFCell cell135s = row.createCell(165);
				if (boning.getNode16SubDiffDay() != null) {//计划
					cell135s.setCellValue(boning.getNode16SubDiffDay());
				}

				HSSFCell cell136s = row.createCell(166);
				if (boning.getNode16RuleDate() != null) {//提报时间
					cell136s.setCellValue(format1.format(boning.getNode16RuleDate()));
				}
				
				HSSFCell cell137s = row.createCell(167);
				if (boning.getNode16ApplyEntryDate() != null) {//申请进场
					cell137s.setCellValue(format.format(boning.getNode16ApplyEntryDate()));
				}
				
				HSSFCell cell138s = row.createCell(168);
				if (boning.getNode16ActualEntryDate() != null) {//实际进场
					cell138s.setCellValue(format1.format(boning.getNode16ActualEntryDate()));
				}
				HSSFCell cell139s = row.createCell(169);
				if (boning.getNode16EntryDiffDay() != null) {//实际完成
					cell139s.setCellValue(boning.getNode16EntryDiffDay());
				}

				HSSFCell cell140s = row.createCell(170);
				if (boning.getNode16PlanDate() != null) {//实际验收
					cell140s.setCellValue(format.format(boning.getNode16PlanDate()));
				}

				HSSFCell cell141s = row.createCell(171);
				if (boning.getNode16InstallDate() != null) {//正常/延期/提前天数
					cell141s.setCellValue(format1.format(boning.getNode16InstallDate()));
				}
				HSSFCell cell142s = row.createCell(172);
				if (boning.getNode16ActualDate() != null) {//计划
					cell142s.setCellValue(format1.format(boning.getNode16ActualDate()));
				}
				
				HSSFCell cell143s = row.createCell(173);
				if (boning.getNode16DiffDay() != null) {//提报时间
					cell143s.setCellValue(boning.getNode16DiffDay());
				}
				//木地板
				HSSFCell cell144s = row.createCell(174);
				if (boning.getNode17PlanSubDate() != null) {//申请进场
					cell144s.setCellValue(format.format(boning.getNode17PlanSubDate()));
				}
				
				HSSFCell cell145s = row.createCell(175);
				if (boning.getNode17SubmDate() != null) {//实际进场
					 cell145s.setCellValue(format1.format(boning.getNode17SubmDate()));
				}

				HSSFCell cell146s = row.createCell(176);
				if (boning.getNode17SubDiffDay() != null) {//实际完成
					cell146s.setCellValue(boning.getNode17SubDiffDay());
				}
//定制衣柜
				HSSFCell cell147s = row.createCell(177);
				if (boning.getNode17ApplyEntryDate() != null) {//实际验收
					cell147s.setCellValue(format.format(boning.getNode17ApplyEntryDate()));
				}

				HSSFCell cell148s = row.createCell(178);
				if (boning.getNode17ActualEntryDate() != null) {//正常/延期/提前天数
					cell148s.setCellValue(format1.format(boning.getNode17ActualEntryDate()));
				}
				
				HSSFCell cell149s= row.createCell(179);
				if (boning.getNode17EntryDiffDay() != null) {//正常/延期/提前天数
					cell149s.setCellValue(boning.getNode17EntryDiffDay());
				}
				
				
				HSSFCell cell123ss = row.createCell(180);
				if (boning.getNode17PlanDate() != null) {//实际进场
					cell123ss.setCellValue(format.format(boning.getNode17PlanDate()));
				}
				
				HSSFCell cell124ss = row.createCell(181);
				if (boning.getNode17InstallDate() != null) {//实际完成
					cell124ss.setCellValue(format1.format(boning.getNode17InstallDate()));
				}

				HSSFCell cell125ss = row.createCell(182);
				if (boning.getNode17ActualDate() != null) {//实际验收
					cell125ss.setCellValue(format1.format(boning.getNode17ActualDate()));
				}

				HSSFCell cell126ss = row.createCell(183);
				if (boning.getNode17DiffDay() != null) {//正常/延期/提前天数
					cell126ss.setCellValue(boning.getNode17DiffDay());
				}
				//窗帘
				HSSFCell cel81ss = row.createCell(184);
				if (boning.getNode18PlanSubDate() != null) {//计划
					cel81ss.setCellValue(format.format(boning.getNode18PlanSubDate()));
				}
				HSSFCell cell128ss = row.createCell(185);
				if (boning.getNode18SubmDate() != null) {//提报时间
					cell128ss.setCellValue(format1.format(boning.getNode18SubmDate()));
				}
				
				HSSFCell cell129ss = row.createCell(186);
				if (boning.getNode18SubDiffDay() != null) {//期望
					cell129ss.setCellValue(boning.getNode18SubDiffDay());
				}
				
				HSSFCell cell130ss = row.createCell(187);
				if (boning.getNode18RuleDate() != null) {//实际
					cell130ss.setCellValue(format1.format(boning.getNode18RuleDate()));
				}

				HSSFCell cell131ss = row.createCell(188);
				if (boning.getNode18ApplyEntryDate() != null) {//正常/延期/提前天数
					cell131ss.setCellValue(format.format(boning.getNode18ApplyEntryDate()));
				}

				HSSFCell cell132ss = row.createCell(189);
				if (boning.getNode18ActualEntryDate() != null) {//计划
					cell132ss.setCellValue(format1.format(boning.getNode18ActualEntryDate()));
				}
				HSSFCell cell133ss = row.createCell(190);
				if (boning.getNode18EntryDiffDay() != null) {//实际
					cell133ss.setCellValue(boning.getNode18EntryDiffDay());
				}

				HSSFCell cell134ss = row.createCell(191);
				if (boning.getNode18PlanDate() != null) {//正常/延期/提前天数
					cell134ss.setCellValue(format.format(boning.getNode18PlanDate()));
				}

				HSSFCell cell135ss = row.createCell(192);
				if (boning.getNode18InstallDate() != null) {//计划
					cell135ss.setCellValue(format1.format(boning.getNode18InstallDate()));
				}

				HSSFCell cell136ss = row.createCell(193);
				if (boning.getNode18ActualDate() != null) {//提报时间
					cell136ss.setCellValue(format1.format(boning.getNode18ActualDate()));
				}
				
				HSSFCell cell137ss = row.createCell(194);
				if (boning.getNode18DiffDay() != null) {//申请进场
					cell137ss.setCellValue(boning.getNode18DiffDay());
				}
				HSSFCell cell138ss = row.createCell(195);
				if (boning.getNode19PlanSubDate() != null) {//实际进场
					cell138ss.setCellValue(format.format(boning.getNode19PlanSubDate()));
				}
				HSSFCell cell139ss = row.createCell(196);
				if (boning.getNode19SubmDate() != null) {//实际完成
					cell139ss.setCellValue(format1.format(boning.getNode19SubmDate()));
				}

				HSSFCell cell140ss = row.createCell(197);
				if (boning.getNode19SubDiffDay() != null) {//实际验收
					cell140ss.setCellValue(boning.getNode19SubDiffDay());
				}

				HSSFCell cell141ss = row.createCell(198);
				if (boning.getNode19ExpectDate() != null) {//正常/延期/提前天数
					cell141ss.setCellValue(format.format(boning.getNode19ExpectDate()));
				}

				HSSFCell cell142ss = row.createCell(199);
				if (boning.getNode19ActualEntryDate() != null) {//计划
					cell142ss.setCellValue(format1.format(boning.getNode19ActualEntryDate()));
				}
				
				HSSFCell cell143ss = row.createCell(200);
				if (boning.getNode19EntryDiffDay() != null) {//提报时间
					cell143ss.setCellValue(boning.getNode19EntryDiffDay());
				}
				HSSFCell cell144ss = row.createCell(201);
				if (boning.getNode19PlanDate() != null) {//申请进场
					cell144ss.setCellValue(format.format(boning.getNode19PlanDate()));
				}
				
				HSSFCell cell145ss = row.createCell(202);
				if (boning.getNode19ActualDate() != null) {//实际进场
					 cell145ss.setCellValue(format1.format(boning.getNode19ActualDate()));
				}

				HSSFCell cell146ss = row.createCell(203);
				if (boning.getNode19DiffDay() != null) {//实际完成
					cell146ss.setCellValue(boning.getNode19DiffDay());
				}
				//尾款
				HSSFCell cell147ss = row.createCell(204);
				if (boning.getNode20PlanDate() != null) {//实际验收
					cell147ss.setCellValue(format.format(boning.getNode20PlanDate()));
				}

				HSSFCell cell148ss = row.createCell(205);
				if (boning.getNode20ActualDate() != null) {//正常/延期/提前天数
					cell148ss.setCellValue(format1.format(boning.getNode20ActualDate()));
				}
				
				HSSFCell cell149ss= row.createCell(206);
				if (boning.getNode20DiffDay() != null) {//正常/延期/提前天数
					cell149ss.setCellValue(boning.getNode20DiffDay());
				}
				//家电
				HSSFCell cell23s = row.createCell(207);
				if (boning.getNode21PlanSubDate() != null) {//计划提报日期
					cell23s.setCellValue(format.format(boning.getNode21PlanSubDate()));
				}
				
				HSSFCell cell17s = row.createCell(208);
				if (boning.getNode21SubmDate() != null) {//实际提报日期
					cell17s.setCellValue(format1.format(boning.getNode21SubmDate()));
				}

				HSSFCell cell18ss = row.createCell(209);
				if (boning.getNode21SubDiffDay() != null) {//提报延期天数
					cell18ss.setCellValue(boning.getNode21SubDiffDay());
				}
				HSSFCell cell18s = row.createCell(210);
				if (boning.getNode21ApplyEntryDate() != null) {//提报延期天数
					cell18s.setCellValue(format.format(boning.getNode21ApplyEntryDate()));
				}
                
				HSSFCell cell19s = row.createCell(211);
				if (boning.getNode21ActualEntryDate() != null) {//期望进场日期
					cell19s.setCellValue(format1.format(boning.getNode21ActualEntryDate()));
				}
				
				HSSFCell cell27s = row.createCell(212);
				if (boning.getNode21EntryDiffDay()!= null) {//实际进场日期
					cell27s.setCellValue(boning.getNode21EntryDiffDay());
				}

				HSSFCell cell28s = row.createCell(213);
				if (boning.getNode21PlanDate() != null) {//进场延期天数
					cell28s.setCellValue(format.format(boning.getNode21PlanDate()));
				}

				HSSFCell cell29s = row.createCell(214);
				if (boning.getNode21InstallDate() != null) {//计划完成日期
					cell29s.setCellValue(format1.format(boning.getNode21InstallDate()));
				}

				HSSFCell cell22s = row.createCell(215);
				if (boning.getNode21ActualDate() != null) {//实际完成日期
					cell22s.setCellValue(format1.format(boning.getNode21ActualDate()));
				}
				
				HSSFCell cell22g1s = row.createCell(216);
				if (boning.getNode21DiffDay() != null) {//常/延期/提前天数
					cell22g1s.setCellValue(boning.getNode21DiffDay());
				}
				////家具
				HSSFCell cell31s = row.createCell(217);
				if (boning.getNode22PlanSubDate() != null) {//计划
					cell31s.setCellValue(format.format(boning.getNode22PlanSubDate()));
				}
				
				HSSFCell cell32s = row.createCell(218);
				if (boning.getNode22SubmDate() != null) {//提报时间
					cell32s.setCellValue(format1.format(boning.getNode22SubmDate()));
				}

				HSSFCell cell33s = row.createCell(219);
				if (boning.getNode22SubDiffDay() != null) {//申请期望
					cell33s.setCellValue(boning.getNode22SubDiffDay());
				}

				HSSFCell cell25s = row.createCell(220);
				if (boning.getNode22ApplyEntryDate() != null) {//实际
					cell25s.setCellValue(format.format(boning.getNode22ApplyEntryDate()));
				}

				HSSFCell cell26s = row.createCell(221);
				if (boning.getNode22ActualEntryDate() != null) {//正常/延期/提前天数
					cell26s.setCellValue(format1.format(boning.getNode22ActualEntryDate()));
				}
				HSSFCell cell36s = row.createCell(222);
				if (boning.getNode22EntryDiffDay() != null) {//计划
					cell36s.setCellValue(boning.getNode22EntryDiffDay());
				}
				
				HSSFCell cell37s = row.createCell(223);
				if (boning.getNode22PlanDate() != null) {//提报时间
					cell37s.setCellValue(format.format(boning.getNode22PlanDate()));
				}
				
				HSSFCell cell38s = row.createCell(224);
				if (boning.getNode22InstallDate() != null) {//期望
					cell38s.setCellValue(format1.format(boning.getNode22InstallDate()));
				}
				
				HSSFCell cell39s = row.createCell(225);
				if (boning.getNode22ActualDate() != null) {//实际
					cell39s.setCellValue(format1.format(boning.getNode22ActualDate()));
				}

				
				HSSFCell cell40s = row.createCell(226);
				if (boning.getNode22DiffDay() != null) {//正常/延期/提前天数
					cell40s.setCellValue(boning.getNode22DiffDay());
				}

			}
		}

		return wb;
	}
	
	public List<BizProjectProgressBoning> queryIsScrapOrder(){
		return dao.queryIsScrapOrder();
	}
}