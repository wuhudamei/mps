/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizOrderExcel;
import cn.damei.dao.modules.BizOrderExcelDao;

/**
 * 材料文件上传Service
 * @author wyb
 * @version 2016-09-08
 */
@Service
@Transactional(readOnly = true)
public class BizOrderExcelService extends CrudService<BizOrderExcelDao, BizOrderExcel> {

	public BizOrderExcel get(String id) {
		return super.get(id);
	}
	
	public List<BizOrderExcel> findList(BizOrderExcel bizOrderExcel) {
		return super.findList(bizOrderExcel);
	}
	
	public Page<BizOrderExcel> findPage(Page<BizOrderExcel> page, BizOrderExcel bizOrderExcel) {
		return super.findPage(page, bizOrderExcel);
	}
	
	@Transactional(readOnly = false)
	public void save(BizOrderExcel bizOrderExcel) {
		super.save(bizOrderExcel);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizOrderExcel bizOrderExcel) {
		super.delete(bizOrderExcel);
	}
	
}