package cn.damei.service.modules;

import cn.damei.common.constantUtils.InstallPlanConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.ReportCheckDetailsPic;
import cn.damei.dao.modules.EnginInstallNewDao;
import cn.damei.entity.modules.EnginInstallNew;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 主材安装Service
 */
@Service
@Transactional(readOnly = true)
public class EnginInstallNewService extends CrudService2<EnginInstallNewDao, EnginInstallNew> {

	public EnginInstallNew get(Integer id) {
		return super.get(id);
	}

	public List<EnginInstallNew> findList(EnginInstallNew enginInstallNew) {
		return super.findList(enginInstallNew);
	}

	/**
	 * 主材安装申请 待办
	 */
	public Page<EnginInstallNew> findPage(Page<EnginInstallNew> page, EnginInstallNew enginInstallNew) {
		return super.findPage(page, enginInstallNew);
	}

	/**
	 * 统计每个状态下的安装项数量
	 * 
	 * @param installPlanStatus
	 * @return
	 */
	public Integer findInstallCountUnderStatus(String installPlanStatus) {

		// 状态
		List<String> statusList = new ArrayList<String>();

		if (installPlanStatus.equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_2)) {
			// 项目经理已申请的数量
			statusList.add(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_2);

		} else if (installPlanStatus.equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_6)) {
			// 项目经理驳回后申请的数量
			statusList.add(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_6);

		} else if (installPlanStatus.equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3)) {
			// 项目经理已转供应商的数量
			statusList.add(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3);
			statusList.add(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_310);
			statusList.add(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_320);
			statusList.add(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_330);

		} else {
			// 项目经理已驳回的数量
			statusList.add(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_5);
		}

		EnginInstallNew enginInstallNew = new EnginInstallNew();
		enginInstallNew.setInstallStatusList(statusList);

		return dao.findInstallCountUnderStatus(enginInstallNew);
	}

	/**
	 * 主材安装申请 已处理
	 * 
	 * @param page
	 * @param enginInstallNew
	 * @return
	 */
	public Page<EnginInstallNew> findDealWithPage(Page<EnginInstallNew> page, EnginInstallNew enginInstallNew) {
		enginInstallNew.setPage(page);
		page.setList(dao.findDealWithList(enginInstallNew));
		return page;
	}

	/**
	 * 主材安装申请 已驳回
	 * 
	 * @param page
	 * @param enginInstallNew
	 * @return
	 */
	public Page<EnginInstallNew> findRejectedPage(Page<EnginInstallNew> page, EnginInstallNew enginInstallNew) {
		enginInstallNew.setPage(page);
		page.setList(dao.findRejectedList(enginInstallNew));
		return page;
	}

	/**
	 * 主材安装申请 特殊处理
	 * 
	 * @param page
	 * @param enginInstallNew
	 * @return
	 */
	public Page<EnginInstallNew> findSpecialDealWithPage(Page<EnginInstallNew> page, EnginInstallNew enginInstallNew) {
		enginInstallNew.setPage(page);
		page.setList(dao.findSpecialDealWithList(enginInstallNew));
		return page;
	}

	/**
	 * 主材安装操作日志(申请、下达供应商、验收)
	 * 
	 * @param installId
	 * @param businessType
	 * @return
	 */
	public List<BizBusinessStatusLog> findInstallStatusLog(Integer installId, String businessType) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		bizBusinessStatusLog.setBusinessType(businessType);
		bizBusinessStatusLog.setBusinessOnlyMarkInt(installId);

		return dao.findInstallStatusLog(bizBusinessStatusLog);
	}

	/**
	 * 安装项操作日志(重新申请、驳回)
	 * 
	 * @param installId
	 * @param businessType
	 * @return
	 */
	public List<BizBusinessStatusLog> findInstallStatusLogTwo(Integer installId, String businessType) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		bizBusinessStatusLog.setBusinessType(businessType);
		bizBusinessStatusLog.setBusinessOnlyMarkInt(installId);

		return dao.findInstallStatusLogTwo(bizBusinessStatusLog);
	}

	public BizBusinessStatusLog getcailiaozhuang(Integer valueOf, String businessType) {
		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		bizBusinessStatusLog.setBusinessType(businessType);
		bizBusinessStatusLog.setBusinessOnlyMarkInt(valueOf);

		return dao.getcailiaozhuang(bizBusinessStatusLog);
	}

	public BizBusinessStatusLog getyanshou(Integer valueOf, String businessType) {
		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		bizBusinessStatusLog.setBusinessType(businessType);
		bizBusinessStatusLog.setBusinessOnlyMarkInt(valueOf);

		return dao.getyanshou(bizBusinessStatusLog);
	}

	public BizBusinessStatusLog getfenpeigonrenzu(Integer valueOf, String businessType901) {
		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		bizBusinessStatusLog.setBusinessType(businessType901);
		bizBusinessStatusLog.setBusinessOnlyMarkInt(valueOf);

		return dao.BizBusinessStatusLog(bizBusinessStatusLog);
	}

	public BizBusinessStatusLog getgonrenrenqiandao(Integer valueOf, String businessType901) {
		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		bizBusinessStatusLog.setBusinessType(businessType901);
		bizBusinessStatusLog.setBusinessOnlyMarkInt(valueOf);

		return dao.getgonrenrenqiandao(bizBusinessStatusLog);
	}

	public BizBusinessStatusLog bizBusinessscuesss(Integer valueOf, String businessType901) {
		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		bizBusinessStatusLog.setBusinessType(businessType901);
		bizBusinessStatusLog.setBusinessOnlyMarkInt(valueOf);

		return dao.BizBusinessscuesss(bizBusinessStatusLog);
	}

	public List<BizBusinessStatusLog> findInstallStatusLogC(Integer valueOf, String businessType901) {
		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		bizBusinessStatusLog.setBusinessType(businessType901);
		bizBusinessStatusLog.setBusinessOnlyMarkInt(valueOf);

		return dao.findInstallStatusLogC(bizBusinessStatusLog);
	}

	/**
	 * 查询图片公共方法
	 * 
	 * @Title: findPic
	 * @Description: TODO
	 * @param @param valueOf
	 * @param @param type
	 * @param @return
	 * @return List<ReportCheckDetailsPic>
	 * @author ZhangTongWei
	 * @throws
	 */
	public List<ReportCheckDetailsPic> findPic(Integer id, String type) {
		ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
		reportCheckDetailsPic.setBusinessIdInt(id);
		reportCheckDetailsPic.setBusinessType(type);

		return dao.findPic(reportCheckDetailsPic);
	}

	/**
	 * 查询验收图片
	 * 
	 * @Title: findAcceptPic
	 * @Description: TODO
	 * @param @param valueOf
	 * @param @return
	 * @return List<ReportCheckDetailsPic>
	 * @author ZhangTongWei
	 * @throws
	 */
	public List<ReportCheckDetailsPic> findAcceptPic(Integer id) {
		return dao.findAcceptPic(id);
	}

    public List<BizBusinessStatusLog> getAcceptLog(String installId) {
        return dao.getAcceptLog(installId);
    }
}