package cn.damei.dao.mobile.Manager;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.BizOrderTaskpackageProcedure;


@MyBatisDao
public interface BizOrderTaskpackageProcedureDao  extends CrudDao2<BizOrderTaskpackageProcedure>{
	

	public List<BizOrderTaskpackageProcedure> queryOrderTaskpackageProcedure(Map<String, Object> map);

	public List<BizOrderTaskpackageProcedure> queryOrderTaskpackageProcedure1(Map<String, Object> map);
}