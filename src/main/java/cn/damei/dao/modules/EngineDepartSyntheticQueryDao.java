package cn.damei.dao.modules;
import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.EngineDepartEntity;
import cn.damei.entity.modules.EngineDepartSyntheticQueryEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by joseph on 2017/4/7.
 */
@MyBatisDao
public interface EngineDepartSyntheticQueryDao  extends CrudDao<EngineDepartSyntheticQueryEntity>{



    List<EngineDepartSyntheticQueryEntity> selectEngineDepartSyntheticList(EngineDepartSyntheticQueryEntity entity);


List<EngineDepartSyntheticQueryEntity>pqcSyntheticQuery(EngineDepartSyntheticQueryEntity entity);

List<Map<String,Object>> pqcSyntheticDataInfo(Map<String,Object> map);
List<Map<String,Object>> pqcSyntheticDataInfoForSameDay(Map<String,Object> map);


















  List<Map<String,Object>> findEngineByStoreId(String storeId);



  List<Map<String,Object>> findOrderCountByEngine (Map<String,Object> map);
  List<Map<String,Object>> findDiscloseCountByEngine (Map<String,Object> map);
  List<Map<String,Object>> findStartCountByEngine (Map<String,Object> map);
  List<Map<String,Object>> findDistributeCountByEngine (Map<String,Object> map);
















  /**
   * 2017-08-17  统计报表 需求
   */


  /**
   * 根据门店和模式查询大区下的经理星级
   * @param storeId
   * @param projectMode
   * @return
   */
  List<EngineDepartEntity> findManagerStarGroupByEngineDepartByStoreIdAndProjectMode(String storeId,String projectMode);

  List<Integer> getDictListByTypeOrderByValue(String dicType);



  /**
   * 2017-08-17  统计报表 需求
   */


  /**
   * 根据门店和模式查询大区下的工人类型和数量
   * @param storeId
   * @param projectMode
   * @return
   */
  List<EngineDepartEntity> findWorkerTypeGroupByEngineDepartByStoreIdAndProjectMode(String storeId,String projectMode);




  List<String> findWorkerTypeList();

















  /**
   * 根据门店和模式 和时间  查询质检数量统计
   * @param storeId
   * @param projectMode
   * @return
   */


  @SuppressWarnings("JavadocReference")
  Integer qcBillReportCount(Map<String,Object> map);
  Integer managerApplyCheckQcBillCount(Map<String,Object> map);
  Integer qcSignCount(Map<String,Object> map);
  Integer qcApplyCheckCount(Map<String,Object> map);
  Integer qcBillDoneCount(Map<String,Object> map);
  Integer qcIssueReportCount(Map<String,Object> map);
  Integer qcRecheckCount(Map<String,Object> map);
  Map<String,Object> fineOrderCountAndTotalMoney(Map<String,Object> map);



}
