package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderTaskpackage;
import cn.damei.entity.modules.TaskpackageProceduces;
import cn.damei.entity.modules.UrgeCheck;

@MyBatisDao
public interface BizOrderTaskpackageDao extends CrudDao2<BizOrderTaskpackage>{

	List<BizOrderTaskpackage> findList1(BizOrderTaskpackage bizOrderTaskpackage);

	List<BizOrderTaskpackage> findList2(BizOrderTaskpackage bizOrderTaskpackage);

	List<BizOrderTaskpackage> findList3(BizOrderTaskpackage bizOrderTaskpackage);

	List<BizOrderTaskpackage> findList4(BizOrderTaskpackage bizOrderTaskpackage);

	List<UrgeCheck> findList5(UrgeCheck urgeCheck);

	List<BizOrderTaskpackage> findList6(BizOrderTaskpackage bizOrderTaskpackage);

	List<TaskpackageProceduces> queryProceduresByPackageId(Integer id);

	List<BizOrderTaskpackage> findList7(BizOrderTaskpackage bizOrderTaskpackage);

}
