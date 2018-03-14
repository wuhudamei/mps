/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizMaterialsStandard;

/**
 * 标化辅材DAO接口
 * @author 汪文文
 * @version 2016-12-24
 */
@MyBatisDao
public interface BizMaterialsStandardDao extends CrudDao2<BizMaterialsStandard> {

	List<DropModel> findMaterialsByStroeId(String storeId);

	List<BizMaterialsStandard> queryMaterialsByStoreId(String storeId);
	
}