/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ApplyMaterialSelfbuyReimburseStatusLog;
import cn.damei.entity.modules.BizMaterialSelfbuyReimburse;

/**
 * 材料自采报销单DAO接口
 * @author wyb
 * @version 2017-06-22
 */
@MyBatisDao
public interface BizMaterialSelfbuyReimburseDao extends CrudDao2<BizMaterialSelfbuyReimburse> {

	/**
	 * 删除自采材料报销
	 * @param materialSelfbuyReimburseId
	 */
	void deleteMaterialSelfbuyReimburse(Integer materialSelfbuyReimburseId);

	/**
	 * 更新初次申请的自采材料报销
	 * @param bizMaterialSelfbuyReimburse
	 * @return
	 */
	boolean updateMaterialSelfbuyReimburse(BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse);

	/**
	 * 根据本次自采材料id查询详情
	 * @param materialId
	 * @return
	 */
	BizMaterialSelfbuyReimburse findMaterialAndOrderByMaterialId(Integer materialId);

	/**
	 * 自采材料报销详情
	 * @param materialId
	 * @return
	 */
	List<BizMaterialSelfbuyReimburse> findMaterialSelfbuyReimburseDetails(Integer materialId);

	/**
	 * 自采材料报销 状态 详情
	 * @param applyMaterialSelfbuyReimburseStatusLog
	 * @return
	 */
	List<ApplyMaterialSelfbuyReimburseStatusLog> findMaterialStatusLogDetails(
			ApplyMaterialSelfbuyReimburseStatusLog applyMaterialSelfbuyReimburseStatusLog);
	
}