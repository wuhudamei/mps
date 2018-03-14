/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailMerge;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailMergeTxtVo;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailMergeVo;

/**
 * 付款单明细合并DAO接口
 * @author qww
 * @version 2016-10-27
 */
@MyBatisDao
public interface BizOrderTaskpackagePaymentDetailMergeDao extends CrudDao2<BizOrderTaskpackagePaymentDetailMerge> {
	
	/**
	 * 根据批次id和员工id查询
	 * @param map
	 * @return
	 */
	public BizOrderTaskpackagePaymentDetailMerge queryPaymentDetailMergeBySummaryIdAndEmployeeId(Map<String, Object> map);

	/**
	 * 财务人员批次列表
	 * @param summaryId
	 * @return
	 */
	public List<BizOrderTaskpackagePaymentDetailMergeVo> queryPaymentDetailMergeBySummaryId(Integer summaryId);
	
	/**
	 * 根据批次id或状态查询
	 * @param map
	 * @return
	 */
	public Integer queryPaymentDetailMergeCountByMap(Map<String, Object> map);


	public List<BizOrderTaskpackagePaymentDetailMerge> queryTaskpackagePaymentDetailMergeBySummaryId(Integer summaryId);

	/**
	 * 徽商银行导出数据
	 * @param summaryId
	 * @return
	 */
	public List<BizOrderTaskpackagePaymentDetailMergeTxtVo> queryPaymentDetailMergeForTxtAndExcel(Integer summaryId);

	/**
	 * 通知工人-已付款成功
	 * @param summaryId
	 * @return
	 */
	public List<BizOrderTaskpackagePaymentDetailMergeVo> querySendMsgForEmployee(Integer summaryId);
	/**
	 * 导出中信Excel
	 * @param summaryId
	 * @return
	 */
	public List<BizOrderTaskpackagePaymentDetailMergeTxtVo> exportChinaCiticBank(Integer summaryId);
}