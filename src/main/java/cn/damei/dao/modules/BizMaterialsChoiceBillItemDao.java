/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsChoiceBillItem;
import cn.damei.entity.modules.BizMaterialsChoiceCategory;

/**
 * 选材单材料表DAO接口
 * @author wyb
 * @version 2017-06-13
 */
@MyBatisDao
public interface BizMaterialsChoiceBillItemDao extends CrudDao2<BizMaterialsChoiceBillItem> {

	/**
	 * 删除选材单材料
	 * @param id
	 */
	void deleteMaterialsChoiceBillItem(Integer id);

	/**
	 * 批量保存材料单材料
	 * @param list
	 * @return
	 */
	boolean insertMaterialsChoiceBillItemList(List<BizMaterialsChoiceBillItem> list);

	/**
	 * 选材类目明细查询
	 * @param bizMaterialsChoiceBillItem
	 * @return
	 */
	List<BizMaterialsChoiceBillItem> findMaterialsList(BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem);

	/**
	 * 查询一级商品类目
	 * @param bizMaterialsChoiceCategory
	 * @return
	 */
	List<BizMaterialsChoiceCategory> findFirstMaterialsChoiceCategory(BizMaterialsChoiceCategory bizMaterialsChoiceCategory);

	
	/**
	 * 查询二级商品类目
	 * @param bizMaterialsChoiceCategory
	 * @return
	 */
	List<BizMaterialsChoiceCategory> findSecondMaterialsChoiceCategory(BizMaterialsChoiceCategory bizMaterialsChoiceCategory);

	
}