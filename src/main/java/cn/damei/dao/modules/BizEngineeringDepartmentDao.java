/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEngineeringDepartment;

/**
 * 工程部管理DAO接口
 * @author haven
 * @version 2016-09-05
 */
@MyBatisDao
public interface BizEngineeringDepartmentDao extends CrudDao<BizEngineeringDepartment> {
    
    public List<
    
    DropModel> findEngDepList();
    public List<DropModel> findEngDepListWithUserConditions(List<Integer> ids);
    /**
     * 加入产业模式, 所以过滤不是产业的工程模式
     * @return
     */
    public List<DropModel> findEngDepListOnlyIndustry();
    public List<BizEngineeringDepartment> findByStoreId(String storeId);
	public List<DropModel> findEngDepListByStoreId(String storeId);
	public List<DropModel> findEngDepListByStoreId1();
	public List<DropModel> findAllEngDepList();
	/**
	 * 根据门店id 工程模式  当前登录人 查询工程部列表
	 * @param map
	 * @return
	 */
	public List<DropModel> findEngDepListByMap(Map<String, Object> map);
	public List<Integer> findAll();
	public List<Integer> findByEmployeeId(Integer employeeId);


	/**
	 * 查询门店,模式下的验收节点
	 */
	public List<DropModel> findCheckNodeByStoreIdAndProjectModel(String storeId,String projectModel);

}