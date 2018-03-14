package cn.damei.dao.mobile.Manager;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.Checksize;
import cn.damei.entity.mobile.Manager.ChecksizeOrder;
import cn.damei.entity.mobile.Manager.ChecksizePic;
import cn.damei.entity.mobile.Manager.ChecksizeType;


@MyBatisDao
public interface ChecksizeDao extends CrudDao2<Checksize>{


	List<ChecksizeOrder> findOrderByManagerId(Integer id);


	Integer saveChecksize(Checksize checksize);


	void saveChecksizePic(ChecksizePic checksizePic);


	List<ChecksizeType> findType(Integer id);

	ChecksizeOrder findOrder(Integer orderId);

	String findcheck(String checksizeType);


	void saveChecksizePicAll(List<ChecksizePic> pList);


	Checksize findTimeSpan(int id);

	Date findByOrderIdAndType(Integer orderId, String type);


	List<Checksize> findCheckSizeList(Integer id);

	String findDaysPlanChecksize(String checksizeType);

	String findInstallItemName(String checksizeType);

	String findDictValue(String str);


	Checksize findChecksizeCanApplyDate(String checksizeType);
	
	
}
