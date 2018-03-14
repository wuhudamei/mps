package cn.damei.service.mobile.Manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.mobile.Manager.OrderDiscloseDao;
import cn.damei.entity.mobile.Manager.OrderDisclose;


@Service
@Transactional(readOnly = true)
public class OrderDiscloseService extends CrudService2<OrderDiscloseDao, OrderDisclose>{

	@Autowired
	private OrderDiscloseDao orderCadfileDao;


	@Transactional(readOnly = false)
	public int insertByDisclose(String orderID,String disCloseDate,Integer managerID, double measureSquare) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		OrderDisclose dis = new OrderDisclose();
		dis.setId(null);
		dis.setDiscloseDate(sf.parse(disCloseDate));
		dis.setOrderId(Integer.valueOf(orderID));
		dis.setDiscloseEmployeeId(managerID);
		dis.setRemarks(null);
		dis.setCreateByAuthor("1");
		dis.setCreateDate(DateUtils.addDays(new Date(), 0));
		dis.setUpdateByAuthor("1");
		dis.setUpdateDate(DateUtils.addDays(new Date(), 0));
		dis.setDelFlag("0");
		dis.setMeasureSquare(measureSquare);
		orderCadfileDao.insertByDisclose(dis);
		
		logger.info("返回的主键ID："+dis.getId());
		return dis.getId();
	}

	public Integer findByOrderId(Integer orderId) {
		
		return orderCadfileDao.findByOrderId(orderId);
	}
}
