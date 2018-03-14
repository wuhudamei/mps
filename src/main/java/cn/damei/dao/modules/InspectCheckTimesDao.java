package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.InspectCheckTimesEntity;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface InspectCheckTimesDao  extends CrudDao<InspectCheckTimesEntity>{


    /**
     * 根据门店 模式(或者输入了质检员的姓名)
     * @param  entity
     * @return
     */
    List<InspectCheckTimesEntity> findPqcInfoByStoreIdAndProjectMode(InspectCheckTimesEntity entity);



    /**
     * 根据质检员id 集合 和一个开始时间和结束时间查询所有的签到信息
     * @param map 一个是质检员id List   (key:list), 一个是startDate,endDate(key)
     * @return
     */

    List<InspectCheckTimesEntity>findSignInfoByEmpId(Map<String,Object> map);


    /**
     *根据质检员id  查询所有的质检单集合
     * @param map 一个是质检员id List   (key:list), 一个是startDate,endDate(key)
     * @return
     */
    List<InspectCheckTimesEntity> findAllPqcBillByEmpId(Map<String,Object> map);


    /**
     * 根据质检单集合 list  查询罚款信息
     * @param list 质检单id list
     * @return
     */
    List<InspectCheckTimesEntity> findPqcItemInfoByBillId(List<InspectCheckTimesEntity> list);


    /**
     * 查询检查工地次数
     * @param map 一个是质检员id List   (key:list), 一个是startDate,endDate(key)
     * @return
     */
    List<InspectCheckTimesEntity>findCheckTimesByPqcId(Map<String,Object> map);

}
