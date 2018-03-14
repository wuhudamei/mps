package cn.damei.service.mobile.Manager;

import cn.damei.common.service.CrudService2;
import cn.damei.entity.mobile.Manager.AppOrder;
import cn.damei.dao.mobile.Manager.SignDao;
import cn.damei.entity.mobile.Manager.OrderSignVo;
import cn.damei.entity.mobile.Manager.SignDetail;
import cn.damei.entity.modules.BizPmAttendCnfg;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author 梅浩   meihao@zzhyun.cn:
 * @version 创建时间：2016年9月22日 下午7:32:55
 * 类说明
 */
@Service
@Transactional(readOnly=true)
public class SignService extends CrudService2<SignDao,OrderSignVo> {

	/**
	 * 根据项目经理查询订单
	 * @param itemManagerId
	 * @return
	 */
	public  List<OrderSignVo> orderByManagerId(Integer itemManagerId){


		return dao.orderByManagerId(itemManagerId);
	}


	/**
	 * 根据相关订单id 查询签到详情
	 * @param orderId
	 * @return
	 */
	public SignDetail getSignDetailByOrderId(Integer orderId){

		return dao.getSignDetailByOrderId(orderId);
	}
	/**
	 * 根据相关订单id 查询签到详情 limit 1
	 * @param orderId
	 * @param id
     * @return
	 */
	public List<SignDetail>   getSignDetailByOrderIdLimit(Integer orderId, Integer id){
		return dao.getSignDetailByOrderIdLimit(orderId,id);
	}


	/**
	 * 成功签到,保存数据到签到表
	 */
	@Transactional(readOnly=false)
	public void  signSuccess(SignDetail sign){

		dao.signSuccess(sign);
	}

	/**
	 * 根据订单id查询顾客信息: 地址,小区名,姓名,电话
	 * @param orderId
	 * @return
	 */
	public AppOrder getCustomerInfoByOrderId(Integer orderId){

		return dao.getCustomerInfoByOrderId(orderId);
	}
	/**
	 * 根据订单id和节点顺序（6或9）查出基装验收日期或竣工验收日期
	 * @param orderId
	 * @param nodeOrder
	 * @return
	 */
	public  String getCheckTimeByOrderIdAndNode(Integer orderId,String nodeOrder){

		return dao.getCheckTimeByOrderIdAndNode(orderId, nodeOrder);
	}
	/**
	 * 通过门店Id和生效月份得到基装签到周期天数和主材签到周期天数，返回实体
	 * @param effectMonth
	 * @param storeId
	 * @return
	 */
	public BizPmAttendCnfg getCnfgByStoreIdAndEffectMonth(String effectMonth, String storeId){

		return dao.getCnfgByStoreIdAndEffectMonth(effectMonth, storeId);
	}
	/**
	 * 通过订单Id查出基装验收的时间
	 * @param orderId
	 * @return
	 */
	public String getCheckDatetimeByOrderId(Integer orderId){
		return dao.getCheckDatetimeByOrderId(orderId);
	}
	public String getIsValidByOrderIdAndManagerIdAndSignDate(Map map){

		return  dao.getIsValidByOrderIdAndManagerIdAndSignDate(map);
	}

	@Transactional(readOnly=false)
	public void insertDayOrderBySignDetail(SignDetail signDetail){
		dao.insertDayOrderBySignDetail(signDetail);
	}
	@Transactional(readOnly=false)
	public void updateDayOrderBySignDetail(SignDetail signDetail){
		dao.updateDayOrderBySignDetail(signDetail);
	}
	public int getAlreadySignTimesByMap(Map map){
		return dao.getAlreadySignTimesByMap(map);
	}


	public BizPmAttendCnfg getMaxMonthCnfgByStoreIdAndEffectMonth(
			String effectMonth, String storeId) {
		return dao.getMaxMonthCnfgByStoreIdAndEffectMonth(effectMonth,  storeId);
	}

	public List<String> findDistributeLogByOrderId(Integer orderId) {
		return dao.findDistributeLogByOrderId(orderId);
	}
}
