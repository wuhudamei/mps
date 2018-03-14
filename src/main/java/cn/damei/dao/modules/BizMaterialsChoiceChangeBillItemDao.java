/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsChoiceChangeBillItem;

/**
 * 选材变更单材料表DAO接口
 * @author wyb
 * @version 2017-06-14
 */
@MyBatisDao
public interface BizMaterialsChoiceChangeBillItemDao extends CrudDao2<BizMaterialsChoiceChangeBillItem> {

	/**
	 * 批量保存选材变更单材料
	 * @param list
	 * @return
	 */
	boolean insertMaterialsChoiceChangeBillItemList(List<BizMaterialsChoiceChangeBillItem> list);
	
}