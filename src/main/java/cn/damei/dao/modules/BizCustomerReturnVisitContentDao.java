/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCustomerReturnVisitContent;

/**
 * 客户回访节点内容DAO接口
 * @author LiWancai
 * @version 2017-05-23
 */
@MyBatisDao
public interface BizCustomerReturnVisitContentDao extends CrudDao<BizCustomerReturnVisitContent> {
	
	void insertBatch(List<BizCustomerReturnVisitContent> list);
	
	List<BizCustomerReturnVisitContent> getByReturnVisitId(String returnVisitId);
	
	List<BizCustomerReturnVisitContent> getByProjectNode(@Param("projectNode")String projectNode,@Param("storeId")String storeId,@Param("projectMode")String projectMode );
	
	void deleteByReturnVisitId(String returnVisitId);
}