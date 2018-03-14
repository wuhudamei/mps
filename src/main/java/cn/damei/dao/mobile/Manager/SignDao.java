package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.AppOrder;
import cn.damei.entity.mobile.Manager.OrderSignVo;
import cn.damei.entity.mobile.Manager.SignDetail;
import cn.damei.entity.modules.BizPmAttendCnfg;

import java.util.List;
import java.util.Map;

/**
 * @author 梅浩   meihao@zzhyun.cn:
 * @version 创建时间：2016年9月22日 下午6:31:10
 * 项目经理签到dao
 */
@MyBatisDao
public interface SignDao extends CrudDao2<OrderSignVo> {

	/**
	 * 根据项目经理查询订单
	 * @param itemManagerId
	 * @return
	 */
	public  List<OrderSignVo> orderByManagerId(Integer itemManagerId);


	/**
	 * 根据相关订单id 查询签到详情
	 * @param orderId
	 * @return
	 */
	public SignDetail getSignDetailByOrderId(Integer orderId);

	/**
	 * 根据相关订单id 查询签到详情 limit 1
	 * @param orderId
	 * @param id
     * @return
	 */
	public List<SignDetail>  getSignDetailByOrderIdLimit(Integer orderId, Integer id);


	/**
	 * 成功签到,保存数据到签到表
	 */
	public void  signSuccess(SignDetail sign);

	/**
	 * 根据订单id查询顾客信息: 地址,小区名,姓名,电话
	 * @param orderId
	 * @return
	 */
	public AppOrder getCustomerInfoByOrderId(Integer orderId);
	public  String getCheckTimeByOrderIdAndNode(Integer orderId, String nodeOrder);
	public BizPmAttendCnfg getCnfgByStoreIdAndEffectMonth(String effectMonth, String storeId);
	public String getCheckDatetimeByOrderId(Integer orderId);
	public String getIsValidByOrderIdAndManagerIdAndSignDate(Map map);
	public void insertDayOrderBySignDetail(SignDetail signDetail);
	public void updateDayOrderBySignDetail(SignDetail signDetail);
	public int getAlreadySignTimesByMap(Map map);


	public BizPmAttendCnfg getMaxMonthCnfgByStoreIdAndEffectMonth(String effectMonth, String storeId);


	public boolean isSignSuccess(SignDetail signDetail);

	List<String> findDistributeLogByOrderId(Integer orderId);
}
