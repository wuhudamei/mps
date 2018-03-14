
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEmployeeBankcardRelatedIdcard;


@MyBatisDao
public interface BizEmployeeBankcardRelatedIdcardDao extends CrudDao2<BizEmployeeBankcardRelatedIdcard> {
	

	public List<BizEmployeeBankcardRelatedIdcard> queryEmployeeBankcardRelatedIdcardByEmpId(Integer empId);
}