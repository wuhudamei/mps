package cn.damei.dao.mobile.Inspector;

import java.util.List;
import java.util.Map;
import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.PqcOrderTaskpackageProcedure;


@MyBatisDao
public interface PqcOrderTaskpackageProcedureDao  extends CrudDao2<PqcOrderTaskpackageProcedure>{
	

	public List<PqcOrderTaskpackageProcedure> queryBizOrderTaskpackageProcedure(Map<String, Object> map);
}