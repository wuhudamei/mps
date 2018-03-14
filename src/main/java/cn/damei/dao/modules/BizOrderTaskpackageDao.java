package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderTaskpackage;
import cn.damei.entity.modules.TaskpackageProceduces;
import cn.damei.entity.modules.UrgeCheck;

@MyBatisDao
public interface BizOrderTaskpackageDao extends CrudDao2<BizOrderTaskpackage>{
	//待分配  已分配  已完工
	List<BizOrderTaskpackage> findList1(BizOrderTaskpackage bizOrderTaskpackage);
	//未完工
	List<BizOrderTaskpackage> findList2(BizOrderTaskpackage bizOrderTaskpackage);
	//工人未签到
	List<BizOrderTaskpackage> findList3(BizOrderTaskpackage bizOrderTaskpackage);
	//完工未验收
	List<BizOrderTaskpackage> findList4(BizOrderTaskpackage bizOrderTaskpackage);
	//催促验收
	List<UrgeCheck> findList5(UrgeCheck urgeCheck);
	//超定额复检查询
	List<BizOrderTaskpackage> findList6(BizOrderTaskpackage bizOrderTaskpackage);
	//根据任务包id查询工序
	List<TaskpackageProceduces> queryProceduresByPackageId(Integer id);
	//派工记录查询
	List<BizOrderTaskpackage> findList7(BizOrderTaskpackage bizOrderTaskpackage);

}
