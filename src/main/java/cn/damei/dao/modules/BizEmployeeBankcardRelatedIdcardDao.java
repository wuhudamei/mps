/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEmployeeBankcardRelatedIdcard;

/**
 * 员工身份证关联DAO接口
 * @author qww
 * @version 2016-10-31
 */
@MyBatisDao
public interface BizEmployeeBankcardRelatedIdcardDao extends CrudDao2<BizEmployeeBankcardRelatedIdcard> {
	
	/**
	 * 根据员工id查询关联身份证
	 * @param empId
	 * @return
	 */
	public List<BizEmployeeBankcardRelatedIdcard> queryEmployeeBankcardRelatedIdcardByEmpId(Integer empId);
}