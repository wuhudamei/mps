package cn.damei.service.modules;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.PurchaseStatisticsDao;
import cn.damei.entity.modules.PurchaseStatistics;

/**
 * 采购单统计表Service
 * 
 */
@Service
@Transactional(readOnly = true)
public class PurchaseStatisticsService{


	@Autowired
	private PurchaseStatisticsDao dao;

	/**
	 * 材料统计表
	 */
	public List<PurchaseStatistics> findList(PurchaseStatistics purchaseStatistics) {
		
		//材料统计表
		List<PurchaseStatistics> list = dao.findList(purchaseStatistics);
		
		if(CollectionUtils.isNotEmpty(list)){
			for(PurchaseStatistics entity:list){
				
				//时间段
				entity.setBeginDateTime(purchaseStatistics.getBeginDateTime());
				entity.setEndDateTime(purchaseStatistics.getEndDateTime());
				
				//----------------------------------------- 一 ---------------------------------------------
				
				//1.辅料、开关面板、墙地砖、沙子水泥 发货申请单数
				List<PurchaseStatistics> applyList = dao.findApplyList(entity);
				
				if(CollectionUtils.isNotEmpty(applyList)){
					for(PurchaseStatistics a:applyList){
						if(StringUtils.isNotBlank(a.getType())){
							if(a.getType().equals("1")){
								//1.1：辅料    发货申请单数
								entity.setAuxiliaryApplyCount(a.getTypeCount());
							}else if(a.getType().equals("2")){
								//1.2：开关面板    发货申请单数
								entity.setMainPanelApplyCount(a.getTypeCount());
							}else if(a.getType().equals("5")){
								//1.3：墙地砖   发货申请单数
								entity.setWallFloorApplyCount(a.getTypeCount());
							}else if(a.getType().equals("6")){
								//1.4：沙子水泥   发货申请单数
								entity.setSandApplyCount(a.getTypeCount());
							}
						}
							
					}
				}
				
				//---------------------------------------- 二 ----------------------------------------------
				
				
				//2.辅料、开关面板、墙地砖、沙子水泥 转给供应商单数
				List<PurchaseStatistics> transferSupplierList = dao.findTransferSupplierList(entity);
				
				
				if(CollectionUtils.isNotEmpty(transferSupplierList)){
					for(PurchaseStatistics a:transferSupplierList){
						if(StringUtils.isNotBlank(a.getType())){
							if(a.getType().equals("1")){
								//2.1：辅料    转给供应商单数
								entity.setAuxiliaryTransferSupplierCount(a.getTypeCount());
							}else if(a.getType().equals("2")){
								//2.2：开关面板    转给供应商单数
								entity.setMainPanelTransferSupplierCount(a.getTypeCount());
							}else if(a.getType().equals("5")){
								//2.3：墙地砖   转给供应商单数
								entity.setWallFloorTransferSupplierCount(a.getTypeCount());
							}else if(a.getType().equals("6")){
								//2.4：沙子水泥   转给供应商单数
								entity.setSandTransferSupplierCount(a.getTypeCount());
							}
						}
							
					}
				}
				
				//------------------------------------------ 三 --------------------------------------------
				
				//3.辅料、开关面板、墙地砖、沙子水泥  收货单数
				List<PurchaseStatistics> receiveList = dao.findReceiveList(entity);
				
				if(CollectionUtils.isNotEmpty(receiveList)){
					for(PurchaseStatistics a:receiveList){
						if(StringUtils.isNotBlank(a.getType())){
							if(a.getType().equals("1")){
								//3.1：辅料    收货单数
								entity.setAuxiliaryReceiveCount(a.getTypeCount());
							}else if(a.getType().equals("2")){
								//3.2：开关面板    收货单数
								entity.setMainPanelReceiveCount(a.getTypeCount());
							}else if(a.getType().equals("5")){
								//3.3：墙地砖   收货单数
								entity.setWallFloorReceiveCount(a.getTypeCount());
							}else if(a.getType().equals("6")){
								//3.4：沙子水泥   收货单数
								entity.setSandReceiveCount(a.getTypeCount());
							}
						}
							
					}
				}
				
				//------------------------------------------ 四 --------------------------------------------
				
				
				//4.标化、筒灯灯带  申请单数
				List<PurchaseStatistics> standardApplyList = dao.findStandardApplyList(entity);
				
				if(CollectionUtils.isNotEmpty(standardApplyList)){
					for(PurchaseStatistics a:standardApplyList){
						if(StringUtils.isNotBlank(a.getType())){
							if(a.getType().equals("1")){
								//4.1：标化  申请单数
								entity.setStandardApplyCount(a.getTypeCount());
							}else if(a.getType().equals("2")){
								//4.2：筒灯灯带  申请单数
								entity.setDownlightApplyCount(a.getTypeCount());
							}
						}
							
					}
				}
				
				//------------------------------------------ 五 --------------------------------------------
				
				//5.标化、筒灯灯带  领取单数
				List<PurchaseStatistics> standardReceiveList = dao.findStandardReceiveList(entity);
				
				if(CollectionUtils.isNotEmpty(standardReceiveList)){
					for(PurchaseStatistics a:standardReceiveList){
						if(StringUtils.isNotBlank(a.getType())){
							if(a.getType().equals("1")){
								//5.1：标化  领取单数
								entity.setStandardReceiveCount(a.getTypeCount());
							}else if(a.getType().equals("2")){
								//5.2：筒灯灯带  领取单数
								entity.setDownlightReceiveCount(a.getTypeCount());
							}
						}
							
					}
				}
				
				//--------------------------------------------------------------------------------------
				
			}
		}
		
		return list;
	}
	



}