package cn.damei.dao.mobile.Inspector;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.InspectSign;
import cn.damei.entity.mobile.Inspector.RecheckEntity;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;

/**
 * 
 * @author 梅浩
 * @2016年11月9日
 * @mdn大美装饰管理平台
 * @author_phone : 18610507472
 */
@MyBatisDao
public interface RecheckDao {

	/**
	 * 查询所有该质检员下的复检单
	 * @param entity
	 */
	
	public List<RecheckEntity>  findRecheckList(Integer   inspectId);
	
	
	
		// 订单经纬度
		public String getOrderLngLatByOrderId(Integer orderId);

		/**
		 * 质检员签到
		 * 
		 * @param detail
		 */
		public void inspectorSign(InspectSign inspectSign);

		/**
		 * 该质检单是否有签到记录
		 * 
		 * @param inspectId
		 */
		public Integer findInspectSignRecord(Integer inspectId);

		/**
		 * 更新质检员签到记录
		 * 
		 * @param detail
		 */
		public void updateInspectRecord(InspectSign InspectSign);
		
		
		
		/**
		 * 复检单的照片
		 * @param recheckId
		 * @return
		 */
		public List<RecheckEntity> findRecheckPic(Integer recheckId);
		/**
		 * 复检单的检查项
		 * @param recheckId
		 * @return
		 */
		public List<RecheckEntity> findRecheckCheckItem(Integer recheckId);
		
		
		
		/*
		 * 删除图片
		 */
		public void deletePic(Integer picId);

		
		
		
		/**
		 * 保存复检单检查时上传的图片
		 * @param p
		 */
		public void savePic(ReportCheckDetailsPic p);


		/**
		 * 更新复检单的检查项  是否合格, 更新时间
		 * @param entity
		 */
		public void updateRecheckCheckItem(RecheckEntity entity);


		/**
		 * 查询复检单
		 * @param recheckId
		 */
		public RecheckEntity findRecheckById(Integer recheckId);


		/**
		 * 更新复检单 状态, 复检次数
		 * @param recheckEntity
		 */
		public void updateRecheck(RecheckEntity recheckEntity);
}
