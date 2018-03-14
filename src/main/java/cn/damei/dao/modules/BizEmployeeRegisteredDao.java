package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEmployeeRegistered;

import java.util.List;


@MyBatisDao
public interface BizEmployeeRegisteredDao extends CrudDao2<BizEmployeeRegistered> {


	List<BizEmployeeRegistered> queryExportExcel(BizEmployeeRegistered bizEmployeeRegistered);
}