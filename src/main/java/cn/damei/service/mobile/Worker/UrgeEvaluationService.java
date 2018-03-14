package cn.damei.service.mobile.Worker;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessUrgeConstantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.dao.mobile.Worker.UrgeEvaluationDao;
import cn.damei.entity.mobile.Worker.UrgeEvaluation;
import cn.damei.dao.modules.BizBusinessUrgeDao;
import cn.damei.entity.modules.BizBusinessUrge;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;

@Service
@Transactional(readOnly=false)
public class UrgeEvaluationService {
	@Autowired
	private UrgeEvaluationDao urgeEvaluationDao;
	@Autowired
	private BizBusinessUrgeDao bizBusinessUrgeDao;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;

	public List<UrgeEvaluation> findEvaluationTaskpageByGroupId(UrgeEvaluation urgeEvaluation) {
		
		return urgeEvaluationDao.findEvaluationTaskpageByGroupId(urgeEvaluation);
	}

	public Integer findCount(UrgeEvaluation urgeEvaluation) {

		BizBusinessUrge bizBusinessUrge = new BizBusinessUrge();
		bizBusinessUrge.setBusinessOnlyMarkInt(urgeEvaluation.getId());
		bizBusinessUrge.setBusinesType(BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_3);
		bizBusinessUrge.setOperateType(1+"");
		bizBusinessUrge.setOperatorEmployeeId(Integer.parseInt(urgeEvaluation.getWorkerId()));
		bizBusinessUrge.setOperatorType("3");
		Integer findCount = bizBusinessUrgeDao.findCount(bizBusinessUrge);
		if(findCount == 0){

			bizBusinessUrge.preInsert();
			bizBusinessUrge.setOperateDatetime(new Date());
			bizBusinessUrgeDao.insert(bizBusinessUrge);

			String content = "您好，订单（"+urgeEvaluation.getCommunityName()+"-"+urgeEvaluation.getBuildNumber()+"-"+urgeEvaluation.getBuildUnit()+"-"+urgeEvaluation.getBuildRoom()+"-"+urgeEvaluation.getCustomerName()+"）家的任务包（"+urgeEvaluation.getPackageName()+"）已经完工了，请您在百忙之中抽空帮忙评价一下吧，非常感谢您对我工作的支持！";
			BizPhoneMsg phone = new BizPhoneMsg();
			phone.setReceiveEmployeeId(urgeEvaluation.getOrderInspectorId());
			phone.setReceivePhone(urgeEvaluation.getOrderInspectorPhone());
			phone.setMsgContent(content);
			phone.setMsgGenerateDatetime(new Date());
			phone.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			phone.setRelatedBusinessType(SendMsgBusinessType.URGE_EVALUATION_991000);
			phone.setRelatedBusinessIdInt(urgeEvaluation.getId());
			bizPhoneMsgService.insert(phone);
			
			
			return findCount;
		}else{
			return findCount;
		}
	}

}
