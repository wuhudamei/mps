package cn.damei.dao.mobile.Inspector;

import java.util.List;
import java.util.Map;
import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.PqcOrderTaskPackage;
import cn.damei.entity.mobile.Inspector.PqcOrderTaskpackageVo;


@MyBatisDao
public interface PqcOrderTaskPackageDao  extends CrudDao2<PqcOrderTaskPackage>{
	

	public List<PqcOrderTaskPackage> queryTaskPackageByInspectorId(Map<String, Object> map);
	

	public PqcOrderTaskpackageVo queryTaskPackageRecheck(Integer id);
	

	public void updatePackageStateIdById(PqcOrderTaskPackage task);
	

	public PqcOrderTaskPackage querySmsMessageForManager(Integer id);
	
	public PqcOrderTaskPackage queryOrderTaskPackageByParam(Map<String,Object> param);
}