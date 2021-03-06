package cn.damei.service.mobile.Manager;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.mobile.Manager.RecheckScaleBillDao;
import cn.damei.entity.mobile.Manager.RecheckScaleBill;


@Service
@Transactional(readOnly = true)
public class RecheckScaleBillService extends CrudService2<RecheckScaleBillDao,RecheckScaleBill>{

	@Autowired
	private RecheckScaleBillDao recheckScaleBillDao;

	@Transactional(readOnly = false)
	public int insert(Integer orderID, String planInstallDate, String number,String type, Integer managerID) throws ParseException {
		RecheckScaleBill scaleBill = new RecheckScaleBill();
		
		scaleBill.setId(null);
		scaleBill.setOrderId(orderID);
		scaleBill.setRecheckScaleBillCode(number);
		if(type.equals(ConstantUtils.COMPLEX_CONTENT_MAP_1)){
			scaleBill.setType(ConstantUtils.COMPLEX_CONTENT_MAP_1);
		}else if(type.equals(ConstantUtils.COMPLEX_CONTENT_MAP_2)){
			scaleBill.setType(ConstantUtils.COMPLEX_CONTENT_MAP_2);
		}else if(type.equals(ConstantUtils.COMPLEX_CONTENT_MAP_3)){
			scaleBill.setType(ConstantUtils.COMPLEX_CONTENT_MAP_3);
		}else if(type.equals(ConstantUtils.COMPLEX_CONTENT_MAP_4)){
			scaleBill.setType(ConstantUtils.COMPLEX_CONTENT_MAP_4);
		}else if(type.equals(ConstantUtils.COMPLEX_CONTENT_MAP_5)){
			scaleBill.setType(ConstantUtils.COMPLEX_CONTENT_MAP_5);
		}else if(type.equals(ConstantUtils.COMPLEX_CONTENT_MAP_6)){
			scaleBill.setType(ConstantUtils.COMPLEX_CONTENT_MAP_6);
		}
		scaleBill.setPlanInstallDate(DateUtils.parseDate(planInstallDate));
		scaleBill.setStatus("0");
		scaleBill.setRemarks("项目经理已申请");
		scaleBill.setCreateDate(DateUtils.addDate(new Date(), 0));
		scaleBill.setCreatePeo(managerID.toString());;
		scaleBill.setUpdateDate(DateUtils.addDate(new Date(), 0));
		scaleBill.setUpdatePeo(managerID.toString());
		scaleBill.setDelFlag("0");
		recheckScaleBillDao.insert(scaleBill);
		
		int idKey = scaleBill.getId();
		logger.info("返回主键的编号："+idKey);
		return idKey;
	}


	public List<RecheckScaleBill> queryListByOrderID(Integer orderID) {

		return recheckScaleBillDao.queryListByOrderID(orderID);
	}


	public RecheckScaleBill getByID(Integer recheckIDs) {

		return recheckScaleBillDao.getByID(recheckIDs);
	}
	

}
