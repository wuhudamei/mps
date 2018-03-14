package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.AppOrder;
import cn.damei.entity.mobile.Manager.OrderSignVo;
import cn.damei.entity.mobile.Manager.SignDetail;
import cn.damei.entity.modules.BizPmAttendCnfg;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface SignDao extends CrudDao2<OrderSignVo> {


	public  List<OrderSignVo> orderByManagerId(Integer itemManagerId);



	public SignDetail getSignDetailByOrderId(Integer orderId);


	public List<SignDetail>  getSignDetailByOrderIdLimit(Integer orderId, Integer id);



	public void  signSuccess(SignDetail sign);


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
