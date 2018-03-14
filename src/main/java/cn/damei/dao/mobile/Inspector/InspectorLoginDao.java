package cn.damei.dao.mobile.Inspector;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.Inspector;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年10月24日 下午3:48:23 
* 质检登录系统首页
*/
@MyBatisDao
public interface InspectorLoginDao   extends CrudDao2<Inspector> {
	
	/**
	 * 根据手机查询是否有该质检员用户
	 * @param phone
	 * @return
	 */
	Inspector selectInspectorByPhone(String phone);
	
	/**
	 * 根据质检员id查询 订单总数和 正在施工数
	 * @param itemManagerId
	 * @return
	 */
	public int findCount(Integer inspectorId);

	public int findBuildingCount(Integer inspectorId);
	public Integer	findInspectReport(Integer inspectorId);

	/**
	 * 根据质检员ID和当前时间 查询质检未评价数
	 * @param inspector
	 * @return
	 */
	Integer findEvalCount(Inspector inspector);
	Integer findView(Inspector inspector);
	public Integer isLeader(Integer id);

	Inspector selectInspectorByPhone1(String username, String string);
}
