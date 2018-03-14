package cn.damei.dao.mobile.Manager;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.Checksize;
import cn.damei.entity.mobile.Manager.ChecksizeOrder;
import cn.damei.entity.mobile.Manager.ChecksizePic;
import cn.damei.entity.mobile.Manager.ChecksizeType;

/**
 * 上报复尺
 * @author Administrator
 *
 */
@MyBatisDao
public interface ChecksizeDao extends CrudDao2<Checksize>{

	//根据项目经理id查询项目经理下的状态小于300的所有订单
	List<ChecksizeOrder> findOrderByManagerId(Integer id);

	//保存上报复尺信息
	Integer saveChecksize(Checksize checksize);

	//保存复尺图片
	void saveChecksizePic(ChecksizePic checksizePic);

	//查询复尺类型
	List<ChecksizeType> findType(Integer id);

	ChecksizeOrder findOrder(Integer orderId);

	String findcheck(String checksizeType);

	//批量插入上报厂家复尺图片
	void saveChecksizePicAll(List<ChecksizePic> pList);

	/**
	 * 查询该订单最新一次申请厂家复尺的时间是否间隔有5分钟
	 * @param id
	 * @return
	 */
	Checksize findTimeSpan(int id);

	Date findByOrderIdAndType(Integer orderId, String type);

	/**
	 * 根据订单ID查询所有的厂家复尺
	 * @param id
	 * @return
	 */
	List<Checksize> findCheckSizeList(Integer id);

	String findDaysPlanChecksize(String checksizeType);
	/**
	 * 查询安装项的名字
	 * @param checksizeType
	 * @return
	 */
	String findInstallItemName(String checksizeType);
	/**
	 * 查询字典表里的值
	 * @param str
	 * @return
	 */
	String findDictValue(String str);

	/**
	 * 校验：该工地2017-7-1开工，按照工程部规定主材（橱柜、台面）开工10天后（2017-7-21）才可以申请橱柜复尺，如有提前完工或疑问请联系大区经理。
	 * @param checksizeType
	 * @return
	 */
	Checksize findChecksizeCanApplyDate(String checksizeType);
	
	
}
