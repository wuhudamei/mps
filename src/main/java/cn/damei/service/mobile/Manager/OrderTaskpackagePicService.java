package cn.damei.service.mobile.Manager;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.OrderTaskpackagePicDao;
import cn.damei.entity.mobile.Manager.OrderTaskpackagePic;


@Service
@Transactional(readOnly = true)
public class OrderTaskpackagePicService extends CrudService2<OrderTaskpackagePicDao, OrderTaskpackagePic> {
	

	public List<OrderTaskpackagePic> queryOrderTaskpackagePicByOrderTaskpackageId(Integer orderTaskpackageId){
		return dao.queryOrderTaskpackagePicByOrderTaskpackageId(orderTaskpackageId);
	}
}