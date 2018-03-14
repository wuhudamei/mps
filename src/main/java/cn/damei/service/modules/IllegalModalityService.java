/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.IllegalModalityDao;
import cn.damei.entity.modules.IllegalModality;

/**
 * PC违规形式Service
 * @author 梅浩
 * @version 2016-10-26
 */
@Service
@Transactional(readOnly = true)
public class IllegalModalityService extends CrudService2<IllegalModalityDao, IllegalModality> {

	public IllegalModality get(Integer id) {
		return super.get(id);
	}
	
	public List<IllegalModality> findList(IllegalModality illegalModality) {
		return super.findList(illegalModality);
	}
	
	public Page<IllegalModality> findPage(Page<IllegalModality> page, IllegalModality illegalModality) {
		return super.findPage(page, illegalModality);
	}
	
	@Transactional(readOnly = false)
	public void save(IllegalModality illegalModality) {
		super.save(illegalModality);
	}
	
	@Transactional(readOnly = false)
	public void delete(IllegalModality illegalModality) {
		super.delete(illegalModality);
	}
	
	
	
	/**
	 * 根据 违规形式id,查询门店,检查项,检查分类信息
	 * @param id
	 * @return
	 */
	public IllegalModality getStoreKindItemInfoByIllegalModalityId(Integer id){
		
		return dao.getStoreKindItemInfoByIllegalModalityId(id);
	}
	
	
}