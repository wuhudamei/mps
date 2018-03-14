package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.Seiralnum;


@MyBatisDao
public interface SeiralnumDao extends CrudDao<Seiralnum> {

	Seiralnum get();

	void insertBySeiralnum(Seiralnum sn);

	void updateById(String seiralNum, String updateDateU,String id);

	Seiralnum getById();

}
