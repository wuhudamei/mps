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


@Service
@Transactional(readOnly = true)
public class EnginInstallNewService extends CrudService2<EnginInstallNewDao, EnginInstallNew> {

	public EnginInstallNew get(Integer id) {
		return super.get(id);
	}

	public List<EnginInstallNew> findList(EnginInstallNew enginInstallNew) {
		return super.findList(enginInstallNew);
	}


	public Page<EnginInstallNew> findPage(Page<EnginInstallNew> page, EnginInstallNew enginInstallNew) {
		return super.findPage(page, enginInstallNew);
	}


	public Integer findInstallCountUnderStatus(String installPlanStatus) {


		List<String> statusList = new ArrayList<String>();

		if (installPlanStatus.equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_2)) {

			statusList.add(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_2);

		} else if (installPlanStatus.equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_6)) {

			statusList.add(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_6);

		} else if (installPlanStatus.equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3)) {

			statusList.add(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3);
			statusList.add(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_310);
			statusList.add(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_320);
			statusList.add(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_330);

		} else {

			statusList.add(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_5);
		}

		EnginInstallNew enginInstallNew = new EnginInstallNew();
		enginInstallNew.setInstallStatusList(statusList);

		return dao.findInstallCountUnderStatus(enginInstallNew);
	}


	public Page<EnginInstallNew> findDealWithPage(Page<EnginInstallNew> page, EnginInstallNew enginInstallNew) {
		enginInstallNew.setPage(page);
		page.setList(dao.findDealWithList(enginInstallNew));
		return page;
	}


	public Page<EnginInstallNew> findRejectedPage(Page<EnginInstallNew> page, EnginInstallNew enginInstallNew) {
		enginInstallNew.setPage(page);
		page.setList(dao.findRejectedList(enginInstallNew));
		return page;
	}


	public Page<EnginInstallNew> findSpecialDealWithPage(Page<EnginInstallNew> page, EnginInstallNew enginInstallNew) {
		enginInstallNew.setPage(page);
		page.setList(dao.findSpecialDealWithList(enginInstallNew));
		return page;
	}


	public List<BizBusinessStatusLog> findInstallStatusLog(Integer installId, String businessType) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		bizBusinessStatusLog.setBusinessType(businessType);
		bizBusinessStatusLog.setBusinessOnlyMarkInt(installId);

		return dao.findInstallStatusLog(bizBusinessStatusLog);
	}


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


	public List<ReportCheckDetailsPic> findPic(Integer id, String type) {
		ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
		reportCheckDetailsPic.setBusinessIdInt(id);
		reportCheckDetailsPic.setBusinessType(type);

		return dao.findPic(reportCheckDetailsPic);
	}


	public List<ReportCheckDetailsPic> findAcceptPic(Integer id) {
		return dao.findAcceptPic(id);
	}

    public List<BizBusinessStatusLog> getAcceptLog(String installId) {
        return dao.getAcceptLog(installId);
    }
}