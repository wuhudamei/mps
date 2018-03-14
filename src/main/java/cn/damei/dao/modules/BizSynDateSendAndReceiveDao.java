package cn.damei.dao.modules;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizSynDataCnfg;

@MyBatisDao
public interface BizSynDateSendAndReceiveDao {

	public void saveJSONDate(String string, String businessType, Date date);
	
	public void saveData(Map map);
	public void updateData(Map map);
	
	public List<BizSynDataCnfg> findSendJsonDate();
	
	public void updateBizSynDate(String string2, Date date, String string22);

	public void updateBizSynDateStatus(String id);

	public String findBizSynDateConf(String businessType);

	public List<String> findJson();

	public void saveCustomerServiceDataByMap(Map map);

	public int selectCountBizSynDataByTypeAndMarkInt(String businessType,
			String businessOnlyMarkInt);

	public void updateCustomerServiceDataByMap(Map m);
	/**
	 * 查询同步表里是否已经有数据
	 * @param businessType
	 * @param businessOnlyMarkInt
	 * @return
	 */
	public int selectCountSynByTypeAndMarkInt(String businessType,
			String businessOnlyMarkInt);
	/**
	 * 查询problem表里是否已经有数据
	 * @param workOrderCode
	 * @return
	 */
	public int selectCountProblemByTypeAndMarkInt(String workOrderCode);
	/**
	 * 查询biz_cus_service_liable_type表里是否已经有数据
	 * @param maplLiableType
	 * @return
	 */
	public int selectliableTypeCountByMap(String liableTypeCode);

	public void insertLiableTypeByMap(Map<String, Object> maplLiableType);
	
	public void updateLiableTypeByMap(Map<String, Object> maplLiableType);
}
