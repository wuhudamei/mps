/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 客诉罚款Service
 * 
 * @author ZTW
 * @version 2017-10-27
 */
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

	/**
	 * 这里面有个大坑很大很大的坑作为写"坑"的人 友情提示你一定要把备注看清楚
	 * 
	 * @Title: updateAgreeForfeit
	 * @Description: TODO
	 * @param @param proComplaintForfeit
	 * @return void
	 * @author ZhangTongWei
	 * @throws
	 */
	@Transactional(readOnly = false)
	public void updateAgreeForfeit(ProComplaintForfeit proComplaintForfeit) {
		Date date = new Date();
		proComplaintForfeit.setStatus(ConstantUtils.COMPLAINT_FORFEIT_20);
		dao.updateAgreeForfeit(proComplaintForfeit);
		// 添加项目经理扣款
		BizAssessRewardPunish bizAssessRewardPunish = new BizAssessRewardPunish();
		bizAssessRewardPunish.setRelatedBusinessType("1");// 产业项目经理
		bizAssessRewardPunish.setRewardPunishStatus("1"); // 奖惩状态
		bizAssessRewardPunish.setRelatedBusinessIdInt(Integer.parseInt(proComplaintForfeit.getOrderId()));// 订单ID
		bizAssessRewardPunish.setAssessRuleTypeId(9);// 考核条例分类ID
														// 考核条例分类的ID是查数据条例分类名字和工程投诉有关的ID
		bizAssessRewardPunish.setAssessRuleId(82);// 考核条例ID
													// 考核条例明细的ID也是查数据条例分类名字和工程投诉有关的ID
		bizAssessRewardPunish.setIsRewardOrPunish("2");// // 奖励或惩罚 //1:奖励 2：惩罚
		bizAssessRewardPunish.setIsRewardOrPunish("2");// // 奖励或惩罚 //1:奖励 2：惩罚
		bizAssessRewardPunish.setRewardPunishTargetType("10");// 奖惩对象 10：订单
																// 20：员工
		bizAssessRewardPunish.setRewardPunishTargetEmployeeType("1");// 奖惩对象员工类型
																		// 1：项目经理
																		// 2：工人
																		// 3：质检员
		bizAssessRewardPunish.setRewardPunishTargetEmployeeId(Integer.parseInt(proComplaintForfeit.getItemManagerId()));// 奖惩对象员工Id
		bizAssessRewardPunish.setRewardPunishAmount(Double.parseDouble(proComplaintForfeit.getPunishMoney()));// 奖惩金额
		bizAssessRewardPunish.setRewardPunishScore(0.0);// 奖惩分数
		bizAssessRewardPunish.setRewardPunishRemarks("工程投诉罚款");// 奖惩说明
		bizAssessRewardPunish.setRewardPunishDatetime(date);// 奖惩日期时间
		bizAssessRewardPunish.setStatusOperator(Integer.parseInt(UserUtils.getUser().getId()));// 状态操作人
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