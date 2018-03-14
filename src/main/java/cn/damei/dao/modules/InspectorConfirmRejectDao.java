/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;


import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.InspectorConfirmReject;

/**
 * 约检验收驳回原因查询DAO接口
 * @author wyb
 */
@MyBatisDao
public interface InspectorConfirmRejectDao extends CrudDao2<InspectorConfirmReject> {

	
}