
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ComplaintForOtherDepartMent;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface ComplaintForOtherDepartMentDao extends CrudDao<ComplaintForOtherDepartMent> {




    List<Map<String,String>> findOrderInfoByText(Map<String,String> map);


     Integer insert(Map<String,Object> map);



     Integer saveProblemInfo(Map<String,Object> map);



     Map<String,Object> findDetailById(String preComplaintId);


}