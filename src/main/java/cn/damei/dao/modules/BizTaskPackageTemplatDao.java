/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.BizTaskPackageTemplat;

/**
 * 单表生成DAO接口
 * @author ThinkGem
 * @version 2016-09-03
 */
@MyBatisDao
public interface BizTaskPackageTemplatDao extends CrudDao<BizTaskPackageTemplat> {
	public List<BizTaskPackageTemplat>findTaskPackageList(String storeId);

	public BizTaskPackageTemplat getByprocedureNo(String procedureNo);
	
	public List<BizTaskPackageTemplat> queryTaskpackageTemplat();

	public BizTaskPackageTemplat getByNo(String no);

	public List<BizTaskPackageTemplat> getByNo1(String no);
	
	//查询门店下的任务包模板--首款不为100
	public List<BizTaskPackageTemplat> queryByStoreId(Integer storeid,Integer projectMode);

	public List<DropModel> findtaskpackageByStroeId(Integer storeid ,String status);

	public List<DropModel> findtaskpackageByStroeId1(String status);

	public List<BizTaskPackageTemplat> getTaskList(BizEmployee bizEmployee);

	/**
	 * 校验-门店+工程模式+工种的唯一性
	 * @param bizTaskPackageTemplat
	 * @return
	 */
	public Integer checkEmpWorkType(BizTaskPackageTemplat bizTaskPackageTemplat);
}