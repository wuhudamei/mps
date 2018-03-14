/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ComplaintForOtherDepartMent;

import java.util.List;
import java.util.Map;

/**
 * 其他部门投诉DAO接口
 * @author mh
 * @version 2017-07-24
 */
@MyBatisDao
public interface ComplaintForOtherDepartMentDao extends CrudDao<ComplaintForOtherDepartMent> {




    List<Map<String,String>> findOrderInfoByText(Map<String,String> map);


     Integer insert(Map<String,Object> map);



     Integer saveProblemInfo(Map<String,Object> map);



     Map<String,Object> findDetailById(String preComplaintId);


}