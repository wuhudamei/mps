/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.mobile.Manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.mobile.Manager.DictTypeDao;
import cn.damei.entity.mobile.Manager.DictType;

/**
 * 字典Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class DictTypeService extends CrudService<DictTypeDao, DictType> {

	@Autowired
	private DictTypeDao dictTypeDao;
	
	@Transactional(readOnly = true)
	public List<DictType> queryListByType(String key) {
		return dictTypeDao.queryListByType(key);
	}
	

}