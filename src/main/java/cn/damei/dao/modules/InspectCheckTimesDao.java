package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.InspectCheckTimesEntity;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface InspectCheckTimesDao  extends CrudDao<InspectCheckTimesEntity>{



    List<InspectCheckTimesEntity> findPqcInfoByStoreIdAndProjectMode(InspectCheckTimesEntity entity);





    List<InspectCheckTimesEntity>findSignInfoByEmpId(Map<String,Object> map);



    List<InspectCheckTimesEntity> findAllPqcBillByEmpId(Map<String,Object> map);



    List<InspectCheckTimesEntity> findPqcItemInfoByBillId(List<InspectCheckTimesEntity> list);



    List<InspectCheckTimesEntity>findCheckTimesByPqcId(Map<String,Object> map);

}
