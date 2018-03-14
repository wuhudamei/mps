/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialSelfbuy;

/**
 * 材料自采表DAO接口
 * @author wyb
 * @version 2017-06-10
 */
@MyBatisDao
public interface BizMaterialSelfbuyDao extends CrudDao2<BizMaterialSelfbuy> {

	/**
	 * 门店+工程模式+自采材料名称 = 自采材料清单
	 * @param bizMaterialSelfbuy
	 * @return
	 */
	List<BizMaterialSelfbuy> findMaterialList(BizMaterialSelfbuy bizMaterialSelfbuy);

	/**
	 * 根据门店和工程模式  动态加载自选材料列表
	 * @param bizMaterialSelfbuy
	 * @return
	 */
	List<BizMaterialSelfbuy> findMaterialSelfbuyListAjax(BizMaterialSelfbuy bizMaterialSelfbuy);
	
}