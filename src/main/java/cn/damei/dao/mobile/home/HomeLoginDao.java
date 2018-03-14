package cn.damei.dao.mobile.home;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.home.CustomerVo;

import java.util.List;
import java.util.Map;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年10月24日 下午3:48:23 
* 质检登录系统首页
*/
@MyBatisDao
public interface HomeLoginDao  {
	
	/**
	 * 根据手机查询是否有该质检员用户
	 * @param vo
	 * @return
	 */
	 Integer selectRecordPhone(CustomerVo vo);

	/**
	 * 客户未读信息
	 * @param customerPhone 得到关联未读消息的id
	 * @return
	 */
	List<Map<String,String>> findUnReadReportCountByCustomerPhone(String customerPhone);
	 List<Integer> findProgressCountByCustomerPhone(String customerPhone);
	List<Integer> findProjectChangeCountByCustomerPhone(String customerPhone);
	List<Integer> findEvalCountByCustomerPhone(String customerPhone);
	List<Integer> findProjectProgressCountByCustomerPhone(String customerPhone);

	/**
	 * view_log数据过大, 分离function
	 * 参数: 关联id List ,businessType
	 *
	 */
	int commonViewLogCountByBusinessIntId(Map<String,Object> map);

}
