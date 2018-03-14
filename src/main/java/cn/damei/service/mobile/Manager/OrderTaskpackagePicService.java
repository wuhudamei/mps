package cn.damei.service.mobile.Manager;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.OrderTaskpackagePicDao;
import cn.damei.entity.mobile.Manager.OrderTaskpackagePic;

/**
 * @author 邱威威qww
 * @version 创建时间：2016年9月19日 下午5:00:04 类说明
 */
@Service
@Transactional(readOnly = true)
public class OrderTaskpackagePicService extends CrudService2<OrderTaskpackagePicDao, OrderTaskpackagePic> {
	
	/**
	 * 根据订单任务包id查询所有图片
	 * @param orderTaskpackageId
	 * @return
	 */
	public List<OrderTaskpackagePic> queryOrderTaskpackagePicByOrderTaskpackageId(Integer orderTaskpackageId){
		return dao.queryOrderTaskpackagePicByOrderTaskpackageId(orderTaskpackageId);
	}
}