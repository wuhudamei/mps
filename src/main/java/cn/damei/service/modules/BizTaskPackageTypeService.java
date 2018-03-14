
package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.UserDao;
import cn.damei.entity.modules.User;
import cn.damei.dao.modules.BizTaskPackageTypeDao;
import cn.damei.entity.modules.BizTaskPackageType;


@Service
@Transactional(readOnly = true)
public class BizTaskPackageTypeService extends CrudService<BizTaskPackageTypeDao, BizTaskPackageType> {
	
	@Autowired
	UserDao userDao;	
	
	public BizTaskPackageType get(String id) {
		return super.get(id);
	}
	
	public List<BizTaskPackageType> findList(BizTaskPackageType bizTaskPackageType) {
		return super.findList(bizTaskPackageType);
	}
	
	public Page<BizTaskPackageType> findPage(Page<BizTaskPackageType> page, BizTaskPackageType bizTaskPackageType) {
		Page<BizTaskPackageType> result =  super.findPage(page, bizTaskPackageType);
		for (BizTaskPackageType tempType : result.getList()) {
			User u = userDao.get(tempType.getUpdateBy().getId());
			tempType.setUpdateBy(u);
		}
		return result;
	}
	
	@Transactional(readOnly = false)
	public void save(BizTaskPackageType bizTaskPackageType) {
		super.save(bizTaskPackageType);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizTaskPackageType bizTaskPackageType) {
		super.delete(bizTaskPackageType);
	}
	
	public String findTypeByTaskpackageId(Integer taskpackageId){
		return dao.findTypeByTaskpackageId(taskpackageId);
	}
}