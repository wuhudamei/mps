
package cn.damei.service.modules;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.dao.modules.BizAssessRewardPunishDao;
import cn.damei.entity.modules.BizAssessRewardPunish;
import cn.damei.dao.modules.ProComplaintForfeitDao;
import cn.damei.entity.modules.ProComplaintForfeit;
import cn.damei.common.utils.UserUtils;


@Service
@Transactional(readOnly = true)
public class ProComplaintForfeitService extends CrudService<ProComplaintForfeitDao, ProComplaintForfeit> {

	@Autowired
	private BizAssessRewardPunishDao bizAssessRewardPunishDao;

	public ProComplaintForfeit get(String id) {
		return super.get(id);
	}

	public List<ProComplaintForfeit> findList(ProComplaintForfeit proComplaintForfeit) {
		return super.findList(proComplaintForfeit);
	}

	public Page<ProComplaintForfeit> findPage(Page<ProComplaintForfeit> page, ProComplaintForfeit proComplaintForfeit) {
		proComplaintForfeit.setPage(page);

		List<ProComplaintForfeit> findAllList = dao.findList(proComplaintForfeit);
		if (null != findAllList) {
			for (int i = 1; i < findAllList.size(); i++) {
				findAllList.get(i).setSortId(i + "");
			}
		}

		return super.findPage(page, proComplaintForfeit);
	}

	@Transactional(readOnly = false)
	public void save(ProComplaintForfeit proComplaintForfeit) {
		super.save(proComplaintForfeit);
	}

	@Transactional(readOnly = false)
	public void delete(ProComplaintForfeit proComplaintForfeit) {
		super.delete(proComplaintForfeit);
	}


	@Transactional(readOnly = false)
	public void updateAgreeForfeit(ProComplaintForfeit proComplaintForfeit) {
		Date date = new Date();
		proComplaintForfeit.setStatus(ConstantUtils.COMPLAINT_FORFEIT_20);
		dao.updateAgreeForfeit(proComplaintForfeit);

		BizAssessRewardPunish bizAssessRewardPunish = new BizAssessRewardPunish();
		bizAssessRewardPunish.setRelatedBusinessType("1");
		bizAssessRewardPunish.setRewardPunishStatus("1");
		bizAssessRewardPunish.setRelatedBusinessIdInt(Integer.parseInt(proComplaintForfeit.getOrderId()));
		bizAssessRewardPunish.setAssessRuleTypeId(9);

		bizAssessRewardPunish.setAssessRuleId(82);

		bizAssessRewardPunish.setIsRewardOrPunish("2");
		bizAssessRewardPunish.setIsRewardOrPunish("2");
		bizAssessRewardPunish.setRewardPunishTargetType("10");

		bizAssessRewardPunish.setRewardPunishTargetEmployeeType("1");



		bizAssessRewardPunish.setRewardPunishTargetEmployeeId(Integer.parseInt(proComplaintForfeit.getItemManagerId()));
		bizAssessRewardPunish.setRewardPunishAmount(Double.parseDouble(proComplaintForfeit.getPunishMoney()));
		bizAssessRewardPunish.setRewardPunishScore(0.0);
		bizAssessRewardPunish.setRewardPunishRemarks("工程投诉罚款");
		bizAssessRewardPunish.setRewardPunishDatetime(date);
		bizAssessRewardPunish.setStatusOperator(Integer.parseInt(UserUtils.getUser().getId()));
		bizAssessRewardPunish.setStatusDescribe("未关联结算");
		bizAssessRewardPunish.setStatusDatetime(date);
		bizAssessRewardPunish.preInsert ();
		bizAssessRewardPunishDao.insert(bizAssessRewardPunish);

	}

	@Transactional(readOnly = false)
	public void updateRefuseForfeit(ProComplaintForfeit proComplaintForfeit) {
		proComplaintForfeit.setStatus(ConstantUtils.COMPLAINT_FORFEIT_30);
		dao.updateRefuseForfeit(proComplaintForfeit);

	}
}