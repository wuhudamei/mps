/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderInstallPlanAdvanceApply;

/**
 * 主材安装项提前申请记录DAO接口
 * @author wyb
 */
@MyBatisDao
public interface BizOrderInstallPlanAdvanceApplyDao extends CrudDao2<BizOrderInstallPlanAdvanceApply> {

	/**
	 * 查询是否已经提交过该主材安装项的提前申请(安装/复尺)
	 * @param bizOrderInstallPlanAdvanceApply
	 * @return
	 */
	BizOrderInstallPlanAdvanceApply findInstallPlanAdvanceApplyLastRecord(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply);

	/**
	 * 查询该安装项【提前申请】的次数
	 * @param bizOrderInstallPlanAdvanceApply
	 * @return
	 */
	Integer findInstallPlanAdvanceApplyCount(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply);

	/**
	 * 生成新的【订单安装项】【安装】
	 * @param resultMap
	 */
	void saveOrderInstallItemForInstallAdvanceApply(Map<String, Object> resultMap);

	/**
	 * 生成新的【订单安装项计划】【安装】
	 * @param resultMap
	 */
	void saveOrderInstallPlanForInstallAdvanceApply(Map<String, Object> resultMap);
	/**
	 * 生成新的【订单安装项】【复尺】
	 * @param resultMap
	 */
	void saveOrderInstallItemForChecksizeAdvanceApply(Map<String, Object> resultMap);
	
	/**
	 * 生成新的【订单安装项计划】【复尺】
	 * @param resultMap
	 */
	void saveOrderInstallPlanForChecksizeAdvanceApply(Map<String, Object> resultMap);
	
}