package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.Checksize;
import cn.damei.entity.mobile.Manager.ChecksizeOrder;
import cn.damei.entity.mobile.Manager.ChecksizePic;
import cn.damei.entity.mobile.Manager.ChecksizeType;
import cn.damei.entity.mobile.Manager.OrderInstallPlan;

/**
 * 上报厂家复尺
 * @author wyb
 *
 */
@MyBatisDao
public interface ChecksizeNewDao extends CrudDao2<Checksize>{

	/**
	 * 根据项目经理id查询项目经理下的状态小于300的所有订单(加搜索条件)
	 * @param checksizeOrder
	 * @return
	 */
	List<ChecksizeOrder> findOrderByManagerId(ChecksizeOrder checksizeOrder);

	
	/**
	 * 查询订单可复尺的安装项数量
	 * @param orderId
	 * @return
	 */
	Integer findCanApplyChecksizeCount(Integer orderId);


	/**
	 * 同一个订单两次厂家复尺申请操作时间必须间隔5分钟，请过5分钟后再申请
	 * @param orderId
	 * @return
	 */
	Integer findFiveTimeChecksizeCount(Integer orderId);
	
	
	/**
	 * 查询订单
	 * @param orderId
	 * @return
	 */
	ChecksizeOrder findOrder(Integer orderId);
	
	
	/**
	 * 查询订单可复尺的安装项列表
	 * @param orderId
	 * @return
	 */
	List<ChecksizeType> findChecksizeTypeList(Integer orderId);
	
	
	/**
	 * 校验该安装项是否已经申请
	 * @param checksize
	 * @return
	 */
	Integer findOrderInstallItemChecksizeCount(Checksize checksize);
	
	
	/**
	 * 校验：该工地2017-7-1开工，按照工程部规定主材（橱柜、台面）开工10天后（2017-7-21）才可以申请橱柜复尺，如有提前完工或疑问请联系大区经理。
	 * @param orderInstallItemId
	 * @return
	 */
	Checksize findChecksizeCanApplyDate(String orderInstallItemId);
	
	/**
	 * 查询该主材复尺项对应的复尺类型
	 * @param str
	 * @return
	 */
	String findChecksizeTypeDictValue(String str);
	
	/**
	 * 保存上报复尺信息
	 * @param checksize
	 * @return
	 */
	Integer saveChecksize(Checksize checksize);
	
	
	/**
	 * 批量插入上报厂家复尺图片
	 * @param list
	 */
	void saveChecksizePicAll(List<ChecksizePic> list);
	
	
	/**
	 * 查询安装项的名字
	 * @param checksizeType
	 * @return
	 */
	String findInstallItemName(String orderInstallItemId);
	
	/**
	 * 根据订单ID查询所有的厂家复尺
	 * @param checksize
	 * @return
	 */
	List<Checksize> findCheckSizeList(Checksize checksize);

	/**
	 * 主材安装项复尺内容及时间
	 * @param orderInstallItemId
	 * @return
	 */
	OrderInstallPlan queryInstallItemDetail(Integer orderInstallItemId);
	
	
}
