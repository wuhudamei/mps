package cn.damei.dao.modules;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizStarBasicSalary;

@MyBatisDao
public interface BizStarBasicSalaryDao extends CrudDao2<BizStarBasicSalary> {
	//获取最大版本号
	public String getMaxVersion();
	
	/**
	 * 获取对应的底薪
	 * @param storeId
	 * @param projectMode
	 * @param starLevel
	 * @return
	 */
	public Double getSalary(@Param("attendMonth")String attendMonth,@Param("storeId")Integer storeId, @Param("projectMode")String projectMode, @Param("starLevel")Integer starLevel);

}
