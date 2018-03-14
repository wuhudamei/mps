package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEmployeeRegistered;

import java.util.List;

/**
 * 在册人员DAO接口
 * @author wyb
 */
@MyBatisDao
public interface BizEmployeeRegisteredDao extends CrudDao2<BizEmployeeRegistered> {

	/**
	 * 查询在册人员导出
	 * @param bizEmployeeRegistered
	 * @return
	 */
	List<BizEmployeeRegistered> queryExportExcel(BizEmployeeRegistered bizEmployeeRegistered);
}