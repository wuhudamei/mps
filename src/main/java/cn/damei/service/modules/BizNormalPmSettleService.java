/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizNormalPmSettle;
import cn.damei.dao.modules.BizNormalPmSettleDao;

/**
 * 经理申请结算单Service
 * @author 梅浩
 * @version 2017-04-17
 */
@Service
@Transactional(readOnly = true)
public class BizNormalPmSettleService extends CrudService<BizNormalPmSettleDao, BizNormalPmSettle> {

	public BizNormalPmSettle get(String id) {
		return super.get(id);
	}
	
	public List<BizNormalPmSettle> findList(BizNormalPmSettle bizNormalPmSettle) {
		return super.findList(bizNormalPmSettle);
	}
	
	public Page<BizNormalPmSettle> findPage(Page<BizNormalPmSettle> page, BizNormalPmSettle bizNormalPmSettle) {
		return super.findPage(page, bizNormalPmSettle);
	}
	
	@Transactional(readOnly = false)
	public void save(BizNormalPmSettle bizNormalPmSettle) {
		super.save(bizNormalPmSettle);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizNormalPmSettle bizNormalPmSettle) {
		super.delete(bizNormalPmSettle);
	}


	/**
	 *申请结算图片
	 * @param settleId
	 * @param picType 666
	 * @return
	 */
	public List<String> findSettlePicBySettleId(Integer settleId, String picType){

		return dao.findSettlePicBySettleId(settleId,picType);
	}

	/**
	 * 根据门店查询结算节点
	 * @param storeId
	 * @return
	 */
	public List<String> findSettleNodeByStoreId(Integer storeId){


		return dao.findSettleNodeByStoreId(storeId);
	}

	public String checkSettleIsExit(Integer settleId){


		return dao.checkSettleIsExist(settleId);
	}
}