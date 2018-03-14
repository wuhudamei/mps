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


@Service
@Transactional(readOnly=true)
public class SignService extends CrudService2<SignDao,OrderSignVo> {


	public  List<OrderSignVo> orderByManagerId(Integer itemManagerId){


		return dao.orderByManagerId(itemManagerId);
	}



	public SignDetail getSignDetailByOrderId(Integer orderId){

		return dao.getSignDetailByOrderId(orderId);
	}

	public List<SignDetail>   getSignDetailByOrderIdLimit(Integer orderId, Integer id){
		return dao.getSignDetailByOrderIdLimit(orderId,id);
	}



	@Transactional(readOnly=false)
	public void  signSuccess(SignDetail sign){

		dao.signSuccess(sign);
	}


	public AppOrder getCustomerInfoByOrderId(Integer orderId){

		return dao.getCustomerInfoByOrderId(orderId);
	}

	public  String getCheckTimeByOrderIdAndNode(Integer orderId,String nodeOrder){

		return dao.getCheckTimeByOrderIdAndNode(orderId, nodeOrder);
	}

	public BizPmAttendCnfg getCnfgByStoreIdAndEffectMonth(String effectMonth, String storeId){

		return dao.getCnfgByStoreIdAndEffectMonth(effectMonth, storeId);
	}

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
