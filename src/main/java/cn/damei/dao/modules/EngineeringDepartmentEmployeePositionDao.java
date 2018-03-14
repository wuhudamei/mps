package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.EngineeringDepartmentEmployeePosition;

@MyBatisDao
public interface EngineeringDepartmentEmployeePositionDao extends CrudDao2<EngineeringDepartmentEmployeePosition>{

	void insertList(List<EngineeringDepartmentEmployeePosition> positions);

	void deleteByDepartmentId(Integer departmentId);

	void delete(String id);

}
