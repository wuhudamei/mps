package cn.damei.service.modules;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.PurchaseStatisticsDao;
import cn.damei.entity.modules.PurchaseStatistics;


@Service
@Transactional(readOnly = true)
public class PurchaseStatisticsService{


	@Autowired
	private PurchaseStatisticsDao dao;


	public List<PurchaseStatistics> findList(PurchaseStatistics purchaseStatistics) {
		

		List<PurchaseStatistics> list = dao.findList(purchaseStatistics);
		
		if(CollectionUtils.isNotEmpty(list)){
			for(PurchaseStatistics entity:list){
				

				entity.setBeginDateTime(purchaseStatistics.getBeginDateTime());
				entity.setEndDateTime(purchaseStatistics.getEndDateTime());
				

				

				List<PurchaseStatistics> applyList = dao.findApplyList(entity);
				
				if(CollectionUtils.isNotEmpty(applyList)){
					for(PurchaseStatistics a:applyList){
						if(StringUtils.isNotBlank(a.getType())){
							if(a.getType().equals("1")){

								entity.setAuxiliaryApplyCount(a.getTypeCount());
							}else if(a.getType().equals("2")){

								entity.setMainPanelApplyCount(a.getTypeCount());
							}else if(a.getType().equals("5")){

								entity.setWallFloorApplyCount(a.getTypeCount());
							}else if(a.getType().equals("6")){

								entity.setSandApplyCount(a.getTypeCount());
							}
						}
							
					}
				}
				

				
				

				List<PurchaseStatistics> transferSupplierList = dao.findTransferSupplierList(entity);
				
				
				if(CollectionUtils.isNotEmpty(transferSupplierList)){
					for(PurchaseStatistics a:transferSupplierList){
						if(StringUtils.isNotBlank(a.getType())){
							if(a.getType().equals("1")){

								entity.setAuxiliaryTransferSupplierCount(a.getTypeCount());
							}else if(a.getType().equals("2")){

								entity.setMainPanelTransferSupplierCount(a.getTypeCount());
							}else if(a.getType().equals("5")){

								entity.setWallFloorTransferSupplierCount(a.getTypeCount());
							}else if(a.getType().equals("6")){

								entity.setSandTransferSupplierCount(a.getTypeCount());
							}
						}
							
					}
				}
				

				

				List<PurchaseStatistics> receiveList = dao.findReceiveList(entity);
				
				if(CollectionUtils.isNotEmpty(receiveList)){
					for(PurchaseStatistics a:receiveList){
						if(StringUtils.isNotBlank(a.getType())){
							if(a.getType().equals("1")){

								entity.setAuxiliaryReceiveCount(a.getTypeCount());
							}else if(a.getType().equals("2")){

								entity.setMainPanelReceiveCount(a.getTypeCount());
							}else if(a.getType().equals("5")){

								entity.setWallFloorReceiveCount(a.getTypeCount());
							}else if(a.getType().equals("6")){

								entity.setSandReceiveCount(a.getTypeCount());
							}
						}
							
					}
				}
				

				
				

				List<PurchaseStatistics> standardApplyList = dao.findStandardApplyList(entity);
				
				if(CollectionUtils.isNotEmpty(standardApplyList)){
					for(PurchaseStatistics a:standardApplyList){
						if(StringUtils.isNotBlank(a.getType())){
							if(a.getType().equals("1")){

								entity.setStandardApplyCount(a.getTypeCount());
							}else if(a.getType().equals("2")){

								entity.setDownlightApplyCount(a.getTypeCount());
							}
						}
							
					}
				}
				

				

				List<PurchaseStatistics> standardReceiveList = dao.findStandardReceiveList(entity);
				
				if(CollectionUtils.isNotEmpty(standardReceiveList)){
					for(PurchaseStatistics a:standardReceiveList){
						if(StringUtils.isNotBlank(a.getType())){
							if(a.getType().equals("1")){

								entity.setStandardReceiveCount(a.getTypeCount());
							}else if(a.getType().equals("2")){

								entity.setDownlightReceiveCount(a.getTypeCount());
							}
						}
							
					}
				}
				

				
			}
		}
		
		return list;
	}
	



}