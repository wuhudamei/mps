package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.Checksize;
import cn.damei.entity.mobile.Manager.ChecksizeOrder;
import cn.damei.entity.mobile.Manager.ChecksizePic;
import cn.damei.entity.mobile.Manager.ChecksizeType;
import cn.damei.entity.mobile.Manager.OrderInstallPlan;


@MyBatisDao
public interface ChecksizeNewDao extends CrudDao2<Checksize>{


	List<ChecksizeOrder> findOrderByManagerId(ChecksizeOrder checksizeOrder);

	

	Integer findCanApplyChecksizeCount(Integer orderId);



	Integer findFiveTimeChecksizeCount(Integer orderId);
	
	

	ChecksizeOrder findOrder(Integer orderId);
	
	

	List<ChecksizeType> findChecksizeTypeList(Integer orderId);
	
	

	Integer findOrderInstallItemChecksizeCount(Checksize checksize);
	
	

	Checksize findChecksizeCanApplyDate(String orderInstallItemId);
	

	String findChecksizeTypeDictValue(String str);
	

	Integer saveChecksize(Checksize checksize);
	
	

	void saveChecksizePicAll(List<ChecksizePic> list);
	
	

	String findInstallItemName(String orderInstallItemId);
	

	List<Checksize> findCheckSizeList(Checksize checksize);


	OrderInstallPlan queryInstallItemDetail(Integer orderInstallItemId);
	
	
}
