
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailMerge;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailMergeTxtVo;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailMergeVo;


@MyBatisDao
public interface BizOrderTaskpackagePaymentDetailMergeDao extends CrudDao2<BizOrderTaskpackagePaymentDetailMerge> {
	

	public BizOrderTaskpackagePaymentDetailMerge queryPaymentDetailMergeBySummaryIdAndEmployeeId(Map<String, Object> map);


	public List<BizOrderTaskpackagePaymentDetailMergeVo> queryPaymentDetailMergeBySummaryId(Integer summaryId);
	

	public Integer queryPaymentDetailMergeCountByMap(Map<String, Object> map);


	public List<BizOrderTaskpackagePaymentDetailMerge> queryTaskpackagePaymentDetailMergeBySummaryId(Integer summaryId);


	public List<BizOrderTaskpackagePaymentDetailMergeTxtVo> queryPaymentDetailMergeForTxtAndExcel(Integer summaryId);


	public List<BizOrderTaskpackagePaymentDetailMergeVo> querySendMsgForEmployee(Integer summaryId);

	public List<BizOrderTaskpackagePaymentDetailMergeTxtVo> exportChinaCiticBank(Integer summaryId);
}