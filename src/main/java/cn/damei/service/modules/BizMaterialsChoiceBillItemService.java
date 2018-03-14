/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizMaterialsChoiceBillItem;
import cn.damei.entity.modules.BizMaterialsChoiceCategory;
import cn.damei.dao.modules.BizMaterialsChoiceBillItemDao;

/**
 * 选材单材料表Service
 * @author wyb
 * @version 2017-06-13
 */
@Service
@Transactional(readOnly = true)
public class BizMaterialsChoiceBillItemService extends CrudService2<BizMaterialsChoiceBillItemDao, BizMaterialsChoiceBillItem> {

	public BizMaterialsChoiceBillItem get(Integer id) {
		return super.get(id);
	}
	
	public List<BizMaterialsChoiceBillItem> findList(BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem) {
		return super.findList(bizMaterialsChoiceBillItem);
	}
	
	public Page<BizMaterialsChoiceBillItem> findPage(Page<BizMaterialsChoiceBillItem> page, BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem) {
		return super.findPage(page, bizMaterialsChoiceBillItem);
	}
	
	@Transactional(readOnly = false)
	public void save(BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem) {
		super.save(bizMaterialsChoiceBillItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem) {
		super.delete(bizMaterialsChoiceBillItem);
	}

	/**
	 * 选材类目明细查询
	 * @param page
	 * @param bizMaterialsChoiceBillItem
	 * @return
	 */
	public Page<BizMaterialsChoiceBillItem> findMaterialsPage(Page<BizMaterialsChoiceBillItem> page,
			BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem) {
		bizMaterialsChoiceBillItem.setPage(page);
		page.setList(dao.findMaterialsList(bizMaterialsChoiceBillItem));
		return page;
	}

	/**
	 * 查询一级商品类目
	 * @param bizMaterialsChoiceCategory
	 * @return
	 */
	public List<BizMaterialsChoiceCategory> findFirstMaterialsChoiceCategory(
			BizMaterialsChoiceCategory bizMaterialsChoiceCategory) {
		return dao.findFirstMaterialsChoiceCategory(bizMaterialsChoiceCategory);
	}
	
	/**
	 * 查询二级商品类目
	 * @param bizMaterialsChoiceCategory
	 * @return
	 */
	public List<BizMaterialsChoiceCategory> findSecondMaterialsChoiceCategory(
			BizMaterialsChoiceCategory bizMaterialsChoiceCategory) {
		return dao.findSecondMaterialsChoiceCategory(bizMaterialsChoiceCategory);
	}
	
}